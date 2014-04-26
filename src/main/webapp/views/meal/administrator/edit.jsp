<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@page import="domain.Meals"%>


<form:form action="meal/administrator/edit.do" modelAttribute="meal">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="amounts" />

	<form:label path="name">
		<spring:message code="meal.name" />
	</form:label>
	<form:select path="name">
		<jstl:forEach var="name" items="${names}">
			<form:option value="${name}">
				<spring:message code="meal.name.${name}" />
			</form:option>
		</jstl:forEach>
	</form:select>
	<br>

	<input type="submit" name="save" class="btn btn-default"
		value="<spring:message code="meal.save" />" />

	<jstl:if test="${!create}">
		<input type="submit" class="btn btn-default" name="delete"
			value="<spring:message code="meal.delete"/>"
			onclick="return confirm('<spring:message code="meal.delete"/>')" />
	</jstl:if>

	<input type="button" class="btn btn-default"
		value="<spring:message code="meal.cancel"/>" onclick="history.back()" />


</form:form>

