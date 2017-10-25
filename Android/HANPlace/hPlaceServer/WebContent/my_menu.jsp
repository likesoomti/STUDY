<%@page import="dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controller.UserController"%>
<%@ page import="domain.User"%>

<%
	String id = request.getParameter("id");
	String result = null;
	//싱글톤 방식으로 자바 클래스를 불러옵니다.
	UserController userController = new UserController();
	
	if(id != null) {
		
		result = userController.requestUserInfo(id);
		
	}
	
	out.print(result);
	
%>