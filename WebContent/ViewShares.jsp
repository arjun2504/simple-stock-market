<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% if(session.getAttribute("isadmin").equals("1")) { %>
<jsp:include page="Layout.jsp">
	<jsp:param value="View Shares" name="title"/>
	<jsp:param value="administration" name="back"/>
</jsp:include>
<% } else if(session.getAttribute("isadmin").equals("0")) { %>
<jsp:include page="Layout.jsp">
	<jsp:param value="View Shares" name="title"/>
	<jsp:param value="dashboard" name="back"/>
</jsp:include>
<% } %>

<c:if test="${ not empty param.edited && param.edited == 1 }">
	<center>
		<div class="alert">
			Changes Saved.
		</div>
	</center>
</c:if>
<c:if test="${ not empty param.edited && param.edited == 0 }">
	<center>
		<div class="alert-failure">
			Something went wrong.
		</div>
	</center>
</c:if>
<c:if test="${ not empty param.deleted && param.deleted == 1 }">
	<center>
		<div class="alert">
			Stock Deleted Successfully.
		</div>
	</center>
</c:if>
<c:if test="${ not empty param.deleted && param.deleted == 0 }">
	<center>
		<div class="alert-failure">
			Something went wrong.
		</div>
	</center>
</c:if>
<c:if test="${ not empty param.error && param.error == 1 }">
	<center>
		<div class="alert-failure">
			Something went wrong
		</div>
	</center>
</c:if>
<c:if test="${ not empty param.success && param.success == 1 }">
	<center>
		<div class="alert">
			<c:out value="Purchase Successful"/>
		</div>
	</center>
</c:if>
<c:if test="${ not empty stocks }">
<table class="mytable" border="1">
	<tr>
		<th>ID</th>
		<th>Company Name</th>
		<th>Total Shares</th>
		<th>Available Shares</th>
		<th>Current Price</th>
		<% if(session.getAttribute("isadmin").equals("1") ) { %>
		<th>Edit</th>
		<th>Delete</th>
		<% } else if(session.getAttribute("isadmin").equals("0") && request.getParameter("mode").equals("purchase") )  { %>
		<th>Purchase</th>
		<% } else if(session.getAttribute("isadmin").equals("0") && request.getParameter("mode").equals("sell") )  { %>
		<th>Sell</th>
		<% } %>
	</tr>
	<c:forEach items="${ stocks }" var="stock">
	<tr>
		<td align="left"><c:out value="${ stock.getId() }" /></td>
		<td align="left"><c:out value="${ stock.getName() }" /></td>
		<td align="right"><c:out value="${ stock.getTotalShares() }" /></td>
		<td align="right"><c:out value="${ stock.getAvailableShares() }" /></td>
		<td align="right"><c:out value="${ stock.getCurrentPrice() }" /></td>
		<% if(session.getAttribute("isadmin").equals("1") ) { %>
		<td>
			<form method="post">
				<input type="hidden" name="id" value="${ stock.getId() }">
				<center><button type="submit" class="btn" name="action" value="edit">Edit</button></center>
			</form>
		</td>
		<td>
			<form method="post">
				<input type="hidden" name="id" value="${ stock.getId() }">
				<center><button type="submit" class="btn" name="action" value="delete" onclick="return confirm('Are you sure that you want to delete this stock?');">Delete</button></center>
			</form>
		</td>
		<% } else if(session.getAttribute("isadmin").equals("0") && request.getParameter("mode").equals("purchase") )  { %>
		<td>
			<center>
				<a href="purchase?id=${ stock.getId() }">
					<button type="button" class="btn" name="action" value="purchase">Purchase</button>
				</a>
			</center>
		</td>
		<% } else if(session.getAttribute("isadmin").equals("0") && request.getParameter("mode").equals("sell") )  { %>
		<td>
			<center>
				<a href="sell?id=${ stock.getId() }">
					<button type="button" class="btn" name="action" value="sell">Sell</button>
				</a>
			</center>
		</td>
		<% } %>
	</tr>
	</c:forEach>
</table>
</c:if>
<jsp:include page="Footer.jsp"></jsp:include>