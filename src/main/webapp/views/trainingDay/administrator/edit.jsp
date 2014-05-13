<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<style>
body {
	padding-top: 20px;
}
</style>

<div class="container">
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<div class="well well-sm">
				<form:form action="trainingDay/administrator/edit.do"
					modelAttribute="trainingDay" class="form-horizontal">
					<form:hidden path="id" />
					<form:hidden path="version" />
					<form:hidden path="entityLanguage" />

					<fieldset>
						<h1 class="text-center">
							<spring:message code="trainingDay.details" />
						</h1>
						<br />
						<!-- Name input-->
						<div class="form-group">
							<form:label path="name" class="col-md-3 control-label" for="name">
								<spring:message code="trainingDay.name" />
							</form:label>
							<div class="col-md-6">
								<form:select path="name" class="form-control">
									<form:options items="${days}" />
								</form:select>
							</div>
							<form:errors path="name" cssClass="error" />
						</div>

						<div class="form-group">
							<form:label path="descriptiveName" class="col-md-3 control-label"
								for="descriptiveName">
								<spring:message code="trainingDay.descName" />
							</form:label>
							<div class="col-md-6">
								<form:textarea path="descriptiveName" id="descriptiveName"
									name="descriptiveName" type="text" class="form-control" />
							</div>
							<form:errors path="descriptiveName" cssClass="error" />
						</div>
						<div class="form-group">
							<form:label path="exerciseGroups" class="col-md-3 control-label">
								<spring:message code="trainingDay.exerciseGroups" />
							</form:label>
							<div class="col-md-6">
								<form:select multiple="${exerciseGroups.size()}"
									items="${exerciseGroups}" itemLabel="name" id="id"
									code="trainingDay.exerciseGroups" path="exerciseGroups"
									class="form-control" />
								<form:errors path="exerciseGroups" cssClass="error" />
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
