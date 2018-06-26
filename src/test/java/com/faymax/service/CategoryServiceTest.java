package com.faymax.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.faymax.pojo.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class CategoryServiceTest {
	
	@Autowired
	CategoryService cgService;

	@Test
	public void testList() {
		for(Category cg:cgService.list()) {
			System.out.println(cg.getId() + " " + cg.getName());
		}
	}

	@Test
	public void testFindWithPage() {
		
	}

	@Test
	public void testAdd() {
		Category cg = new Category();
		cg.setName("测试分类11");
		cgService.add(cg);
		System.out.println("已添加");
	}

}
