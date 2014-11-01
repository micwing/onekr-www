package onekr.cardservice.card.intf;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import onekr.commonservice.model.FileStore;
import onekr.commonservice.model.FileType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

@Validated
public interface CardFileBiz {
	
	public static final String CARD_COVER_PHOTO_DESC = "cover";
	public static final String CARD_PEOPLE1_PHOTO_DESC = "people1";
	public static final String CARD_PEOPLE2_PHOTO_DESC = "people2";
	
	public static final int squareImageThumbWidth = 200;
	
	FileStore saveCardPhoto(@NotNull @Min(1) Long cardId, @NotNull MultipartFile mfile, @NotNull @Min(1) Long uid);
	FileStore saveCardPhotoThumb(@NotNull @Min(1) Long cardId, @NotNull MultipartFile mfile, @NotNull @Min(1) Long uid);

	FileStore[] saveCardPhoto(@NotNull @Min(1) Long cardId, @NotNull MultipartFile[] mfiles, @NotNull @Min(1) Long uid);
	FileStore[] saveCardPhotoThumb(@NotNull @Min(1) Long cardId, @NotNull MultipartFile[] mfiles, @NotNull @Min(1) Long uid);
	
	FileType getFileType4Filename(@NotBlank String filename);
	
	long getNewRank4Card(@NotNull @Min(1) Long cardId);
	
	List<FileStore> listCardPhoto(@NotNull @Min(1) Long cardId);
	
	FileStore usePhotoAs(@NotNull @Min(1) Long fileStoreId,@NotNull String cardPhotoDesc, @NotNull @Min(1) Long uid);
	
	FileStore cancelPhotoAs(@NotNull @Min(1) Long fileStoreId,@NotNull String cardPhotoDesc, @NotNull @Min(1) Long uid);
	
	FileStore getCardPhotoByUse(@NotNull @Min(1) Long cardId, @NotNull String cardPhotoDesc);
	
	FileStore delete(@NotNull @Min(1) Long fileStoreId, @NotNull @Min(1) Long uid);
	
	
	FileStore saveCardMapPic(@NotNull @Min(1) Long cardId, @NotNull MultipartFile mfile, @NotNull @Min(1) Long uid);
	
	FileStore findCardMappic(@NotNull @Min(1) Long cardId);
}
