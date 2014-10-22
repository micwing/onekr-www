package onekr.cardservice.card.dao;

import onekr.cardservice.model.Card;

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
	
}
