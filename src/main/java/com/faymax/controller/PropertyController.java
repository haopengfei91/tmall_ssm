package com.faymax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faymax.pojo.Category;
import com.faymax.pojo.Property;
import com.faymax.service.CategoryService;
import com.faymax.service.PropertyService;
import com.faymax.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("")
public class PropertyController {
	
	@Autowired
	CategoryService cgService;
	@Autowired
	PropertyService pService;
	
	@RequestMapping("/admin_property_list")
	public String List(Model model, int cid, Page page) {
		Category cg = cgService.get(cid);
		PageHelper.offsetPage(page.getStart(), page.getCount());
		List ps = pService.list(cid);
		int total = (int) new PageInfo<>(ps).getTotal();
		page.setTotal(total);
		page.setParam("&cid=" + cg.getId());
		model.addAttribute("ps", ps);
		model.addAttribute("cg", cg);
		model.addAttribute("page", page);
		return "admin/listProperty";
		
	}
	
	@RequestMapping("/admin_property_add")
	public String add(Model model, Property p) {
		pService.add(p);
		return "redirect:admin_property_list?cid=" + p.getCid();
	}
	
	@RequestMapping("/admin_property_delete")
	public String delete(Model model, int id) {
		Property p = pService.get(id);
		pService.delete(id);
		return "redirect:/admin_property_list?cid=" + p.getCid();
	}
	
	@RequestMapping("/admin_property_edit")
	public String edit(Model model, int id) {
		Property p = pService.get(id);
		System.out.println(p.getCid());
		Category cg = cgService.get(p.getCid());
		p.setCategory(cg);
		model.addAttribute("p", p);
		return "admin/editProperty";
	}
	
	@RequestMapping("/admin_property_update")
	public String update(Property p) {
		pService.update(p);
		return "redirect:admin_property_list?cid=" + p.getCid();
	}
	

}
