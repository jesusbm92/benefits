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
	<spring:message code="plan.change" />
</h1>
<br/>

<div class="container">
	<div class="row">
		<div class="col-md-4 col-centered">
			<form:form action="plan/administrator/change.do"
				modelAttribute="customer" role="form" class="form-horizontal">

				<!-- Poner todos los atributos, los no usados en oculto -->

				<form:hidden path="id" />
				<form:hidden path="userAccount" />
				<form:hidden path="version" />
				<form:hidden path="name" />
				<form:hidden path="email" />
				<form:hidden path="surname" />
				<form:hidden path="city" />
				<form:hidden path="nationality" />
				<form:hidden path="weight" />
				<form:hidden path="height" />
				<form:hidden path="bodyfat" />
				<form:hidden path="waistlineMeasure" />
				<form:hidden path="hipMeasure" />
				<form:hidden path="chestMeasure" />
				<form:hidden path="issues" />

				<div class="form-group">
					<form:label path="plan" class="col-md-4 control-label">
						<spring:message code="plan.plan" />
					</form:label>
					<div class="col-md-7">
						<form:select path="plan" class="form-control">
							<form:options items="${plans}" itemValue="id" itemLabel="name" />
						</form:select>
						<form:errors path="plan" cssClass="error" />
					</div>
				</div>

				<input type="submit" name="saveChange" class="btn btn-default"
					value="<spring:message code="plan.save" />" />


				<a href="issue/administrator/listAll.do"><input type="button"
					class="btn btn-default"
					value="<spring:message code="plan.cancel"/>" id="cancelar"
					name="cancelar"
					onclick="self.location.href = issue/administrator/listAll.do" /></a>

			</form:form>

		</div>
	</div>
</div>

