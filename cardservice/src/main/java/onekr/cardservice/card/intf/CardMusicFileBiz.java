package onekr.cardservice.card.intf;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import onekr.commonservice.model.FileStore;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

/**
 * 请柬音乐文件业务接口
 */
@Validated
public interface CardMusicFileBiz {
	
	public static final String SYSTEM_MUSIC_FILE_STORE_OWNER = "SYSTEM";

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
	 * 保存系统请柬音乐
	 * @param cardId
	 * @param mfile
	 * @param uid
	 * @return
	 */
	FileStore saveSystemMusic(@NotNull MultipartFile mfile, @NotNull @Min(1) Long uid);
	
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
	 * 删除系统音乐
	 * 
	 * @param fileStoreId
	 * @param uid
	 */
	void deleteSystemMusic(@NotNull @Min(1) Long fileStoreId,
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
	 * 系统音乐列表
	 * @param cardId
	 * @return
	 */
	List<FileStore> listSysteMusic();
	
}
