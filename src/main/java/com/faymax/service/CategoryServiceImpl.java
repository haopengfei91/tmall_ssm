package com.faymax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faymax.dao.CategoryDao;
import com.faymax.pojo.Category;
import com.faymax.pojo.CategoryExample;
import com.faymax.util.Page;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao cgDao;
	
	@Override
	public List<Category> list() {
		CategoryExample example =new CategoryExample();
        example.setOrderByClause("id desc");
        return cgDao.selectByExample(example);
	}

	@Override
	public int add(Category category) {
		return cgDao.insert(category);
	}

	@Override
	public void delete(int id) {
		cgDao.deleteByPrimaryKey(id);
		
	}

	@Override
	public Category get(int id) {
		return cgDao.selectByPrimaryKey(id);
	}
	
	@Override
	public void update(Category category) {
		cgDao.updateByPrimaryKey(category);
	}
}
