package com.faymax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faymax.dao.UserDao;
import com.faymax.pojo.User;
import com.faymax.pojo.UserExample;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao uDao;
	
	@Override
	public void add(User user) {
		uDao.insert(user);
	}

	@Override
	public void delete(int id) {
		uDao.deleteByPrimaryKey(id);
	}

	@Override
	public void update(User user) {
		uDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public User get(int id) {
		return uDao.selectByPrimaryKey(id);
	}

	@Override
	public List<User> list() {
		UserExample example = new UserExample();
		example.setOrderByClause("id desc");
		return uDao.selectByExample(example);
	}

}
