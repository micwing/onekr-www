package onekr.cardservice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import onekr.cardservice.utils.CardConstants;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = CardConstants.TABLE_PREFIX_NAMING + "make_code")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CardMakeCode implements Serializable {

	private static final long serialVersionUID = -441280225512165188L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "maker")
	private String maker;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "make_at")
	private Date makeAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	private Date createAt;
	
	@Column(name = "create_by")
	private Long createBy;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "card_id")
	private Card card;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public Date getMakeAt() {
		return makeAt;
	}

	public void setMakeAt(Date makeAt) {
		this.makeAt = makeAt;
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

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
}
