package com.faymax.service;

import java.util.List;

import com.faymax.pojo.Property;

public interface PropertyService {
	
	public void add(Property p);
	public void delete(int id);
	public void update(Property p);
	public Property get(int id);
	public List<Property> list(int cid);

}
