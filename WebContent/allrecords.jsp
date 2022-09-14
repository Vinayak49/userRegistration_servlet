<%@page import="java.sql.ResultSet"%>
<%@page import="com.Hotwax.AllRecordsServlet"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

 
<title>All Records</title>
</head>
<body>
<%
AllRecordsServlet ars = new AllRecordsServlet();
ResultSet rs = ars.selectAll();
while(rs.next()){
	%>
	<div class="card text-center mt-4">
  <div class="card-header bg-success">
    <%=rs.getString(2)%> <%=rs.getString(3)%>
  </div>
  <div class="card-body">
    <h4><p class="card-text"> Address: <%=rs.getString(4)%> City: <%=rs.getString(5)%> Zip: <%=rs.getString(6)%> State: <%=rs.getString(7)%> Country: <%=rs.getString(8)%> Phone: <%=rs.getString(9)%> Password: <%=rs.getString(10)%></p></h4>
    <a href="allrecords.jsp?PartyId=<%=rs.getString(1)%>" class="btn btn-success">Update</a>
        <a href="DeleteServlet?PartyId=<%=rs.getString(1)%>" class="btn btn-danger">Delete</a>
  </div>

</div>
<%
}
%>

<%
	String pid = request.getParameter("PartyId");
	AllRecordsServlet ars1 = new AllRecordsServlet();
	if(pid!=null){
		int partyId = Integer.parseInt(pid);
		ResultSet rs1 = ars1.updateRecord(partyId);
		rs1.next();
%>

<form action="update" method="post">
<div>
Party id: <input type= "number" name="partyid" value="<%=rs1.getString(1)%>" readonly>
First name: <input type= "text" name="fname" value="<%=rs1.getString(2)%>">
Last name: <input type= "text" name="lname" value="<%=rs1.getString(3)%>">
Address: <input type= "text" name="address" value="<%=rs1.getString(4)%>">
City: <input type= "text" name="city" value="<%=rs1.getString(5)%>">
State: <input type= "text" name="state" value="<%=rs1.getString(7)%>">
Country: <input type= "text" name="country" value="<%=rs1.getString(8)%>">
Zip: <input type= "number" name="zip" value="<%=rs1.getString(6)%>">
Phone: <input type= "number" name="phone" value="<%=rs1.getInt(9)%>">
Password: <input type= "password" name="password" value="<%=rs1.getString(10)%>">
</div>
  <button type="submit" class="btn btn-success">Update</button>
</form>	

    <%
    }
	
    %>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>