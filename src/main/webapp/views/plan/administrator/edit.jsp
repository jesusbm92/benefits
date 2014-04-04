<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="plan/administrator/edit.do" modelAttribute="plan">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="customers" />
	<form:hidden path="comments" />
	<form:hidden path="issues" />
	
	
	<acme:textbox code="plan.goal" path="goal" />
	<br>
	
	<acme:select items="${diets}" itemLabel="name" id="id" code="plan.diet" path="diet"/>
	<br>
	
	<acme:select items="${trainings}" itemLabel="name" id="id" code="plan.training" path="training"/>
	<br>
	
	<acme:submit name="save" code="plan.save"/>

	<jstl:if test="${!create}">
		<input type="submit" name="delete" value="<spring:message code="plan.delete"/>"  onclick="return confirm('<spring:message code="plan.delete"/>')" />
	</jstl:if>
	
	<a href = "plan/list.do"><input type="button" value="<spring:message code="plan.cancel"/>"  
	id="cancelar" name="cancelar" onclick= "self.location.href = plan/list.do" /></a>
	
	
</form:form>

