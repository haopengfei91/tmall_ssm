package com.faymax.dao;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.faymax.pojo.CategoryExample;
import com.faymax.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class CategoryDaoTest {

	@Autowired
	CategoryDao cgDao;
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Test
	public void testSelectByExample() {
		CategoryExample example =new CategoryExample();
		log.info(example+"");
        example.setOrderByClause("id desc");
        log.info(cgDao.selectByExample(example)+"");
	}

}
