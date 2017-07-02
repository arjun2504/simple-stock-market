<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="Layout.jsp">
	<jsp:param value="Portfolio" name="title"/>
	<jsp:param value="dashboard" name="back"/>
</jsp:include>
<c:if test="${ not empty param.success && param.success == 0 }">
	<center>
		<div class="alert-failure">
			Something went wrong
		</div>
	</center>
</c:if>
<c:if test="${ not empty param.success && param.success == 1 }">
	<center>
		<div class="alert">
			Sold successfully
		</div>
	</center>
</c:if>
<table class="mytable" border="1">
	<tr>
		<th>Company Id</th>
		<th>Company Name</th>
		<th>Shares Purchased</th>
		<th>Current Price</th>
		<th>Sell</th>
	</tr>
	<c:forEach items="${ portfolio }" var="p">
	<tr>
		<td align="center"><c:out value="${ p.getCompany().getId() }" /></td>
		<td align="center"><c:out value="${ p.getCompany().getName() }" /></td>
		<td align="center"><c:out value="${ p.getShares() }" /></td>
		<td align="center"><c:out value="${ p.getCompany().getCurrentPrice() }" /></td>
		<td align="center">
			<a href="sell?id=${ p.getId() }"><button type="button" class="btn">Sell</button></a>
		</td>
	</tr>
	</c:forEach>
</table>
<jsp:include page="Footer.jsp"/>