<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<style>
.logo-pos {
	margin-left: 5%;
	margin-top: 2%;
	margin-bottom: 1%;
}
</style>

<div class="logo-pos">
	<img src="images/benefits-transparente.png" alt="Benefits Co., Inc." />
</div>

<div class="navbar navbar-default">
	<div class="container">
		<!-- 		<div class="collapse navbar-collapse" -->
		<!-- 			id="bs-example-navbar-collapse-1"> -->
		<ul class="nav navbar-nav">
			<!-- Do not forget the "fNiv" class for the first level links !! -->
			<security:authorize access="hasRole('ADMIN')">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown"><spring:message
							code="master.page.administrator" /><b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li class="arrow"></li>
						<li><a href="plan/administrator/list.do"><spring:message
									code="master.page.admin.plansAll" /></a></li>
					</ul></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown"><spring:message
							code="master.page.guest.register" /><b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li class="arrow"></li>
						<li><a href="register/registerAdministrator.do"><spring:message
									code="master.page.guest.registerAsAdministrator" /></a></li>
					</ul></li>
			</security:authorize>

			<security:authorize access="hasRole('CUSTOMER')">
				<li class="dropdown"><a class="fNiv dropdown-toggle"
					data-toggle="dropdown"><spring:message
							code="master.page.customer" /><b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li class="arrow"></li>
						<li><a href="plan/customer/list.do"><spring:message
									code="master.page.customer.plans" /></a></li>
					</ul></li>
			</security:authorize>

			<security:authorize access="isAnonymous()">
				<li class="dropdown"><a class="fNiv" href="security/login.do"><spring:message
							code="master.page.login" /></a></li>
				<li class="dropdown"><a class="fNiv dropdown-toggle"
					data-toggle="dropdown"><spring:message
							code="master.page.guest.register" /><b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li class="arrow"></li>
						<li><a href="register/registerCustomer.do"><spring:message
									code="master.page.guest.registerAsCustomer" /></a></li>
					</ul></li>
			</security:authorize>

			<security:authorize access="isAuthenticated()">
				<li class="dropdown"><a class="fNiv dropdown-toggle"
					data-toggle="dropdown"> <spring:message
							code="master.page.profile" /> (<security:authentication
							property="principal.username" />) <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li class="arrow"></li>
						<security:authorize access="hasRole('ADMIN')">
							<li><a href="profile/administrator/edit.do"><spring:message
										code="master.page.profile.administrator.edit" /></a></li>
						</security:authorize>
						<security:authorize access="hasRole('CUSTOMER')">
							<li><a href="profile/customer/edit.do"><spring:message
										code="master.page.profile.administrator.edit" /></a></li>
						</security:authorize>
						<li><a href="j_spring_security_logout"><spring:message
									code="master.page.logout" /> </a></li>
					</ul></li>
			</security:authorize>

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><spring:message code="master.page.language" /> <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="?language=en">EN</a></li>
					<li><a href="?language=es">ES</a></li>
				</ul></li>
		</ul>
		<!-- 		</div> -->
	</div>
</div>