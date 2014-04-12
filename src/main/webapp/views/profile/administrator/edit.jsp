<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page import="forms.AdministratorForm"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="profile/administrator/edit.do"
	modelAttribute="administratorForm">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="city" />
	<form:hidden path="nationality" />
	<form:hidden path="name" />
	<form:hidden path="surname" />
	<form:hidden path="password" />
	<form:hidden path="repeatPassword" />
	
	<acme:password code="profile.administrator.currentPassword" path="originalPassword" />
	<acme:password code="profile.administrator.password" path="newPassword" />
	<acme:password code="profile.administrator.passwordRepeat" path="newConfirmedPassword" />
	<acme:textbox code="profile.administrator.email" path="email" />
	<acme:textbox code="profile.administrator.city" path="city" />
	
	<input type="submit" name="save" class="btn btn-sm btn-info"
		value="<spring:message code="profile.administrator.save" />" />
	
</form:form>