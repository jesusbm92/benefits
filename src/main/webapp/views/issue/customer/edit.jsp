<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="issue/customer/edit.do" modelAttribute="issue">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="customer" />
	<form:hidden path="plan" />
	<form:hidden path="version" />


	<acme:textarea code="issue.content" path="description" />
	<br>

	<input type="submit" name="save" class="btn btn-sm btn-info"
		value="<spring:message code="issue.save" />" />

	<jstl:if test="${!create}">
		<input type="submit" class="btn btn-sm btn-info" name="delete"
			value="<spring:message code="issue.delete"/>"
			onclick="return confirm('<spring:message code="issue.delete"/>')" />
	</jstl:if>

	<a href="plan/list.do"><input type="button"
		class="btn btn-sm btn-info"
		value="<spring:message code="issue.cancel"/>" id="cancelar"
		name="cancelar" onclick="self.location.href = plan/list.do" /></a>


</form:form>

