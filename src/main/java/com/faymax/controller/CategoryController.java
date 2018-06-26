package com.faymax.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.faymax.pojo.Category;
import com.faymax.service.CategoryService;
import com.faymax.util.ImageUtil;
import com.faymax.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("")
public class CategoryController {
	
	@Autowired
	private CategoryService cgService;
	
	@RequestMapping("/admin_category_list")
	public String list(Model model, Page page) {
		if(page==null) {
			page = new Page();
		}
		PageHelper.offsetPage(page.getStart(), page.getCount());
		List<Category> cs = cgService.list();
		int total = (int) new PageInfo<>(cs).getTotal();
		page.setTotal(total);
        model.addAttribute("cs", cs);
        model.addAttribute("page", page);
        return "admin/listCategory";
	}
	
	@RequestMapping("/admin_category_add")
	public String add(Category category, HttpServletRequest request, MultipartFile image) throws IOException {  
		cgService.add(category);
	    String path = request.getSession().getServletContext().getRealPath("img/category");
	    File file = new File(path, category.getId() + ".jpg");
	    if(!file.getParentFile().exists())
	        file.getParentFile().mkdirs();
	    image.transferTo(file);
	    BufferedImage img = ImageUtil.change2jpg(file);
	    ImageIO.write(img, "jpg", file);
	    return "redirect:/admin_category_list";
	}
	
	@RequestMapping("/admin_category_delete")
	public String delete(int id, HttpServletRequest request) {
		cgService.delete(id);
		File imageFolder= new File(request.getSession().getServletContext().getRealPath("img/category"));
	    File file = new File(imageFolder,id+".jpg");
	    file.delete();
	 
	    return "redirect:/admin_category_list";
	}
	
	@RequestMapping("admin_category_edit")
	public String edit(int id, Model model) throws IOException {
	    Category cg= cgService.get(id);
	    model.addAttribute("cg", cg);
	    return "admin/editCategory";
	}
	
	@RequestMapping("admin_category_update")
	public String update(Category category, HttpServletRequest request, MultipartFile image) throws IOException {
		cgService.update(category);
	    if(null!=image &&!image.isEmpty()){
	    	String path = request.getSession().getServletContext().getRealPath("img/category");
	        File file = new File(path, category.getId()+".jpg");
	        if(!file.getParentFile().exists())
		        file.getParentFile().mkdirs();
	        image.transferTo(file);
	        BufferedImage img = ImageUtil.change2jpg(file);
	        ImageIO.write(img, "jpg", file);
	    }
	    return "redirect:/admin_category_list";
	}
}
