<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="Layout.jsp">
	<jsp:param value="Sell ${ portfolio.getCompany().getName() } Shares" name="title"/>
	<jsp:param value="portfolio" name="back"/>
</jsp:include>
<c:if test="${ not empty ise_error }">
	<center>
		<div class="alert-failure">
			<c:out value="${ ise_error }"/>
		</div>
	</center>
</c:if>
<table class="mytable">
	<tr>
		<th>Company Id</th>
		<th>Company Name</th>
		<th>Shares Purchased</th>
		<th>Current Price</th>
	</tr>
	<tr>
		<td align="center"><c:out value="${ portfolio.getCompany().getId() }" /></td>
		<td align="center"><c:out value="${ portfolio.getCompany().getName() }" /></td>
		<td align="center"><c:out value="${ portfolio.getShares() }" /></td>
		<td align="center"><c:out value="${ portfolio.getCompany().getCurrentPrice() }" /></td>
	</tr>
</table>
<hr>
<form method="post" class="form">
	<input type="hidden" name="id" value="${ company.getId() }">
	<table>
		<tr>
			<td align="right"><b>Quantity</b></td>
			<td><input type="number" name="qty" class="myform" required></td>
		</tr>
		<tr>
			<td></td>
			<td><center><button type="submit" class="btn">Sell</button></center></td>
		</tr>
	</table>
</form>
<jsp:include page="Footer.jsp"/>