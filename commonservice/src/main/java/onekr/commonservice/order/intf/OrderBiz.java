package onekr.commonservice.order.intf;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.model.Order;

@Validated
public interface OrderBiz {
	
	Order findById(@NotNull @Min(1) Long id);
	
	List<Order> findOrders(@NotNull Biz biz);

	List<Order> findOrders(@NotNull Biz biz,@NotNull String owner);
	
	Order saveOrder(@NotNull Order order);
}
