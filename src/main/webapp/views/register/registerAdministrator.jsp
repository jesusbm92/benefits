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

<style>
body {
	padding-top: 30px;
}

.form-control {
	margin-bottom: 10px;
}
</style>

<br />
<br />
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-4 col-sm-offset-4 well well-sm">
			<legend>
				<spring:message code="register.form.admin" />
			</legend>
			<form:form action="register/saveAdministrator.do"
				modelAttribute="administratorForm" method="post" class="form"
				role="form">
				<form:hidden path="id" />
				<form:hidden path="version" />
				<div class="row">
					<div class="col-xs-6 col-md-6">
						<spring:message code="register.name" var="name" />
						<form:input path="name" class="form-control" name="name"
							placeholder="${name}" type="text" />
						<form:errors class="error" path="name" />
					</div>
					<div class="col-xs-6 col-md-6">
						<spring:message code="register.surname" var="surname" />
						<form:input path="surname" class="form-control" name="surname"
							placeholder="${surname}" type="text" />
						<form:errors class="error" path="surname" />
					</div>
				</div>
				<spring:message code="register.email" var="email" />
				<form:input path="email" class="form-control" name="youremail"
					placeholder="${email}" type="email" />
				<form:errors class="error" path="email" />
				<spring:message code="register.username" var="username" />
				<form:input path="username" class="form-control" name="username"
					placeholder="${username}" type="text" />
				<form:errors class="error" path="username" />
				<spring:message code="register.password" var="password" />
				<form:input path="password" class="form-control" name="password"
					placeholder="${password}" type="password" />
				<form:errors class="error" path="password" />
				<spring:message code="register.passwordRepeat" var="passwordRepeat" />
				<form:input path="repeatPassword" class="form-control"
					name="password" placeholder="${passwordRepeat}" type="password" />
				<form:errors class="error" path="repeatPassword" />
				<spring:message code="register.city" var="city" />
				<form:input path="city" class="form-control" name="city"
					placeholder="${city}" type="city" />
				<form:errors class="error" path="city" />
				<spring:message code="register.nationality" var="nationality" />
				<form:input path="nationality" class="form-control" name="text"
					placeholder="${nationality}" type="text" />
				<form:errors class="error" path="nationality" />
				<br />
				<input type="submit" name="save"
					class="btn btn-lg btn-primary btn-block"
					value="<spring:message code="register.create" />" />
			</form:form>
		</div>
	</div>
<%-- 	<div class="col-md-1 col-centered">
		<a href="welcome/index.do"><input type="button"
			class="btn btn-md btn-default"
			value="<spring:message code="register.cancel"/>" id="cancelar"
			name="cancelar" onclick="self.location.href = welcome/index.do" /></a>
	</div> --%>
</div>

<!-- <div class="container"> -->

<%-- 	<form:form action="register/saveAdministrator.do" --%>
<%-- 		modelAttribute="administratorForm"> --%>

<%-- 		<form:hidden path="id" /> --%>
<%-- 		<form:hidden path="version" /> --%>

<%-- 		<acme:textbox code="register.username" path="username" /> --%>
<%-- 		<acme:password code="register.password" path="password" /> --%>
<%-- 		<acme:password code="register.passwordRepeat" path="repeatPassword" /> --%>
<%-- 		<acme:textbox code="register.name" path="name" /> --%>
<%-- 		<acme:textbox code="register.surname" path="surname" /> --%>
<%-- 		<acme:textbox code="register.email" path="email" /> --%>
<%-- 		<acme:textbox code="register.nationality" path="nationality" /> --%>
<%-- 		<acme:textbox code="register.city" path="city" /> --%>


<!-- 		<br /> -->
<!-- 		<input type="submit" name="save" class="btn btn-sm btn-info" -->
<%-- 			value="<spring:message code="register.save" />" /> --%>

<!-- 		<a href="welcome/index.do"><input type="button" -->
<!-- 			class="btn btn-sm btn-info" -->
<%-- 			value="<spring:message code="register.cancel"/>" id="cancelar" --%>
<!-- 			name="cancelar" onclick="self.location.href = welcome/index.do" /></a> -->
<!-- 		<br /> -->


<%-- 	</form:form> --%>
<!-- </div> -->
