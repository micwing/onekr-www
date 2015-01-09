package onekr.commonservice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import onekr.commonservice.utils.CommonConstants;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = CommonConstants.TABLE_PREFIX_NAMING + "order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Order implements Serializable {

	private static final long serialVersionUID = -441280225512165188L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * 业务key
	 */
	@Column(name = "biz", nullable = false)
	private String biz;
	
	/**
	 * 业务owner
	 */
	@Column(name = "owner")
	private String owner;
	
	/**
	 * 业务备注
	 */
	@Column(name = "remark")
	private String remark;
	
	/**
	 * 向支付宝提交的参数：service
	 */
	@Column(name = "service")
	private String service;
	
	/**
	 * 向支付宝提交的参数：partner
	 */
	@Column(name = "partner")
	private String partner;
	
	/**
	 * 向支付宝提交的参数：_input_charset
	 */
	@Column(name = "input_charset")
	private String inputCharset;
	
	/**
	 * 向支付宝提交的参数：payment_type
	 */
	@Column(name = "payment_type")
	private String paymentType;
	
	/**
	 * 向支付宝提交的参数：notify_url
	 */
	@Column(name = "notify_url")
	private String notifyUrl;
	
	/**
	 * 向支付宝提交的参数：return_url
	 */
	@Column(name = "return_url")
	private String returnUrl;
	
	/**
	 * 向支付宝提交的参数：seller_email
	 */
	@Column(name = "seller_email")
	private String sellerEmail;
	
	/**
	 * 向支付宝提交的参数：subject
	 */
	@Column(name = "subject")
	private String subject;
	
	/**
	 * 向支付宝提交的参数：price
	 */
	@Column(name = "price")
	private String price;
	
	/**
	 * 向支付宝提交的参数：quantity
	 */
	@Column(name = "quantity")
	private String quantity;
	
	/**
	 * 向支付宝提交的参数：logistics_fee
	 */
	@Column(name = "logistics_fee")
	private String logistics_fee;
	
	/**
	 * 向支付宝提交的参数：logistics_type
	 */
	@Column(name = "logistics_type")
	private String logistics_type;
	
	/**
	 * 向支付宝提交的参数：logistics_payment
	 */
	@Column(name = "logistics_payment")
	private String logistics_payment;
	
	/**
	 * 向支付宝提交的参数：body
	 */
	@Column(name = "body")
	private String body;
	
	/**
	 * 向支付宝提交的参数：show_url
	 */
	@Column(name = "show_url")
	private String showUrl;
	
	/**
	 * 向支付宝提交的参数：receive_name
	 */
	@Column(name = "receive_name")
	private String receive_name;
	
	/**
	 * 向支付宝提交的参数：receive_address
	 */
	@Column(name = "receive_address")
	private String receive_address;
	
	/**
	 * 向支付宝提交的参数：receive_zip
	 */
	@Column(name = "receive_zip")
	private String receive_zip;
	
	/**
	 * 向支付宝提交的参数：receive_phone
	 */
	@Column(name = "receive_phone")
	private String receive_phone;
	
	/**
	 * 向支付宝提交的参数：receive_mobile
	 */
	@Column(name = "receive_mobile")
	private String receive_mobile;
	
	/**
	 * 支付宝返回参数：trade_no
	 */
	@Column(name = "trade_no")
	private String tradeNo;
	
	/**
	 * 支付宝返回参数：trade_status
	 */
	@Column(name = "trade_status")
	private String tradeStatus;
	
	/**
	 * 支付宝异步通知时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "notice_at")
	private Date noticeAt;
	
	/**
	 * 支付宝同步通知时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "return_at")
	private Date returnAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	private Date createAt;
	
	@Column(name = "create_by")
	private Long createBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at")
	private Date updateAt;
	
	@Column(name = "update_by")
	private Long updateBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBiz() {
		return biz;
	}

	public void setBiz(String biz) {
		this.biz = biz;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getInputCharset() {
		return inputCharset;
	}

	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getLogistics_fee() {
		return logistics_fee;
	}

	public void setLogistics_fee(String logistics_fee) {
		this.logistics_fee = logistics_fee;
	}

	public String getLogistics_type() {
		return logistics_type;
	}

	public void setLogistics_type(String logistics_type) {
		this.logistics_type = logistics_type;
	}

	public String getLogistics_payment() {
		return logistics_payment;
	}

	public void setLogistics_payment(String logistics_payment) {
		this.logistics_payment = logistics_payment;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}

	public String getReceive_name() {
		return receive_name;
	}

	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}

	public String getReceive_address() {
		return receive_address;
	}

	public void setReceive_address(String receive_address) {
		this.receive_address = receive_address;
	}

	public String getReceive_zip() {
		return receive_zip;
	}

	public void setReceive_zip(String receive_zip) {
		this.receive_zip = receive_zip;
	}

	public String getReceive_phone() {
		return receive_phone;
	}

	public void setReceive_phone(String receive_phone) {
		this.receive_phone = receive_phone;
	}

	public String getReceive_mobile() {
		return receive_mobile;
	}

	public void setReceive_mobile(String receive_mobile) {
		this.receive_mobile = receive_mobile;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public Date getNoticeAt() {
		return noticeAt;
	}

	public void setNoticeAt(Date noticeAt) {
		this.noticeAt = noticeAt;
	}

	public Date getReturnAt() {
		return returnAt;
	}

	public void setReturnAt(Date returnAt) {
		this.returnAt = returnAt;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

}
