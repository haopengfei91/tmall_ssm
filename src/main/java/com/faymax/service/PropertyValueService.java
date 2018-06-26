package com.faymax.service;

import java.util.List;

import com.faymax.pojo.Product;
import com.faymax.pojo.PropertyValue;

public interface PropertyValueService {
	
	public void init(Product p);
	public void update(PropertyValue pv);
	public PropertyValue get(int ptid, int pid);
	public List<PropertyValue> list(int pid);

}
