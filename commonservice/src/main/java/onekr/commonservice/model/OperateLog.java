//package onekr.commonservice.model;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
//
//import onekr.commonservice.model.ModelConstants;
//
//@Entity
//@Table(name = ModelConstants.TABLE_PREFIX_NAMING + "operate_log")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//public class OperateLog implements Serializable {
//
//	private static final long serialVersionUID = -441280225512165188L;
//	
//	@Id
//	@Column(name = "id", unique = true, nullable = false)
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	
//	@Column(name = "biz_id", nullable = false)
//	private Long bizId;
//	
//	@Column(name = "owner", nullable = false)
//	private String owner;
//	
//	@Column(name = "user_id", nullable = false)
//	private Long userId;
//	
//	@Column(name = "content")
//	private String content;
//	
//	@Column(name = "json", length = 3000)
//	private String json;
//	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "create_at", nullable = false)
//	private Date createAt;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Long getBizId() {
//		return bizId;
//	}
//
//	public void setBizId(Long bizId) {
//		this.bizId = bizId;
//	}
//
//	public String getOwner() {
//		return owner;
//	}
//
//	public void setOwner(String owner) {
//		this.owner = owner;
//	}
//
//	public Long getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Long userId) {
//		this.userId = userId;
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	public Date getCreateAt() {
//		return createAt;
//	}
//
//	public void setCreateAt(Date createAt) {
//		this.createAt = createAt;
//	}
//
//	public String getJson() {
//		return json;
//	}
//
//	public void setJson(String json) {
//		this.json = json;
//	}
//	
//}
