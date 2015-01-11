package onekr.web.console.card;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import onekr.cardservice.model.CardType;
import onekr.cardservice.model.Template;
import onekr.commonservice.biz.Biz;
import onekr.commonservice.biz.ConfigOwner;
import onekr.commonservice.common.intf.ConfigBiz;
import onekr.commonservice.model.Config;
import onekr.commonservice.model.Order;
import onekr.commonservice.order.intf.OrderBiz;
import onekr.framework.exception.AppException;
import onekr.framework.exception.ErrorCode;
import onekr.identityservice.model.Group;
import onekr.identityservice.model.User;
import onekr.web.base.ConsoleBaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.config.AlipayConfig;


@Controller
@RequestMapping(value = "/console/card/order")
public class CardOrderController extends ConsoleBaseController {
	
	@Value("#{systemConfig['site.root.url']}")
	private String siteRootUrl;
	
	@Autowired
	private OrderBiz orderBiz;
	
	@Autowired
	private ConfigBiz configBiz;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(CardType cardType, @PageableDefaults(value = 20) Pageable pageable) {
		
		Page<Order> page = null;
		User user = getCurrentUser();
		if (user.getGroup().equals(Group.ADMINISTRATOR)) {
			page = orderBiz.listOrder(Biz.MAKECODE_ORDER, user.getId()+"", pageable);
		} else {
			page = orderBiz.listOrder(Biz.MAKECODE_ORDER, pageable);
		}
		
		ModelAndView mav = new ModelAndView("card:card-order-list");
		mav.addObject("page", page);
		return mav;
	}
	
	/**
	 * 提交支付宝
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doSubmitAlipay", method = RequestMethod.POST)
	public ModelAndView doSubmitAlipay(@RequestParam(value = "template") Template template, HttpSession session) {
		ModelAndView mav = new ModelAndView("portalweb:order-submit-alipay");
		
		Config alipayEmailConfig = configBiz.findConfig(Biz.SYSTEM, ConfigOwner.SYSTEM_ORDER_ALIPAY_SELLER_EMAIL.name());
		Config alipayPartnerConfig = configBiz.findConfig(Biz.SYSTEM, ConfigOwner.SYSTEM_ORDER_ALIPAY_SELLER_PARTNER.name());
		Config alipayKeyConfig = configBiz.findConfig(Biz.SYSTEM, ConfigOwner.SYSTEM_ORDER_ALIPAY_SELLER_KEY.name());
		if (alipayEmailConfig == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND, "未配置卖家支付宝账号");
		if (alipayPartnerConfig == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND, "未配置卖家支付宝账号PARTNER");
		if (alipayKeyConfig == null)
			throw new AppException(ErrorCode.ENTITY_NOT_FOUND, "未配置卖家支付宝账号key");
		
		AlipayConfig.partner = alipayPartnerConfig.getValue();
		AlipayConfig.key = alipayKeyConfig.getValue();
				
		String service = "create_partner_trade_by_buyer";
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
		String price = template.getPrice()+"";
		String quantity = "1";
		//必填		//订单描述		
		String body = "购买用来在www.onekr.com制作电子请柬的制作码";
		//商品展示地址
		String show_url = siteRootUrl+"/portal/buymakecode";
		//需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html		//防钓鱼时间戳
		
		//物流费用
		String logistics_fee = "0.00";
		//必填，即运费
		//物流类型
		String logistics_type = "EXPRESS";
		//必填，三个值可选：EXPRESS（快递）、POST（平邮）、EMS（EMS）
		//物流支付方式
		String logistics_payment = "SELLER_PAY";
		//必填，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）
		
		//收货人姓名
		String receive_name = "暂无";
		//如：张三
	
		//收货人地址
		String receive_address = "无";
		//如：XX省XXX市XXX区XXX路XXX小区XXX栋XXX单元XXX号
	
		//收货人邮编
		String receive_zip = "000000";
		//如：123456
	
		//收货人电话号码
		String receive_phone = "00000000";
		//如：0571-88158090
	
		//收货人手机号码
		String receive_mobile = "";
		//如：13312341234
		
		
		User user = getCurrentUser();
		Order order = new Order();
		order.setBiz(Biz.MAKECODE_ORDER.name());
		order.setOwner(user.getId()+"");
		order.setCreateBy(user.getId());
		order.setUpdateBy(user.getId());
		
		order.setService(service);
		order.setPartner(partner);
		order.setInputCharset(_input_charset);
		order.setPaymentType(payment_type);
		order.setNotifyUrl(notify_url);
		order.setReturnUrl(return_url);
		order.setSellerEmail(seller_email);
		order.setSubject(subject);
		order.setPrice(price);
		order.setQuantity(quantity);
		order.setLogistics_fee(logistics_fee);
		order.setLogistics_payment(logistics_payment);
		order.setLogistics_type(logistics_type);
		order.setBody(body);
		order.setShowUrl(show_url);
		order.setReceive_name(receive_name);
		order.setReceive_address(receive_address);
		order.setReceive_zip(receive_zip);
		order.setReceive_phone(receive_phone);
		order.setReceive_mobile(receive_mobile);
		
		order = orderBiz.saveOrder(order);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("service", service);
		map.put("partner", partner);
		map.put("_input_charset", _input_charset);
		map.put("payment_type", payment_type);
		
		map.put("notify_url", notify_url);
		map.put("return_url", return_url);
		map.put("seller_email", seller_email);
		map.put("out_trade_no", order.getId()+"");//必填 商户订单号
		map.put("subject", subject);
		map.put("price", price);
		map.put("quantity", quantity);
		map.put("logistics_fee", logistics_fee);
		map.put("logistics_type", logistics_type);
		map.put("logistics_payment", logistics_payment);
		map.put("body", body);
		map.put("show_url", show_url);
		map.put("receive_name", receive_name);
		map.put("receive_address", receive_address);
		map.put("receive_zip", receive_zip);
		map.put("receive_phone", receive_phone);
		map.put("receive_mobile", receive_mobile);
		
		mav.addObject("map", map);
		return mav;
	}
}
