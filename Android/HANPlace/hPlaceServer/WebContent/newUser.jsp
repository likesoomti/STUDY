<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controller.UserController"%>
<%@ page import="domain.User"%>

<%
	request.setCharacterEncoding("UTF-8");
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	String nickname = request.getParameter("nickname");
	String gender = request.getParameter("gender");
	String result = "null";
	
	User newUser = new User(email,password, nickname, gender);
	
	UserController userController = new UserController();
	
	result = userController.requestInsertUser(newUser);
	
	out.print(result);
	
%>