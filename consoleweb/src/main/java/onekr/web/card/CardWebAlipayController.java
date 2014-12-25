package onekr.web.card;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import onekr.cardservice.card.intf.CardMakeCodeBiz;
import onekr.commonservice.model.Order;
import onekr.commonservice.order.intf.OrderBiz;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.identityservice.model.User;
import onekr.identityservice.user.intf.UserBiz;
import onekr.web.base.ConsoleBaseController;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.util.AlipayNotify;


@Controller
@RequestMapping(value = "/card_alipay")
public class CardWebAlipayController extends ConsoleBaseController {
	
	@Autowired
	private CardMakeCodeBiz cardMakeCodeBiz;
	
	@Autowired
	private OrderBiz orderBiz;
	
	@Autowired
	private UserBiz userBiz;
	
	@RequestMapping(value = "/notify_url", method = RequestMethod.GET)
	@ResponseBody
	public String notifyUrl(HttpServletRequest request) throws Exception {
		System.out.println("alipay..........start.........................");
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		if(AlipayNotify.verify(params)){//验证成功
			
			Order order = orderBiz.findById(new Long(out_trade_no));
			if (order == null)
				throw new AppException(ErrorCode.ENTITY_NOT_FOUND, "订单order"+out_trade_no+"没找到");
			
			User user = userBiz.findById(order.getCreateBy());
			
			Date now = new Date();
			order.setAlipayNoticeAt(now);
			order.setAlipayTradeNo(trade_no);
			order.setAlipayTradeStatus(trade_status);
			
			order.setUpdateAt(now);
			order.setUpdateBy(user.getId());
			
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//该种交易状态只在两种情况下出现
				//1、开通了普通即时到账，买家付款成功后。
				//2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
				
				if (StringUtils.isEmpty(order.getRemark())) {
					String code = cardMakeCodeBiz.generateNewCode(user.getName(), user.getId());
					order.setRemark(code);
				}
			} else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
				
				if (StringUtils.isEmpty(order.getRemark())) {
					String code = cardMakeCodeBiz.generateNewCode(user.getName(), user.getId());
					order.setRemark(code);
				}
			}
			
			orderBiz.saveOrder(order);
			System.out.println("alipay..........success.........................");
			return "success";
		}else{//验证失败
			System.out.println("alipay..........fail.........................");
			return "fail";
		}
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("portalweb:order-pay-success");
		Order order = new Order();
		order.setId(323L);
		order.setRemark("ufd9sa890fd7s90afdsa89fd8sa");
		mav.addObject("order", order);
		return mav;
	}
	
	@RequestMapping(value = "/return_url", method = RequestMethod.GET)
	public ModelAndView returnUrl(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("portalweb:order-pay-success");
		
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);
		
		if(verify_result){//验证成功
			User user = getCurrentUser();
			if (user == null)
				throw new AppException(ErrorCode.NO_PERMISSON, "请先登录");
			
			Order order = orderBiz.findById(new Long(out_trade_no));
			if (order == null)
				throw new AppException(ErrorCode.ENTITY_NOT_FOUND, "订单order"+out_trade_no+"没找到");
			
			Date now = new Date();
			order.setAlipayNoticeAt(now);
			order.setAlipayTradeNo(trade_no);
			order.setAlipayTradeStatus(trade_status);
			
			order.setUpdateAt(now);
			order.setUpdateBy(user.getId());

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				
				if (StringUtils.isEmpty(order.getRemark())) {
					String code = cardMakeCodeBiz.generateNewCode(user.getName(), user.getId());
					order.setRemark(code);
				}
			}
			
			orderBiz.saveOrder(order);
			
			mav.addObject("order", order);
			
		}else{
			throw new AppException(ErrorCode.SERVER_ERROR, "支付宝支付结果回调页面参数验证失败");
		}
		
		return mav;
	}
	
}
