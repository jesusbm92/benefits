<%--
 * edit.jsp
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

<form:form action="profile/customer/edit.do"
	modelAttribute="customer">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount" />

	<acme:textbox code="profile.customer.name" path="name" />
	<acme:textbox code="profile.customer.surname" path="surname" />
	<acme:textbox code="profile.customer.email" path="email" />
	<acme:textbox code="profile.customer.city" path="city" />
	<acme:textbox code="profile.customer.nationality" path="nationality" />
	<acme:textbox code="profile.customer.weight" path="weight" />
	<acme:textbox code="profile.customer.height" path="height" />
	<acme:textbox code="profile.customer.bodyFat" path="bodyfat" />
	<acme:textbox code="profile.customer.waistlineMeasure"
		path="waistlineMeasure" />
	<acme:textbox code="profile.customer.hipMeasure" path="hipMeasure" />
	<acme:textbox code="profile.customer.chestMeasure" path="chestMeasure" />

	<input type="submit" name="save" class="btn btn-sm btn-info"
		value="<spring:message code="profile.customer.save" />" />

</form:form>

<form:form action="profile/customer/edit.do"
	modelAttribute="cpForm">

	<acme:password code="profile.customer.currentPassword"
		path="currentPassword" />
	<acme:password code="profile.customer.password" path="newPassword" />
	<acme:password code="profile.customer.passwordRepeat"
		path="newPasswordConfirmation" />

	<input type="submit" name="changePassword" class="btn btn-sm btn-info"
		value="<spring:message code="profile.customer.save" />" />

</form:form>