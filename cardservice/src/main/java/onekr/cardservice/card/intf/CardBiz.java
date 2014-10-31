package onekr.cardservice.card.intf;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import onekr.cardservice.model.Card;
import onekr.cardservice.model.CardType;
import onekr.commonservice.model.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CardBiz {

//	CardDto findCardInfo(@NotNull @Min(1) Long cardId);

	Card findById(@NotNull @Min(1) Long cardId);

	Page<Card> listCard(@NotNull CardType cardType, @NotNull Status status,
			@NotNull Pageable pageable);

	Card saveCard(@NotNull Card card, @NotNull  @Min(1) Long uid);

	Card updateCardMap(Long cardId, String mapPicUrl, String mapUrl, Long uid);
}