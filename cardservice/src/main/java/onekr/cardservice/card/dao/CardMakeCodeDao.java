package onekr.cardservice.card.dao;

import java.util.Date;

import onekr.cardservice.model.CardMakeCode;

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
public interface CardMakeCodeDao extends JpaRepository<CardMakeCode, Long> {
	
	CardMakeCode findByCode(String code);

	Page<CardMakeCode> findByCreateAtGreaterThanAndCreateAtLessThanOrderByCreateAtDesc(Date start, Date end, Pageable pageable);
	
	CardMakeCode findByCodeAndCardIsNull(String code);
}
