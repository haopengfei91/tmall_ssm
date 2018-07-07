package com.faymax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faymax.pojo.User;
import com.faymax.service.UserService;
import com.faymax.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("")
public class UserController {
	
	@Autowired
	private UserService uService;
	
	@RequestMapping("/admin_user_list")
	public String list(Model model, Page page) {
		PageHelper.offsetPage(page.getStart(), page.getCount());
		List<User> us = uService.list();
		int total = (int) new PageInfo<>(us).getTotal();
		page.setTotal(total);
		model.addAttribute("us", us);
		model.addAttribute("page", page);
		return "admin/listUser";
	}
}
