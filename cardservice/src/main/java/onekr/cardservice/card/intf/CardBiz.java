package onekr.cardservice.card.intf;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import onekr.cardservice.model.Card;
import onekr.cardservice.model.CardType;
import onekr.commonservice.model.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

/**
 * 请柬业务接口
 * @author Administrator
 *
 */
@Validated
public interface CardBiz {

	Card findById(@NotNull @Min(1) Long cardId);
	
	Page<Card> listCard(@NotNull CardType cardType,
			@NotNull  @Min(1) Long uid,
			@NotNull Pageable pageable);

	Page<Card> listCard(@NotNull CardType cardType,
			@NotNull Pageable pageable);

	Card saveCard(@NotNull Card card, @NotNull  @Min(1) Long uid);

	/**
	 * 更新请柬地图
	 * @param cardId
	 * @param mapPicUrl
	 * @param mapUrl
	 * @param uid
	 * @return
	 */
	Card updateCardMap(Long cardId, String mapPicUrl, String mapUrl, Long uid);
}