<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="food/administrator/edit.do" modelAttribute="food">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="amounts" />

	<form:label path="name">
		<spring:message code="food.name" />
	</form:label>
	<form:input path="name" type="text" />
	<br>

	<acme:textarea path="description" code="food.description" />
	<br>

	<input type="submit" name="save" class="btn btn-default"
		value="<spring:message code="food.save" />" />

	<jstl:if test="${!create}">
		<input type="submit" class="btn btn-default" name="delete"
			value="<spring:message code="food.delete"/>"
			onclick="return confirm('<spring:message code="food.delete"/>')" />
	</jstl:if>

	<a href="food/administrator/list.do"><input type="button"
		class="btn btn-default" value="<spring:message code="food.cancel"/>"
		id="cancelar" name="cancelar"
		onclick="self.location.href = food/administrator/list.do" /></a>


</form:form>

