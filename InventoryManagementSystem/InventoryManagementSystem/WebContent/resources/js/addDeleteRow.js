function getPurchaseDetails() {
			document.forms["form1"].action="purchaseDetail.html";
		    document.forms["form1"].method="post";
			document.forms["form1"].submit();
		}
		function displayPurchaseDetails() {			
			document.forms["form1"].action="getPaymentDetails.html";
		    document.forms["form1"].method="post";
		    document.forms["form1"].submit();
		}
		function callFun(){
			
			if(document.getElementById("purchaseId").value == ""){
				document.getElementById("showHide").style.display = "none";
				document.getElementById("btnShowHide").style.display = "none";
			}else{
				document.getElementById("showHide").style.display = "block";
				document.getElementById("btnShowHide").style.display = "block";
			}
			
			
		}
		function disableChequeNo(tableID){
			var table = document.getElementById(tableID);
			var rowCount = table.rows.length;	
			var x = 0;
			for (var i = 1; i < rowCount; i++) {
				var row = table.rows[i];
				x = (i-1)*10+2;	
				if(row.children[7].firstElementChild.defaultValue == 'Y'){					
					if(row.children[2].children[0].form[x].value != "Cheque"){
						row.children[3].childNodes[0].readOnly = 'true';						
					}else{
						row.children[3].firstElementChild.removeAttribute('readonly');						
					}
				}				
			}			
		}
		function addPaymentRow(tableID) {	
			if(document.getElementById("vendorName").value == "" || document.getElementById("purchaseId").value == ""){
				alert("Either Vendor Name or Purchase Id is not selected. Please select both to proceed further.");
				return false;
			}
			var table = document.getElementById(tableID);
			if(table != null){
			var rowCount = table.rows.length;
			
			for (var i = 1; i < rowCount; i++) {
				var row = table.rows[i];
				if(row.children[1].firstElementChild.defaultValue != ''){
					row.children[2].children[0].setAttribute("disabled","true");
				}else{
					row.children[2].children[1].setAttribute("disabled","true");
				}
				}	
			}
			
			document.forms["form2"].action="addRow.html";
		    document.forms["form2"].method="post";
		    document.forms["form2"].submit();
		}	
		
		function save_row(tableID)
		{
			var table = document.getElementById(tableID);
			var rowCount = table.rows.length;
			var count = 0;
			var totalAmt = 0.0;
			var balance = document.getElementById('bal').value;
			var x = 0;
			for (var i = 1; i < rowCount; i++) {
				var oldPaidAmt = 0;
				var row = table.rows[i];
				var chkbox = row.cells[0].childNodes[0];
				if (null != chkbox && true == chkbox.checked) {
					if(row.children[7].firstElementChild.defaultValue != 'Y'){	
						alert("The data in row " + i + " is already saved.");
						return false;
					}
					count = count + 1;
					if(row.children[1].firstElementChild.defaultValue != ''){
						oldPaidAmt = row.children[5].firstElementChild.defaultValue;
					}
					x = (i-1)*10+6;				
					totalAmt = Number(totalAmt)+(Number(row.children[5].firstChild.form[x].value) - Number(oldPaidAmt));					
				}				
			}
			
			if(count<=0){
				alert("Please select atleast one row to save the data.");
				return;
			}
			
			if(balance <= 0){
				var val= confirm("You have already paid your balance. Do you want to record the overpaid amount?");
				if (val == true) {
					callSave(tableID);
			    } else {
			       return;
			    }
			}else if(Number(totalAmt) > Number(balance)){
				var returnVal= confirm("You are paying amount greater than the balance amount. Do you want to proceed?");
				if (returnVal == true) {
					callSave(tableID);
			    } else {
			       return;
			    }
			}else{
				callSave(tableID);
			}
		}
		
		function callSave(tableID){
			var table = document.getElementById(tableID);
			var rowCount = table.rows.length;
			var totalAmt = 0.0;
			var balance = document.getElementById('bal').value;
			for (var i = 1; i < rowCount; i++) {
				var row = table.rows[i];
				var chkbox = row.cells[0].childNodes[0];
				if (null != chkbox && true == chkbox.checked) {						
					row.children[8].firstElementChild.defaultValue='Y';		
					if(row.children[1].firstElementChild.defaultValue != ''){	
						row.children[2].children[1].setAttribute("disabled","true");
					}else{
						row.children[2].children[1].setAttribute("disabled","true");
					}
				}else{
					if(row.children[1].firstElementChild.defaultValue != ''){	
						if(row.children[2].children[1].getAttribute('disabled') != "true"){
							row.children[2].children[0].setAttribute("disabled","true");
						}
					}else{
						row.children[2].children[1].setAttribute("disabled","true");
					}
				}				
			}
			
			document.forms["form2"].action="saveRow.html";
		    document.forms["form2"].method="post";
		    document.forms["form2"].submit();
			
		}
		
		function deletePaymentRow(tableID) {	
			var table = document.getElementById(tableID);
			var rowCount = table.rows.length;
			var count = 0;
			for (var i = 1; i < rowCount; i++) {
				var row = table.rows[i];
				var chkbox = row.cells[0].childNodes[0];
				if (null != chkbox && true == chkbox.checked) {
					count = count + 1;
					if(row.children[1].firstElementChild.defaultValue != ''){						
						alert("You can not delete already paid data.");
						return;
					} else{
						row.children[8].firstElementChild.defaultValue='Y';
					}
					
				}else{
					if(row.children[1].firstElementChild.defaultValue != ''){
						row.children[2].children[0].setAttribute("disabled","true");
					}else{
						row.children[2].children[1].setAttribute("disabled","true");
					}
				}		
			}
			
			
			if(count > 0){
				document.forms["form2"].action="deletePaymentRow.html";
			    document.forms["form2"].method="post";
			    document.forms["form2"].submit();
			}else{
				alert("Please select atleast one row for deletion.");
				return;
			}
		}
		
		function editPaymentRow(tableID) {	
			document.getElementById("msg").style.display = "none";
			var table = document.getElementById(tableID);
			var rowCount = table.rows.length;
			var count = 0;
			for (var i = 1; i < rowCount; i++) {
				var row = table.rows[i];
				var chkbox = row.cells[0].childNodes[0];
				if (null != chkbox && true == chkbox.checked) {
					
					count = count + 1;	
					row.children[2].firstElementChild.setAttribute("style","display:inline-block;");
					row.children[2].children[1].setAttribute("style","display:none;");
					row.children[2].children[1].setAttribute("disabled","true");
					row.children[3].firstElementChild.removeAttribute('readonly');
					/*row.children[4].firstElementChild.removeAttribute('readonly');*/
					row.children[5].firstElementChild.removeAttribute('readonly');
					row.children[6].firstElementChild.removeAttribute('readonly');
					row.children[7].firstElementChild.defaultValue = 'Y';
				}else{
					if(row.children[7].firstElementChild.defaultValue != 'Y'){	
						row.children[2].children[0].setAttribute("disabled","true");
					}else{
						row.children[2].children[1].setAttribute("disabled","true");
					}
				}			
			}	
			if(count > 0){
				document.getElementById("addbtn").disabled = true;
				document.getElementById("deletebtn").disabled = true;
				document.getElementById("edit_button").disabled = true;
				for (var i = 1; i < rowCount; i++) {
					var row = table.rows[i];
					var chkbox = row.cells[0].childNodes[0];					
					row.children[0].firstElementChild.setAttribute("disabled","true");					
				}
			}else{
				alert("Please select atleast one row to edit the data.");
				return;
			}
		}
		