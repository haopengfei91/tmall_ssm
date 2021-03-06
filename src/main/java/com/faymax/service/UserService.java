package com.faymax.service;

import java.util.List;

import com.faymax.pojo.User;

public interface UserService {

	public void add(User user);
	public void delete(int id);
	public void update(User user);
	public User get(int id);
	public List<User> list();
}
