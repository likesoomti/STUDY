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
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>시설 등록 페이지 </title>
</head>
<body>
<div class="container section">
<div class="row">
  <div class="col-md-offset-4 col-md-4">
	  <form action="admin_map_create.jsp" method="post">
	  <div class="form-group">
	    <label > 시설이름  </label>
	    <input type="text" class="form-control" name="title" id="title" placeholder="제 입력하세요">
	  </div>
	  <div class="form-group">
	    <label>시설 카테고리 0~13입  </label>
	    <input type="text" class="form-control" name="category" id="category" placeholder="카테고">
	  </div>
	  <div class="form-group">
	    <label>시설 주소   </label>
	    <input type="text" class="form-control" name="address" id="address" placeholder="시작">
	  </div>
	  <div class="form-group">
	  <label>시설 전화번   </label>
	    <input type="text" class="form-control" name="phone" id="phone" placeholder="시작">
	  </div>
	  <div class="form-group">
	    <label>시설 번호 1,2,3,4  </label>
	    <input type="text" class="form-control" name="fac_num" id="fac_num" >
	  </div>
	  <div class="form-group">
	    <label>시설 프로필 사진    </label>
	    <input type="text" class="form-control" name="picture" id="picture">
	  </div>
	  <div class="form-group">
	    <label>시설 longi   </label>
	    <input type="text" class="form-control" name="lon" id="lon">
	  </div>
	  <div class="form-group">
	    <label>시설 lati   </label>
	    <input type="text" class="form-control" name="lat" id="lat">
	  </div>
	  <div class="form-group">
	  <label>시설 상세내용    </label>
	  <textarea class="form-control" rows="3" name="content"></textarea>
	  <button type="submit" class="btn btn-default">제출</button>
	  </div>
	</form>
	
	<a href="admin_map_list.jsp">맵 리스트 보기.</a>
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
 
