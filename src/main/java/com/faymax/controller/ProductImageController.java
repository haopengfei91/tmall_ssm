package com.faymax.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.faymax.pojo.Product;
import com.faymax.pojo.ProductImage;
import com.faymax.service.ProductImageService;
import com.faymax.service.ProductService;
import com.faymax.util.ImageUtil;

@Controller
@RequestMapping("")
public class ProductImageController {
	
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;
 
    @RequestMapping("/admin_productImage_add")
    public String add(ProductImage  pi, HttpServletRequest request, MultipartFile image) {
        productImageService.add(pi);
        String fileName = pi.getId()+ ".jpg";
        String imageFolder;
        String imageFolder_small=null;
        String imageFolder_middle=null;
        if(ProductImageService.type_single.equals(pi.getType())){
            imageFolder= request.getSession().getServletContext().getRealPath("img/productSingle");
            imageFolder_small= request.getSession().getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle= request.getSession().getServletContext().getRealPath("img/productSingle_middle");
        }
        else{
            imageFolder= request.getSession().getServletContext().getRealPath("img/productDetail");
        }
        
        File file = new File(imageFolder, fileName);
        if(!file.getParentFile().exists())
        	file.getParentFile().mkdirs();
        try {
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
 
            if(ProductImageService.type_single.equals(pi.getType())) {
                File file_small = new File(imageFolder_small, fileName);
                File file_middle = new File(imageFolder_middle, fileName);
 
                ImageUtil.resizeImage(file, 56, 56, file_small);
                ImageUtil.resizeImage(file, 217, 190, file_middle);
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:admin_productImage_list?pid="+pi.getPid();
    }
 
    @RequestMapping("/admin_productImage_delete")
    public String delete(int id, HttpSession session) {
        ProductImage pi = productImageService.get(id);
 
        String fileName = pi.getId()+ ".jpg";
        String imageFolder;
        String imageFolder_small=null;
        String imageFolder_middle=null;
 
        if(ProductImageService.type_single.equals(pi.getType())){
            imageFolder= session.getServletContext().getRealPath("img/productSingle");
            imageFolder_small= session.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle= session.getServletContext().getRealPath("img/productSingle_middle");
            File imageFile = new File(imageFolder,fileName);
            File f_small = new File(imageFolder_small,fileName);
            File f_middle = new File(imageFolder_middle,fileName);
            imageFile.delete();
            f_small.delete();
            f_middle.delete();
 
        }
        else{
            imageFolder= session.getServletContext().getRealPath("img/productDetail");
            File imageFile = new File(imageFolder,fileName);
            imageFile.delete();
        }
 
        productImageService.delete(id);
 
        return "redirect:admin_productImage_list?pid="+pi.getPid();
    }
 
    @RequestMapping("/admin_productImage_list")
    public String list(int pid, Model model) {
        Product p =productService.get(pid);
        List<ProductImage> pisSingle = productImageService.list(pid, ProductImageService.type_single);
        List<ProductImage> pisDetail = productImageService.list(pid, ProductImageService.type_detail);
 
        model.addAttribute("p", p);
        model.addAttribute("pisSingle", pisSingle);
        model.addAttribute("pisDetail", pisDetail);
 
        return "admin/listProductImage";
    }
}
