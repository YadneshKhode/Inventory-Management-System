<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
function loadPage() {
	document.forms["form1"].action="validateLogin.html";
	document.forms["form1"].submit();
}
</script> 
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container">
<h1>Inventory Management System</h1>
<div class="navbar">
  <a  href="index.jsp"><i class="fa fa-fw fa-home"></i> Home</a> 
  <a class="active" href="#"><i class="fa fa-fw fa-user"></i> Login</a>
</div>
<h2 align="center">Login</h2>

<f:form name="form1" modelAttribute="loginBean"> 
    <table class="bodycontainer">
    <tr><td><label for="uname">Username</label></td>
    <td> <f:input path="username" placeholder="Enter Username" />
    <font color="red"><f:errors path="username" style="font-family:sans serif;"/></font>
    </td>
	</tr>
    <tr><td><label for="psw">Password</label></td>
    <td><f:input path="password" placeholder="Enter Password"/>
     <font color="red"><f:errors path="password" style="font-family:sans serif;"/></font>
    </td></tr>
    <tr><td colspan="2"><input type="button" value="Submit" onClick="javascript:loadPage();"/></td></tr>
    <tr><td colspan="2"><font style="font-family:sans serif;" color="red">${message}</font></td></tr>
  </table>
</f:form>
 
</div>
<div class="terms1">
  <p align="center" style="font-family: calibri;color: #6666CC;">Copyright © 2018 Accenture All Rights Reserved.</p>
</div>
</body>
</html>
