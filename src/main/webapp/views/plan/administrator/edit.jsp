<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@page import="domain.Goals"%>

<div class="container">
	<div class="row">
		<div class="col-md-4 col-centered">
			<form:form action="plan/administrator/edit.do" modelAttribute="plan"
				role="form" class="form-horizontal">

				<!-- Poner todos los atributos, los no usados en oculto -->

				<form:hidden path="id" />
				<form:hidden path="version" />
				<form:hidden path="customers" />
				<form:hidden path="comments" />
				<form:hidden path="issues" />

				<div class="form-group">
					<form:label path="goal" class="col-md-4 control-label">
						<spring:message code="plan.goal" />
					</form:label>
					<div class="col-md-4">
						<form:select path="goal" class="form-control">
							<form:options items="${goals}" />
						</form:select>
						<form:errors cssClass="error" path="goal"></form:errors>
						<br>
					</div>
				</div>
				<div class="form-group">
					<form:label path="diet" for="dietId" class="col-md-4 control-label">
						<spring:message code="plan.diet" />
					</form:label>
					<div class="col-md-4">
						<form:select id="dietId" path="diet" class="form-control">
							<form:option value="0" label="----" />
							<form:options items="${diets}" itemValue="id" itemLabel="name" />
						</form:select>
						<form:errors path="diet" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="training" for="trainingId"
						class="col-md-4 control-label">
						<spring:message code="plan.training" />
					</form:label>
					<div class="col-md-4">
						<form:select id="trainingId" path="training" class="form-control">
							<form:option value="0" label="----" />
							<form:options items="${trainings}" itemValue="id"
								itemLabel="name" />
						</form:select>
						<form:errors path="training" cssClass="error" />
					</div>
				</div>



				<div class="form-group">

					<input type="submit" name="save" class="btn btn-default"
						value="<spring:message code="plan.save" />" />

					<jstl:if test="${!create}">
						<input type="submit" class="btn btn-default" name="delete"
							value="<spring:message code="plan.delete"/>"
							onclick="return confirm('<spring:message code="plan.delete"/>')" />
					</jstl:if>

					<a href="plan/administrator/list.do"><input type="button"
						class="btn btn-default"
						value="<spring:message code="plan.cancel"/>" id="cancelar"
						name="cancelar"
						onclick="self.location.href = plan/administrator/list.do" /></a>
				</div>
			</form:form>

		</div>
	</div>
</div>

