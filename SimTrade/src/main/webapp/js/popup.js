//Function to check whether necessary fields are entered while adding a new Sim 
function check_empty() {
	if (document.getElementById('phoneNumber').value == ""
			|| document.getElementById('currentStatus').value == ""
	||	 document.getElementById('expiryDate').value == ""		
	
	) {
		alert("Fill All Fields !");
	} else {
		document.getElementById('form').submit();
		alert("Form Submitted Successfully...");

	}
}

// Function to validate whether the user currently has a sim or not

/* This function is called after a sim is dropped */
function drop(ev) {
	ev.preventDefault();
	var data = ev.dataTransfer.getData("text");

	ev.target.appendChild(document.getElementById(data));
	div_show();
}
function drop1(ev) {
	ev.preventDefault();
	var dataset = ev.dataTransfer.getData("text");

	$.ajax({
		type : "POST",
		url : "checkAvailable",
		data : dataset,
		success : function(response) {
			if (response == 'available')
				div_show1(dataset);
			else
				alert1("NO SIMS AVAILABLE FOR " + dataset);
		},
		error : function(e) {

		}
	});
	/*
	 * Hello(dataset);
	 */
}
/* Drop function ends here */
function allowDrop(ev) {
	ev.preventDefault();
}
/* This function is called when a sim is dragged from it's original position */
function drag(ev) {
	ev.dataTransfer.setData("text", ev.target.id);
	var data = ev.dataTransfer.getData("text");

}
/* Drag function ends here */

function checkName(ev) {

	var data = ev.target.value;

	$.ajax({
		type : "POST",
		url : "checkName",
		data : data,
		success : function(response) {
			if (response == 'exists') {

				alert1("pls return your old Sim");

			}

		},
		error : function(e) {

		}
	});
}

// Function to validate email address
/*
 * function validateEmail() {
 * 
 * var data = document.getElementById('email').value; var atpos =
 * data.indexOf("@"); var dotpos = data.lastIndexOf(".");
 * 
 * if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) { alert("Not a valid
 * e-mail ");
 * 
 * return false; } return true; }
 */

// Function to check whether necessary fields are entered while booking a Sim
function check_empty1() {
	var data = document.getElementById('email').value;
	var atpos = data.indexOf("@");
	var dotpos = data.lastIndexOf(".");

	if (document.getElementById('userName').value == ""
			|| document.getElementById('email').value == "") {
		alert("Fill All Fields correctly!");
	} else {
		if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= data.length) {
			alert("Not a valid Email ");
		} else {

			document.getElementById('form1').submit();
			alert("Form Submitted Successfully...");
		}
	}
}
// Function To Display Popup to add a new sim
function div_show() {
	document.getElementById('depositSim').style.display = "block";
}
// Function To Display Popup to book a sim
function div_show1(data) {

	document.getElementById('country').value = data;
	
	alert(document.getElementById('country').value);
	document.getElementById('bookSim').style.display = "block";

}

// Function to Hide add a new sim Popup
function div_hide() {
	document.getElementById('depositSim').style.display = "none";
}
// Function to Hide book a sim Popup
function div_hide1() {
	document.getElementById('bookSim').style.display = "none";
}