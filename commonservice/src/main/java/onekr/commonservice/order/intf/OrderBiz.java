package onekr.commonservice.order.intf;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import onekr.commonservice.biz.Biz;
import onekr.commonservice.model.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

@Validated
public interface OrderBiz {
	
	Order findById(@NotNull @Min(1) Long id);
	
	Page<Order> listOrder(@NotNull Biz biz,
			@NotNull Pageable pageable);
	
	Page<Order> listOrder(@NotNull Biz biz,@NotNull String owner,
			@NotNull Pageable pageable);
	
	Order saveOrder(@NotNull Order order);
}
