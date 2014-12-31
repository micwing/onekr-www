package onekr.cardservice.card.dao;

import onekr.cardservice.model.Card;
import onekr.cardservice.model.CardType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author MICHAEL
 * 
 */
@Repository
public interface CardDao extends JpaRepository<Card, Long> {

	Page<Card> findByCardTypeOrderByCreateAtDesc(CardType cardType, Pageable pageable);
	
	Page<Card> findByCardTypeAndCreateByOrderByCreateAtDesc(CardType cardType, Long uid, Pageable pageable);
}
