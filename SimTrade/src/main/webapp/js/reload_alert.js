var ALERT_TITLE1 = "ERRORS";
var ALERT_BUTTON_TEXT1 = "OK";

if(document.getElementById) {
	window.alert1 = function(txt) {
		createCustomAlert1(txt);
	}
}

function createCustomAlert1(txt) {
	d = document;

	if(d.getElementById("modalContainer")) return;

	mObj = d.getElementsByTagName("body")[0].appendChild(d.createElement("div"));
	mObj.id = "modalContainer";
	mObj.style.height = d.documentElement.scrollHeight + "px";
	
	alertObj = mObj.appendChild(d.createElement("div"));
	alertObj.id = "alertBox";
	if(d.all && !window.opera) alertObj.style.top = document.documentElement.scrollTop + "px";
	alertObj.style.left = (d.documentElement.scrollWidth - alertObj.offsetWidth)/2 + "px";
	alertObj.style.visiblity="visible";

	h1 = alertObj.appendChild(d.createElement("h1"));
	h1.appendChild(d.createTextNode(ALERT_TITLE1));

	msg = alertObj.appendChild(d.createElement("p"));
	//msg.appendChild(d.createTextNode(txt));
	msg.innerHTML = txt;

	btn = alertObj.appendChild(d.createElement("a"));
	btn.id = "closeBtn";
	btn.appendChild(d.createTextNode(ALERT_BUTTON_TEXT1));
	btn.href = "#";
	btn.focus();
	btn.onclick = function() { location.reload(); removeCustomAlert1();return false;  }

	alertObj.style.display = "block";
	
}

function removeCustomAlert1() {
	document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
	
}


