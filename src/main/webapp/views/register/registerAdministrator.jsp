<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="register/saveAdministrator.do"
	modelAttribute="administratorForm">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<acme:textbox code="register.username" path="username" />
	<acme:password code="register.password" path="password" />
	<acme:password code="register.passwordRepeat" path="repeatPassword" />
	<acme:textbox code="register.name" path="name" />
	<acme:textbox code="register.surname" path="surname" />
	<acme:textbox code="register.email" path="email" />
	<acme:textbox code="register.nationality" path="nationality" />
	<acme:textbox code="register.city" path="city" />

	<h3>
		<spring:message code="register.condition" />
	</h3>
	<textarea rows="8" cols="0" style="width: 600px; height: 150px"
		readonly="true">
	 	<spring:message code="register.condition.path" />
	</textarea>
	<form:checkbox code="register.TOSAccepted" path="TOSAccepted" />

	<br />
	<input type="submit" name="save" class="btn btn-sm btn-info"
		value="<spring:message code="register.save" />" />

	<a href="welcome/index.do"><input type="button"
		class="btn btn-sm btn-info"
		value="<spring:message code="register.cancel"/>" id="cancelar"
		name="cancelar" onclick="self.location.href = welcome/index.do" /></a>
	<br />


</form:form>

