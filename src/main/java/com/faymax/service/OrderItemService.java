package com.faymax.service;

import java.util.List;

import com.faymax.pojo.Order;
import com.faymax.pojo.OrderItem;

public interface OrderItemService {

	void add(OrderItem c);	 
    void delete(int id);
    void update(OrderItem c);
    OrderItem get(int id);
    List<OrderItem> list();
 
    void fill(List<Order> os);
    void fill(Order o);  
}
