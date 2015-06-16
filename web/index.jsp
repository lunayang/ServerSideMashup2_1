<%@ page import="java.util.*" %>
<%@ page import="store.*" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" language="javascript" src="ajax1.js"></script>
 <script type="text/javascript" language="javascript" src="cart.js"></script> 
<link rel="stylesheet" type="text/css" href="newcss.css" />
</head>
<body onload="updateCart()"> 
<h2>Catalog</h2>
<table>
  <thead><th>Name</th><th>Source</th><th colspan="2">&nbsp;</th></thead>
  <tbody>
      
  <%
    for (Iterator<Item> I = new Catalog().getAllItems().iterator() ; I.hasNext() ; ) {
      Item item = I.next();
  %>
    <tr>
    	<td><%= item.getName() %></td>
    	<td><%= item.getSource() %></td>
        <td><button onclick="addToCart('<%= item.getName() %>','<%= item.getUrl() %>')">Add to List</button></td>
        <td><button onclick="deleteFromCart('<%= item.getName() %>', '<%= item.getUrl() %>')">Delete from List</button></td>
   </tr>
 <% } %>
 <tr><td colspan="4"><button onclick="getDocsFromCart()">generate newspaper</button></td></tr>
 
 
  </tbody>
</table>
<div style="position: absolute; top: 0px; right: 0px; width: 250px">
<h2>List Contents</h2>
<ul id="contents">
</ul>
Number of feeds selected: <span id="total">0</span>
</div>
<div style="width:100%;height: 100%" id="newsDiv"></div>
</body>
</html>
