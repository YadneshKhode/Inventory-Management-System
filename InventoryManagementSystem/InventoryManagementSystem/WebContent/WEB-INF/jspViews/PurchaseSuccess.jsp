<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container">
<jsp:include page="include.jsp" />
<h2 align="center">Material Purchase Details</h2>
</div>
<table width="500" align="center">
	<tr>
	  	<td><label>Vendor name</label></td>
	  	<td></td>
		<td>${purchaseBean.vendorName}</td> 
	</tr>
	<tr>
		<td ><label>Material category</label></td>
		<td></td>
		<td>${purchaseBean.materialCategoryName}</td>
	</tr>
	<tr>
		<td><label>Material Type</label></td>
		<td></td>
		<td>${purchaseBean.materialTypeName}</td>		
	</tr>
	<tr>
		<td><label>Unit</label></td>
		<td></td>
		<td>${purchaseBean.materialUnitName}</td>
	</tr>
	<tr>
		<td><label>Brand name</label></td>
		<td></td>
		<td>${purchaseBean.brandName}</td>
	</tr>
	<tr>
		<td><label>Quantity</label></td>
		<td></td>
		<td>${purchaseBean.quantity}</td>
	</tr>
	<tr>
		<td><label>Purchase Amount(&#8377;)</label></td>
		<td></td>
		<td><fmt:formatNumber type="number" groupingUsed="true" minFractionDigits="2" value="${purchaseBean.purchaseAmount}" /></td>
	</tr>
	
	<tr>
		<td><label>Purchase Date</label></td>
		<td></td>
		<td>
		<fmt:parseDate value="${purchaseBean.purchaseDate}" type="DATE" pattern="yyyy-MM-dd" var="formatedDate"/> 
		<fmt:formatDate value="${formatedDate}" pattern="dd-MMM-yyyy" type="DATE"/></td>
	
	</tr>
	<tr><td><br></td><td></td><td></td></tr>
	<tr><td><br></td><td></td><td></td></tr>
	<tr>
    <td><label>Purchase Id</label></td>
    <!-- <td>&nbsp;&nbsp;&nbsp;</td> -->
    <td>${message}</td>
    </tr>
    <tr><td><br></td><td></td><td></td></tr>
	<tr>
	<td></td>
	<td colspan="2" style="color: green" >Data added successfully!</td>
	</tr>
</table>
<div class="terms1">
  <p align="center" style="font-family: calibri;color: #6666CC;">Copyright © 2018 Accenture All Rights Reserved.</p>
</div>
</body>
</html>