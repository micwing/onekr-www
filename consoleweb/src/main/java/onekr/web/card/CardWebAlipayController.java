package onekr.web.card;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	/**
	 * 支付宝异步通知URL
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/notify_url", method = RequestMethod.POST)
	public void notifyUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		String out_trade_no = params.get("out_trade_no");

		//支付宝交易号
		String trade_no = params.get("trade_no");

		//交易状态
		String trade_status = params.get("trade_status");

		
		if(AlipayNotify.verify(params)){//验证成功
			Order order = orderBiz.findById(new Long(out_trade_no));
			if (order == null)
				throw new AppException(ErrorCode.ENTITY_NOT_FOUND, "订单order"+out_trade_no+"没找到");
			
			User user = userBiz.findById(order.getCreateBy());
			
			Date now = new Date();
			order.setNoticeAt(now);
			order.setTradeNo(trade_no);
			order.setTradeStatus(trade_status);
			
			order.setUpdateAt(now);
			order.setUpdateBy(user.getId());
			
			if(trade_status.equals("WAIT_BUYER_PAY")){
				//该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
				
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				
				//out.println("success");	//请不要修改或删除
			} else if(trade_status.equals("WAIT_SELLER_SEND_GOODS")//该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
					|| trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")//该判断表示卖家已经发了货，但买家还没有做确认收货的操作
					|| trade_status.equals("TRADE_FINISHED")){//该判断表示买家已经确认收货，这笔交易完成
			
				if (StringUtils.isEmpty(order.getRemark())) {
					String code = cardMakeCodeBiz.generateNewCode(user.getName(), user.getId());
					order.setRemark(code);
				}
			}

			orderBiz.saveOrder(order);
			response.getOutputStream().write("success".getBytes());
		}else{//验证失败
			response.getOutputStream().write("fail".getBytes());
		}
	}
	
	/**
	 * 支付宝同步通知URL
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/return_url", method = RequestMethod.GET)
	public ModelAndView returnUrl(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("portalweb:order-pay-success");
		
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
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		//商户订单号
		String out_trade_no = params.get("out_trade_no");

		//支付宝交易号
		String trade_no = params.get("trade_no");

		//交易状态
		String trade_status = params.get("trade_status");

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
			order.setNoticeAt(now);
			order.setTradeNo(trade_no);
			order.setTradeStatus(trade_status);
			
			order.setUpdateAt(now);
			order.setUpdateBy(user.getId());

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if(trade_status.equals("WAIT_SELLER_SEND_GOODS")){
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
			return mav;
		}else{
			throw new AppException(ErrorCode.SERVER_ERROR, "支付宝支付结果回调页面参数验证失败");
		}
		
	}
	
}
