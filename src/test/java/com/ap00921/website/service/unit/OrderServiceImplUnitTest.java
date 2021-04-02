package com.ap00921.website.service.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ap00921.website.model.order.Order;
import com.ap00921.website.model.order.OrderAddress;
import com.ap00921.website.model.order.OrderItem;
import com.ap00921.website.repository.OrderRepository;
import com.ap00921.website.service.OrderService;
import com.ap00921.website.service.impl.OrderServiceImpl;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
public class OrderServiceImplUnitTest {

    @TestConfiguration
    static class OrderServiceImplUnitTestConfiguration {
        @Bean
        public OrderService orderService() {
            return new OrderServiceImpl();
        }
    }
	
	@MockBean
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderService orderService;
	
	private Order order = null;
	
	@Before
	public void before() {
		String string = "string";
		
		OrderAddress address = null;
		address = new OrderAddress(string, string, string, string, string, string, string);
		
		List<OrderItem> items = new ArrayList<OrderItem>();
		OrderItem item = new OrderItem(string, string, string, string, string, string, string, 10,10);
		items.add(item);
		
		order = new Order("userId", address, items, 10);
		
		Mockito.when(orderRepository.findAll()).thenReturn(Arrays.asList(order));
		Mockito.when(orderRepository.insert(order)).thenReturn(order);
		Mockito.when(orderRepository.findByOrderId(order.getOrderId())).thenReturn(null);
	}
	
	@After
	public void after() {
		orderRepository.deleteAll();
	}
	
	@Test
	public void whenGetAllOrders_thenListOfOrdersShouldBeReturned() {
		// when
		List<Order> returned = orderService.getAllOrders();
		
		// then
		assertThat(returned.get(0).getOrderId()).isEqualTo(order.getOrderId());
	}
	
	@Test
	public void whenInserValidOrder_thenOrderShouldBeReturned() {
		// given
		this.after();
		
		// when
		Order returned = orderService.insert(order);
		
		// then
		assertThat(returned.getOrderId()).isEqualTo(order.getOrderId());
	}
	
	@Test
	public void whenRemoveByOrderId_thenNullShouldBeReturned() {
		// when
		orderService.remove(order.getOrderId());
		
		// then
		assertNull(orderRepository.findByOrderId(order.getOrderId()));
	}
	
}
