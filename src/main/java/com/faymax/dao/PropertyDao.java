package com.faymax.dao;

import com.faymax.pojo.Property;
import com.faymax.pojo.PropertyExample;
import java.util.List;

public interface PropertyDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Property record);

    int insertSelective(Property record);

    List<Property> selectByExample(PropertyExample example);

    Property selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Property record);

    int updateByPrimaryKey(Property record);
}