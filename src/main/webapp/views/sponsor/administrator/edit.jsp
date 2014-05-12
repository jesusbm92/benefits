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
		<div class="col-md-6 col-md-offset-3">
			<div class="well well-sm">

				<form:form action="sponsor/administrator/edit.do"
					modelAttribute="sponsor" role="form" class="form-horizontal">

					<form:hidden path="id" />
					<form:hidden path="version" />

					<fieldset>
						<h1 class="text-center">
							<spring:message code="sponsor.details" />
						</h1>
						<br />

						<!-- Name input-->
						<div class="form-group">
							<form:label path="name" class="col-md-3 control-label" for="name">
								<spring:message code="sponsor.name" />
							</form:label>
							<div class="col-md-6">
								<form:input path="name" id="name" name="name" type="text"
									class="form-control" />
							</div>
							<form:errors path="name" cssClass="error" />
						</div>

						<!-- Description -->
						<div class="form-group">
							<form:label path="address" class="col-md-3 control-label"
								for="address">
								<spring:message code="sponsor.address" />
							</form:label>
							<div class="col-md-6">
								<form:input path="address" id="address" name="address"
									type="text" class="form-control" />

							</div>
							<form:errors path="address" cssClass="error" />
						</div>

						<div class="form-group">
							<form:label path="email" class="col-md-3 control-label"
								for="email">
								<spring:message code="sponsor.email" />
							</form:label>
							<div class="col-md-6">
								<form:input path="email" id="email" name="email" type="text"
									class="form-control" />
							</div>
							<form:errors path="email" cssClass="error" />
						</div>

						<div class="form-group">
							<form:label path="phone" class="col-md-3 control-label"
								for="phone">
								<spring:message code="sponsor.phone" />
							</form:label>
							<div class="col-md-6">
								<form:input path="phone" id="phone" name="phone" type="text"
									class="form-control" />
							</div>
							<form:errors path="phone" cssClass="error" />
						</div>
						<br/>						
						<div class="form-group">
							<div class="col-md-12 text-center">
								<input type="submit" name="save" class="btn btn-primary btn-lg"
									value="<spring:message code="sponsor.save" />" />

								<jstl:if test="${!create}">

									<a class="btn btn-primary btn-lg" data-toggle="modal"
										data-target="#basicModal"><spring:message
											code="sponsor.delete" /></a>

									<div class="modal fade" id="basicModal" tabindex="-1"
										role="dialog" aria-labelledby="basicModal" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title" id="myModalLabel">
														<spring:message code="sponsor.confirm.title" />
													</h4>
												</div>
												<div class="modal-body">
													<h3>
														<spring:message code="sponsor.confirm.body" />
													</h3>
												</div>
												<div class="modal-footer">
													<button type="submit" name="delete"
														class="btn btn-default" onclick="history.back()">
														<spring:message code="sponsor.confirm.yes" />
													</button>
													<button type="button" class="btn btn-primary"
														data-dismiss="modal">
														<spring:message code="sponsor.confirm.no" />
													</button>
												</div>
											</div>
										</div>
									</div>

								</jstl:if>

								<input type="button" class="btn btn-primary btn-lg"
									value="<spring:message code="sponsor.cancel"/>"
									onclick="history.back()" />
							</div>
						</div>
						<!-- Form actions -->
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>
</div>
