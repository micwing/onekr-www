package onekr.web.console.order;

import javax.servlet.http.HttpSession;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.biz.ConfigOwner;
import onekr.commonservice.common.intf.ConfigBiz;
import onekr.commonservice.model.Config;
import onekr.commonservice.model.Order;
import onekr.commonservice.order.intf.OrderBiz;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.framework.result.Result;
import onekr.identityservice.model.User;
import onekr.web.base.ConsoleBaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/console/order")
public class CardOrderController extends ConsoleBaseController {
	
	@Value("#{systemConfig['site.root.url']}")
	private String siteRootUrl;
	
	@Autowired
	private OrderBiz orderBiz;
	
	@Autowired
	private ConfigBiz configBiz;
	
//	/**
//	 * 查看购物车
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/shoppingcart", method = RequestMethod.GET)
//	public ModelAndView shoppingcart(HttpSession session) {
//		ModelAndView mav = new ModelAndView("portalweb:order-shoppingcart");
//		//TODO
//		return mav;
//	}
//	
//	/**
//	 * 添加商品到购物车
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "/doAddShoppingcart", method = RequestMethod.POST)
//	@ResponseBody
//	public Result doAddShoppingcart(HttpSession session) {
//		//TODO
//		return new Result();
//	}
//	
	/**
	 * 立刻购买
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/payAtOnce", method = RequestMethod.GET)
	public ModelAndView payAtOnce(HttpSession session) {
		ModelAndView mav = new ModelAndView("portalweb:order-shoppingcart");
		return mav;
	}
	
	/**
	 * 提交支付宝
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doSubmitAlipay", method = RequestMethod.POST)
	public ModelAndView doSubmitAlipay(HttpSession session) {
		ModelAndView mav = new ModelAndView("portalweb:order-submit-alipay");
		
		User user = getCurrentUser();
		Order order = new Order();
		order.setBiz(Biz.WEDING_MAKECODE_ORDER.name());
		order.setOwner(user.getId()+"");
		order.setCreateBy(user.getId());
		order.setUpdateBy(user.getId());
		orderBiz.saveOrder(order);
		
		Config alipayEmailConfig = configBiz.findConfig(Biz.SYSTEM, ConfigOwner.SYSTEM_ORDER_ALIPAY_SELLER_EMAIL.name());
		if (alipayEmailConfig == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND, "未配置卖家支付宝账号");
		
		//服务器异步通知页面路径
		String notify_url = siteRootUrl+"/card_alipay/notify_url";
		//需http://格式的完整路径，不能加?id=123这类自定义参数		//页面跳转同步通知页面路径
		String return_url = siteRootUrl+"/card_alipay/return_url";
		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/		//卖家支付宝帐户
		String seller_email = alipayEmailConfig.getValue();
		//必填		//商户订单号
		String out_trade_no = order.getId()+"";
		//商户网站订单系统中唯一订单号，必填		//订单名称
		String subject = "婚礼请柬制作码";
		//必填		//付款金额
		String total_fee = "10";
		//必填		//订单描述		
		String body = "购买用来在www.onekr.com创建婚礼电子请的制作码";
		//商品展示地址
		String show_url = siteRootUrl+"/portal/buymakecode";
		//需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html		//防钓鱼时间戳
		String anti_phishing_key = System.currentTimeMillis()+"";
		//若要使用请调用类文件submit中的query_timestamp函数		//客户端的IP地址
		String exter_invoke_ip = "";
		//非局域网的外网IP地址，如：221.0.0.1
				
		mav.addObject("notify_url", notify_url);
		mav.addObject("return_url", return_url);
		mav.addObject("WIDseller_email", seller_email);
		mav.addObject("WIDout_trade_no", out_trade_no);
		mav.addObject("WIDsubject", subject);
		mav.addObject("WIDtotal_fee", total_fee);
		mav.addObject("WIDbody", body);
		mav.addObject("WIDshow_url", show_url);
		mav.addObject("anti_phishing_key", anti_phishing_key);
		mav.addObject("exter_invoke_ip", exter_invoke_ip);
		
		return mav;
	}
}
