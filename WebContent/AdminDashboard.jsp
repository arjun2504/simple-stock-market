<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="Layout.jsp">
	<jsp:param value="Administration" name="title"/>
</jsp:include>
<hr>
<ul class="menu">
	<a href="add"><li>Add Stock</li></a>
	<a href="view-shares"><li>View / Edit Shares</li></a>
	<a href="report"><li>View Report</li></a>
	<a href="logout"><li>Log out</li></a>
</ul>
<jsp:include page="Footer.jsp"></jsp:include>