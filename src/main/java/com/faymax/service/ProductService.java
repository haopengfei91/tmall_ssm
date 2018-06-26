package com.faymax.service;

import java.util.List;

import com.faymax.pojo.Product;
import com.faymax.pojo.ProductImage;

public interface ProductService {

	public void add(Product p);
	public void delete(int id);
	public void update(Product p);
	public Product get(int id);
	public List<Product> list(int cid);
//	public void setFirstProductImage(ProductImage pi);
}
