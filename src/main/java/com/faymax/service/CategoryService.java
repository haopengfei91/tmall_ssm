package com.faymax.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.faymax.pojo.Category;
import com.faymax.util.Page;

public interface CategoryService {
	
	public List<Category> list();
	
	public int add(Category category);
	
	public void delete(int id);
	
	public Category get(int id);
	
	public void update(Category category);
}
