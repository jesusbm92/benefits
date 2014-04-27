<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="trainingDay/administrator/edit.do" modelAttribute="trainingDay">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:label path="name">
		<spring:message code="trainingDay.name" />
	</form:label>
	<form:select path="name">
		<form:options items="${days}" />
	</form:select>
	<br>	
	
	<form:label path="exerciseGroups"><spring:message code="trainingDay.exerciseGroups" /></form:label>
	<form:select multiple="${exerciseGroups.size()}" items="${exerciseGroups}" itemLabel="name" id="id" code="trainingDay.exerciseGroups" path="exerciseGroups"/>
	<br>
	<br>
	<input type="submit" name="save" class="btn btn-default"
		value="<spring:message code="trainingDay.save" />" />

	<jstl:if test="${trainingDay.id!=0}">
		<input type="submit" class="btn btn-default" name="delete"
			value="<spring:message code="trainingDay.delete"/>"
			onclick="return confirm('<spring:message code="trainingDay.delete.confirm"/>')" />
	</jstl:if>

	<input type="button" class="btn btn-default"
		value="<spring:message code="trainingDay.cancel"/>" onclick="history.back()" />


</form:form>

