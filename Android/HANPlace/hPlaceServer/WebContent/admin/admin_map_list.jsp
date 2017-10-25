<%@page import="domain.HG_Facility"%>
<%@page import="domain.HG_Event"%>
<%@page import="java.util.List"%>
<%@page import="controller.AdminController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="controller.EventController"%>    
<%
    AdminController adminController = new AdminController();
	List<HG_Facility> list =adminController.admin_facility();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
<a href="admin_map.jsp">시설등록 </a><br>
<a href="admin_main.jsp">홈으로  </a><br>
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

for(int i =0 ; i < list.size() ; i ++ ){
	
		out.print(list.get(i).getFAC_id());
		out.print("<br>");
		out.println(list.get(i).getCategory_no());
		out.print("<br>");
		out.println(list.get(i).getFAC_Address());
		out.print("<br>");
		out.println(list.get(i).getFAC_LOC_Lati());
		out.print("<br>");
		out.println(list.get(i).getFAC_name());
		out.print("<br>");
		out.println(list.get(i).getFAC_Place_detail());
		out.print("<br>");
		out.println(list.get(i).getFAC_phone());
		out.print("<br>");
		out.println(list.get(i).getFAC_Place_no());
		out.print("<br>");
		out.println(list.get(i).getFAC_Proifle());
%>
<hr>
<% } %>
	
	

</div>
</body>
</html>