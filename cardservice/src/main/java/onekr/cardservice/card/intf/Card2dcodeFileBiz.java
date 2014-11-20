package onekr.cardservice.card.intf;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import onekr.commonservice.model.FileStore;

import org.springframework.validation.annotation.Validated;

/**
 * 请柬音乐文件业务接口
 */
@Validated
public interface Card2dcodeFileBiz {
	
	FileStore get2dcodeFileStore(@NotNull @Min(1) Long cardId, @NotNull @Min(1) Long uid);
	
	FileStore generate2dcode(@NotNull @Min(1) Long cardId, @NotNull @Min(1) Long uid);
}
