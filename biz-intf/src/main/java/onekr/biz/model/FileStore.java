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
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;
//
//import onekr.service.standard.enums.FileType;
//import onekr.service.standard.enums.Status;
//import onekr.biz.model.ModelConstants;
//
//
//@Entity
//@Table(name = ModelConstants.TABLE_PREFIX_NAMING + "file_store")
//public class FileStore implements Serializable {
//
//	private static final long serialVersionUID = 8217067386398303531L;
//	
//	@Id
//	@Column(name = "id", unique = true, nullable = false)
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	
//	@NotNull
//	@Min(1)
//	@Column(name = "biz_id", nullable = false)
//	private Long bizId;
//	
//	@NotNull
//	@Column(name = "owner", nullable = false)
//	private String owner;
//	
//	@NotNull
//	@Min(1)
//	@Column(name = "user_id", nullable = false)
//	private Long userId;
//	
//	@Column(name = "original_name")
//	private String originalName;
//	
//	@Column(name = "suffix_name")
//	private String suffixName;
//	
//	@Column(name = "store_name")
//	private String storeName;
//	
//	@NotNull
//	@Enumerated
//	@Column(name = "type")
//	private FileType type;
//	
//	@Column(name = "size")
//	private Long size;
//	
//	@Column(name = "description", length = 3000)
//	private String description;
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
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "update_at", nullable = false)
//	private Date updateAt;
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
//	public String getOriginalName() {
//		return originalName;
//	}
//
//	public void setOriginalName(String originalName) {
//		this.originalName = originalName;
//	}
//
//	public String getStoreName() {
//		return storeName;
//	}
//
//	public void setStoreName(String storeName) {
//		this.storeName = storeName;
//	}
//
//	public FileType getType() {
//		return type;
//	}
//
//	public void setType(FileType type) {
//		this.type = type;
//	}
//
//	public Long getSize() {
//		return size;
//	}
//
//	public void setSize(Long size) {
//		this.size = size;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
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
//	public Date getUpdateAt() {
//		return updateAt;
//	}
//
//	public void setUpdateAt(Date updateAt) {
//		this.updateAt = updateAt;
//	}
//
//	public String getSuffixName() {
//		return suffixName;
//	}
//
//	public void setSuffixName(String suffixName) {
//		this.suffixName = suffixName;
//	}
//
//}
