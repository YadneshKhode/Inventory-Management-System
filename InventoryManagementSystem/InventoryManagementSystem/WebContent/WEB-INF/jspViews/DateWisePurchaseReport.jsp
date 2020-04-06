<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="jstlcore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.accenture.lkm.business.bean.PurchaseBean"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" />
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/datetimepicker.js"></script>
<title>Purchase Report - Datewise</title>
<script type="text/javascript">
	function callSubmit() {
		var fromDate = document.getElementById("fromdate").value;
	    var toDate = document.getElementById("todate").value;

	    if ((Date.parse(fromDate) > Date.parse(toDate))) {
	        alert("To date should be greater than or equal to From date");
	        document.getElementById("todate").value = "";
	        return;
	    }
		document.forms["form1"].action = "getDateWisePurchaseDetail.html";
		document.forms["form1"].submit();		
	}
</script>
</head>
<body>
	<div class="container">
		<jsp:include page="include.jsp" />
		<h2 align="center">Purchase Report - Datewise</h2>
	</div>


	<f:form name="form1" modelAttribute="dateWisePurchaseReportBean">

		<table width="800px" align="center">
			<tr>
				<td><b>From Date:<font color="red">*</font></b></td>
				<td><f:input path="fromDate" readonly="true" onclick="javascript:NewCssCal('fromdate')" id="fromdate" /></td>
				<td><b>To Date:<font color="red">*</font></b></td>
				<td><f:input path="toDate" readonly="true" onclick="javascript:NewCssCal('todate')" id="todate" /></td>
				<td>
				<td><input type="button" value="Search"
					onClick="javascript:callSubmit();" /></td>
			</tr>
			<tr>
				<td colspan="2"><font color="red"><f:errors
							path="fromDate" style="font-family:sans serif;" /></font></td>
				<td colspan="2"><font color="red"><f:errors
							path="toDate" style="font-family:sans serif;" /></font></td>
			</tr>
		</table>
	</f:form>
		
		<jstlcore:if test="${!empty purchaseBeanList}">
			<h4 align="center">
				Materials purchased between <font color="green">
				<fmt:parseDate value="${dateWisePurchaseReportBean.fromDate}" type="DATE" pattern="yyyy-MM-dd" var="fDate"/> 
				<fmt:formatDate value="${fDate}" pattern="dd-MMM-yyyy" type="DATE"/>				
				</font> and <font color="green">
				<fmt:parseDate value="${dateWisePurchaseReportBean.toDate}" type="DATE" pattern="yyyy-MM-dd" var="tDate"/> 
				<fmt:formatDate value="${tDate}" pattern="dd-MMM-yyyy" type="DATE"/>
				</font>
			</h4>
			
				<%
				List<PurchaseBean> purchaseBeanList  = (ArrayList)session.getAttribute("purchaseBeanList");
				%>				
			<jstlcore:set var="purchaseBeanList" scope="session" value="<%=purchaseBeanList%>" />
			<jstlcore:set var="totalCount" scope="session"
				value="<%=purchaseBeanList.size()%>" />
			<jstlcore:set var="perPage" scope="session" value="${5}" />
			<jstlcore:set var="pageStart" value="${param.start}" />/
			<jstlcore:if test="${empty pageStart or pageStart < 0}">
				<jstlcore:set var="pageStart" value="0" />
			</jstlcore:if>
			<jstlcore:choose>
			<jstlcore:when test="${totalCount <= perPage}">
				<jstlcore:set var="pageStart" value="0" />
			</jstlcore:when>
			<jstlcore:when test="${totalCount == pageStart + perPage}">
				<jstlcore:set var="pageStart" value="${pageStart - perPage}" />
			</jstlcore:when>
			<jstlcore:when test="${totalCount < pageStart}">
				<jstlcore:set var="pageStart" value="${pageStart - perPage}" />
			</jstlcore:when>
			</jstlcore:choose>			
			<div class="container" align="right">
			<a href="?start=${pageStart - perPage}" class="next">&laquo;</a>
			${pageStart + 1} - 
			<jstlcore:choose>
			<jstlcore:when test="${totalCount < perPage}">
				${totalCount}
			</jstlcore:when>
			<jstlcore:when test="${totalCount < perPage + pageStart}">
				${totalCount}
			</jstlcore:when>
			<jstlcore:otherwise>
				${pageStart + perPage} 
			</jstlcore:otherwise>
			</jstlcore:choose>
			
			
    		<a href="?start=${pageStart + perPage}"  class="next">&raquo;</a>
    		</div>
			<TABLE id="dataTable" border="1" class="container">
				<tr class="tr1">
					<th>S.No</th>
					<th>Material Category</th>
					<th>Material Type</th>
					<th>Brand</th>
					<th>Quantity</th>
					<th>Unit</th>
					<th>Price (&#8377;)</th>
					<th>Status</th>
					<th>Vendor</th>
				</tr>
				<% int i = 0; %>
				<jstlcore:forEach items="${purchaseBeanList}" var="purchaselist"
					begin="${pageStart}" end="${pageStart + perPage - 1}" varStatus="i">
					<jstlcore:set var="j" value="${i.index}" />
					<tr>
						<td align="center">${j+1}</td>
						<td align="center"><jstlcore:out
								value="${purchaselist.materialCategoryName}"></jstlcore:out></td>
						<td align="center"><jstlcore:out value="${purchaselist.materialTypeName}"></jstlcore:out></td>
						<td align="center"><jstlcore:out value="${purchaselist.brandName}"></jstlcore:out></td>
						<td align="center"><jstlcore:out value="${purchaselist.quantity}"></jstlcore:out></td>
						<td align="center"><jstlcore:out
								value="${purchaselist.materialUnitName}"></jstlcore:out></td>
						<td align="center"><fmt:formatNumber type="number" groupingUsed="true" minFractionDigits="2" value="${purchaselist.purchaseAmount}" /></td>
						<td align="center"><jstlcore:out
								value="${purchaselist.status}"></jstlcore:out></td>
						<td align="center"><jstlcore:out
								value="${purchaselist.vendorName}"></jstlcore:out></td>
					</tr>
				</jstlcore:forEach>
			</TABLE>
		</jstlcore:if>
		<h4 align="center" style="color: red">${message}</h4>
<div class="terms1">
  <p align="center" style="font-family: calibri;color: #6666CC;">Copyright © 2018 Accenture All Rights Reserved.</p>
</div>
</body>
</html>