package onekr.biz.model;

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

import onekr.framework.type.Constants;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = Constants.TABLE_PREFIX_NAMING + "common_count")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Count implements Serializable {

	private static final long serialVersionUID = -441280225512165188L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "biz", nullable = false)
	private String biz;
	
	@Column(name = "owner", nullable = false)
	private String owner;
	
	@Column(name = "num")
	private Long num;
	
	@Enumerated
	@Column(name = "status", nullable = false)
	private Status status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at", nullable = false)
	private Date updateAt;

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

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

}
