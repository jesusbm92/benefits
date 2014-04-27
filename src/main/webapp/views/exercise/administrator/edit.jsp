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
		<div class="col-centered">

			<form:form action="exercise/administrator/edit.do"
				modelAttribute="exercise" role="form" class="form-horizontal">

				<!-- Poner todos los atributos, los no usados en oculto -->

				<form:hidden path="id" />
				<form:hidden path="version" />
				<form:hidden path="exerciseGroups" />

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
					<input type="submit" class="btn btn-default" name="delete"
						value="<spring:message code="exercise.delete"/>"
						onclick="return confirm('<spring:message code="exercise.delete.confirm"/>')" />
				</jstl:if>

				<input type="button" class="btn btn-default"
		value="<spring:message code="exercise.cancel"/>" onclick="history.back()" />

			</form:form>
		</div>
	</div>
</div>