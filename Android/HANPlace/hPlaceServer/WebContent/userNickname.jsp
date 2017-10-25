<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controller.LoginController"%>
<%@  page import="domain.User"%>

<%
	request.setCharacterEncoding("UTF-8");
	String email = request.getParameter("email");
	String user = null;
	
	LoginController loginController = new LoginController();
	
	user = loginController.requestUserNickname(email);
	
	out.print(user);
	
%>