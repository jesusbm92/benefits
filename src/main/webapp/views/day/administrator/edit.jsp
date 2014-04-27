<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@page import="domain.Days"%>

<form:form action="day/administrator/edit.do" modelAttribute="day">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="name">
		<spring:message code="day.name" />
	</form:label>
	<form:select path="name">
		<jstl:forEach var="name" items="${names}">
			<form:option value="${name}">
				<spring:message code="day.name.${name}" />
			</form:option>
		</jstl:forEach>
	</form:select>
	<br>

	<form:label path="meals">
		<spring:message code="day.meals.edit" />
	</form:label>
	<form:select multiple="${meals.size()}" items="${meals}" itemLabel="name"
		id="id" code="day.meal" path="meals" />
	<br>

	<input type="submit" name="save" class="btn btn-default"
		value="<spring:message code="day.save" />" />

	<jstl:if test="${!create}">
		<input type="submit" class="btn btn-default" name="delete"
			value="<spring:message code="day.delete"/>"
			onclick="return confirm('<spring:message code="day.delete"/>')" />
	</jstl:if>

	<input type="button" class="btn btn-default"
		value="<spring:message code="day.cancel"/>" onclick="history.back()" />


</form:form>

