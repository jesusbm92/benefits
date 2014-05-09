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


<style>
.colorgraph {
	height: 5px;
	border-top: 0;
	background: #993232;
	border-radius: 5px;
}
</style>

<h1 class="text-center"><spring:message code="profile.administrador.edit" /></h1>

<div class="container">

	<div class="row" style="margin-top: 20px">
		<div
			class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
			<form:form action="profile/administrator/edit.do"
				modelAttribute="administrator" role="form">
				<form:hidden path="id" />
				<form:hidden path="version" />
				<form:hidden path="nationality" />
				<form:hidden path="name" />
				<form:hidden path="surname" />
				<form:hidden path="userAccount" />
				<fieldset>
					<hr class="colorgraph">
					<h3>
						<spring:message code="register.personalInfoHeader" />
					</h3>
					<div class="form-group">
						<div class="form-group">
							<spring:message code="profile.administrator.email" var="email" />
							<form:input path="email" type="email" name="email" id="email"
								class="form-control input-lg" placeholder="${email}" />
							<form:errors class="error" path="email" />
						</div>
					</div>

					<div class="form-group">
						<spring:message code="profile.administrator.city" var="city" />
						<form:input path="city" type="text" name="city" id="city"
							class="form-control input-lg" placeholder="${city}" />
						<form:errors class="error" path="city" />
					</div>
					<br />
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<input type="submit" name="save"
								class="btn btn-lg btn-primary btn-block"
								value="<spring:message code="profile.customer.save" />" />
						</div>
					</div>
				</fieldset>
			</form:form>
			<form:form action="profile/administrator/edit.do"
				modelAttribute="cpForm" role="form">

				<fieldset>
					<hr class="colorgraph">
					<h3>
						<spring:message code="profile.administrator.changePassword" />
					</h3>
					<div class="form-group">
						<spring:message code="profile.administrator.currentPassword"
							var="currentPassword" />
						<form:input path="currentPassword" type="password"
							name="currentPassword" id="currentPassword"
							class="form-control input-lg" placeholder="${currentPassword}" />
						<form:errors class="error" path="currentPassword" />
					</div>
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<spring:message code="profile.administrator.password"
									var="newPassword" />
								<form:input path="newPassword" type="password"
									name="newPassword" id="newPassword"
									class="form-control input-lg" placeholder="${newPassword}" />
								<form:errors class="error" path="newPassword" />
							</div>
						</div>
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<spring:message code="profile.administrator.passwordRepeat"
									var="newPasswordConfirmation" />
								<form:input path="newPasswordConfirmation" type="password"
									name="newPasswordConfirmation" id="newPasswordConfirmation"
									class="form-control input-lg"
									placeholder="${newPasswordConfirmation}" />
								<form:errors class="error" path="newPasswordConfirmation" />
							</div>
						</div>
					</div>

					<hr class="colorgraph">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<input type="submit" name="changePassword"
								class="btn btn-lg btn-primary btn-block"
								value="<spring:message code="profile.administrator.changePassword" />" />
						</div>
					</div>
					<br />
				</fieldset>
			</form:form>
		</div>
	</div>

</div>

<br />
<br />
<br />
