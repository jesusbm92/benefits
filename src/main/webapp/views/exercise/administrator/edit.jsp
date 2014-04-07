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

<form:form action="training/administrator/edit.do" modelAttribute="plan">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="customers" />
	<form:hidden path="comments" />
	<form:hidden path="issues" />

	<form:label path="goal">
		<spring:message code="plan.goal" />
	</form:label>
	<form:select path="goal">
		<form:option value="-" label="--Please Select" />
		<form:options items="${goals}" />
	</form:select>
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

