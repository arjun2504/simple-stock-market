<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="Layout.jsp">
	<jsp:param value="Edit ${ company.getName() }" name="title"/>
	<jsp:param value="view-shares" name="back"/>
</jsp:include>
<form method="post" class="form" onsubmit="return checkIfTotalHigher();">
	<table>
		<tr>
			<td>Company Name</td>
			<td><input type="text" name="name" class="myform" value="${ company.getName() }" required /></td>
		</tr>
		<tr>
			<td>Total Shares</td>
			<td><input type="number" name="totalshares" id="totalshares" class="myform" value="${ company.getTotalShares() }" required /></td>
		</tr>
		<tr>
			<td>Available Shares</td>
			<td><input type="number" name="availableshares" id="availableshares" class="myform" value="${ company.getAvailableShares() }" required /></td>
		</tr>
		<tr>
			<td>Current Price (in Rs.)</td>
			<td><input type="number" name="price" class="myform" id="price" value="${ company.getCurrentPrice() }" required /></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input type="hidden" name="id" value="${ company.getId() }">
				<button type="submit" class="btn">Save</button>
			</td>
		</tr>
	</table>
</form>
<jsp:include page="Footer.jsp"></jsp:include>