<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="plan/customer/request.do" modelAttribute="plan">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="customers" />
	<form:hidden path="comments" />
	<form:hidden path="issues" />
	<form:hidden path="diet" />
	<form:hidden path="training" />


	<form:label path="goal">
		<spring:message code="plan.goal" />
	</form:label>
	<form:select path="goal">
		<form:option value="-" label="--Please Select" />
		<form:options items="${goals}" />
	</form:select>
	<br>


	<input type="submit" name="save" class="btn btn-sm btn-info"
		value="<spring:message code="plan.request.save" />" />

<%-- 	<acme:submit name="save" code="plan.request.save" /> --%>

	<a href="plan/customer/list.do"><input type="button" class="btn btn-sm btn-info"
		value="<spring:message code="plan.cancel"/>" id="cancelar"
		name="cancelar" onclick="self.location.href = plan/customer/list.do" /></a>


</form:form>

