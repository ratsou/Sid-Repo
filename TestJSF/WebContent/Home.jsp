<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<html>
<head>
<title>User Page</title>
</head>
<body>
	<f:view>
		<h:form>
			<h:panelGrid border="1" columns="2">
				<h:outputText value="Enter your Name: "></h:outputText>
				<h:inputText value="#{testBean.userName}"></h:inputText>
				<h:commandButton action="#{testBean.displayUser}" value="Fetch User Details"></h:commandButton>
				<h:commandButton action="hello" value="Say Hello"></h:commandButton>
			</h:panelGrid>
		</h:form>
	</f:view>
</body>
</html>