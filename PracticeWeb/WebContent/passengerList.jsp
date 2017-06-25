<%@page import="webview.SelectTable"%>
<%@page import="model.Passenger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div align="center" >
		Passengers<select size="5" name="passengerId">
			<%
				Class<Passenger> c = Passenger.class;
				for (Object x : SelectTable.getController().getObjectList(c)) {
					Passenger obj = (Passenger) x;
			%>
			<option><%=obj.toString()%></option>
			<%
				}
			%>
		</select>
	</div>
</body>
</html>