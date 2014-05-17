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
				<form:form class="form-horizontal"
					action="amount/administrator/edit.do" modelAttribute="amount">
					<form:hidden path="id" />
					<form:hidden path="version" />
					<form:hidden path="meal" />

					<fieldset>
						<h1 class="text-center">
							<spring:message code="amount.details" />
						</h1>
						<br />


						<!-- Name input-->
						<div class="form-group">
							<form:label path="quantity" class="col-md-3 control-label"
								for="quantity">
								<spring:message code="amount.quantity" />
							</form:label>
							<div class="col-md-6">
								<form:input path="quantity" id="quantity" name="quantity"
									type="text" class="form-control" />
							</div>
							<form:errors path="quantity" cssClass="error" />
						</div>

						<!-- Measure input-->
						<div class="form-group">
							<form:label path="measure" class="col-md-3 control-label"
								for="measure">
								<spring:message code="amount.measure" />
							</form:label>
							<div class="col-md-6">
								<form:input path="measure" id="measure" name="measure"
									type="text" class="form-control" />
							</div>
							<form:errors path="measure" cssClass="error" />
						</div>

						<div class="form-group">
							<form:label path="food" class="col-md-3 control-label">
								<spring:message code="amount.food" />
							</form:label>
							<div class="col-md-6">
								<form:select path="food" class="form-control">
									<form:options items="${foods}" itemValue="id" itemLabel="name" />
								</form:select>
								<form:errors cssClass="error" path="food"></form:errors>
							</div>
						</div>

						<!-- Form actions -->
						<div class="form-group">
							<div class="col-md-12 text-center">
								<input type="submit" name="save" class="btn btn-primary btn-lg"
									value="<spring:message code="amount.save" />" />
								<jstl:if test="${!create}">
									<a class="btn btn-primary btn-lg" data-toggle="modal"
										data-target="#basicModal"><spring:message
											code="amount.delete" /></a>
									<div class="modal fade" id="basicModal" tabindex="-1"
										role="dialog" aria-labelledby="basicModal" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title" id="myModalLabel">
														<spring:message code="amount.confirm.title" />
													</h4>
												</div>
												<div class="modal-body">
													<h3>
														<spring:message code="amount.confirm.body" />
													</h3>
												</div>
												<div class="modal-footer">
													<button type="submit" name="delete" class="btn btn-default"
														onclick="history.back()">
														<spring:message code="amount.confirm.yes" />
													</button>
													<button type="button" class="btn btn-primary"
														data-dismiss="modal">
														<spring:message code="amount.confirm.no" />
													</button>
												</div>
											</div>
										</div>
									</div>
								</jstl:if>
								<a href="meal/administrator/list.do"><input type="button"
									class="btn btn-primary btn-lg"
									value="<spring:message code="meal.cancel"/>" id="cancelar"
									name="cancelar"
									onclick="self.location.href = meal/administrator/list.do" /></a>
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>
</div>