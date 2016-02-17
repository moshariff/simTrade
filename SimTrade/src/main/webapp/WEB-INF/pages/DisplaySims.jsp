<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	function submitForm() {
		document.getElementById("envselection").submit();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sim Details</title>
</head>
<body>
	<script>
		function gohome() {

			alert("Data entered will be lost")
			window.location = "http://localhost:8080/home"
		}
	</script>

	<form>
		<input type="button" onClick="gohome()" value="Take Me Home!">
	</form>
	<a href="javascript:gohome()"><img
		src="https://pbs.twimg.com/profile_images/438337000895045633/ZK-2662S_400x400.png"
		width="60" height="60"></a>

	<h1>Sim Details</h1>


	<form id="envselection" action="booked" method="post"
		onsubmit="submitForm()">
		<table border="2">

			<tr>
				<td>Sim ID</td>
				<td>User Name</td>
				<td>Country</td>
				<td>Expiry Date</td>
				<td>Sim Type</td>
				<td>phone Number</td>
				<td>plan</td>
				<td>currentStatus</td>
				<td>ownerId</td>
				<td>expectedDateChange</td>
				<td>rechargeDetails</td>
				<td>currentUser</td>
				<td>timestamp</td>


			</tr>




			<c:forEach items="${simDetails}" var="entry" varStatus="count">

				<tr>
					<td>${entry.simId}</td>
					<td>${entry.userName}</td>
					<td>${entry.country}</td>
					<td>${entry.expiryDate}</td>
					<td>${entry.simType}</td>
					<td>${entry.phoneNumber}</td>
					<td>${entry.plan}</td>
					<td>${entry.currentStatus}</td>
					<td>${entry.ownerId}</td>
					<td>${entry.expectedDateChange}</td>
					<td>${entry.rechargeDetails}</td>
					<td>${entry.currentUser}</td>
					<td>${entry.timestamp}</td>
					<c:choose>
						<c:when test="${entry.currentStatus=='passive'}">
							<td><input type="radio" name="env" id="radioSelection"
								value="${entry.userName}_${entry.country}" disabled="disabled"></td>

						</c:when>
						<c:otherwise>

							<td><input type="radio" name="env" id="radioSelection"
								value="${entry.userName}_${entry.country}"></td>

						</c:otherwise>
					</c:choose>


				</tr>
			</c:forEach>
			<tr>
				<td><br> <input type="submit" name="confirmButton"
					value="Confirm" style="height: 50px; width: 100px"></input></td>
			</tr>
		</table>



	</form>


</body>
</html>