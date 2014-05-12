<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@page import="domain.Days"%>
<%@page import="domain.Language"%>


<style>
body {
	padding-top: 20px;
}
</style>

<h1 class="text-center">
	<spring:message code="day.create.day" />
</h1>


<div class="container">
	<div class="row">
		<div class="col-md-7 col-md-offset-2">
			<div class="well well-sm">
				<form:form class="form-horizontal"
					action="day/administrator/edit.do" modelAttribute="day">

					<form:hidden path="id" />
					<form:hidden path="version" />
					<form:hidden path="entityLanguage" />

					<fieldset>
						<h1 class="text-center">
							<spring:message code="day.create.day" />
						</h1>
						<br />
						<!-- Name -->
						<div class="form-group">
							<form:label path="name" class="col-md-3 control-label" for="name">
								<spring:message code="day.name" />
							</form:label>
							<div class="col-md-6">
								<form:select path="name" class="form-control">
									<jstl:forEach var="name" items="${names}">
										<form:option value="${name}">
											<spring:message code="day.name.${name}" />
										</form:option>
									</jstl:forEach>
								</form:select>
							</div>
							<form:errors path="name" cssClass="error" />
						</div>

						<!-- Descriptive Name -->
						<div class="form-group">
							<form:label path="descriptiveName" class="col-md-3 control-label"
								for="description">
								<spring:message code="day.descName" />
							</form:label>
							<div class="col-md-6">

								<form:input type="text" path="descriptiveName"
									class="form-control" id="description" name="description" />
							</div>
							<form:errors path="descriptiveName" cssClass="error" />
						</div>

						<!-- Meals -->
						<div class="form-group">
							<form:label path="meals" for="mealsId"
								class="col-md-3 control-label">
								<spring:message code="day.meals.edit" />
							</form:label>
							<div class="col-md-6">
								<form:select multiple="${meals.size()}" items="${meals}"
									itemLabel="name" id="id" code="day.meal" path="meals"
									class="form-control multiselect" />
								<form:errors path="meals" cssClass="error" />
							</div>
						</div>


						<br />
						<!-- Form actions -->
						<div class="form-group">
							<div class="col-md-12 text-center">
								<input type="submit" name="save" class="btn btn-primary btn-lg"
									value="<spring:message code="day.save" />" />
								<jstl:if test="${!create}">
									<a class="btn btn-primary btn-lg" data-toggle="modal"
										data-target="#basicModal"><spring:message
											code="day.delete" /></a>
									<div class="modal fade" id="basicModal" tabindex="-1"
										role="dialog" aria-labelledby="basicModal" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title" id="myModalLabel">
														<spring:message code="day.confirm.title" />
													</h4>
												</div>
												<div class="modal-body">
													<h3>
														<spring:message code="day.confirm.body" />
													</h3>
												</div>
												<div class="modal-footer">
													<button type="submit" name="delete" class="btn btn-default"
														onclick="history.back()">
														<spring:message code="day.confirm.yes" />
													</button>
													<button type="button" class="btn btn-primary"
														data-dismiss="modal">
														<spring:message code="day.confirm.no" />
													</button>
												</div>
											</div>
										</div>
									</div>
								</jstl:if>
								<a href="day/administrator/list.do"><input type="button"
									class="btn btn-primary btn-lg"
									value="<spring:message code="day.cancel"/>" id="cancelar"
									name="cancelar"
									onclick="self.location.href = day/administrator/list.do" /></a>
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>
</div>
