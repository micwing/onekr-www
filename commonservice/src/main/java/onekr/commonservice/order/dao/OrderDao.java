package onekr.commonservice.order.dao;

import onekr.commonservice.model.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {

	Page<Order> findByBizOrderByCreateAtDesc(String biz, Pageable pageable);
	
	Page<Order> findByBizAndOwnerOrderByCreateAtDesc(String biz, String owner, Pageable pageable);
}
