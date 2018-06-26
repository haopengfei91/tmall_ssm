package com.faymax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faymax.dao.PropertyDao;
import com.faymax.pojo.Property;
import com.faymax.pojo.PropertyExample;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyDao pDao;
	
	@Override
	public void add(Property p) {
		pDao.insert(p);

	}

	@Override
	public void delete(int id) {
		pDao.deleteByPrimaryKey(id);

	}

	@Override
	public void update(Property p) {
		pDao.updateByPrimaryKeySelective(p);

	}

	@Override
	public Property get(int id) {
		return pDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Property> list(int cid) {
		PropertyExample example = new PropertyExample();
		example.createCriteria().andCidEqualTo(cid);
		example.setOrderByClause("id desc");
		return pDao.selectByExample(example);
	}

}
