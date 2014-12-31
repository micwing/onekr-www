package onekr.web.console.order;

import javax.servlet.http.HttpSession;

import onekr.cardservice.utils.CardConstants;
import onekr.commonservice.biz.Biz;
import onekr.commonservice.biz.ConfigOwner;
import onekr.commonservice.common.intf.ConfigBiz;
import onekr.commonservice.model.Config;
import onekr.commonservice.model.Order;
import onekr.commonservice.order.intf.OrderBiz;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.identityservice.model.User;
import onekr.web.base.ConsoleBaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.config.AlipayConfig;


@Controller
@RequestMapping(value = "/console/order")
public class CardOrderController extends ConsoleBaseController {
	
	@Value("#{systemConfig['site.root.url']}")
	private String siteRootUrl;
	
	@Autowired
	private OrderBiz orderBiz;
	
	@Autowired
	private ConfigBiz configBiz;
	
	/**
	 * 提交支付宝
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doSubmitAlipay", method = RequestMethod.POST)
	public ModelAndView doSubmitAlipay(HttpSession session) {
		ModelAndView mav = new ModelAndView("portalweb:order-submit-alipay");
		
		Config alipayEmailConfig = configBiz.findConfig(Biz.SYSTEM, ConfigOwner.SYSTEM_ORDER_ALIPAY_SELLER_EMAIL.name());
		if (alipayEmailConfig == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND, "未配置卖家支付宝账号");
		
		String service = "create_direct_pay_by_user";
		String partner = AlipayConfig.partner;
		String _input_charset = AlipayConfig.input_charset;
		String payment_type = "1";
		//服务器异步通知页面路径
		String notify_url = siteRootUrl+"/card_alipay/notify_url";
		//需http://格式的完整路径，不能加?id=123这类自定义参数		//页面跳转同步通知页面路径
		String return_url = siteRootUrl+"/card_alipay/return_url";
		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/		//卖家支付宝帐户
		String seller_email = alipayEmailConfig.getValue();
		
		//商户网站订单系统中唯一订单号，必填		//订单名称
		String subject = "婚礼请柬制作码";
		//必填		//付款金额
		String total_fee = CardConstants.CARD_PRICE+"";
		//必填		//订单描述		
		String body = "购买用来在www.onekr.com创建婚礼电子请的制作码";
		//商品展示地址
		String show_url = siteRootUrl+"/portal/buymakecode";
		//需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html		//防钓鱼时间戳
		String anti_phishing_key = "";
		//若要使用请调用类文件submit中的query_timestamp函数		//客户端的IP地址
		String exter_invoke_ip = "";
		//非局域网的外网IP地址，如：221.0.0.1
		
		User user = getCurrentUser();
		Order order = new Order();
		order.setBiz(Biz.WEDING_MAKECODE_ORDER.name());
		order.setOwner(user.getId()+"");
		order.setCreateBy(user.getId());
		order.setUpdateBy(user.getId());
		
		order.setService(service);
		order.setPartner(partner);
		order.setInputCharset(_input_charset);
		order.setPaymentType(payment_type);
		order.setAntiPhishingKey(anti_phishing_key);
		order.setBody(body);
		order.setExterInvokeIp(exter_invoke_ip);
		order.setNotifyUrl(notify_url);
		order.setReturnUrl(return_url);
		order.setSellerEmail(seller_email);
		order.setShowUrl(show_url);
		order.setSubject(subject);
		order.setTotalFee(total_fee);
		
		order = orderBiz.saveOrder(order);
		
		mav.addObject("service", service);
		mav.addObject("partner", partner);
		mav.addObject("_input_charset", _input_charset);
		mav.addObject("payment_type", payment_type);
		
		mav.addObject("notify_url", notify_url);
		mav.addObject("return_url", return_url);
		mav.addObject("WIDseller_email", seller_email);
		mav.addObject("WIDout_trade_no", order.getId()+"");//必填 商户订单号
		mav.addObject("WIDsubject", subject);
		mav.addObject("WIDtotal_fee", total_fee);
		mav.addObject("WIDbody", body);
		mav.addObject("WIDshow_url", show_url);
		mav.addObject("anti_phishing_key", anti_phishing_key);
		mav.addObject("exter_invoke_ip", exter_invoke_ip);
		
		return mav;
	}
}
