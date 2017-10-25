<%@page import="dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controller.UserController"%>
<%@ page import="domain.User"%>

<%

	request.setCharacterEncoding("utf-8");
	String user_id = request.getParameter("id");
	String nickname = request.getParameter("nickname");
	String intro = request.getParameter("intro");

	if(user_id == null){
		user_id = "1";
	}
	if(nickname == null){
		nickname = " ";
	}
	if(intro == null){
		intro = " ";
	}
	
	System.out.println("id"+user_id+nickname+intro);
	//싱글톤 방식으로 자바 클래스를 불러옵니다.
	UserController loginController = new UserController();
	String result = loginController.requestUserUpdate(Integer.valueOf(user_id),nickname,intro);
	out.print(result);
	
%>