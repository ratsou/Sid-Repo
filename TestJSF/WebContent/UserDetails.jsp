<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%><%@taglib
	uri="http://java.sun.com/jsf/html" prefix="h"%>
<html>
<head>
<title>Users Details</title>
</head>
<body>
	<f:view>
		<h:form>
		<h1> USER DETAILS </h1>
		<br>
			User Name: <h:outputText value="#{testBean.userName}"></h:outputText>
		</h:form>
	</f:view>
</body>
</html>