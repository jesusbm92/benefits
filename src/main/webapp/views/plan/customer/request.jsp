<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<div class="container">

	<form:form action="plan/customer/request.do" modelAttribute="plan"
		role="form">

		<!-- Poner todos los atributos, los no usados en oculto -->

		<form:hidden path="id" />
		<form:hidden path="customers" />
		<form:hidden path="comments" />
		<form:hidden path="issues" />
		<form:hidden path="diet" />
		<form:hidden path="training" />

		<div class="row">
			<div class="col-md-2 col-md-offset-5">
				<br /> <br />
				<div class="form-group">
					<form:label path="goal" class="control-label">
						<%-- 					<spring:message code="plan.goal" /> --%>
					</form:label>
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
		</div>


		<!-- 		<div class="form-group"> -->
		<%-- 			<form:label path="goal"> --%>
		<%-- 				<spring:message code="plan.goal" /> --%>
		<%-- 			</form:label> --%>
		<%-- 			<form:select path="goal" class="form-control selectpicker"> --%>
		<%-- 				<form:option value="-" label="--Please Select" /> --%>
		<%-- 				<form:options items="${goals}" /> --%>
		<%-- 			</form:select> --%>
		<!-- 			<br> -->
		<!-- 			</div> -->
		<!-- 		</div> -->

		<div class="row">
			<div class="col-md-2 col-md-offset-5">

				<input type="submit" name="save" class="btn btn-default"
					value="<spring:message code="plan.request.save" />" />

				<%-- 	<acme:submit name="save" code="plan.request.save" /> --%>

				<a href="plan/customer/list.do"><input type="button"
					class="btn btn-default"
					value="<spring:message code="plan.cancel"/>" id="cancelar"
					name="cancelar"
					onclick="self.location.href = plan/customer/list.do" /></a>
			</div>
		</div>
	</form:form>

</div>

<br/>
<br/>
<br/>
<br/>

