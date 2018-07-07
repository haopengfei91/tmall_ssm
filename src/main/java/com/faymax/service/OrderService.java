package com.faymax.service;

import java.util.List;

import com.faymax.pojo.Order;

public interface OrderService {
	
	public String waitPay = "waitPay";
	public String waitDelivery = "waitDelivery";
	public String waitConfirm = "waitConfirm";
	public String waitReview = "waitReview";
	public String finish = "finish";
	public String delete = "delete";

	public void add(Order order);
	public void delete(int id);
	public void update(Order order);
	public Order get(int id);
	public List<Order> list();
}
