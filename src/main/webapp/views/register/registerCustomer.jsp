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
.colorgraph {
	height: 5px;
	border-top: 0;
	background: #993232;
	border-radius: 5px;
}
</style>

<h1 class="text-center">
	<spring:message code="register.create" />
</h1>

<div class="container">

	<div class="row" style="margin-top: 20px">
		<div
			class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
			<form:form action="register/saveCustomer.do"
				modelAttribute="customerForm" role="form">
				<fieldset>
					<hr class="colorgraph">
					<h3>
						<spring:message code="register.userInfoHeader" />
					</h3>
					<div class="form-group">
						<spring:message code="register.username" var="username" />
						<form:input path="username" type="text" name="username"
							id="username" class="form-control input-lg"
							placeholder="${username}" />
						<form:errors class="error" path="username" />
					</div>
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<spring:message code="register.password" var="password" />
								<form:input path="password" type="password" name="password"
									id="password" class="form-control input-lg"
									placeholder="${password}" />
								<form:errors class="error" path="password" />
							</div>
						</div>
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<spring:message code="register.passwordRepeat"
									var="repeatPassword" />
								<form:input path="repeatPassword" type="password"
									name="repeatPassword" id="repeatPassword"
									class="form-control input-lg" placeholder="${repeatPassword}" />
								<form:errors class="error" path="repeatPassword" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<spring:message code="register.email" var="email" />
						<form:input path="email" type="email" name="email" id="email"
							class="form-control input-lg" placeholder="${email}" />
						<form:errors class="error" path="username" />
					</div>
					<h3>
						<spring:message code="register.personalInfoHeader" />
					</h3>

					<div class="row">
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<spring:message code="register.name" var="name" />
								<form:input path="name" type="text" name="name" id="name"
									class="form-control input-lg" placeholder="${name}" />
								<form:errors class="error" path="name" />
							</div>
						</div>
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<spring:message code="register.surname" var="surname" />
								<form:input path="surname" type="text" name="surname"
									id="surname" class="form-control input-lg"
									placeholder="${surname}" />
								<form:errors class="error" path="surname" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<spring:message code="register.city" var="city" />
								<form:input path="city" type="text" name="city" id="city"
									class="form-control input-lg" placeholder="${city}" />
								<form:errors class="error" path="city" />
							</div>
						</div>
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<spring:message code="register.nationality" var="nationality" />
								<form:input path="nationality" type="text" name="nationality"
									id="nationality" class="form-control input-lg"
									placeholder="${nationality}" />
								<form:errors class="error" path="nationality" />
							</div>
						</div>
					</div>
					<hr class="colorgraph">
					<h3>
						<spring:message code="register.bodyInfoHeader" />
					</h3>
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><spring:message
											code="register.weight" /></span>
									<form:input path="weight" name="weight" id="weight"
										class="form-control input-lg" />
									<span class="input-group-addon">kg</span>
								</div>
								<form:errors class="error" path="weight" />
							</div>
						</div>
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><spring:message
											code="register.height" /></span>
									<form:input path="height" name="height" id="height"
										class="form-control input-lg" placeholder="${height}" />
									<span class="input-group-addon">m</span>
								</div>
								<form:errors class="error" path="height" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><spring:message
											code="register.bodyFat" /></span>
									<form:input path="bodyfat" name="bodyfat" id="bodyfat"
										class="form-control input-lg" />
									<span class="input-group-addon">kg</span>
								</div>
								<form:errors class="error" path="bodyfat" />
							</div>
						</div>
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><spring:message
											code="register.waistlineMeasure" /></span>
									<form:input path="waistlineMeasure" name="waistlineMeasure"
										id="waistlineMeasure" class="form-control input-lg" />
									<span class="input-group-addon">cm</span>
								</div>
								<form:errors class="error" path="waistlineMeasure" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><spring:message
											code="register.hipMeasure" /></span>
									<form:input path="hipMeasure" name="hipMeasure" id="hipMeasure"
										class="form-control input-lg" />
									<span class="input-group-addon">kg</span>
								</div>
								<form:errors class="error" path="hipMeasure" />
							</div>
						</div>
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><spring:message
											code="register.chestMeasure" /></span>
									<form:input path="chestMeasure" name="chestMeasure"
										id="chestMeasure" class="form-control input-lg"
										placeholder="${chestMeasure}" />
									<span class="input-group-addon">cm</span>
								</div>
								<form:errors class="error" path="chestMeasure" />
							</div>
						</div>
					</div>
					<hr class="colorgraph">
					<span class="button-checkbox"> <label class="checkbox">
							<form:checkbox code="register.TOSAccepted" path="TOSAccepted" />
							<spring:message code="register.TOSAccepted" /> <a
							href="tos/tos.do"><spring:message code="register.tos" /></a>
					</label>
					</span> <br />
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<input type="submit" name="save"
								class="btn btn-lg btn-success btn-block"
								value="<spring:message code="register.create" />">
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
