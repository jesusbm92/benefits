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

<div>
	<img src="images/logo.png" alt="Benefits Co., Inc." />
</div>


<div class="navbar navbar-default">
	<div class="container">
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<!-- Do not forget the "fNiv" class for the first level links !! -->
				<security:authorize access="hasRole('ADMIN')">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown"><spring:message
								code="master.page.administrator.plan" /><b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li class="arrow"></li>
							<li><a href="plan/administrator/list.do"><spring:message
										code="master.page.admin.plansAll" /></a></li>
						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown"><spring:message
								code="master.page.administrator.training" /><b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li class="arrow"></li>
							<li><a href="training/administrator/list.do"><spring:message
										code="master.page.admin.trainingAll" /></a></li>
							<li><a href="training/administrator/list.do"><spring:message
										code="master.page.admin.trainingAsigned" /></a></li>
							<li><a href="training/administrator/list.do"><spring:message
										code="master.page.admin.trainingFree" /></a></li>
						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown"><spring:message
								code="master.page.administrator.diet" /><b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li class="arrow"></li>
							<li><a href="diet/administrator/list.do"><spring:message
										code="master.page.admin.dietAll" /></a></li>
							<li><a href="diet/administrator/listAssigned.do"><spring:message
										code="master.page.admin.dietAsigned" /></a></li>
							<li><a href="diet/administrator/listFree.do"><spring:message
										code="master.page.admin.dietFree" /></a></li>
							<li><a href="food/administrator/list.do"><spring:message
										code="master.page.admin.foodAll" /></a></li>

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
							<li><a href="profile/action-1.do"><spring:message
										code="master.page.profile.action.1" /></a></li>
							<li><a href="profile/action-2.do"><spring:message
										code="master.page.profile.action.2" /></a></li>
							<li><a href="profile/action-3.do"><spring:message
										code="master.page.profile.action.3" /></a></li>
							<li><a href="j_spring_security_logout"><spring:message
										code="master.page.logout" /> </a></li>
						</ul></li>
				</security:authorize>
			</ul>
		</div>
	</div>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>