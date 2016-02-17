<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
          "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>booked sim details</title>
</head>
<body>
	<script>
		function gohome() {

			alert("Collect Sim")
			window.location = "http://localhost:8080/home"
		}
	</script>

	<form>
		<input type="button" onClick="gohome()" value="Take Me Home!">
	</form>
	<a href="javascript:gohome()"><img
		src="https://pbs.twimg.com/profile_images/438337000895045633/ZK-2662S_400x400.png"
		width="60" height="60"></a>

	<form method="get">

		<h3>Sim Details:</h3>
		<table border="1" cellpadding="5">
			<tr>
				<td>userName</td>
				<td>${envselection.userName}</td>

			</tr>
			<tr>
				<td>country</td>
				<td>${envselection.country}</td>
			</tr>
			<tr>
				<td>phoneNumber</td>
				<td>${envselection.phoneNumber}</td>
			</tr>
			<tr>
				<td>ownerId</td>
				<td>${envselection.ownerId}</td>
			</tr>

		</table>




	</form>
</body>
</html>