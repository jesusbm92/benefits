<%--
 * about.jsp
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
html,body {
	position: relative;
	height: 100%;
}

.login-container {
	position: relative;
	width: 500px;
	margin: 25px auto;
	padding: 20px 40px 40px;
	text-align: center;
	background: #fff;
	border: 1px solid #ccc;
}

#output {
	position: absolute;
	width: 300px;
	top: -75px;
	left: 0;
	color: #fff;
}

.login-container::before,.login-container::after {
	content: "";
	position: absolute;
	width: 100%;
	height: 100%;
	top: 3.5px;
	left: 0;
	background: #fff;
	z-index: -1;
	-webkit-transform: rotateZ(4deg);
	-moz-transform: rotateZ(4deg);
	-ms-transform: rotateZ(4deg);
	border: 1px solid #ccc;
}

.login-container::after {
	top: 5px;
	z-index: -2;
	-webkit-transform: rotateZ(-2deg);
	-moz-transform: rotateZ(-2deg);
	-ms-transform: rotateZ(-2deg);
}

p {
font-size:17px
}
</style>

<div class="container">
	<div class="login-container">
		<div id="output"></div>
		<div class="form-box">

			<h2>
				<spring:message code="about.why" />
			</h2>
			<br />
			<p>
				<spring:message code="about.first" />
			</p>
			<br />
			<p>
				<spring:message code="about.second" />
			</p>
			<br /> <a href="register/registerCustomer.do"><input
				type="button" class="btn btn-lg btn-primary btn-block"
				value="<spring:message code="master.page.guest.register"/>"
				onclick="self.location.href = register/registerCustomer.do" /></a>

		</div>
	</div>

</div>
