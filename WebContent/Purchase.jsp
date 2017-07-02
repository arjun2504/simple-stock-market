<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="Layout.jsp">
	<jsp:param value="Purchase ${ company.getName() }" name="title"/>
	<jsp:param value="view-shares?mode=purchase" name="back"/>
</jsp:include>
<c:if test="${ not empty ise_error }">
	<center>
		<div class="alert-failure">
			<c:out value="${ ise_error }"/>
		</div>
	</center>
</c:if>
<c:if test="${ not empty ple_error }">
	<center>
		<div class="alert-failure">
			<c:out value="${ ple_error }"/>
		</div>
	</center>
</c:if>
<table class="mytable" border="0.5">
  <tr>
    <th>Company Id</th>
    <th>Company Name</th>
    <th>Total Shares</th>
    <th>Available Shares</th>
    <th>Current Price</th>
  </tr>
  <tr>
    <td align="center"><c:out value="${ company.getId() }" /></td>
    <td align="center"><c:out value="${ company.getName() }" /></td>
    <td align="center"><c:out value="${ company.getTotalShares() }" /></td>
    <td align="center"><c:out value="${ company.getAvailableShares() }" /></td>
    <td align="center"><c:out value="${ company.getCurrentPrice() }" /></td>
  </tr>
</table>
<hr/>
<form method="post" class="form">
	<input type="hidden" name="id" value="${ company.getId() }">
	<table>
		<tr>
			<td align="right"><b>Quantity</b></td>
			<td><input type="number" name="qty" class="myform" required></td>
		</tr>
		<tr>
			<td></td>
			<td><center><button type="submit" class="btn">Purchase</button></center></td>
		</tr>
	</table>
</form>
<jsp:include page="Footer.jsp"></jsp:include>