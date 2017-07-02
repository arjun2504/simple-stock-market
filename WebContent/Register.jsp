<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="Layout.jsp">
	<jsp:param value="Register" name="title"/>
</jsp:include>
<form onsubmit="return validate()" method="post" class="form">
	<table>
		<tr>
			<td align="right">Name</td>
			<td><input type="text" name="name" class="myform" required></td>
		</tr>
		<tr>
			<td align="right">Email</td>
			<td><input type="email" name="email" class="myform" required></td>
		</tr>
		<tr>
			<td align="right">Password</td>
			<td><input type="password" name="password1" id="password1" class="myform" required></td>
		</tr>
		<tr>
			<td align="right">Re-Enter Password</td>
			<td><input type="password" name="password2" id="password2" class="myform" required></td>
		</tr>
		<tr>
			<td align="right">Phone</td>
			<td><input type="number" name="phone" class="myform" maxlength="10" required></td>
		</tr>
		<tr>
			<td align="right">Location</td>
			<td>
				<select name="location" class="myform" required>
					<option>Bangalore</option>
					<option>Hyderabad</option>
					<option>Pune</option>
					<option>Chennai</option>
					<option>Delhi</option>
					<option>Kolkata</option>
				</select>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<button type="submit" class="btn">Register</button>
			</td>
		</tr>
	</table>
</form>
<hr>
<p align="center">Already have an account? 
<a href="login">Login</a></p>
<jsp:include page="Footer.jsp"></jsp:include>