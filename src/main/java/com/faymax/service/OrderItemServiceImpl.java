package com.faymax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faymax.dao.OrderItemDao;
import com.faymax.pojo.Order;
import com.faymax.pojo.OrderItem;
import com.faymax.pojo.OrderItemExample;
import com.faymax.pojo.Product;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
    OrderItemDao oIDao;
    @Autowired
    ProductService pService;
 
    @Override
    public void add(OrderItem c) {
        oIDao.insert(c);
    }
 
    @Override
    public void delete(int id) {
        oIDao.deleteByPrimaryKey(id);
    }
 
    @Override
    public void update(OrderItem c) {
        oIDao.updateByPrimaryKeySelective(c);
    }
 
    @Override
    public OrderItem get(int id) {
        OrderItem result = oIDao.selectByPrimaryKey(id);
        setProduct(result);
        return result;
    }
 
    public List<OrderItem> list(){
        OrderItemExample example =new OrderItemExample();
        example.setOrderByClause("id desc");
        return oIDao.selectByExample(example);
 
    }
 
    @Override
    public void fill(List<Order> os) {
        for (Order o : os) {
            fill(o);
        }
    }
 
    public void fill(Order o) {
        OrderItemExample example =new OrderItemExample();
        example.createCriteria().andOidEqualTo(o.getId());
        example.setOrderByClause("id desc");
        List<OrderItem> ois =oIDao.selectByExample(example);
        setProduct(ois);
 
        float total = 0;
        int totalNumber = 0;
        for (OrderItem oi : ois) {
            total+=oi.getNumber()*oi.getProduct().getPromotePrice();
            totalNumber+=oi.getNumber();
        }
        o.setTotal(total);
        o.setTotalNumber(totalNumber);
        o.setOrderItems(ois);
 
    }
 
    public void setProduct(List<OrderItem> ois){
        for (OrderItem oi: ois) {
            setProduct(oi);
        }
    }
 
    private void setProduct(OrderItem oi) {
        Product p = pService.get(oi.getPid());
        oi.setProduct(p);
    }
 
}
