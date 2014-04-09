<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@page import="domain.Goals"%>

<form:form action="exercise/administrator/edit.do" modelAttribute="exercise">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="exerciseGroup.exercises" />
	<form:hidden path="exerciseGroup.trainingDays" />
	<form:hidden path="muscle.exercises" />

	<form:label path="muscle">
		<spring:message code="exercise.muscle" />
	</form:label>
	<form:select path="muscle.name">
		<form:options items="${muscles}" itemLabel="name" />
	</form:select>
	<br>
	
	<form:label path="exerciseGroup">
		<spring:message code="exercise.groupExercise" />
	</form:label>
	<form:select path="exerciseGroup.name">
		<form:options items="${groupExercises}" itemLabel="name"/>
	</form:select>
	<br>
	
	<acme:textbox code="exercise.name" path="name" />
	<acme:textbox code="exercise.repetitions" path="repetitions" />
	<acme:textbox code="exercise.cycles" path="cycles" />
	<br>
	


	<input type="submit" name="save" class="btn btn-sm btn-info"
		value="<spring:message code="exercise.save" />" />

	<jstl:if test="${!create}">
		<input type="submit" class="btn btn-sm btn-info" name="delete"
			value="<spring:message code="exercise.delete"/>"
			onclick="return confirm('<spring:message code="exercise.delete.confirm"/>')" />
	</jstl:if>

	<a href="exercise/administrator/list.do"><input type="button"
		class="btn btn-sm btn-info"
		value="<spring:message code="exercise.cancel"/>" id="cancelar"
		name="cancelar"
		onclick="self.location.href = exercise/administrator/list.do" /></a>


</form:form>

