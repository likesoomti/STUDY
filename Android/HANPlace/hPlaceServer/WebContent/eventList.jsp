<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %> --%>
<%@ page import="controller.EventController"%>

<%
	request.setCharacterEncoding("UTF-8");
	
	EventController eventController = new EventController();
	
	String eventList = eventController.requestEventList();
	
	out.print(eventList);
	
%>