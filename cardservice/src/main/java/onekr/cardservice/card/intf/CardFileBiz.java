package onekr.cardservice.card.intf;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import onekr.commonservice.model.FileStore;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

/**
 * 请柬文件业务接口
 */
@Validated
public interface CardFileBiz {

	public static final int SQUARE_IMAGE_THUMB_WIDTH = 300;
	public static final int SQUARE_IMAGE_PHOTO_MAX_WIDTH = 960;
	
	public static final String CARD_PHOTO_JSON_ATTR_KEY_THUMB = "thumbId";
	public static final String CARD_PHOTO_JSON_ATTR_KEY_COVER = "cover";
	public static final String CARD_PHOTO_JSON_ATTR_KEY_PEOPLE1 = "people1";
	public static final String CARD_PHOTO_JSON_ATTR_KEY_PEOPLE2 = "people2";
	
	public static final String CARD_MUSIC_JSON_ATTR_KEY_USE = "use";
	
	/**
	 * 保存请柬音乐
	 * @param cardId
	 * @param mfile
	 * @param uid
	 * @return
	 */
	FileStore saveCardMusic(@NotNull @Min(1) Long cardId,
			@NotNull MultipartFile mfile, @NotNull @Min(1) Long uid);
	
	/**
	 * 设置为请柬音乐
	 * @param cardId
	 * @param fileStoreId
	 * @param uid
	 */
	void useMusic(@NotNull @Min(1) Long cardId, @NotNull @Min(1) Long fileStoreId, @NotNull @Min(1) Long uid);
	
	/**
	 * 删除请柬音乐
	 * 
	 * @param fileStoreId
	 * @param uid
	 */
	void deleteCardMusic(@NotNull @Min(1) Long cardId, @NotNull @Min(1) Long fileStoreId,
			@NotNull @Min(1) Long uid);
	
	/**
	 * 获得请柬音乐
	 * @param cardId
	 * @return
	 */
	FileStore getUseMusic(@NotNull @Min(1) Long cardId);
	
	/**
	 * 请柬音乐列表
	 * @param cardId
	 * @return
	 */
	List<FileStore> listCardMusic(@NotNull @Min(1) Long cardId);
	
	/**
	 * 保存请柬照片
	 * @param cardId
	 * @param mfile
	 * @param cardPhotoThumb
	 * @param uid
	 * @return
	 */
	FileStore saveCardPhoto(@NotNull @Min(1) Long cardId,
			@NotNull MultipartFile mfile, @NotNull FileStore cardPhotoThumb,
			@NotNull @Min(1) Long uid);

	/**
	 * 保存请柬照片缩略图
	 * @param cardId
	 * @param mfile
	 * @param uid
	 * @return
	 */
	FileStore saveCardPhotoThumb(@NotNull @Min(1) Long cardId,
			@NotNull MultipartFile mfile, @NotNull @Min(1) Long uid);

	/**
	 * 批量保存请柬照片
	 */
	FileStore[] saveCardPhoto(@NotNull @Min(1) Long cardId,
			@NotNull MultipartFile[] mfiles, @NotNull FileStore[] cardPhotoThumb,
			@NotNull @Min(1) Long uid);

	/**
	 * 批量保存请柬照片缩略图
	 * @param cardId
	 * @param mfiles
	 * @param uid
	 * @return
	 */
	FileStore[] saveCardPhotoThumb(@NotNull @Min(1) Long cardId,
			@NotNull MultipartFile[] mfiles, @NotNull Long uid);

	/**
	 * 请柬照片列表
	 * @param cardId
	 * @return
	 */
	List<CardPhotoDto> listCardPhoto(@NotNull @Min(1) Long cardId);

	/**
	 * 把cardId的fileStoreId的照片设为封面
	 * @param cardId
	 * @param fileStoreId
	 * @param cardPhotoAs
	 * @param uid
	 * @return
	 */
	void usePhotoAsCover(@NotNull @Min(1) Long cardId, @NotNull @Min(1) Long fileStoreId,
			@NotNull @Min(1) Long uid);

	/**
	 * 把cardId的fileStoreId的照片取消设为封面
	 * @param cardId
	 * @param fileStoreId
	 * @param cardPhotoAs
	 * @param uid
	 * @return
	 */
	void cancelPhotoAsCover(@NotNull @Min(1) Long cardId, @NotNull @Min(1) Long fileStoreId,
			@NotNull @Min(1) Long uid);

	/**
	 * 获取封面照片
	 * @param cardId
	 * @return
	 */
	CardPhotoDto getCardPhotoCover(@NotNull @Min(1) Long cardId);
	
	/**
	 * 把cardId的fileStoreId的照片设为用户1新郎
	 * @param cardId
	 * @param fileStoreId
	 * @param cardPhotoAs
	 * @param uid
	 * @return
	 */
	void usePhotoAsPeople1(@NotNull @Min(1) Long cardId, @NotNull @Min(1) Long fileStoreId,
			@NotNull @Min(1) Long uid);

	/**
	 * 把cardId的fileStoreId的照片取消设为用户1新郎
	 * @param cardId
	 * @param fileStoreId
	 * @param cardPhotoAs
	 * @param uid
	 * @return
	 */
	void cancelPhotoAsPeople1(@NotNull @Min(1) Long cardId, @NotNull @Min(1) Long fileStoreId,
			@NotNull @Min(1) Long uid);

	/**
	 * 获取用户1新郎照片
	 * @param cardId
	 * @return
	 */
	CardPhotoDto getCardPhotoPeople1(@NotNull @Min(1) Long cardId);
	
	/**
	 * 把cardId的fileStoreId的照片设为用户2新娘
	 * @param cardId
	 * @param fileStoreId
	 * @param cardPhotoAs
	 * @param uid
	 * @return
	 */
	void usePhotoAsPeople2(@NotNull @Min(1) Long cardId, @NotNull @Min(1) Long fileStoreId,
			@NotNull @Min(1) Long uid);

	/**
	 * 把cardId的fileStoreId的照片取消设为用户2新娘
	 * @param cardId
	 * @param fileStoreId
	 * @param cardPhotoAs
	 * @param uid
	 * @return
	 */
	void cancelPhotoAsPeople2(@NotNull @Min(1) Long cardId, @NotNull @Min(1) Long fileStoreId,
			@NotNull @Min(1) Long uid);

	/**
	 * 获取用户2新娘照片
	 * @param cardId
	 * @return
	 */
	CardPhotoDto getCardPhotoPeople2(@NotNull @Min(1) Long cardId);

	/**
	 * 删除照片，同时该照片对应的缩略图也会被删除
	 * 
	 * @param fileStoreId
	 * @param uid
	 */
	void deleteCardPhoto(@NotNull @Min(1) Long fileStoreId,
			@NotNull @Min(1) Long uid);

}
