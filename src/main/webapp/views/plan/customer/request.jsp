<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<div class="container">
	<div class="row">
		<div class="col-md-10">
			<h1 class="text-center">
				<spring:message code="plan.request.goal" />
			</h1>

			<form:form action="plan/customer/request.do" modelAttribute="plan"
				role="form">

				<!-- Poner todos los atributos, los no usados en oculto -->

				<form:hidden path="id" />
				<form:hidden path="customers" />
				<form:hidden path="comments" />
				<form:hidden path="issues" />
				<form:hidden path="diet" />
				<form:hidden path="training" />

				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<br /> <br />
						<div class="form-group">
							<form:label path="goal" class="control-label">
							</form:label>
							<form:select path="goal" class="form-control">
								<jstl:forEach var="goal" items="${goals}">
									<form:option value="${goal}">
										<spring:message code="plan.goal.${goal}" />
									</form:option>
								</jstl:forEach>
							</form:select>
							<form:errors cssClass="error" path="goal"></form:errors>
							<br>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="text-center">
						<div class="btn-group">
							<input type="submit" name="save" class="btn btn-default"
								value="<spring:message code="plan.request.save" />" /> <a
								href="plan/customer/list.do"><input type="button"
								class="btn btn-default"
								value="<spring:message code="plan.cancel"/>" id="cancelar"
								name="cancelar"
								onclick="self.location.href = plan/customer/list.do" /></a>
						</div>
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
<br />
<br />
<br />
<br />