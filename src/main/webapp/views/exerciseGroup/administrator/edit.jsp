<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="exerciseGroup/administrator/edit.do" modelAttribute="exerciseGroup">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<acme:textbox code="exerciseGroup.name" path="name" />
	<br>
	
	<form:select multiple="${exercises.size()}" items="${exercises}" itemLabel="name" id="id" code="exerciseGroup.exercises" path="exercises"/>
	<br>
	<br>
	<input type="submit" name="save" class="btn btn-sm btn-info"
		value="<spring:message code="exerciseGroup.save" />" />

	<jstl:if test="${exerciseGroup.id!=0}">
		<input type="submit" class="btn btn-sm btn-info" name="delete"
			value="<spring:message code="exerciseGroup.delete"/>"
			onclick="return confirm('<spring:message code="exerciseGroup.delete.confirm"/>')" />
	</jstl:if>

	<a href="exercise/administrator/list.do"><input type="button"
		class="btn btn-sm btn-info"
		value="<spring:message code="exercise.cancel"/>" id="cancelar"
		name="cancelar"
		onclick="self.location.href = exercise/administrator/list.do" /></a>


</form:form>

