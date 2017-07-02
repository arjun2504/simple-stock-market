<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="Layout.jsp">
	<jsp:param value="Dashboard" name="title"/>
</jsp:include>
<hr>
<ul class="menu">
	<a href="view-shares?mode=view"><li>View Shares</li></a>
	<a href="view-shares?mode=purchase"><li>Purchase</li></a>
	<!-- <a href="view-shares?mode=sell"><li>Sell</li></a> -->
	<a href="portfolio"><li>Portfolio</li></a>
	<a href="logout"><li>Log out</li></a>
</ul>
<jsp:include page="Footer.jsp"></jsp:include>