//package onekr.biz.model;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Enumerated;
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
//import onekr.service.standard.enums.Status;
//import onekr.biz.model.ModelConstants;
//
//@Entity
//@Table(name = ModelConstants.TABLE_PREFIX_NAMING + "favorite")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//public class Favorite implements Serializable {
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
//	@Column(name = "title")
//	private String title;
//
//	@Column(name = "url")
//	private String url;
//	
//	
//	@Column(name = "rank", length = 255)
//	private Long rank;
//	
//	@Column(name = "json", length = 3000)
//	private String json;
//	
//	@Enumerated
//	@Column(name = "status", nullable = false)
//	private Status status;
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
//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	public Long getRank() {
//		return rank;
//	}
//
//	public void setRank(Long rank) {
//		this.rank = rank;
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
//	public Status getStatus() {
//		return status;
//	}
//
//	public void setStatus(Status status) {
//		this.status = status;
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
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//}
