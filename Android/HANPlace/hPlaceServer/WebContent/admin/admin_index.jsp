<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	
	String s = (String)session.getAttribute("id");
	if(s == null)
	{        
		System.out.println("null redirect");// id가 Null 이거나 없을 경우
		   // 로그인 페이지로 리다이렉트 한다.
	}
	else{
		response.sendRedirect("admin_main.jsp"); 
		System.out.print(s);
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Page</title>
</head>
<body>
<div class="container section">
<h3>로그인 페이지 </h3>
<div class="row">
  <div class="col-md-offset-4 col-md-4">
	  <form action="admin_login.jsp" method="post">
	  <div class="form-group">
	    <label for="exampleInputEmail1">아이디 </label>
	    <input type="text" class="form-control" name="email" id="exampleInputEmail1" placeholder="이메일을 입력하세요">
	  </div>
	  <div class="form-group">
	    <label for="exampleInputPassword1">비밀번호 </label>
	    <input type="password" class="form-control" name="password" id="exampleInputPassword1" placeholder="암호">
	  </div>
	  <button type="submit" class="btn btn-default">제출</button>
	</form>
  </div>
</div>

</html>
</div>
 <style type="text/css">
 
.section{
width : 100%;
height: 500px;
margin-top: 200px
}
h3{
text-align: center;
}
 </style>
