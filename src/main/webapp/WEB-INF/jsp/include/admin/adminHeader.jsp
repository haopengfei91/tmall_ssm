<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
 
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
		<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>	 
		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<!-- 引入 Bootstrap -->
		<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
		<link href="css/back/style.css" rel="stylesheet">
    	     
		<script>
			function checkEmpty(id, name){
			    var value = $("#"+id).val();
			    if(value.length==0){
			        alert(name+ "不能为空");
			        $("#"+id)[0].focus();
			        return false;
			    }
			    return true;
			}
			function checkNumber(id, name){
			    var value = $("#"+id).val();
			    if(value.length==0){
			        alert(name+ "不能为空");
			        $("#"+id)[0].focus();
			        return false;
			    }
			    if(isNaN(value)){
			        alert(name+ "必须是数字");
			        $("#"+id)[0].focus();
			        return false;
			    }  
			    return true;
			}
			function checkInt(id, name){
			    var value = $("#"+id).val();
			    if(value.length==0){
			        alert(name+ "不能为空");
			        $("#"+id)[0].focus();
			        return false;
			    }
			    if(parseInt(value)!=value){
			        alert(name+ "必须是整数");
			        $("#"+id)[0].focus();
			        return false;
			    }    
			    return true;
			} 
			$(function(){
			    $("a").click(function(){
			        var deleteLink = $(this).attr("deleteLink");
			        console.log(deleteLink);
			        if("true"==deleteLink){
			            var confirmDelete = confirm("确认要删除");
			            if(confirmDelete)
			                return true;
			            return false;
			             
			        }
			    });
			})
		</script>
	</head>
	<body>