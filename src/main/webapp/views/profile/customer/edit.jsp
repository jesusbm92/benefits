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

<form:form action="profile/customer/edit.do"
	modelAttribute="customerForm">

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

	<acme:textbox code="register.weight" path="weight" />
	<acme:textbox code="register.heigth" path="height" />
	<acme:textbox code="register.bodyFat" path="bodyfat" />
	<acme:textbox code="register.waistlineMeasure" path="waistlineMeasure" />
	<acme:textbox code="register.hipMeasure" path="hipMeasure" />
	<acme:textbox code="register.chestMeasure" path="chestMeasure" />

</form:form>