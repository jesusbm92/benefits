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

<div class="row">
	<div class="col-md-5 col-md-offset-1">
		<h2 class="text-center">
			<spring:message code="profile.administrator.editInfoHeader" />
		</h2>
		<br />
		<form:form action="profile/administrator/edit.do"
			modelAttribute="administrator" role="form" class="form-horizontal">
			<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="nationality" />
			<form:hidden path="name" />
			<form:hidden path="surname" />
			<form:hidden path="userAccount" />


			<div class="form-group">
				<form:label for="administratorEmail" path="email"
					class="col-md-3 col-md-offset-1 control-label">
					<spring:message code="profile.administrator.email" />
				</form:label>
				<div class="col-md-4">
					<form:input id="administratorEmail" path="email"
						class="form-control" />
				</div>
				<form:errors path="email" cssClass="error" />
			</div>

			<div class="form-group">
				<form:label for="administratorCity" path="city"
					class="col-md-3 col-md-offset-1 control-label">
					<spring:message code="profile.administrator.city" />
				</form:label>
				<div class="col-md-4">
					<form:input id="administratorCity" path="city" class="form-control" />
				</div>
				<form:errors path="city" cssClass="error" />
			</div>

			<div class="form-group">
				<div class="col-md-offset-4 col-md-5">
					<input type="submit" name="save" class="btn btn-default"
						value="<spring:message code="profile.administrator.save" />" /> <input
						type="button" class="btn btn-default"
						value="<spring:message code="profile.administrator.cancel"/>"
						onclick="self.location.href = welcome/index.do" />
				</div>
			</div>
		</form:form>
	</div>

	<div class="col-md-5">
		<h2 class="text-center">
			<spring:message code="profile.administrator.changePassword" />
		</h2>
		<br />
		<form:form action="profile/administrator/edit.do"
			modelAttribute="cpForm" role="form" class="form-horizontal">
			<div class="form-group">
				<form:label for="administratorCurrentPassword"
					path="currentPassword"
					class="col-md-3 col-md-offset-1 control-label">
					<spring:message code="profile.administrator.currentPassword" />
				</form:label>
				<div class="col-md-4">
					<form:input type="password" id="administratorCurrentPassword"
						path="currentPassword" class="form-control" />
				</div>
				<form:errors path="currentPassword" cssClass="error" />
			</div>
			<div class="form-group">
				<form:label for="administratorNewPassword" path="newPassword"
					class="col-md-3 col-md-offset-1 control-label">
					<spring:message code="profile.administrator.password" />
				</form:label>
				<div class="col-md-4">
					<form:input type="password" id="administratorNewPassword"
						path="newPassword" class="form-control" />
				</div>
				<form:errors path="newPassword" cssClass="error" />
			</div>
			<div class="form-group">
				<form:label for="administratorNewPasswordConfirmation"
					path="newPasswordConfirmation"
					class="col-md-3 col-md-offset-1 control-label">
					<spring:message code="profile.administrator.passwordRepeat" />
				</form:label>
				<div class="col-md-4">
					<form:input type="password"
						id="administratorNewPasswordConfirmation"
						path="newPasswordConfirmation" class="form-control" />
				</div>
				<form:errors path="newPasswordConfirmation" cssClass="error" />
			</div>
			<div class="form-group">
				<div class="col-md-offset-4 col-md-3">
					<input type="submit" name="changePassword" class="btn btn-default"
						value="<spring:message code="profile.administrator.changePassword" />" />
				</div>
			</div>
		</form:form>
	</div>

</div>