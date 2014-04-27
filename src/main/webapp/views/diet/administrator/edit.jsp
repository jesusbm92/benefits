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

	<acme:textarea path="description" code="diet.description" />
	<br>

	<form:label path="sponsor">
		<spring:message code="diet.sponsor" />
	</form:label>
	<form:select id="sponsors" path="sponsor">
		<form:option value="0" label="----" />
		<form:options items="${sponsors }" itemValue="id" itemLabel="name" />
	</form:select>
	<br>

	<form:label path="days">
		<spring:message code="diet.days.edit" />
	</form:label>
	<form:select multiple="${days.size()}" items="${days}" itemLabel="name"
		id="id" code="diet.day" path="days" />
	<br>

	<input type="submit" name="save" class="btn btn-default"
		value="<spring:message code="diet.save" />" />

	<jstl:if test="${diet.id!=0}">
		<input type="submit" class="btn btn-default" name="delete"
			value="<spring:message code="diet.delete"/>"
			onclick="return confirm('<spring:message code="diet.delete.confirm"/>')" />
	</jstl:if>

	<input type="button" class="btn btn-default"
		value="<spring:message code="diet.cancel"/>" onclick="history.back()" />


</form:form>

