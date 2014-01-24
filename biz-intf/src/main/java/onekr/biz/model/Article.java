package onekr.biz.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import onekr.framework.type.Constants;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = Constants.TABLE_PREFIX_NAMING + "article")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Article implements Serializable {

	private static final long serialVersionUID = -441280225512165188L;
	
	@Transient
	private Long totalComment;
	@Transient
	private Long totalViewCount;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "title", length = 255)
	private String title;
	
	@Column(name = "image_url", length = 1000)
	private String imageUrl;
	
	@Column(name = "summary", length = 1000)
	private String summary;
	
	@Column(name = "author", length = 255)
	private String author;
	
	@Lob
	@Column(name = "content")
	private String content;
	
	@Column(name = "from_url")
	private String fromUrl;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	private Date createAt;

	public Long getTotalComment() {
		return totalComment;
	}

	public void setTotalComment(Long totalComment) {
		this.totalComment = totalComment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFromUrl() {
		return fromUrl;
	}

	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Long getTotalViewCount() {
		return totalViewCount;
	}

	public void setTotalViewCount(Long totalViewCount) {
		this.totalViewCount = totalViewCount;
	}
	
}
