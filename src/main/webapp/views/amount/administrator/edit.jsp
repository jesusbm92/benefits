<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="amount/administrator/edit.do?mealId=${param.mealId}"
	modelAttribute="amount">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="meal" />

	<form:label path="quantity">
		<spring:message code="amount.quantity" />
	</form:label>
	<form:input path="quantity" type="text" />
	<br>

	<form:label path="measure">
		<spring:message code="amount.measure" />
	</form:label>
	<form:input path="measure" type="text" />
	<br>

	<form:label path="food" for="foodId">
		<spring:message code="amount.food" />
	</form:label>
	<form:select id="foodId" path="food">
		<form:options items="${foods}" itemValue="id" itemLabel="name" />
	</form:select>
	<form:errors path="food" cssClass="error" />

	<input type="submit" name="save" class="btn btn-default"
		value="<spring:message code="amount.save" />" />

	<jstl:if test="${!create}">
		<input type="submit" class="btn btn-default" name="delete"
			value="<spring:message code="amount.delete"/>"
			onclick="return confirm('<spring:message code="amount.delete"/>')" />
	</jstl:if>

	<a href="amount/administrator/listDetails.do?mealId=${param.mealId}"><input
		type="button" class="btn btn-default"
		value="<spring:message code="food.cancel"/>" id="cancelar"
		name="cancelar"
		onclick="amount/administrator/listDetails.do?mealId=${param.mealId}" /></a>
</form:form>

