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
.col-centered {
	float: none;
	margin: 0 auto;
}
</style>

<div class="container">
	<form:form action="register/saveCustomer.do"
		modelAttribute="customerForm" role="form">
		<form:hidden path="id" />
		<form:hidden path="version" />


		<div class="row">

			<div class="col-md-3 col-md-offset-2">
				<h3>
					<spring:message code="register.userInfoHeader" />
				</h3>
				<div class="form-group">
					<label for="customerUsername"><spring:message
							code="register.username" /> </label>
					<form:input id="cutomerUsername" path="username"
						class="form-control" />
					<form:errors path="username" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="customerPassword"> <spring:message
							code="register.password" />
					</label>
					<form:input path="password" type="password" id="customerPassword"
						class="form-control" />
					<form:errors path="password" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="customerPasswordRepeat"> <spring:message
							code="register.passwordRepeat" />
					</label>
					<form:input path="repeatPassword" type="password"
						id="customerPasswordRepeat" class="form-control" />
					<form:errors path="repeatPassword" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="customerEmail"> <spring:message
							code="register.email" />
					</label>
					<form:input path="email" id="customerEmail" class="form-control" />
					<form:errors path="email" cssClass="error" />
				</div>
			</div>
			<div class="col-md-3 col-md-offset-2">
				<h3>
					<spring:message code="register.personalInfoHeader" />
				</h3>
				<div class="form-group">
					<label for="customerName"> <spring:message
							code="register.name" />
					</label>
					<form:input path="name" id="customerName" class="form-control" />
					<form:errors path="name" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="customerSurname"> <spring:message
							code="register.surname" />
					</label>
					<form:input path="surname" id="customerSurname"
						class="form-control" />
					<form:errors path="surname" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="customerNationality"> <spring:message
							code="register.nationality" />
					</label>
					<form:input path="nationality" id="customerNationality"
						class="form-control" />
					<form:errors path="nationality" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="customerCity"> <spring:message
							code="register.city" />
					</label>
					<form:input path="city" id="customerCity" class="form-control" />
					<form:errors path="city" cssClass="error" />
				</div>
			</div>
		</div>
		<div class="row">
			<br />
			<h3 class="text-center">
				<spring:message code="register.bodyInfoHeader" />
			</h3>
			<br />
			<div class="col-md-2 col-md-offset-3">
				<div class="form-group">
					<label for="customerWeight"> <spring:message
							code="register.weight" />
					</label>
					<form:input path="weight" id="customerWeight" class="form-control" />
					<form:errors path="weight" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="customerHeight"> <spring:message
							code="register.height" />
					</label>
					<form:input path="height" id="customerHeigth" class="form-control" />
					<form:errors path="height" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="customerBodyfat"> <spring:message
							code="register.bodyFat" />
					</label>
					<form:input path="bodyfat" id="customerBodyfat"
						class="form-control" />
					<form:errors path="bodyfat" cssClass="error" />
				</div>
			</div>
			<div class="col-md-2 col-md-offset-2">
				<div class="form-group">
					<label for="customerWaistlineMeasure"> <spring:message
							code="register.waistlineMeasure" />
					</label>
					<form:input path="waistlineMeasure" id="customerWaistlineMeasure"
						class="form-control" />
					<form:errors path="waistlineMeasure" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="customerHipMeasure"> <spring:message
							code="register.hipMeasure" />
					</label>
					<form:input path="hipMeasure" id="customerHipMeasure"
						class="form-control" />
					<form:errors path="hipMeasure" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="customerChestMeasure"> <spring:message
							code="register.chestMeasure" />
					</label>
					<form:input path="chestMeasure" id="customerChestMeasure"
						class="form-control" />
					<form:errors path="chestMeasure" cssClass="error" />
				</div>
			</div>
		</div>

		<div class="row">
			<br />
			<div class="col-md-6 col-md-offset-3">
				<h4>
					<spring:message code="register.condition" />
				</h4>

				<textarea rows="8" cols="0" style="width: 555px; height: 150px"
					readonly="true">
	 				<spring:message code="register.condition.path" />
				</textarea>
				<br />
				<div class="checkbox">
					<label> <form:checkbox code="register.TOSAccepted"
							path="TOSAccepted" /> <spring:message
							code="register.TOSAccepted" />
					</label>
				</div>

			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-centered">
				<br /> <input type="submit" name="save" class="btn btn-default"
					value="<spring:message code="register.save" />" />
				<%-- <a
					href="welcome/index.do"> <input type="button"
					class="btn btn-default"
					value="<spring:message code="register.cancel"/>" id="cancelar"
					name="cancelar" onclick="self.location.href = welcome/index.do" /></a> --%>
				<br />
			</div>
		</div>
	</form:form>

</div>