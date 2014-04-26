<%--
 * index.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
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

<style>
.jumbotron {
	margin-bottom: 0px;
	background-image: url(images/gym_opacity_black.png);
	background-position: 0% 25%;
	background-size: cover;
	background-repeat: no-repeat;
	color: white;
	text-shadow: black 0.3em 0.3em 0.3em;
}
</style>

<div class="arriba">
	<div class="jumbotron">
		<div class="row">
			<div class="col-md-7">
				<h1>
					<spring:message code="welcome.header" />
				</h1>
				<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
				<p>
					<spring:message code="welcome.description" />
				</p>
				<br />
				<p>
					<a class="btn btn-primary btn-lg" role="button"><spring:message
							code="welcome.button.whats" /></a>
				</p>
			</div>
			<div class="col-md-3 col-md-offset-1">
				<security:authorize access="isAnonymous()">
					<h2 class="text-center">
						<spring:message code="welcome.signin" />
					</h2>
					<br />
					<form:form action="j_spring_security_check"
						modelAttribute="credentials" class="form-signin" role="form">
						<br />

						<spring:message code="security.username" var="username" />
						<form:input path="username" class="form-control"
							placeholder="${username}" required="" autofocus="" />
						<form:errors class="error" path="username" />
						<br />
						<spring:message code="security.password" var="password" />
						<form:password path="password" class="form-control"
							placeholder="${password}" required="" />
						<form:errors class="error" path="password" />
						<label class="checkbox"> <input type="checkbox"
							name="_spring_security_remember_me" /> <spring:message
								code="security.rememberMe" />
						</label>
						<br />

						<jstl:if test="${showError == true}">
							<div class="error">
								<spring:message code="security.login.failed" />
							</div>
						</jstl:if>

						<input type="submit" class="btn btn-lg btn-success btn-block"
							value="<spring:message code="security.login" />" />
						<br/>
						<a
							href="training/details.do?trainingId=${plan.getTraining().getId()}"><input
							type="button" class="btn btn-lg btn-primary btn-block"
							value="<spring:message code="master.page.guest.register"/>"
							onclick="self.location.href = register/registerCustomer.do" /></a>


					</form:form>
				</security:authorize>
				<security:authorize access="hasRole('ADMIN')">
					<br />
					<br />
					<h2>
						<spring:message code="welcome.hello" />
						<security:authentication property="principal.username" />
						!
					</h2>
					<br />
					<div class="row">
						<a class="btn btn-primary btn-lg" role="button"
							href="plan/administrator/list.do"><spring:message
								code="master.page.admin.plansAll" /></a>
					</div>
					<br />
					<div class="row">
						<a class="btn btn-primary btn-lg" role="button"
							href="training/administrator/list.do"><spring:message
								code="master.page.admin.trainingAll" /></a>
					</div>
					<br />
					<div class="row">
						<a class="btn btn-primary btn-lg" role="button"
							href="diet/administrator/list.do"><spring:message
								code="master.page.admin.dietAll" /></a>
					</div>
				</security:authorize>
				<security:authorize access="hasRole('CUSTOMER')">
					<br />
					<br />
					<h2>
						<spring:message code="welcome.hello" />
						<security:authentication property="principal.username" />
						!
					</h2>
					<br />
					<a class="btn btn-primary btn-lg" role="button"
						href="plan/customer/list.do"><spring:message
							code="welcome.button.myplan" /></a>
				</security:authorize>
			</div>
		</div>
	</div>
</div>