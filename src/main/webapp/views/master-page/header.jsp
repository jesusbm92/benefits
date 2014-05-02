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
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>


<style>
.logo-pos {
	margin-left: 5%;
	margin-top: 2%;
	margin-bottom: 1%;
}
</style>

<!-- <div class="logo-pos"> -->
<!-- 	<img src="images/benefits-transparente.png" alt="Benefits Co., Inc." /> -->
<!-- </div> -->

<div class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- 		<div class="collapse navbar-collapse" -->
		<!-- 			id="bs-example-navbar-collapse-1"> -->
		<div class="navbar-header">
			<a class="brand" href=""><img src="images/logo_navbar.png"
				style="border: 0; height: 35px; margin-top: 5px;"></a>
			<!-- 			<a class="navbar-brand" href="#" src="images/benefits-transparente.png">Benefits</a> -->
		</div>

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
						<li><a href="plan/administrator/create.do"><spring:message
									code="plan.create" /></a></li>
					</ul></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown"><spring:message
							code="master.page.administrator.training" /><b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li class="arrow"></li>
						<li><a href="training/administrator/list.do"><spring:message
									code="master.page.admin.trainingAll" /></a></li>
						<li><a href="training/administrator/listAsignTraining.do"><spring:message
									code="master.page.admin.trainingAsigned" /></a></li>
						<li><a
							href="training/administrator/listNotTrainingAsigned.do"><spring:message
									code="master.page.admin.trainingFree" /></a></li>
						<li><a href="exercise/administrator/list.do"><spring:message
									code="master.page.admin.listExercise" /></a></li>
						<li><a href="exerciseGroup/administrator/list.do"><spring:message
									code="master.page.admin.listExerciseGroup" /></a></li>
						<li><a href="trainingDay/administrator/list.do"><spring:message
									code="master.page.admin.listTrainingDay" /></a></li>
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
						<li><a href="day/administrator/list.do"><spring:message
									code="master.page.admin.listDay" /></a></li>
						<li><a href="day/administrator/create.do"><spring:message
									code="master.page.admin.createDay" /></a></li>
						<li><a href="meal/administrator/list.do"><spring:message
									code="master.page.admin.meal" /></a></li>
						<li><a href="food/administrator/list.do"><spring:message
									code="master.page.admin.foodAll" /></a></li>

					</ul></li>
			</security:authorize>

			<security:authorize access="hasRole('CUSTOMER')">
				<li class="dropdown"><a class="fNiv"
					href="plan/customer/list.do"><spring:message
							code="master.page.customer.plans" /></a></li>
			</security:authorize>

			<security:authorize access="isAnonymous()">
				<li class="dropdown"><a class="fNiv" href="security/login.do"><spring:message
							code="master.page.login" /></a></li>
				<li class="dropdown"><a class="fNiv"
					href="register/registerCustomer.do"><spring:message
							code="master.page.guest.register" /></a></li>
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
							<li><a href="register/registerAdministrator.do"><spring:message
										code="master.page.guest.registerAsAdministrator" /></a></li>
						</security:authorize>
						<security:authorize access="hasRole('CUSTOMER')">
							<li><a href="profile/customer/edit.do"><spring:message
										code="master.page.profile.administrator.edit" /></a></li>
						</security:authorize>
						<li class="divider"></li>
						<li><a href="j_spring_security_logout"><spring:message
									code="master.page.logout" /> </a></li>
					</ul></li>
			</security:authorize>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><spring:message
						code="master.page.language" /> <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="?language=en">EN</a></li>
					<li><a href="?language=es">ES</a></li>
				</ul></li>
		</ul>
	</div>
</div>

<div class="container">
	<div class="col-md-6 col-centered">
	<jstl:choose>
		<jstl:when test="${message != null}">
			<br />
			<br/>
			<br/>
			<div class="alert alert-block alert-danger">
			<a class="close" data-dismiss="alert">×</a>
			<spring:message
					code="${message}" /></div>
		</jstl:when>
		<jstl:when test="${successMessage != null}">
			<br />
			<br/>
			<br/>
			<div class="alert alert-block alert-success">
			<a class="close" data-dismiss="alert">×</a>
			<spring:message
					code="${successMessage}" /></div>
		</jstl:when>
		<jstl:otherwise>
			<br/>
			<br/>
			<br/>
			<br/>
		</jstl:otherwise>
	</jstl:choose>
	</div>
</div>