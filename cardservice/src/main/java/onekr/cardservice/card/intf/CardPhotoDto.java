package onekr.cardservice.card.intf;

import onekr.commonservice.model.FileStore;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 请柬照片对象
 * @author Administrator
 *
 */
public class CardPhotoDto {

	private Long id;

	private FileStore photo;
	
	private Long thumbId;

	private FileStore thumb;

	private boolean isCover;

	private boolean isPeople1Photo;

	private boolean isPeople2Photo;
	
	public CardPhotoDto(FileStore fs) {
		this.id = fs.getId();
		this.photo = fs;
		JSONObject json = JSON.parseObject(fs.getJson());
		if (json != null) {
			this.setThumbId(json.containsKey(CardPhotoFileBiz.CARD_PHOTO_JSON_ATTR_KEY_THUMB) ? 
					json.getLong(CardPhotoFileBiz.CARD_PHOTO_JSON_ATTR_KEY_THUMB)
					: null);
			this.setCover(json.containsKey(CardPhotoFileBiz.CARD_PHOTO_JSON_ATTR_KEY_COVER) ? 
					json.getBooleanValue(CardPhotoFileBiz.CARD_PHOTO_JSON_ATTR_KEY_COVER)
					: false);
			this.setPeople1Photo(json.containsKey(CardPhotoFileBiz.CARD_PHOTO_JSON_ATTR_KEY_PEOPLE1) ? 
					json.getBooleanValue(CardPhotoFileBiz.CARD_PHOTO_JSON_ATTR_KEY_PEOPLE1)
					: false);
			this.setPeople2Photo(json.containsKey(CardPhotoFileBiz.CARD_PHOTO_JSON_ATTR_KEY_PEOPLE2) ? 
					json.getBooleanValue(CardPhotoFileBiz.CARD_PHOTO_JSON_ATTR_KEY_PEOPLE2)
					: false);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getThumbId() {
		return thumbId;
	}

	public void setThumbId(Long thumbId) {
		this.thumbId = thumbId;
	}

	public FileStore getPhoto() {
		return photo;
	}

	public void setPhoto(FileStore photo) {
		this.photo = photo;
	}

	public FileStore getThumb() {
		return thumb;
	}

	public void setThumb(FileStore thumb) {
		this.thumb = thumb;
	}

	public boolean getIsCover() {
		return isCover;
	}

	public void setCover(boolean isCover) {
		JSONObject json = JSON.parseObject(photo.getJson());
		if (json == null)
			json = new JSONObject();
		json.put(CardPhotoFileBiz.CARD_PHOTO_JSON_ATTR_KEY_COVER, isCover);
		this.photo.setJson(json.toJSONString());
		this.isCover = isCover;
	}

	public boolean getIsPeople1Photo() {
		return isPeople1Photo;
	}

	public void setPeople1Photo(boolean isPeople1Photo) {
		JSONObject json = JSON.parseObject(photo.getJson());
		if (json == null)
			json = new JSONObject();
		json.put(CardPhotoFileBiz.CARD_PHOTO_JSON_ATTR_KEY_PEOPLE1, isPeople1Photo);
		this.photo.setJson(json.toJSONString());
		this.isPeople1Photo = isPeople1Photo;
	}

	public boolean getIsPeople2Photo() {
		return isPeople2Photo;
	}

	public void setPeople2Photo(boolean isPeople2Photo) {
		JSONObject json = JSON.parseObject(photo.getJson());
		if (json == null)
			json = new JSONObject();
		json.put(CardPhotoFileBiz.CARD_PHOTO_JSON_ATTR_KEY_PEOPLE2, isPeople2Photo);
		this.photo.setJson(json.toJSONString());
		this.isPeople2Photo = isPeople2Photo;
	}

}
