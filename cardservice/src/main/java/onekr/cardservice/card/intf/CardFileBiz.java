package onekr.cardservice.card.intf;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import onekr.commonservice.model.FileStore;
import onekr.commonservice.model.FileType;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

@Validated
public interface CardFileBiz {
	
	FileStore saveCardPhoto(@NotNull @Min(1) Long cardId, @NotNull MultipartFile mfile, @NotNull @Min(1) Long uid);

	FileStore[] saveCardPhoto(@NotNull @Min(1) Long cardId, @NotNull MultipartFile[] mfiles, @NotNull @Min(1) Long uid);
	
	FileType getFileType4Filename(String filename);
	
	long getNewRank4Card(Long cardId);
	
	List<FileStore> listCardPhoto(@NotNull @Min(1) Long cardId);
}
