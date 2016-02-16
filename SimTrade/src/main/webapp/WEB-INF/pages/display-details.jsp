<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script>


function gohome(){

alert("Data entered will be lost")
window.location="http://localhost:8080/sim-details/home"
}
</script>

<form>
<input type="button" onClick="gohome()" value="Take Me Home!">
</form>
<a href="javascript:gohome()"><img src="https://pbs.twimg.com/profile_images/438337000895045633/ZK-2662S_400x400.png" width="60" height="60"></a>
<script>
function saveDetails(){

alert("Sim Form Saved")
window.location="http://localhost:8080/sim-details/home"
}
</script>

<h1>Details Display Page</h1>
<p>userName name is ${sim.userName}
<p>Country name is ${sim.country}
<p>expiryDate name is ${sim.expiryDate}
<p>simType name is ${sim.simType}
<p>phoneNumber name is ${sim.phoneNumber}
<p>plan name is ${sim.plan}
<p>currentStatus name is ${sim.currentStatus}
<p>ownerId name is ${sim.ownerId}
<p>expectedDateChange name is ${sim.expectedDateChange}
<p>rechargeDetails name is ${sim.rechargeDetails}
<p>currentUser name is ${sim.currentUser}
<p>timestamp name is ${sim.timestamp}

<p><a href="/sim-details/sim-form">Submit another message</a><p>
<input type="button" name="confirm" value= "confirm" onClick="saveDetails()"> 

</body>
</html>