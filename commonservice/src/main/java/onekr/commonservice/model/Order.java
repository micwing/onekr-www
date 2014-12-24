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
	
	@Column(name = "biz", nullable = false)
	private String biz;
	
	@Column(name = "owner")
	private String owner;
	
	@Column(name = "remark")
	private String remark;
	
	
	@Column(name = "alipay_trade_no")
	private String alipayTradeNo;
	
	@Column(name = "alipay_trade_status")
	private String alipayTradeStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "alipay_notice_at")
	private Date alipayNoticeAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "alipay_return_at")
	private Date alipayReturnAt;
	
	
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

	public String getAlipayTradeNo() {
		return alipayTradeNo;
	}

	public void setAlipayTradeNo(String alipayTradeNo) {
		this.alipayTradeNo = alipayTradeNo;
	}

	public String getAlipayTradeStatus() {
		return alipayTradeStatus;
	}

	public void setAlipayTradeStatus(String alipayTradeStatus) {
		this.alipayTradeStatus = alipayTradeStatus;
	}

	public Date getAlipayNoticeAt() {
		return alipayNoticeAt;
	}

	public void setAlipayNoticeAt(Date alipayNoticeAt) {
		this.alipayNoticeAt = alipayNoticeAt;
	}

	public Date getAlipayReturnAt() {
		return alipayReturnAt;
	}

	public void setAlipayReturnAt(Date alipayReturnAt) {
		this.alipayReturnAt = alipayReturnAt;
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
