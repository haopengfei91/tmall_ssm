package com.faymax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faymax.dao.ProductImageDao;
import com.faymax.pojo.ProductImage;
import com.faymax.pojo.ProductImageExample;

@Service
public class ProductImageServiceImpl implements ProductImageService {
	
	@Autowired
	ProductImageDao piDao;

	@Override
	public void add(ProductImage pi) {
		piDao.insert(pi);
	}

	@Override
	public void delete(int id) {
		piDao.deleteByPrimaryKey(id);
	}

	@Override
	public void update(ProductImage pi) {
		piDao.updateByPrimaryKeySelective(pi);
	}

	@Override
	public ProductImage get(int id) {
		
		return piDao.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductImage> list(int pid, String type) {
		ProductImageExample example = new ProductImageExample();
		example.createCriteria().andPidEqualTo(pid).andTypeEqualTo(type);
		example.setOrderByClause("id desc");
		return piDao.selectByExample(example);
	}

}
