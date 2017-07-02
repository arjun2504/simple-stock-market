<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Random" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ param.title }</title>
<link rel="stylesheet" href="style.css" type="text/css">
<script src="script.js"></script>
</head>
<%
	Random r = new Random();
	int val = r.nextInt(6) + 1;
%>
<body class="backgr<%=val%>">
	<div class="container">
	<c:if test="${ not empty param.back }">
		<a href="${ param.back }"><img src="<%=request.getContextPath() + "/" %>images/back.png" class="goleft"></a>
		<h1 class="goleft">${ param.title }</h1>
		<div class="clearfix"></div>
		<br/>
	</c:if>
	<c:if test="${ empty param.back }">
		<h1>${ param.title }</h1>
	</c:if>