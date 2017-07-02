<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="Layout.jsp">
	<jsp:param value="Add Stock" name="title"/>
	<jsp:param value="administration" name="back"/>
</jsp:include>
<c:if test="${ not empty param.added }">
	<center>
		<div class="alert">
			Added. The company ID is <c:out value="${ param.added }"/>
		</div>
	</center>
</c:if>
<form method="post" class="form" onsubmit="return checkIfTotalHigher();">
	<table>
		<tr>
			<td align="right">Company Name</td>
			<td><input type="text" name="name" class="myform" required /></td>
		</tr>
		<tr>
			<td align="right">Total Shares</td>
			<td><input type="number" name="totalshares" id="totalshares" class="myform" required /></td>
		</tr>
		<tr>
			<td align="right">Available Shares</td>
			<td><input type="number" name="availableshares" id="availableshares" class="myform" required /></td>
		</tr>
		<tr>
			<td align="right">Current Price (in Rs.)</td>
			<td><input type="number" name="price" class="myform" /></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<button type="submit" class="btn">Add</button>
			</td>
		</tr>
	</table>
</form>
<jsp:include page="Footer.jsp"></jsp:include>