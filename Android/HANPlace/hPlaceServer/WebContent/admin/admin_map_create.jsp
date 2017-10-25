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
	
	String lat = request.getParameter("lat");
	String lon = request.getParameter("lon");
	
	String category = request.getParameter("category");
	String fac_num = request.getParameter("fac_num");
	
	String address = request.getParameter("address");
	String phone = request.getParameter("phone");
	
	String profile = request.getParameter("picture");

	//싱글톤 방식으로 자바 클래스를 불러옵니다.
	
	AdminController adminController = new AdminController();
	
	adminController.facility_create(title, Integer.valueOf(category), address,phone,profile,Float.valueOf(lon),Float.valueOf(lat),Integer.valueOf(fac_num),content);

%>
<h1>글이 성공적으로 등록되었습니다 </h1><br>
<a href="admin_map_list.jsp">시설 리스트로 이동 </a>