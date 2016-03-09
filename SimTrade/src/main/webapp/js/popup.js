//Function to check whether necessary fields are entered while adding a new Sim 
function check_empty() {
	if (document.getElementById('phoneNumber').value == ""
			|| document.getElementById('currentStatus').value == "") {
		alert("Fill All Fields !");
	} else {
		document.getElementById('form').submit();
		alert("Form Submitted Successfully...");

	}
}


//Function to validate whether the user currently has a sim or not

/*This function is called after a sim is dropped */
function drop(ev) {
	ev.preventDefault();
	var data = ev.dataTransfer.getData("text");

	ev.target.appendChild(document.getElementById(data));
	div_show();
}
function drop1(ev) {
	ev.preventDefault();
	var dataset = ev.dataTransfer.getData("text");

	Hello(dataset);

}
/*Drop function ends here */
function allowDrop(ev) {
	ev.preventDefault();
}
/*This function is called when a sim is dragged from it's original position*/
function drag(ev) {
	ev.dataTransfer.setData("text", ev.target.id);
	var data = ev.dataTransfer.getData("text");

	$.ajax({
		type : "POST",
		url : "checkAvailable.html",
		data : data,
		success : function(response) {

		},
		error : function(e) {

		}
	});
}
/*Drag function ends here */

function checkName(ev) {
	
	var data = ev.target.value;

	$.ajax({
		type : "POST",
		url : "checkName.html",
		data : data,
		success : function(response) {

			$.get("http://localhost:8080/replyname", function(data, status) {
			
				if (data == 'exists')
					alert("pls return ur old Sim");
			
			});
		},
		error : function(e) {

		}
	});
}
// Function to check whether Sims are available in a particular country or not
function Hello(dataset) {
	var datas;

	$.get("http://localhost:8080/replyavailable", function(data, status) {

		datas = data;
		if (data == 'available')
			div_show1(dataset);
		else
			alert("NO SIMS AVAILABLE FOR " + dataset);
		
	});

}

//Function to validate email address
function validateEmail(ev) {
	
	var data = ev.target.value;
	    var atpos = data.indexOf("@");
	    var dotpos = data.lastIndexOf(".");
	    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
	        alert("Not a valid e-mail address");
	      return false;
	    }
	   	    }

//Function to check whether necessary fields are entered while booking a Sim
function check_empty1() {

	if (document.getElementById('userName').value == ""
			|| document.getElementById('email').value == "") {
		alert("Fill All Fields !");
	} else {
		document.getElementById('form1').submit();

		alert("Form Submitted Successfully");
	}
}
//Function To Display Popup to add a new sim 
function div_show() {
	document.getElementById('depositSim').style.display = "block";
}
//Function To Display Popup to book a sim 
function div_show1(data) {

	document.getElementById('country').value = data;

	document.getElementById('bookSim').style.display = "block";

}

//Function to Hide add a new sim Popup
function div_hide() {
	document.getElementById('depositSim').style.display = "none";
}
//Function to Hide book a sim Popup
function div_hide1() {
	document.getElementById('bookSim').style.display = "none";
}