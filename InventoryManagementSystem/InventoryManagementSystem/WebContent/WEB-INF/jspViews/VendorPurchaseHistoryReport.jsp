<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="jstlcore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.accenture.lkm.business.bean.PaymentReportBeanDateWise"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/datetimepicker.js"></script>
<title>Vendor - Purchase History</title>
<script type="text/javascript">
function getPurchaseIdList(id){    
	document.forms["form1"].action="getPurchaseIdList.html";
    document.forms["form1"].method="post";
	document.forms["form1"].submit();
}
	function callSubmit() {
		var fromDate = document.getElementById("fromdate").value;
	    var toDate = document.getElementById("todate").value;

	    if ((Date.parse(fromDate) > Date.parse(toDate))) {
	    	 alert("To date should be greater than or equal to From date");
	        document.getElementById("todate").value = "";
	        return;
	    }
		document.forms["form1"].action = "getDateWiseVendorPurchaseReport.html";
		document.forms["form1"].submit();		
	}
	
	function displayPurchaseDetails() {
		
		document.forms["form1"].action="loadMaterialDetails.html";
	    document.forms["form1"].method="post";
	    document.forms["form1"].submit();
	}
	
	function showHide() {
		if(document.getElementById("tid").value == ""){
			document.getElementById("show").style.display = "none";
		}else{
			document.getElementById("show").style.display = "block";
		}
	}
</script>

</head>
<body onload="showHide();">
	<div class="container">
		<jsp:include page="include.jsp" />
		<h2 align="center">Vendor - Purchase History</h2>
		<f:form name="form1" modelAttribute="dateWiseVendorPurchaseReportBean"  >

           
			<table width="800px" align="center">
			
			   <tr>
		         <td><label>Vendor:<font color="red">*</font></label></td>
	              <td><f:select path="vendorName" id="vendname" onchange="getPurchaseIdList(vendname.value)">
		          <f:option label="--Select--" value=""/>
			        <f:options items="${vendorList}"  itemValue="vendorName" itemLabel="vendorName"/>
		          </f:select>
		          <font color="red"><f:errors path="vendorName" style="font-family:sans serif;" />
		          </td>
		          <td></td>
		          <td></td>
		          <td><label>Purchase Id:<font color="red">*</font></label></td>
		             <td><f:select path="transactionId" id="tid" onchange="displayPurchaseDetails();">
	               	<f:option label="--Select--" value=""/>
		         	<f:options items="${purchaseIdList}" itemValue="transactionId" itemLabel="transactionId"/>
		            </f:select>
		            <font color="red"><f:errors path="transactionId" style="font-family:sans serif;" />
		            </td>	
             	</tr>
             	
             	
             	
             	
	           	
				<tr>
					<td><b>From Date:</b></td>
					<td><f:input path="fromDate" readonly="true" onclick="javascript:NewCssCal('fromdate')" id="fromdate" /></td>
					<td></td>
		          	<td></td>
					<td><b>To Date:</b></td>
					<td><f:input path="toDate" readonly="true" onclick="javascript:NewCssCal('todate')" id="todate" /></td>
					
				</tr>
				<tr>
					<td colspan="2"><font color="red"><f:errors
								path="fromDate" style="font-family:sans serif;" /></font></td>
					<td colspan="2"><font color="red"><f:errors
								path="toDate" style="font-family:sans serif;" /></font></td>
				</tr>

			</table>
			<table align="center">
			<tr>
				<td>
					<input type="button" value="Search"
						onClick="javascript:callSubmit();" />
				</td>
			</tr>
			</table>
			         
            <table align="center" id="show" width="800px">
			  
                <tr>
		         <td><label align="right">Material Type:</label></td>
		         <td ><f:input path="materialTypeName" value="${dateWiseVendorPurchaseReportBean.materialTypeName}" style="border:none" readonly="true"/></td>
	            
		         <td><label align="right">Material Category:</label></td>
		         <td ><f:input path="materialCategoryName" value="${dateWiseVendorPurchaseReportBean.materialCategoryName}" style="border:none" readonly="true"/></td>
	            
		            <td><label align="right">Brand Name:</label></td>
		            <td ><f:input path="brandName" value="${dateWiseVendorPurchaseReportBean.brandName}" style="border:none" readonly="true"/></td>
	            
		           <td><label align="right">Quantity:</label></td>
		            <td ><f:input path="quantity" value="${dateWiseVendorPurchaseReportBean.quantity}" style="border:none" readonly="true"/></td>
	             </tr>    
             </table>
			<br>

			<jstlcore:if test="${!empty paymentBeanList}">
				<%
				List<PaymentReportBeanDateWise> paymentBeanList  = (ArrayList)session.getAttribute("paymentBeanList");
				%>
			<jstlcore:set var="paymentBeanList" scope="session" value="<%=paymentBeanList%>" />
			<jstlcore:set var="totalCount" scope="session"
				value="<%=paymentBeanList.size()%>" />
			<jstlcore:set var="perPage" scope="session" value="${5}" />
			<jstlcore:set var="pageStart" value="${param.start}" />
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
						<th>Paid Id</th>
						<th>Paid Type</th>
						<th>Cheque No</th>
						<th>Paid Date</th>
						<th>Paid Amount (&#8377;)</th>
						<th>Balance (&#8377;)</th>
						<th>Remarks</th>
					</tr>

					<jstlcore:forEach items="${paymentBeanList}" var="paymentList" begin="${pageStart}" end="${pageStart + perPage - 1}" varStatus="i">
					 <jstlcore:set var = "j" value="${i.index}" />
						<tr>
						    <td align="center">${j+1}</td>
							<td align="center"><jstlcore:out value="${paymentList.paidId}"></jstlcore:out></td>
							<td align="center"><jstlcore:out value="${paymentList.paidType}"></jstlcore:out></td>
							<td align="center"><jstlcore:out value="${paymentList.chequeNo}"></jstlcore:out></td>
							<td align="center">
							<fmt:parseDate value="${paymentList.paidDate}" type="DATE" pattern="yyyy-MM-dd" var="paidDate"/> 
							<fmt:formatDate value="${paidDate}" pattern="dd-MMM-yyyy" type="DATE"/>
							</td>
							<td align="center"><fmt:formatNumber type="number" groupingUsed="true" minFractionDigits="2" value="${paymentList.paidAmount}" /></td>
							<td align="center"><fmt:formatNumber type="number" groupingUsed="true" minFractionDigits="2" value="${paymentList.balance}" /></td>
							<td align="center"><jstlcore:out
									value="${paymentList.remarks}"></jstlcore:out></td>
						</tr>
					</jstlcore:forEach>
				</TABLE>
			</jstlcore:if>
			<h4 align="center" style="color: red">${message}</h4>


		</f:form>

	</div>
<div class="terms2">
  <p align="center" style="font-family: calibri;color: #6666CC;">Copyright � 2018 Accenture All Rights Reserved.</p>
</div>
</body>
</html>