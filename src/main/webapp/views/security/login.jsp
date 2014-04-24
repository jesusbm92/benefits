
<%--
 * login.jsp
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


<div class="container">
	<div class="row">
		<div class="col-md-3 col-centered">
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
					name="_spring_security_remember_me" />
					<spring:message code="security.rememberMe" />
				</label>
				<br />

				<jstl:if test="${showError == true}">
					<div class="error">
						<spring:message code="security.login.failed" />
					</div>
				</jstl:if>

				<input type="submit" class="btn btn-lg btn-primary btn-block"
					value="<spring:message code="security.login" />" />

			</form:form>
		</div>
	</div>
</div>
<br/>
<br/>
<br/>