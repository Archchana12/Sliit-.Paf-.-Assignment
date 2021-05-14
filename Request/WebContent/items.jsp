<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="model.Requests"%>
<%
//Save---------------------------------
if (request.getParameter("req_id") != null)
{
	Requests requestobj=new Requests();
String stsMsg = "";
//Insert--------------------------
if (request.getParameter("hidItemIDSave") == "")
{
stsMsg = requestobj.insertRequest(
request.getParameter("custname"),
request.getParameter("pname"),
request.getParameter("department"),
request.getParameter("p_des"),
request.getParameter("p_purp"),
request.getParameter("p_req"));
}
else//Update----------------------
{
stsMsg = requestobj.updaterequest(request.getParameter("hidItemIDSave"),

request.getParameter("custname"),
request.getParameter("pname"),
request.getParameter("department"),
request.getParameter("p_des"),
request.getParameter("p_purp"),
request.getParameter("p_req"));
}
session.setAttribute("statusMsg", stsMsg);
}
//Delete-----------------------------
if (request.getParameter("hidItemIDDelete") != null)
{
Requests requestobj=new Requests();	
String stsMsg =
requestobj.deleteRequest(request.getParameter("hidItemIDDelete"));
session.setAttribute("statusMsg", stsMsg);
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>
<title>Customer Management</title>
</head>
<body>
<h1>Customer Management</h1>

<form id="formItem" name="formItem" method="post" action="items.jsp">
 Request ID:
<input id="req_id" name="req_id" type="text"
 class="form-control form-control-sm">
<br> Customer name:
<input id="custname" name="custname" type="text"
 class="form-control form-control-sm">
<br> Project Name:
<input id="pname" name="pname" type="text"
 class="form-control form-control-sm">
<br> Description:
<input id="department" name="department" type="text"
 class="form-control form-control-sm">
<br>
<br> Description:
<input id="p_des" name="p_des" type="text"
 class="form-control form-control-sm">
<br>
<br> Purpose:
<input id="p_purp" name="p_purp" type="text"
 class="form-control form-control-sm">
<br>
<br>Project request:
<input id="p_req" name="p_req" type="text"
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<%
 out.print(session.getAttribute("statusMsg"));
%>
<br>
<div id="divItemsGrid">
<%
Requests requestobj=new Requests();
 out.print(requestobj.readRequests());
%>
</div>
</body>
</html>