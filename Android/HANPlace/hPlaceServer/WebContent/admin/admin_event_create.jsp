<%@page import="controller.EventController"%>
<%@page import="controller.AdminController"%>
<%@page import="dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controller.UserController"%>
<%@ page import="domain.User"%>
<% 
	String s = (String)session.getAttribute("id");
	if(s == null)
	{                            // id가 Null 이거나 없을 경우
		   // 로그인 페이지로 리다이렉트 한다.
		response.sendRedirect("admin_login.jsp" ); 
		System.out.print(s);
	}
	else{

	}

%>

<%
	request.setCharacterEncoding("utf-8");

	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String LOC = request.getParameter("loc");
	String start_date = request.getParameter("start_date");
	String end_date = request.getParameter("end_date");
	String picture = request.getParameter("picture");

	//싱글톤 방식으로 자바 클래스를 불러옵니다.
	
	
	AdminController adminController = new AdminController();
	
	adminController.event_create(title, content, LOC, start_date, end_date, picture);

	
	
%>
<h1>글이 성공적으로 등록되었습니다 </h1><br>
<a href="admin_event_list.jsp">이벤트  리스트로 이동 </a>