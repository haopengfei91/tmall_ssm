package com.faymax.dao;

import com.faymax.pojo.Review;
import com.faymax.pojo.ReviewExample;
import java.util.List;

public interface ReviewDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Review record);

    int insertSelective(Review record);

    List<Review> selectByExample(ReviewExample example);

    Review selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Review record);

    int updateByPrimaryKey(Review record);
}