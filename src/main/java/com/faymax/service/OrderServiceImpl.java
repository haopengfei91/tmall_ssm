package com.faymax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faymax.dao.OrderDao;
import com.faymax.pojo.Order;
import com.faymax.pojo.OrderExample;
import com.faymax.pojo.User;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao oDao;
	@Autowired
	private UserService uService;
	
	@Override
	public void add(Order order) {
		oDao.insert(order);
	}

	@Override
	public void delete(int id) {
		oDao.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Order order) {
		oDao.updateByPrimaryKeySelective(order);
	}

	@Override
	public Order get(int id) {
		return oDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Order> list() {
		OrderExample example = new OrderExample();
		example.setOrderByClause("id desc");
		List<Order> result = oDao.selectByExample(example);
		setUser(result);
		return result;
	}

	private void setUser(List<Order> result) {
		for(Order o:result) {
			setUser(o);
		}
	}
	
	private void setUser(Order o) {
		User user = uService.get(o.getUid());
		o.setUser(user);
	}

}
