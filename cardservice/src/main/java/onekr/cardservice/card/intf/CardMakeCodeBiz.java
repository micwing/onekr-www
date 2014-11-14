package onekr.cardservice.card.intf;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import onekr.cardservice.model.CardMakeCode;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

/**
 * 请柬制作码业务接口
 * @author Administrator
 *
 */
@Validated
public interface CardMakeCodeBiz {

	String generateNewCode(@NotBlank String buyerName,@NotNull @Min(1) Long uid);
	
	Page<CardMakeCode> listAll(@NotNull Date start, @NotNull Date end,@NotNull Pageable pageable);
	
	CardMakeCode findNoUseCode(@NotBlank String code);
	
	CardMakeCode useCode(@NotBlank String code, @NotNull @Min(1) Long cardId);
	
	CardMakeCode delete(@NotBlank String code);
}