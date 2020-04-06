<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" />
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/datetimepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/addDeleteRow.js"></script>
<title>Payment Tracker</title>
</head>
<body onload="callFun();">
	<div class="container">
		<jsp:include page="include.jsp" />
		<h2 align="center">Material Payment Tracker</h2>
	</div>
	<f:form name="form1" modelAttribute="beanForPaymentTracking" >
		<table class="bodycontainer">
			<tr>
				<td><label>Vendor Name</label></td>
				<td colspan="2"><f:select path="vendorName" id="vendorName"
						onchange="getPurchaseDetails()">
						<f:option label="--Select--" value="" />
						<f:options items="${vendorList}" itemValue="vendorName"
							itemLabel="vendorName" />
					</f:select></td>
			</tr>

			<tr>
				<td><label>Purchase Id</label></td>
				<td colspan="2"><f:select path="purchaseId" id="purchaseId"
						onchange="displayPurchaseDetails()">
						<f:option label="--Select--" value="" />
						<f:options items="${purchaseList}" itemValue="purchaseId"
							itemLabel="transactionId" />
					</f:select></td>
			</tr>
			</table>
			<table class="bodycontainer" id="showHide">
			<tr>			
				<td><label>Material Type</label></td>
				<td colspan="2"><f:input path="materialTypeName"
						value="${bean.materialTypeName}" style="border:none"
						readonly="true" /></td>
			
			</tr>
			<tr id="showHide">
				<td><label>Brand Name</label></td>
				<td colspan="2"><f:input path="brandName"
						value="${bean.brandName}" style="border:none" readonly="true" /></td>
			</tr>
			<tr id="showHide">
				<td><label>Quantity</label></td>
				<td colspan="2"><f:input path="quantity"
						value="${bean.quantity}" style="border:none" readonly="true" /></td>
			</tr>
			<tr id="showHide">
				<td><label>Purchase Amount (&#8377;)</label></td>
				<td colspan="2"><f:input path="purchaseAmount"
						value="${bean.purchaseAmount}" style="border:none" readonly="true" /></td>
			</tr>
			<tr id="showHide">
				<td><label>Balance (&#8377;)</label></td>
				<td><f:input path="balance" value="${bean.balance}" id="bal"
						style="border:none" readonly="true" /></td>
				<td><f:input path="status" value="${bean.status}"
						style="border:none" readonly="true" /></td>
			</tr>
			
		</table>
	</f:form>

	<f:form name="form2" modelAttribute="paymentCollectionBean">
		<c:if test="${!empty paymentCollectionBean.paymentBeans}">
			<TABLE class="table1" id="dataTable">
				<tr>
					<th class="th1">Select</th>
					<th class="th1">Paid ID</th>
					<th class="th1">Paid Type</th>
					<th class="th1">Cheque No</th>
					<th class="th1">Paid Date</th>
					<th class="th1">Paid Amount (&#8377;)</th>
					<th class="th1">Remarks</th>
				</tr>
				<c:forEach items="${paymentCollectionBean.paymentBeans}"
					var="paymentBean" varStatus="i">
					<c:if test="${paymentBean.enabled.toString() eq 'Y'}">
						<tr>
							<td style="text-align: center; border: none"><input
								type="checkbox" name="name" value="" id="chckOne"></td>
							<td style="border: none"><f:input
									path="paymentBeans[${i.index}].paidId" id="paidId"
									readonly="true" /></td>
							<td style="border: none"><f:select
									path="paymentBeans[${i.index}].paidType" id="paidType" onchange="disableChequeNo('dataTable');">
									<f:option label="Select" value=""/>
									<f:option label="Cash" value="Cash"/>
									<f:option label="Cheque" value="Cheque"/>
								</f:select> <f:input path="paymentBeans[${i.index}].paidType" id="paidType"
									style="display:none;" /> <font color="red"><f:errors
										path="paymentBeans[${i.index}].paidType"
										style="font-family:sans serif;" /></font></td>
							<td style="border: none"><f:input
									path="paymentBeans[${i.index}].chequeNo" id="chequeNo" /> <font
								color="red"><f:errors
										path="paymentBeans[${i.index}].chequeNo"
										style="font-family:sans serif;" /></font></td>
							<td style="border: none"><f:input
									path="paymentBeans[${i.index}].paidDate" id="paidDate"
									readonly="true" onclick="javascript:changeDate('dataTable')"/> <font color="red"><f:errors
										path="paymentBeans[${i.index}].paidDate"
										style="font-family:sans serif;" /></font></td>
							<td style="border: none"><f:input
									path="paymentBeans[${i.index}].paidAmount" id="paidAmount" />
								<font color="red"><f:errors
										path="paymentBeans[${i.index}].paidAmount"
										style="font-family:sans serif;" /></font></td>
							<td style="border: none"><f:input
									path="paymentBeans[${i.index}].remarks" id="remarks" /> <font
								color="red"><f:errors
										path="paymentBeans[${i.index}].remarks"
										style="font-family:sans serif;" /></font></td>
							<td style="border: none"><f:hidden
									path="paymentBeans[${i.index}].enabled" id="enabled" /></td>
							<td style="border: none"><f:hidden
									path="paymentBeans[${i.index}].checked" id="checked" /></td>
						</tr>
					</c:if>
					<c:if test="${paymentBean.enabled.toString() eq 'N'}">
						<tr>
							<td style="text-align: center;"><input type="checkbox"
								name="name" value="" id="chckOne"></td>
							<td><f:input path="paymentBeans[${i.index}].paidId"
									id="paidId" readonly="true" /></td>
							<td><f:select path="paymentBeans[${i.index}].paidType"
									id="paidType" style="display:none" onchange="disableChequeNo('dataTable');">
									<f:option label="Select" value=""/>
									<f:option label="Cash" value="Cash"/>
									<f:option label="Cheque" value="Cheque"/>
								</f:select> <f:input path="paymentBeans[${i.index}].paidType" id="paidType"
									readonly="true" /> <font color="red"><f:errors
										path="paymentBeans[${i.index}].paidType"
										style="font-family:sans serif;" /></font></td>
							<td><f:input path="paymentBeans[${i.index}].chequeNo"
									id="chequeNo" readonly="true" /> <font color="red"><f:errors
										path="paymentBeans[${i.index}].chequeNo"
										style="font-family:sans serif;" /></font></td>
							<td><f:input path="paymentBeans[${i.index}].paidDate"
									id="paidDate" readonly="true" /> <font color="red"><f:errors
										path="paymentBeans[${i.index}].paidDate"
										style="font-family:sans serif;" /></font></td>
							<td><f:input path="paymentBeans[${i.index}].paidAmount"
									id="paidAmount" readonly="true" /> <font color="red"><f:errors
										path="paymentBeans[${i.index}].paidAmount"
										style="font-family:sans serif;" /></font></td>
							<td><f:input path="paymentBeans[${i.index}].remarks"
									id="remarks" readonly="true" /> <font color="red"><f:errors
										path="paymentBeans[${i.index}].remarks"
										style="font-family:sans serif;" /></font></td>
							<td><f:hidden path="paymentBeans[${i.index}].enabled"
									id="enabled" /></td>
							<td><f:hidden path="paymentBeans[${i.index}].checked"
									id="checked" /></td>
						</tr>
					</c:if>
				</c:forEach>
			</TABLE>
			<br>
		</c:if>
		<div class="container" id="msg" align="center">
			<font color="green">${message}</font>
		</div>
		<table class="bodycontainer" id="btnShowHide">
			<tr>
				<td><input type="button" value="Add Pay" id="addbtn"
					onclick="addPaymentRow('dataTable')" class="button"></td>
				<td><input type="button" value="Delete Row" id="deletebtn"
					onclick="deletePaymentRow('dataTable')"></td>
				<td><input type="button" id="edit_button" value="Edit"
					class="edit" onclick="editPaymentRow('dataTable')"></td>
				<td><input type="button" id="save_button" value="Save"
					class="save" onclick="save_row('dataTable')"></td>
			</tr>
		</table>
	</f:form>

<div class="terms2">
  <p align="center" style="font-family: calibri;color: #6666CC;">Copyright © 2018 Accenture All Rights Reserved.</p>
</div>
</body>
</html>