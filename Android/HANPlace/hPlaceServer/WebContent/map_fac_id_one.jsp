<%@page import="com.mysql.jdbc.log.Log"%>
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
	String getid = request.getParameter("id");
	if(getid == null){
		getid = "0";
	}
 	System.out.println("get"+getid);
	
 	String json = mapDao.map_fac_id_one(Integer.valueOf(getid));
 	
 	

	out.println(json);
	
%>	