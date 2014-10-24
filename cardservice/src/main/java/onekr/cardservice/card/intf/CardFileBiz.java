package onekr.cardservice.card.intf;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import onekr.commonservice.model.FileStore;

public interface CardFileBiz {

	FileStore saveCardFile(@NotNull @Min(1) Long cardId, @NotNull MultipartFile mfile, @NotNull @Min(1) Long uid);
}
