<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controller.LoginController"%>

<%
	request.setCharacterEncoding("UTF-8");
	
	String email = request.getParameter("email");
	String pw = request.getParameter("pw");
	String result = "";
	
	LoginController loginController = new LoginController();
	
	if(email!=null && pw!=null) {

		result = loginController.requestLogin(email, pw);
		
	}
	
	out.print(result);
	
%>