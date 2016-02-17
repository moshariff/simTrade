<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
          "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Sim Resrvation System</title>
</head>
<body>
	<h2>Sim Resrvation System - Reservation form</h2>
	<p>The following sim is selected for reservation</p>
	<form>
		<c:set var="rowno" value="${param.radioButton - 1}" />
		<h3>Sim Details:</h3>
		<table border="1" cellpadding="5">
			<tr>
				<td>userName</td>
				<td>${simDetail.userName}</td>
			</tr>
			<tr>
				<td>country</td>
				<td>${simDetail.country}</td>
			</tr>
			<tr>
				<td>expiryDate</td>
				<td>${simDetail.expiryDate}</td>
			</tr>
			<tr>
				<td>simType</td>
				<td>${simDetail.simType}</td>
			</tr>
		</table>

		<input type="submit" name="confirmButton" value="Confirm"></input>
		<button type="button" name="backtoselectButton">Back to
			Select"</button>
		<button type="button" name="backtosearchButton">Back to
			Search"</button>
	</form>
</body>
</html>
