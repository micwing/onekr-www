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
	 * 向支付宝提交的参数：total_fee
	 */
	@Column(name = "total_fee")
	private String totalFee;
	
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
	 * 向支付宝提交的参数：anti_phishing_key
	 */
	@Column(name = "anti_phishing_key")
	private String antiPhishingKey;
	
	/**
	 * 向支付宝提交的参数：exter_invoke_ip
	 */
	@Column(name = "exter_invoke_ip")
	private String exterInvokeIp;
	
	/**
	 * 支付宝返回参数：trade_no
	 */
	@Column(name = "trade_no")
	private String TradeNo;
	
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

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
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

	public String getAntiPhishingKey() {
		return antiPhishingKey;
	}

	public void setAntiPhishingKey(String antiPhishingKey) {
		this.antiPhishingKey = antiPhishingKey;
	}

	public String getExterInvokeIp() {
		return exterInvokeIp;
	}

	public void setExterInvokeIp(String exterInvokeIp) {
		this.exterInvokeIp = exterInvokeIp;
	}

	public String getTradeNo() {
		return TradeNo;
	}

	public void setTradeNo(String tradeNo) {
		TradeNo = tradeNo;
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
