<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<div class="container section">
<a href="admin_event.jsp"><h3>이벤트 관리 게시판</h3></a>
<a href="admin_map.jsp"><h3>지도 관리 게시판</h3></a>
<a href="admin_chat.jsp"><h3>문의 관리 게시판</h3></a>
<a href="admin_logout.jsp"><h3>로그아웃</h3></a>
</div>
</body>
</html>
<style type="text/css">
 
.section{
width : 100%;
height: 500px;
margin-top: 200px
}
h3{
text-align: center;
size:200px
}
a{

}
 </style>