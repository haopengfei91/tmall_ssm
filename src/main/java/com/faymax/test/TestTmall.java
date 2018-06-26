package com.faymax.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestTmall {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		String sql = "insert into category values (null, ?)";
		try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tmall_ssm?useSSL=false&useUnicode=true&characterEncoding=UTF-8",
                        "root", "1234");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		
       try {
    	   PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 1; i <=10 ; i++) {
            	ps.setString(1, "测试分类"+i);
                ps.execute();
            }
             
            System.out.println("已经成功创建10条分类测试数据");
  
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
  
	}

}
