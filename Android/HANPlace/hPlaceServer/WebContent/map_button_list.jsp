<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="dao.MapDao"%>

<%
    
    request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String type = request.getParameter("type");
	//싱글톤 방식으로 자바 클래스를 불러옵니다.
	MapDao mapDao = MapDao.getInstance();
	//

	String place_no = request.getParameter("place_no");
	String category_no = request.getParameter("category_no");
	
	if(place_no == null){
		place_no = "0";
	}
	if(category_no == null){
		category_no = "0";
	}
	
 	System.out.println("get"+place_no);
 	System.out.println("get"+category_no);
 	
 	String json = mapDao.map_button_list(Integer.valueOf(place_no),Integer.valueOf(category_no));
 	

	out.println(json);
	
%>	