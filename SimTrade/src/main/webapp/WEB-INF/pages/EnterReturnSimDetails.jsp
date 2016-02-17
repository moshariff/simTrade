<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sim Details Page</title>
</head>
<script>
	function checkForm() {

		var x = document.forms["user-returning"]["name"].value;
		var y = document.forms["user-returning"]["simId"].value;
		var z = document.forms["user-returning"]["empPhoneNumber"].value;
		var m = document.forms["user-returning"]["empId"].value

		if (x == null || x == "") {
			alert("* fields are mandatory");

			return false;
		}

		else if (y == null || y == "") {
			alert("* fields are mandatory");
			return false;
		} else if (z == null || z == "") {
			alert("* fields are mandatory");
			return false;
		}

		else if (m == null || m == "") {
			alert("* fields are mandatory");
			return false;
		}
	}
	function setValue() {
		document.getElementById("dropdown").value = document
				.getElementById("colour").value;
		document.productForm.submit();
		return true;
	}
</script>
<body>
	<h1>Enter Details</h1>
	<p>Returning user information</p>
	<form:form commandName="user-returning" action="deposited"
		onsubmit="return checkForm()" method="POST">
		<TABLE cellpadding="15" border="1">

			<tr>
				<td><form:label path="userName">userName*:</form:label></td>
				<td><form:input type="text" path="userName" /></td>
			</tr>
			<tr>
			<tr>
				<td><form:label path="country">country:</form:label></td>

				<td><form:select path="country">
						<option value="Select">Select
						<option value="India">India
						<option value="US">US
						<option value="Canada">Canada
					</form:select> <input type="hidden" name="dropdown" id="dropdown"></td>
			</tr>

			<tr>
				<td><form:label path="expiryDate">expiryDate*:</form:label></td>
				<td><form:input type="text" path="expiryDate" /></td>
			</tr>




			<tr>
				<td colspan="2"><input type="submit" value="Submit"></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</form:form>
</body>
</html>