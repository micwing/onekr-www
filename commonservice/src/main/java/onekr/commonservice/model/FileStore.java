package onekr.commonservice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import onekr.commonservice.utils.CommonConstants;


@Entity
@Table(name = CommonConstants.TABLE_PREFIX_NAMING + "file_store")
public class FileStore implements Serializable {

	private static final long serialVersionUID = 8217067386398303531L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "biz", nullable = false)
	private String biz;
	
	@Column(name = "owner", nullable = false)
	private String owner;
	
	@NotNull
	@Column(name = "user_id", nullable = false)
	private Long userId;
	
	@Column(name = "original_name")
	private String originalName;
	
	@Column(name = "suffix_name")
	private String suffixName;
	
	@Column(name = "store_path")
	private String storePath;
	
	@NotNull
	@Enumerated
	@Column(name = "type")
	private FileType type;
	
	@Column(name = "size")
	private Long size;
	
	@Column(name = "description", length = 1000)
	private String description;
	
	@Column(name = "rank", length = 255)
	private Long rank;
	
	@Column(name = "json", length = 1000)
	private String json;
	
	@Enumerated
	@Column(name = "status", nullable = false)
	private Status status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at", nullable = false)
	private Date createAt;
	
	@Column(name = "create_by", nullable = false)
	private Long createBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_by", nullable = false)
	private Date updateBy;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getSuffixName() {
		return suffixName;
	}

	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	public String getStorePath() {
		return storePath;
	}

	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}

	public FileType getType() {
		return type;
	}

	public void setType(FileType type) {
		this.type = type;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public Date getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Date updateBy) {
		this.updateBy = updateBy;
	}

}
