<%@page import="webview.SelectTable"%>
<%@page import="model.Flight"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
	<div align="center">
		Flights<select size="5" name="flightId">
			<%
				Class<Flight> p = Flight.class;
				for (Object x : SelectTable.getController().getObjectList(p)) {
					Flight obj = (Flight) x;
			%>
			<option><%=obj.toString()%></option>
			<%
				}
			%>
		</select>
	</div>
</body>
</html>