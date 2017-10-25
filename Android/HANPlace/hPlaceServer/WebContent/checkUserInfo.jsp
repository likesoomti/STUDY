<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controller.LoginController"%>

<%
	request.setCharacterEncoding("UTF-8");
	String email = request.getParameter("email");
	String nickname = request.getParameter("nickname");
	String result = "null";
	//test
	LoginController loginController = new LoginController();
	
	result = loginController.requestUserInfoCheck(email, nickname);
	
	out.print(result);
	
%>