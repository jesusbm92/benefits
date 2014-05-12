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
	padding-top: 20px; .
	multiselect-container {position: absolute;
	list-style-type: none;
	margin: 0;
	padding: 0
}

.multiselect-container .input-group {
	margin: 5px
}

.multiselect-container>li {
	padding: 0
}

.multiselect-container>li>a.multiselect-all label {
	font-weight: bold
}

.multiselect-container>li>label.multiselect-group {
	margin: 0;
	padding: 3px 20px 3px 10px;
	height: 100%;
	font-weight: bold;
}

.multiselect-container>li>a>label {
	margin: 0;
	height: 100%;
	cursor: pointer;
	font-weight: normal
}

.multiselect-container>li>a>label.radio,.multiselect-container>li>a>label.checkbox
	{
	margin: 0
}

.multiselect-container>li>a>label>input[type="checkbox"] {
	margin-bottom: 5px
}

.btn-group>.btn-group:nth-child(2)>.multiselect.btn {
	border-top-left-radius: 4px;
	border-bottom-left-radius: 4px
}

.row {
	margin-bottom: 20px;
}

.btn {
	padding: 10px 16px;
	font-size: 18px;
	line-height: 1.33;
	border-radius: 6px;
}
}
</style>

<div class="container">
	<div class="row">
		<div class="col-md-7 col-md-offset-2">
			<div class="well well-sm">
				<form:form class="form-horizontal"
					action="diet/administrator/edit.do" modelAttribute="diet">

					<form:hidden path="id" />
					<form:hidden path="version" />
					<form:hidden path="entityLanguage" />

					<fieldset>
						<h1 class="text-center">
							<spring:message code="diet.info" />
						</h1>
						<br />


						<!-- Name input-->
						<div class="form-group">
							<form:label path="name" class="col-md-3 control-label" for="name">
								<spring:message code="diet.name" />
							</form:label>
							<div class="col-md-6">
								<form:input path="name" id="name" name="name" type="text"
									class="form-control" />
							</div>
							<form:errors path="name" cssClass="error" />
						</div>

						<!-- Description -->
						<div class="form-group">
							<form:label path="description" class="col-md-3 control-label"
								for="description">
								<spring:message code="diet.description" />
							</form:label>
							<div class="col-md-6">
								<form:textarea path="description" class="form-control"
									id="description" name="description" rows="5"></form:textarea>
							</div>
							<form:errors path="description" cssClass="error" />
						</div>

						<!-- Sponsor -->
						<div class="form-group">
							<form:label path="sponsor" for="sponsorId"
								class="col-md-3 control-label">
								<spring:message code="diet.sponsor" />
							</form:label>
							<div class="col-md-6">
								<form:select id="sponsorId" path="sponsor" class="form-control">
									<form:option value="0" label="----" />
									<form:options items="${sponsors}" itemValue="id"
										itemLabel="name" />
								</form:select>
								<form:errors path="sponsor" cssClass="error" />
							</div>
						</div>

						<!-- Days -->
						<div class="form-group">
							<form:label path="days" for="daysId"
								class="col-md-3 control-label">
								<spring:message code="diet.days.edit" />
							</form:label>
							<div class="col-md-6">
								<form:select multiple="${days.size()}" items="${days}"
									itemLabel="descriptiveName" id="id" code="diet.day" path="days"
									class="form-control multiselect" />
								<form:errors path="days" cssClass="error" />
							</div>
						</div>


						<br />
						<!-- Form actions -->
						<div class="form-group">
							<div class="col-md-12 text-center">
								<input type="submit" name="save" class="btn btn-primary btn-lg"
									value="<spring:message code="diet.save" />" />
								<jstl:if test="${!create}">
									<a class="btn btn-primary btn-lg" data-toggle="modal"
										data-target="#basicModal"><spring:message
											code="diet.delete" /></a>
									<div class="modal fade" id="basicModal" tabindex="-1"
										role="dialog" aria-labelledby="basicModal" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title" id="myModalLabel">
														<spring:message code="diet.confirm.title" />
													</h4>
												</div>
												<div class="modal-body">
													<h3>
														<spring:message code="diet.confirm.body" />
													</h3>
												</div>
												<div class="modal-footer">
													<button type="submit" name="delete" class="btn btn-default"
														onclick="history.back()">
														<spring:message code="diet.confirm.yes" />
													</button>
													<button type="button" class="btn btn-primary"
														data-dismiss="modal">
														<spring:message code="diet.confirm.no" />
													</button>
												</div>
											</div>
										</div>
									</div>
								</jstl:if>
								<a href="diet/administrator/list.do"><input type="button"
									class="btn btn-primary btn-lg"
									value="<spring:message code="diet.cancel"/>" id="cancelar"
									name="cancelar"
									onclick="self.location.href = diet/administrator/list.do" /></a>
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>
</div>


