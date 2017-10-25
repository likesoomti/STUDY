<%@page import="controller.AdminController"%>
<%@page import="dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controller.UserController"%>
<%@ page import="domain.User"%>



<%
	String email = request.getParameter("email");
	String password = request.getParameter("password");

	//싱글톤 방식으로 자바 클래스를 불러옵니다.
			
	AdminController adminController = new AdminController();
	boolean result = adminController.requestAdminuser(email,password);
	
	
	if(result){                                                        // 로그인 성공시
		session.setAttribute("id", email);                 // 세션에 "id" 이름으로 id 등록
%>
		<script>

		alert("로그인 성공 ");

		location.href="admin_main.jsp";                                   // 이전 페이지로 이동

		</script>              // 로그인 성공 메인페이지 이동
<%
	}else{                                                      // 로그인 실패
%>
		<script>

		alert("로그인 실패");

		history.go(-1);                                    // 이전 페이지로 이동

		</script>

<% 
		}
%>

<a href="admin_event.jsp">이벤트 관리 게시판</a>
<a href="admin_map.jsp">지도 관리 게시판 </a>
<a href="admin_chat.jsp">문의 관리 게시판 </a>