<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="Layout.jsp">
	<jsp:param value="Login" name="title"/>
</jsp:include>
<c:if test="${ not empty param.u }">
	<center>
		<div class="alert">
			Your user ID is <c:out value="${ param.u }"/>
		</div>
	</center>
</c:if>
<c:if test="${ not empty param.error }">
	<center>
		<div class="alert-failure">
			User ID / Password is incorrect
		</div>
	</center>
</c:if>
<form method="post" class="form">
	<table>
		<tr>
			<td align="right">User ID</td>
			<td><input type="text" name="userid" class="myform" required></td>
		</tr>
		<tr>
			<td align="right">Password</td>
			<td><input type="password" name="password" class="myform" required></td>
		</tr>
		<tr>
			<td align="right">Login as</td>
			<td>
				<select name="isadmin" class="myform">
					<option value="0">User</option>
					<option value="1">Admin</option>
				</select>
			</td>
		</tr>
		<tr>
			<td></td>
			<td><button type="submit" class="btn">Login</button></td>
		</tr>
	</table>
</form>
<hr>
<p align="center">Don't have an account? 
<a href="register">Register</a></p>
<jsp:include page="Footer.jsp"></jsp:include>