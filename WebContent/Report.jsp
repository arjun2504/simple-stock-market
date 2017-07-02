<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="Layout.jsp">
	<jsp:param value="Transaction Report" name="title"/>
	<jsp:param value="administration" name="back"/>
</jsp:include>
<table class="mytable" border="1">
	<tr>
		<th>Seller Id</th>
		<th>Buyer Id</th>
		<th>Shares Purchased</th>
		<th>Total Amount</th>
	</tr>
	<c:forEach items="${ transaction }" var="t">
	<tr>
		<td align="center"><c:out value="${ t.getSellerId() }" /></td>
		<td align="center"><c:out value="${ t.getBuyerId() }" /></td>
		<td align="center"><c:out value="${ t.getSharesPurchased() }" /></td>
		<td align="center"><c:out value="${ t.getTotalAmount() }" /></td>
	</tr>
	</c:forEach>
</table>
<jsp:include page="Footer.jsp"/>