<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/datetimepicker.js"></script>
<title>Purchase Entry</title>
<script type="text/javascript">
/* function quantityValidate(){
	var qty=document.getElementById("quantity").value;
	if(isNaN(qty)){
		alert("Please enter only numbers for Quantity.");
		return;
	}
}
function purchaseAmountValidate(){
	
	var qty=document.getElementById("purchaseAmount").value;
	 var regex = /^\d+(?:\.\d{1,2})?$/;
	 if (!regex.test(qty)) {
		 alert('Please enter only numbers with two decimal places for Purchase Amount.');
			return;
    } 	
} */
function getUnitAndTypeList(id){    
	document.forms["form1"].action="getUnitAndTypeList.html";
    document.forms["form1"].method="post";
	document.forms["form1"].submit();
}
function addPurchaseEntry() {
	
var qty=document.getElementById("quantity").value;
if(! (qty===undefined) && (qty != "")){
	if(isNaN(qty)){
		alert("Please enter only numbers for Quantity.");
		return;
	}
}	
	var amt=document.getElementById("purchaseAmount").value;
	 var regex = /^\d+(?:\.\d{1,2})?$/;
	 if(! (amt===undefined) && (amt != "")){
	 if (!regex.test(amt)) {
		 alert('Please enter only numbers with two decimal places for Purchase Amount.');
			return;
   		} 	
	 }
	var e = document.getElementById("category");
	var categoryName = e.options[e.selectedIndex].text;	
	document.getElementById("mcName").value= categoryName;
	
	var e1 = document.getElementById("type");
	var materialTypeName = e1.options[e1.selectedIndex].text;	
	document.getElementById("typeName").value= materialTypeName;
	
	var e2 = document.getElementById("unit");
	var unitName = e2.options[e2.selectedIndex].text;	
	document.getElementById("unitName").value= unitName;
	
	document.forms["form2"].action="addPurchaseDetail.html";
	document.forms["form1"].submit();
	document.forms["form2"].submit();
}
function displayPageElements(){
	if(document.getElementById("category").value == ""){
		
	document.getElementById("belowTables").style.display = "none";
	}else{
		
		document.getElementById("belowTables").style.display = "block";
	}
}
</script>
</head>
<body onload="displayPageElements();" >
<div class="container">
<jsp:include page="include.jsp" />
<h2 align="center">Material Purchase Entry</h2>

<f:form name="form1" modelAttribute="purchaseBean"> 
<table class="bodycontainer" >
	<tr>
		<td><label>Vendor name</label></td>
		<td><f:select path="vendorName" id="vendorName">
		<f:option label="--Select--" value="" />
			<f:options items="${vendorList}"  itemValue="vendorName" itemLabel="vendorName"/>
		</f:select>
		<font color="red"><f:errors
								path="vendorName" style="font-family:sans serif;"/></font></td>
	</tr>

	<tr>
		<td><label>Material category</label></td>
		<td><f:select path="materialCategoryId" id="category" 
		onchange="getUnitAndTypeList(category.value)">
		<f:option label="--Select--" value=""/>
			<f:options items="${categoryList}" itemValue="categoryId" itemLabel="categoryName"/>
		</f:select>
		<font color="red"><f:errors
								path="materialCategoryId" style="font-family:sans serif;" /></font></td>
	</tr>
	
	</table>
	</f:form>
	
	<f:form name="form2" modelAttribute="purchaseBean" action="addPurchase.htm" method="POST">
	<table class="bodycontainer" id="belowTables">
	<tr>
		<td><label>Material Type</label></td>
		<td><f:select path="materialTypeId" id="type">
		<f:option label="--Select--" value=""/>
			<f:options items="${materialTypeList}" itemValue="typeId" itemLabel="typeName"/>
		</f:select>
		<font color="red"><f:errors
								path="materialTypeId" style="font-family:sans serif;" /></font></td>		
	</tr>
	<tr>
		<td><label>Unit</label></td>
		<td><f:select path="unitId" id="unit">
		<f:option label="--Select--" value=""/>
			<f:options items="${unitList}" itemValue="unitId" itemLabel="unitName"/>
		</f:select>
		<font color="red"><f:errors
								path="unitId" style="font-family:sans serif;" /></font></td>
	</tr>
	<tr>
	<f:hidden path="materialCategoryName" id="mcName"/>
	<f:hidden path="materialTypeName" id="typeName"/>
	<f:hidden path="materialUnitName" id="unitName"/>
		<td><label>Brand name</label></td>
		<td><f:input path="brandName"/>
		<font color="red"><f:errors
								path="brandName" style="font-family:sans serif;" /></font></td>
	</tr>
	<tr>
		<td><label>Quantity</label></td>
		<td><f:input path="quantity" />
		<font color="red"><f:errors
								path="quantity" style="font-family:sans serif;" /></font></td>
	</tr>
	<tr>
		<td><label>Purchase Amount (&#8377;)</label></td>
		<td><f:input path="purchaseAmount" />
		<font color="red"><f:errors
								path="purchaseAmount" style="font-family:sans serif;" /></font></td>
	</tr>
	<tr>
		<td><label>Purchase Date</label></td>
		<td><f:input path="purchaseDate" id="purchaseDate"  readonly="true" onclick="javascript:NewCssCal('purchaseDate')"/>
		<!-- <img src="cal.gif" onclick="javascript:NewCssCal('purchaseDate')" style="cursor:pointer"/> -->
		<font color="red"><f:errors
								path="purchaseDate" style="font-family:sans serif;" /></font></td>		
	</tr>
	<tr>
		<td colspan="2" align="center">
		<input type="button" value="Submit" onClick="javascript:addPurchaseEntry();"/></td>
    </tr>	
    <tr>
    <td>${message}</td>
    </tr>
</table>
</f:form>
</div>
<div class="terms2">
  <p align="center" style="font-family: calibri;color: #6666CC;">Copyright © 2018 Accenture All Rights Reserved.</p>
</div>
</body>
</html>