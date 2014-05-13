<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h1 class="text-center">
	<spring:message code="issue.title" />
</h1>

<div class="container">
	<div class="row">
		<div class="col-md-10">
			<form:form action="issue/customer/edit.do" modelAttribute="issue"
				role="form">

				<!-- Poner todos los atributos, los no usados en oculto -->

				<form:hidden path="id" />
				<form:hidden path="customer" />
				<form:hidden path="plan" />
				<form:hidden path="version" />
				<form:hidden path="entityLanguage" />

				<div class="col-md-6 col-md-offset-3">
					<div class="row">
						<br /> <br />
						<h4>
							<spring:message code="issue.explain" />
						</h4>
					</div>
					<div class="row">
						<div class="form-group">
							<form:textarea path="description" rows="3" class="form-control" />
							<form:errors path="description" cssClass="error" />
						</div>
					</div>
					<div class="row">
						<input type="submit" name="save" class="btn btn-default"
							value="<spring:message code="issue.send" />" /> <a
							href="plan/customer/list.do"><input type="button"
							class="btn btn-default"
							value="<spring:message code="issue.cancel"/>" id="cancelar"
							name="cancelar"
							onclick="self.location.href = plan/customer/list.do" /></a>
					</div>
				</div>
			</form:form>
		</div>
		<div class="col-md-2">
			<img class="media-object img-rounded img-responsive"
				src="http://placehold.it/200x100" alt="placehold.it/200x100"><br>
			<img class="media-object img-rounded img-responsive"
				src="http://placehold.it/200x100" alt="placehold.it/200x100"><br>
			<img class="media-object img-rounded img-responsive"
				src="http://placehold.it/200x100" alt="placehold.it/200x100"><br>
			<img class="media-object img-rounded img-responsive"
				src="http://placehold.it/200x100" alt="placehold.it/200x100"><br>
			<img class="media-object img-rounded img-responsive"
				src="http://placehold.it/200x100" alt="placehold.it/200x100"><br>
			<img class="media-object img-rounded img-responsive"
				src="http://placehold.it/200x100" alt="placehold.it/200x100"><br>
		</div>
	</div>
</div>
