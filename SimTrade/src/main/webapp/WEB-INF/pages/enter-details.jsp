<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sim Details Page</title>

<body>
<script>


function gohome(){

alert("Data entered will be lost")
window.location="http://localhost:8080/
	home"
	}
</script>

<form>
<input type="button" onClick="gohome()" value="Take Me Home!">
</form>
<a href="javascript:gohome()"><img src="https://pbs.twimg.com/profile_images/438337000895045633/ZK-2662S_400x400.png" width="60" height="60"></a>

<script>
function validateForm() {
    var x = document.forms["sim-entity"]["userName"].value;
    var y = document.forms["sim-entity"]["expiryDate"].value;
    var z = document.forms["sim-entity"]["phoneNumber"].value;
    var z1 = document.forms["sim-entity"]["simId"].value;
    if (x == null || x == "") {
        alert("Name must be filled out");
   
        return false;
    }

else if(y == null || y == "")
 {
  alert("Expiry Date must be filled out");
  return false;
 }
else if(z == null || z == "")
{
 alert("phone number must be filled out");
 return false;
}
else if (z1 == null || x == "") {
        alert("simId must be filled out");
   
        return false;
    }
}
</script>
<script>
$(function(){
    $('#date').combodate();    
});
</script>

<h1>Sim Entry Page</h1>  
<p>This is Sim Details page</p>  
<form:form  commandName="sim-entity"  action="display-sim-details" onsubmit="return validateForm()" method="POST" >  
<TABLE cellpadding="15" border="1" bgcolor="yellow"> 
 
   <tr>  
        <td><form:label path="userName">userName*:</form:label></td>  
        <td><form:input type="text" path="userName" /></td>  
    </tr> 
     
  <tr>  
         
            <td><form:label path="country">country*:</form:label></td>  
            <td><form:select path="country" items="${countriesMap}"> 
        </form:select></td>
       
     </tr>  
      <tr>  
        <td><form:label path="simId">simId*:</form:label></td>  
        <td><form:input type="text" path="simId" /></td>  
    </tr> 
   <tr>  
        <td><form:label path="expiryDate" >expiryDate(dd/mm/yyyy)*: </form:label></td>
       <td><form:input type="date" path="expiryDate" /> <input type="submit"></td>
 
  
  


    </tr>  
     <tr>  
        <td><form:label path="simType">simType:</form:label></td>  
      
         <td> <select name="simType" id ="simType">
             
                <option value="prepaid">prepaid</option>
                <option value="postpaid">postpaid</option>
               
            </select>
          </td>
    
    </tr>  
     <tr>  
        <td><form:label path="phoneNumber">phoneNumber*:</form:label></td>  
        <td><form:input type="text" path="phoneNumber"  /></td>  
    </tr>  
     <tr>  
        <td><form:label path="plan">plan:</form:label></td>  
        <td><form:input type="text" path="plan"  /></td>  
    </tr>  
     <tr>  
        <td><form:label path="currentStatus">currentStatus:</form:label></td>  
        <td>Active</td>  
    </tr>  
     <tr>  
        <td><form:label path="ownerId">ownerId:</form:label></td>  
        <td><form:input type="text" path="ownerId" /></td>  
    </tr>  
     <tr>  
        <td><form:label path="expectedDateChange">expectedDateChange:</form:label></td>  
        <td><form:input type="text" path="expectedDateChange" /></td>  
    </tr>  
     <tr>  
        <td><form:label path="rechargeDetails">rechargeDetails:</form:label></td>  
        <td><form:input type="text" path="rechargeDetails"  /></td>  
    </tr>   
     <tr>  
        <td><form:label path="timestamp">timestamp:</form:label></td>  
        <td><%= new java.util.Date() %></td>
      

    </tr>  
    
    <tr>  
        <td colspan="2">  
            <input type="submit" value="Submit">  
        </td>  
        <td></td>  
        <td></td>  
    </tr>  
    
</table>    


</form:form>  
</body>
</head>
</html>