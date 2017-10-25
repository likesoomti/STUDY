<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controller.UserController"%>

<%
	request.setCharacterEncoding("UTF-8");
	String nickname = request.getParameter("nickname");
	String email = request.getParameter("email");
	String gender = request.getParameter("gender");
	String profile = request.getParameter("profile");
	int result = 0;;
	
	UserController userController = new UserController();
	
	result = userController.requestSetUserNickname(nickname, email, gender, profile);
	
	out.print(result);
	
%>