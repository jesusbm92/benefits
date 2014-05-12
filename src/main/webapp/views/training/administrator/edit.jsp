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

<style>
body {
	padding-top: 20px;
}
</style>

<div class="container">
	<div class="row">
		<div class="col-md-7 col-md-offset-2">
			<div class="well well-sm">
				<form:form action="training/administrator/edit.do"
					modelAttribute="training" class="form-horizontal">

					<form:hidden path="id" />
					<form:hidden path="version" />
					<form:hidden path="entityLanguage" />

					<fieldset>
						<h1 class="text-center">
							<spring:message code="training.details" />
						</h1>
						<br />
						<!-- Name input-->
						<div class="form-group">
							<form:label path="name" class="col-md-3 control-label" for="name">
								<spring:message code="training.name" />
							</form:label>
							<div class="col-md-6">
								<form:input path="name" id="name" name="name" type="text"
									class="form-control" />
							</div>
							<form:errors path="name" cssClass="error" />
						</div>

						<div class="form-group">
							<form:label path="description" class="col-md-3 control-label"
								for="description">
								<spring:message code="training.description" />
							</form:label>
							<div class="col-md-6">
								<form:input path="description" id="description"
									name="description" type="text" class="form-control" />
							</div>
							<form:errors path="description" cssClass="error" />
						</div>

						<div class="form-group">
							<form:label path="duration" class="col-md-3 control-label"
								for="duration">
								<spring:message code="training.duration" />
							</form:label>
							<div class="col-md-6">
								<form:input path="duration" id="duration" name="duration"
									type="text" class="form-control" />
							</div>
							<form:errors path="duration" cssClass="error" />
						</div>

						<div class="form-group">
							<form:label path="sponsor" class="col-md-3 control-label">
								<spring:message code="training.sponsor" />
							</form:label>
							<div class="col-md-6">
								<form:select id="sponsors" path="sponsor" class="form-control">
									<form:option value="0" label="----" />
									<form:options items="${sponsors }" itemValue="id"
										itemLabel="name" />
								</form:select>
								<form:errors path="sponsor" cssClass="error" />
							</div>
						</div>
						<div class="form-group">
							<form:label path="trainingDays" class="col-md-3 control-label">
								<spring:message code="training.trainingDays" />
							</form:label>
							<div class="col-md-6">
								<form:select multiple="${trainingDays.size()}"
									items="${trainingDays}" itemLabel="descriptiveName" id="id"
									code="training.trainingDays" path="trainingDays" class="form-control" />
								<form:errors path="trainingDays" cssClass="error" />
								<br>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12 text-center">
								<input type="submit" name="save" class="btn btn-primary btn-lg"
									value="<spring:message code="plan.save" />" />
								<jstl:if test="${!create}">
									<a class="btn btn-primary btn-lg" data-toggle="modal"
										data-target="#basicModal"><spring:message
											code="plan.delete" /></a>
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
									class="btn btn-primary btn-lg"
									value="<spring:message code="plan.cancel"/>" id="cancelar"
									name="cancelar"
									onclick="self.location.href = plan/administrator/list.do" /></a>
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>
</div>
