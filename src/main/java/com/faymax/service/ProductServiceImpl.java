package com.faymax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faymax.dao.ProductDao;
import com.faymax.pojo.Category;
import com.faymax.pojo.Product;
import com.faymax.pojo.ProductExample;
import com.faymax.pojo.ProductImage;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao pDao;
	@Autowired
	private CategoryService cService;
	@Autowired
	private ProductImageService piService;
	
	@Override
	public void add(Product p) {
		pDao.insert(p);

	}

	@Override
	public void delete(int id) {
		pDao.deleteByPrimaryKey(id);

	}

	@Override
	public void update(Product p) {
		pDao.updateByPrimaryKeySelective(p);
			
	}

	@Override
	public Product get(int id) {
		Product p = pDao.selectByPrimaryKey(id);
		setCategory(p);
		return p;

	}

	@Override
	public List<Product> list(int cid) {
		ProductExample example = new ProductExample();
		example.createCriteria().andCidEqualTo(cid);
		example.setOrderByClause("id desc");
		List result = pDao.selectByExample(example);
		System.out.println(result+"");
		setCategory(result);
		setFirstProductImage(result);
		return result;
	}
	
	public void setCategory(List<Product> ps) {
		for(Product p:ps) {
			setCategory(p);
		}
	}
	
	public void setCategory(Product p) {
		int cid = p.getCid();
		Category c = cService.get(cid);
		p.setCategory(c);
	}

	public void setFirstProductImage(Product p) {
        List<ProductImage> pis = piService.list(p.getId(), ProductImageService.type_single);
        if (!pis.isEmpty()) {
            ProductImage pi = pis.get(0);
            p.setFirstProductImage(pi);
        }
    }
 
    public void setFirstProductImage(List<Product> ps) {
        for (Product p : ps) {
            setFirstProductImage(p);
        }
    }

}
