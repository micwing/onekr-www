package onekr.cardservice.model;

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

import onekr.cardservice.utils.CardConstants;
import onekr.commonservice.model.Status;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = CardConstants.TABLE_PREFIX_NAMING + "cardinfo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Card implements Serializable {

	private static final long serialVersionUID = -441280225512165188L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "card_type")
	private CardType cardType;
	
	@Column(name = "title")
	private String title;
	
	/**
	 * 沉浸在幸福中的我们<br/>谨定于
	 */
	@Column(name = "before_info", length = 500)
	private String beforeInfo;
	
	@Column(name = "people1_name")
	private String people1Name;
	
	@Column(name = "people1_mobile")
	private String people1Mobile;
	
	@Column(name = "people2_name")
	private String people2Name;
	
	@Column(name = "people2_mobile")
	private String people2Mobile;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "party_time")
	private Date partyTime;
	
	/**
	 * 举行典礼 敬备喜宴<br/>恭请光临
	 */
	@Column(name = "after_info", length = 500)
	private String afterInfo;
	
	/**
	 * 朗庭别苑
	 */
	@Column(name = "restaurant")
	private String restaurant;
	
	/**
	 * 苏州市工业园区李公堤3期22幢(近金鸡湖大道)
	 */
	@Column(name = "address")
	private String address;
	
	/**
	 * 火车站出发，可以乘坐178路，522路到李公堤南下
	 */
	@Column(name = "traffic")
	private String traffic;
	
	/**
	 * 温馨提醒:请勿酒后驾车!
	 */
	@Column(name = "remind")
	private String remind;
	
	/**
	 * 地图图片地址
	 */
	@Column(name = "map_pic_url", length = 2000)
	private String mapPicUrl;
	
	/**
	 * 地图导航链接地址
	 * http://map.baidu.com/?latlng=31.303365913228,120.70021967046&title=%E5%A9%9A%E7%A4%BC%E4%BD%8D%E7%BD%AE&content=%E8%8B%8F%E5%B7%9E%E5%B8%82%E5%B7%A5%E4%B8%9A%E5%9B%AD%E5%8C%BA%E6%9D%8E%E5%85%AC%E5%A0%A43%E6%9C%9F22%E5%B9%A2%28%E8%BF%91%E9%87%91%E9%B8%A1%E6%B9%96%E5%A4%A7%E9%81%93%29&autoOpen=true&l
	 */
	@Column(name = "map_url", length = 2000)
	private String mapUrl;
	
	@Column(name = "taobao_id")
	private String taobaoId;
	
	/**
	 * templetId的前2位表示jspId
	 */
	@Column(name = "templet_id")
	private String templetId;
	
	@Column(name = "remark", length = 500)
	private String remark;
	
	@Column(name = "status")
	private Status status;
	
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

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBeforeInfo() {
		return beforeInfo;
	}

	public void setBeforeInfo(String beforeInfo) {
		this.beforeInfo = beforeInfo;
	}

	public String getPeople1Name() {
		return people1Name;
	}

	public void setPeople1Name(String people1Name) {
		this.people1Name = people1Name;
	}

	public String getPeople1Mobile() {
		return people1Mobile;
	}

	public void setPeople1Mobile(String people1Mobile) {
		this.people1Mobile = people1Mobile;
	}

	public String getPeople2Name() {
		return people2Name;
	}

	public void setPeople2Name(String people2Name) {
		this.people2Name = people2Name;
	}

	public String getPeople2Mobile() {
		return people2Mobile;
	}

	public void setPeople2Mobile(String people2Mobile) {
		this.people2Mobile = people2Mobile;
	}

	public Date getPartyTime() {
		return partyTime;
	}

	public void setPartyTime(Date partyTime) {
		this.partyTime = partyTime;
	}

	public String getAfterInfo() {
		return afterInfo;
	}

	public void setAfterInfo(String afterInfo) {
		this.afterInfo = afterInfo;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public String getRemind() {
		return remind;
	}

	public void setRemind(String remind) {
		this.remind = remind;
	}

	public String getMapUrl() {
		return mapUrl;
	}

	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getTempletId() {
		return templetId;
	}

	public void setTempletId(String templetId) {
		this.templetId = templetId;
	}

	public String getTaobaoId() {
		return taobaoId;
	}

	public void setTaobaoId(String taobaoId) {
		this.taobaoId = taobaoId;
	}

	public String getMapPicUrl() {
		return mapPicUrl;
	}

	public void setMapPicUrl(String mapPicUrl) {
		this.mapPicUrl = mapPicUrl;
	}

}
