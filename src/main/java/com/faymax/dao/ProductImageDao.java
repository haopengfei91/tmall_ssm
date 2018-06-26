package com.faymax.dao;

import com.faymax.pojo.ProductImage;
import com.faymax.pojo.ProductImageExample;
import java.util.List;

public interface ProductImageDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductImage record);

    int insertSelective(ProductImage record);

    List<ProductImage> selectByExample(ProductImageExample example);

    ProductImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductImage record);

    int updateByPrimaryKey(ProductImage record);
}