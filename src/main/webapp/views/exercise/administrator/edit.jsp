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

<h1 class="text-center">
	<spring:message code="exercise.details" />
</h1>
<br />

<div class="container">
	<div class="row">
		<div class="col-centered">

			<form:form action="exercise/administrator/edit.do"
				modelAttribute="exercise" role="form" class="form-horizontal"
				enctype="multipart/form-data">

				<!-- Poner todos los atributos, los no usados en oculto -->

				<form:hidden path="id" />
				<form:hidden path="version" />
				<form:hidden path="exerciseGroups" />
				<form:hidden path="entityLanguage" />

				<div class="form-group">
					<form:label path="muscle" for="muscleId"
						class="col-sm-2 control-label">
						<spring:message code="exercise.muscle" />
					</form:label>
					<div class="col-sm-5">
						<form:select path="muscle" id="muscleId" class="form-control">
							<jstl:forEach var="var" items="${map}">
								<form:option value="${var.value}">${var.key}</form:option>
							</jstl:forEach>
						</form:select>

					</div>
					<form:errors path="muscle" cssClass="error" />
				</div>
				<br />

				<div class="form-group">
					<form:label path="name" class="col-sm-2 control-label" for="nameId">
						<spring:message code="exercise.name" />
					</form:label>
					<div class="col-sm-5">
						<form:input path="name" id="nameId" class="form-control" />
					</div>
					<form:errors path="name" cssClass="error" />
				</div>

				<div class="form-group">
					<form:label path="description" class="col-sm-2 control-label"
						for="descriptionId">
						<spring:message code="exercise.description" />
					</form:label>
					<div class="col-sm-5">
						<form:textarea path="description" id="descriptionId"
							class="form-control" />
					</div>
					<form:errors path="description" cssClass="error" />
				</div>

				<form:label path="image">
					<spring:message code="exercise.image" />
				</form:label>
				<form:input path="image" id="image" type="file" />
				<form:errors path="image" cssClass="error" />

				<jstl:if test="${exercise.validImage }">
					<img src="image/showExercise.do?exerciseId=${exercise.id }"
						style="height: 100px" class="img-thumbnail" />
				</jstl:if>

				<div class="form-group">
					<form:label path="urlYoutube" class="col-sm-2 control-label"
						for="urlId">
						<spring:message code="exercise.urlYoutube" />
					</form:label>
					<div class="col-sm-5">
						<form:input path="urlYoutube" id="urlId" class="form-control" />
						<a
							href="http://www.youtube.com/results?search_query=${exercise.name }"
							type="button" target="_blank"><spring:message
								code="exercise.addLink" /></a>
					</div>
					<form:errors path="urlYoutube" cssClass="error" />
				</div>

				<div class="form-group">
					<form:label path="repetitions" class="col-sm-2 control-label"
						for="repetitionsId">
						<spring:message code="exercise.repetitions" />
					</form:label>
					<div class="col-sm-5">
						<form:input path="repetitions" id="repetitionsId"
							class="form-control" />
					</div>
					<form:errors path="repetitions" cssClass="error" />
				</div>
				<div class="form-group">
					<form:label path="cycles" class="col-sm-2 control-label"
						for="cyclesId">
						<spring:message code="exercise.cycles" />
					</form:label>
					<div class="col-sm-5">
						<form:input path="cycles" id="cyclesId" class="form-control" />
					</div>
					<form:errors path="cycles" cssClass="error" />
				</div>
				<br />
				<input type="submit" name="save" class="btn btn-default"
					value="<spring:message code="exercise.save" />" />

				<jstl:if test="${!create}">

					<a class="btn btn-default" data-toggle="modal"
						data-target="#basicModal"><spring:message
							code="exercise.delete" /></a>

					<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
						aria-labelledby="basicModal" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">
										<spring:message code="exercise.confirm.title" />
									</h4>
								</div>
								<div class="modal-body">
									<h3>
										<spring:message code="exercise.confirm.body" />
									</h3>
								</div>
								<div class="modal-footer">
									<button type="submit" name="delete" class="btn btn-default"
										onclick="history.back()">
										<spring:message code="exercise.confirm.yes" />
									</button>
									<button type="button" class="btn btn-primary"
										data-dismiss="modal">
										<spring:message code="exercise.confirm.no" />
									</button>
								</div>
							</div>
						</div>
					</div>

				</jstl:if>

				<input type="button" class="btn btn-default"
					value="<spring:message code="exercise.cancel"/>"
					onclick="history.back()" />

			</form:form>
		</div>
	</div>
</div>