<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="plan/customer/request.do" modelAttribute="plans">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="customers" />
	<form:hidden path="comments" />
	<form:hidden path="issues" />
	
	
	<acme:select items="${goals}" itemLabel="name" id="id" code="plan.goal" path="goal"/>
	<br>
	
	
	<acme:submit name="request" code="plan.request.save"/>

	<a href = "plan/customer/list.do"><input type="button" value="<spring:message code="plan.cancel"/>"  
	id="cancelar" name="cancelar" onclick= "self.location.href = plan/customer/list.do" /></a>
	
	
</form:form>

