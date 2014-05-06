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

<h1 class="text-center"><spring:message code="plan.createnew" /></h1>
<br/>

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
					<div class="col-md-7">
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
				<div class="form-group">
					<form:label path="diet" for="dietId" class="col-md-4 control-label">
						<spring:message code="plan.diet" />
					</form:label>
					<div class="col-md-6">
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
					<div class="col-md-6">
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

						<a class="btn btn-default" data-toggle="modal"
							data-target="#basicModal"><spring:message code="plan.delete" /></a>

						<div class="modal fade" id="basicModal" tabindex="-1"
							role="dialog" aria-labelledby="basicModal" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="myModalLabel">
											<spring:message code="plan.confirm.title" />
										</h4>
									</div>
									<div class="modal-body">
										<h3>
											<spring:message code="plan.confirm.body" />
										</h3>
									</div>
									<div class="modal-footer">
										<button type="submit" name="delete" class="btn btn-default"
											onclick="history.back()">
											<spring:message code="plan.confirm.yes" />
										</button>
										<button type="button" class="btn btn-primary"
											data-dismiss="modal">
											<spring:message code="plan.confirm.no" />
										</button>
									</div>
								</div>
							</div>
						</div>

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

