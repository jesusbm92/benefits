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

<div class="row">
	<div class="col-md-5">

		<h2 class="text-center">
			<spring:message code="profile.customer.editInfoHeader" />
		</h2>
		<br />
		<form:form action="profile/customer/edit.do" modelAttribute="customer"
			role="form" class="form-horizontal">

			<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="userAccount" />


			<div class="form-group">
				<form:label for="cutomerName" path="name"
					class="col-md-3 control-label">
					<spring:message code="profile.customer.name" />
				</form:label>
				<div class="col-md-4">
					<form:input id="cutomerName" path="name" class="form-control" />
				</div>
			</div>

			<div class="form-group">
				<form:label for="cutomerSurname" path="surname"
					class="col-md-3 control-label">
					<spring:message code="profile.customer.surname" />
				</form:label>
				<div class="col-md-4">
					<form:input id="cutomerSurname" path="surname" class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<form:label for="cutomerEmail" path="email"
					class="col-md-3 control-label">
					<spring:message code="profile.customer.email" />
				</form:label>
				<div class="col-md-4">
					<form:input id="cutomerEmail" path="email" class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<form:label for="cutomerCity" path="city"
					class="col-md-3 control-label">
					<spring:message code="profile.customer.city" />
				</form:label>
				<div class="col-md-4">
					<form:input id="cutomerCity" path="city" class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<form:label for="cutomerNationality" path="nationality"
					class="col-md-3 control-label">
					<spring:message code="profile.customer.nationality" />
				</form:label>
				<div class="col-md-3">
					<form:input id="cutomerNationality" path="nationality"
						class="form-control" />
				</div>
			</div>

			<div class="form-group">
				<form:label for="cutomerWeight" path="weight"
					class="col-md-3 control-label">
					<spring:message code="profile.customer.weight" />
				</form:label>
				<div class="col-md-2">
					<form:input id="cutomerWeight" path="weight" class="form-control" />
				</div>
				<form:label for="cutomerHeight" path="height"
					class="col-md-3 control-label">
					<spring:message code="profile.customer.height" />
				</form:label>
				<div class="col-md-2">
					<form:input id="cutomerHeight" path="height" class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<form:label for="cutomerBodyfat" path="bodyfat"
					class="col-md-3 control-label">
					<spring:message code="profile.customer.bodyFat" />
				</form:label>
				<div class="col-md-2">
					<form:input id="cutomerBodyfat" path="bodyfat" class="form-control" />
				</div>
				<form:label for="cutomerWaistlineMeasure" path="waistlineMeasure"
					class="col-md-3 control-label">
					<spring:message code="profile.customer.waistlineMeasure" />
				</form:label>
				<div class="col-md-2">
					<form:input id="cutomerWaistlineMeasure" path="waistlineMeasure"
						class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<form:label for="cutomerHipMeasure" path="hipMeasure"
					class="col-md-3 control-label">
					<spring:message code="profile.customer.hipMeasure" />
				</form:label>
				<div class="col-md-2">
					<form:input id="cutomerHipMeasure" path="hipMeasure"
						class="form-control" />
				</div>
				<form:label for="cutomerChestMeasure" path="chestMeasure"
					class="col-md-3 control-label">
					<spring:message code="profile.customer.chestMeasure" />
				</form:label>
				<div class="col-md-2">
					<form:input id="cutomerChestMeasure" path="chestMeasure"
						class="form-control" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-md-offset-2 col-md-3">
					<input type="submit" name="save" class="btn btn-default"
						value="<spring:message code="profile.customer.save" />" />
				</div>
			</div>
		</form:form>
	</div>

	<div class="col-md-5">

		<h2 class="text-center">
			<spring:message code="profile.customer.passwordChangeHeader" />
		</h2>
		<br />
		<form:form action="profile/customer/edit.do" modelAttribute="cpForm"
			role="form" class="form-horizontal">
			<div class="form-group">
				<form:label for="cutomerCurrentPassword" path="currentPassword"
					class="col-md-3 control-label">
					<spring:message code="profile.customer.currentPassword" />
				</form:label>
				<div class="col-md-3">
					<form:input type="password" id="customerCurrentPassword"
						path="currentPassword" class="form-control" />
				</div>
			</div>

			<div class="form-group">
				<form:label for="cutomerNewPassword" path="newPassword"
					class="col-md-3 control-label">
					<spring:message code="profile.customer.password" />
				</form:label>
				<div class="col-md-3">
					<form:input type="password" id="customerNewPassword"
						path="newPassword" class="form-control" />
				</div>
			</div>

			<div class="form-group">
				<form:label for="cutomerNewPasswordConfirmation"
					path="newPasswordConfirmation" class="col-md-3 control-label">
					<spring:message code="profile.customer.passwordRepeat" />
				</form:label>
				<div class="col-md-3">
					<form:input type="password" id="customerNewPasswordConfirmation"
						path="newPasswordConfirmation" class="form-control" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-md-offset-2 col-md-10">
					<input type="submit" name="changePassword" class="btn btn-default"
						value="<spring:message code="profile.customer.changePassword" />" />
				</div>
			</div>
		</form:form>
	</div>
</div>
