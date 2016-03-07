function check_empty() {
if (document.getElementById('phoneNumber').value == "" || document.getElementById('currentStatus').value == "")  {
alert("Fill All Fields !");
} else {
document.getElementById('form').submit();
alert("Form Submitted Successfully...");
}
}
function checkName(ev)
{

var data = ev.target.value;



	$.ajax({  
        type : "POST",   
        url : "checkName.html",   
        data :  data,  
         success : function(response) {  
        		
        	 $.get("http://localhost:8080/replyname", function(data, status){
        			if(data=='exists')
        		 alert("pls return ur old Sim");
        			
        			
        			
        	     });
        },  
        error : function(e) {  
            
        }  
        });
	



	
	
	}
function Hello(dataset) {
	var datas;
	
	 $.get("http://localhost:8080/replyavailable", function(data, status){

		datas=data;
		if(data=='available')
		div_show1(dataset);
		else
			alert("NO SIMS AVAILABLE FOR " +dataset);
     });
	
}
function check_empty1() {
	if (document.getElementById('userName').value == "" || document.getElementById('email').value == "" ) {
	alert("Fill All Fields !");
	} else {
	document.getElementById('form1').submit();
	
	alert("Form Submitted Successfully... + value is data");
	}
	}
//Function To Display Popup
function div_show() {
document.getElementById('abc').style.display = "block";
}

function div_show1(data) {
	
	document.getElementById('country').value=data;
	
	document.getElementById('abc1').style.display = "block";

	}


//Function to Hide Popup
function div_hide(){
document.getElementById('abc').style.display = "none";
}
function div_hide1(){
	document.getElementById('abc1').style.display = "none";
	}