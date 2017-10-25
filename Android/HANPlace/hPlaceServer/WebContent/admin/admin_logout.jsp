<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.getSession(false);
if(session != null){
	System.out.println("ㅁㄴㅇ");
	session.invalidate();
	}
out.print("hi");
out.println();

//response.sendRedirect("admin_login.jsp"); 
%> 
<script>

alert("로그아웃 되었습니다.");

location.href="admin_index.jsp";                                    // 로그아웃 페이지로 이동

</script>



