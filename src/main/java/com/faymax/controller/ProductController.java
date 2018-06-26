package com.faymax.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faymax.pojo.Category;
import com.faymax.pojo.Product;
import com.faymax.service.CategoryService;
import com.faymax.service.ProductService;
import com.faymax.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("")
public class ProductController {

	@Autowired
	private ProductService pService;
	@Autowired
	private CategoryService cService;
	
	@RequestMapping("/admin_product_list")
	public String list(int cid, Model model, Page page) {
		Category c = cService.get(cid);
		PageHelper.offsetPage(page.getStart(), page.getCount());
		List<Product> ps = pService.list(cid);
		int total = (int) new PageInfo<>(ps).getTotal();
		page.setTotal(total);
		page.setParam("&cid=" + c.getId());
		model.addAttribute("ps", ps);
		model.addAttribute("c", c);
		model.addAttribute("page", page);
		return "admin/listProduct";
	}
	
	@RequestMapping("/admin_product_add")
	public String add(Model model, Product p) {
	    p.setCreateDate(new Date());
	    pService.add(p);
	    return "redirect:/admin_product_list?cid=" + p.getCid();
	    }
	 
	@RequestMapping("/admin_product_delete")
	public String delete(int id) {
	    Product p = pService.get(id);
	    pService.delete(id);
	    return "redirect:/admin_product_list?cid=" + p.getCid();
	    }
	 
	@RequestMapping("/admin_product_edit")
	public String edit(Model model, int id) {
	    Product p = pService.get(id);
//	    Category c = cService.get(p.getCid());
//	    p.setCategory(c);
	    model.addAttribute("p", p);
	    return "admin/editProduct";
	}
	 
	@RequestMapping("/admin_product_update")
	public String update(Product p) {
	    pService.update(p);
	    return "redirect:admin_product_list?cid=" + p.getCid();
	}
	
	
	
}
