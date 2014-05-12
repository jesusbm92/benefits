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
				<form:form class="form-horizontal"
					action="plan/administrator/edit.do" modelAttribute="plan">

					<form:hidden path="id" />
					<form:hidden path="version" />
					<form:hidden path="customers" />
					<form:hidden path="comments" />
					<form:hidden path="issues" />
					<form:hidden path="entityLanguage" />

					<fieldset>
						<h1 class="text-center">
							<spring:message code="plan.createnew" />
						</h1>
						<br/>


						<!-- Name input-->
						<div class="form-group">
							<form:label path="name" class="col-md-3 control-label" for="name">
								<spring:message code="plan.name" />
							</form:label>
							<div class="col-md-6">
								<form:input path="name" id="name" name="name" type="text"
									class="form-control" />
							</div>
							<form:errors path="name" cssClass="error" />
						</div>

						<div class="form-group">
							<form:label path="goal" class="col-md-3 control-label">
								<spring:message code="plan.goal" />
							</form:label>
							<div class="col-md-6">
								<form:select path="goal" class="form-control">
									<jstl:forEach var="goal" items="${goals}">
										<form:option value="${goal}">
											<spring:message code="plan.goal.${goal}" />
										</form:option>
									</jstl:forEach>
								</form:select>
								<form:errors cssClass="error" path="goal"></form:errors>
							</div>
						</div>



						<!-- Description -->
						<div class="form-group">
							<form:label path="description" class="col-md-3 control-label"
								for="description">
								<spring:message code="plan.description" />
							</form:label>
							<div class="col-md-6">
								<form:textarea path="description" class="form-control"
									id="description" name="description" rows="5"></form:textarea>
							</div>
							<form:errors path="description" cssClass="error" />
						</div>



<!-- 						<div class="form-group"> -->
<%-- 							<form:label path="entityLanguage" class="col-md-3 control-label"> --%>
<%-- 								<spring:message code="plan.language" /> --%>
<%-- 							</form:label> --%>
<!-- 							<div class="col-md-6"> -->
<%-- 								<form:select path="entityLanguage" class="form-control"> --%>
<%-- 									<jstl:forEach var="language" items="${languages}"> --%>
<%-- 										<form:option value="${language}"> --%>
<%-- 											<spring:message code="plan.language.${language}" /> --%>
<%-- 										</form:option> --%>
<%-- 									</jstl:forEach> --%>
<%-- 								</form:select> --%>
<%-- 								<form:errors cssClass="error" path="entityLanguage"></form:errors> --%>
<!-- 							</div> -->
<!-- 						</div> -->

						<div class="form-group">
							<form:label path="diet" for="dietId"
								class="col-md-3 control-label">
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
								class="col-md-3 control-label">
								<spring:message code="plan.training" />
							</form:label>
							<div class="col-md-6">
								<form:select id="trainingId" path="training"
									class="form-control">
									<form:option value="0" label="----" />
									<form:options items="${trainings}" itemValue="id"
										itemLabel="name" />
								</form:select>
								<form:errors path="training" cssClass="error" />
							</div>
						</div>
						<br/>
						
						
						
						
						
						
												<div class="row">
							<div class="col-xs-6 col-md-6">
								<div class="form-group">
									<div class="col-md-9 col-md-offset-3">
										<div class="input-group">
											<span class="input-group-addon"><spring:message
													code="plan.minWeight" /></span>
											<form:input path="minWeight" id="minWeight" name="minWeight"
												type="text" class="form-control" />
											<span class="input-group-addon">kg</span>
										</div>
									</div>
									<form:errors path="minWeight" cssClass="error" />
								</div>
							</div>
							<div class="col-xs-6 col-md-6">
								<div class="form-group">
									<div class="col-md-9 col-md-offset-1">
										<div class="input-group">
											<span class="input-group-addon"><spring:message
													code="plan.minBodyFat" /></span>
											<form:input path="minBodyFat" id="minBodyFat"
												name="minBodyFat" type="text" class="form-control" />
											<span class="input-group-addon">kg</span>
										</div>
									</div>
									<form:errors path="minBodyFat" cssClass="error" />
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-xs-6 col-md-6">
								<div class="form-group">
									<div class="col-md-9 col-md-offset-3">
										<div class="input-group">
											<span class="input-group-addon"><spring:message
													code="plan.maxWeight" /></span>
											<form:input path="maxWeight" id="maxWeight" name="maxWeight"
												type="text" class="form-control" />
											<span class="input-group-addon">kg</span>
										</div>
									</div>
									<form:errors path="maxWeight" cssClass="error" />
								</div>
							</div>
							<div class="col-xs-6 col-md-6">
								<div class="form-group">
									<div class="col-md-9 col-md-offset-1">
										<div class="input-group">
											<span class="input-group-addon"><spring:message
													code="plan.maxBodyFat" /></span>
											<form:input path="maxBodyFat" id="maxBodyFat"
												name="maxBodyFat" type="text" class="form-control" />
											<span class="input-group-addon">kg</span>
										</div>
									</div>
									<form:errors path="maxBodyFat" cssClass="error" />
								</div>
							</div>
						</div>
						<br/>
						<!-- Form actions -->
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
