package onekr.cardservice.card.intf;

import javax.validation.constraints.NotNull;

import onekr.cardservice.model.Card;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CardBiz {
	
	CardDto findCardInfo(@NotNull Long cardId);
	
	Card findById(@NotNull Long cardId);
	
	Page<Card> listCard(Pageable pageable);
	
	Card saveCard(Card card);
}