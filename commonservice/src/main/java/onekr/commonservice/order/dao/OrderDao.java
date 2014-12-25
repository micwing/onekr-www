package onekr.commonservice.order.dao;

import java.util.List;

import onekr.commonservice.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {

	List<Order> findByBizOrderByCreateAtDesc(String biz);
	
	List<Order> findByBizAndOwnerOrderByCreateAtDesc(String biz, String owner);
}
