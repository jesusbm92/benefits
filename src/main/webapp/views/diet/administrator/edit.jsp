<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="diet/administrator/edit.do" modelAttribute="diet">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="name">
		<spring:message code="diet.name" />
	</form:label>
	<form:input path="name" type="text" />
	<br>

	<form:label path="description">
		<spring:message code="diet.description" />
	</form:label>
	<acme:textarea path="description" code="diet.description" />
	<br>


	<acme:select items="${diets}" itemLabel="name" id="id" code="plan.diet"
		path="diet" />
	<br>

	<acme:select items="${trainings}" itemLabel="name" id="id"
		code="plan.training" path="training" />
	<br>

	<input type="submit" name="save" class="btn btn-sm btn-info"
		value="<spring:message code="plan.save" />" />

	<jstl:if test="${!create}">
		<input type="submit" class="btn btn-sm btn-info" name="delete"
			value="<spring:message code="plan.delete"/>"
			onclick="return confirm('<spring:message code="plan.delete"/>')" />
	</jstl:if>

	<a href="plan/administrator/list.do"><input type="button"
		class="btn btn-sm btn-info"
		value="<spring:message code="plan.cancel"/>" id="cancelar"
		name="cancelar"
		onclick="self.location.href = plan/administrator/list.do" /></a>


</form:form>

