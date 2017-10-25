<%@page import="domain.HG_Event"%>
<%@page import="java.util.List"%>
<%@page import="controller.AdminController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="controller.EventController"%>    
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
    AdminController adminController = new AdminController();
	List<HG_Event> list =adminController.admin_event();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
<%

for(int i =0 ; i < list.size() ; i ++ ){
	
		out.print(list.get(i).getEvent_title());
		out.print("<br>");
		out.println(list.get(i).getEvent_content());
		out.print("<br>");
		out.println(list.get(i).getEvent_loc());
		out.print("<br>");
		out.println(list.get(i).getEvent_start_date());
		out.print("<br>");
		out.println(list.get(i).getEvent_end_date());
		out.print("<br>");
		out.println(list.get(i).getEvent_picture());
%>
<hr>
<% } %>
	
	

<a href="admin_event.jsp"> 이벤트 글 등록 </a>
<a href="admin_index.jsp"> 홈으로 </a>

</div>
</body>
</html>