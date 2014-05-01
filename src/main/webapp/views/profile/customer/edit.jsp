<%--
 * edit.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

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
.colorgraph {
	height: 5px;
	border-top: 0;
	background: #993232;
	border-radius: 5px;
}
</style>

<div class="container">

	<div class="row" style="margin-top: 20px">
		<div
			class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
			<form:form action="profile/customer/edit.do"
				modelAttribute="customer" role="form">
			<fieldset>
				<form:hidden path="id" />
				<form:hidden path="version" />
				<form:hidden path="userAccount" />
				<form:hidden path="plan" />
				
					<hr class="colorgraph">
					<h3>
						<spring:message code="register.userInfoHeader" />
					</h3>
					<div class="form-group">
						<spring:message code="profile.customer.name" var="name" />
						<form:input path="name" type="text" name="name" id="name"
							class="form-control input-lg" placeholder="${name}" />
						<form:errors class="error" path="name" />
					</div>
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<spring:message code="profile.customer.surname" var="surname" />
								<form:input path="surname" type="text" name="surname"
									id="surname" class="form-control input-lg"
									placeholder="${surname}" />
								<form:errors class="error" path="surname" />
							</div>
						</div>
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<spring:message code="profile.customer.email" var="email" />
								<form:input path="email" type="email" name="email" id="email"
									class="form-control input-lg" placeholder="${email}" />
								<form:errors class="error" path="email" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<spring:message code="profile.customer.city" var="city" />
						<form:input path="city" type="text" name="city" id="city"
							class="form-control input-lg" placeholder="${city}" />
						<form:errors class="error" path="city" />
					</div>
					<h3>
						<spring:message code="register.personalInfoHeader" />
					</h3>

					<div class="form-group">
						<spring:message code="profile.customer.nationality"
							var="nationality" />
						<form:input path="nationality" type="text" name="nationality"
							id="nationality" class="form-control input-lg"
							placeholder="${nationality}" />
						<form:errors class="error" path="nationality" />
					</div>
					<hr class="colorgraph">
					<h3>
						<spring:message code="register.bodyInfoHeader" />
					</h3>
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<spring:message code="profile.customer.weight" var="weight" />
								<form:input path="weight" name="weight" id="weight"
									class="form-control input-lg" placeholder="${weight}" />
								<form:errors class="error" path="weight" />
							</div>
						</div>
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<spring:message code="profile.customer.height" var="height" />
								<form:input path="height" name="height" id="height"
									class="form-control input-lg" placeholder="${height}" />
								<form:errors class="error" path="height" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<spring:message code="profile.customer.bodyFat" var="bodyfat" />
								<form:input path="bodyfat" name="bodyfat" id="bodyfat"
									class="form-control input-lg" placeholder="${bodyfat}" />
								<form:errors class="error" path="bodyfat" />
							</div>
						</div>
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<spring:message code="profile.customer.waistlineMeasure"
									var="waistlineMeasure" />
								<form:input path="waistlineMeasure" name="waistlineMeasure"
									id="waistlineMeasure" class="form-control input-lg"
									placeholder="${waistlineMeasure}" />
								<form:errors class="error" path="waistlineMeasure" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<spring:message code="profile.customer.hipMeasure"
									var="hipMeasure" />
								<form:input path="hipMeasure" name="hipMeasure" id="hipMeasure"
									class="form-control input-lg" placeholder="${hipMeasure}" />
								<form:errors class="error" path="hipMeasure" />
							</div>
						</div>
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<spring:message code="profile.customer.chestMeasure"
									var="chestMeasure" />
								<form:input path="chestMeasure" name="chestMeasure"
									id="chestMeasure" class="form-control input-lg"
									placeholder="${chestMeasure}" />
								<form:errors class="error" path="chestMeasure" />
							</div>
						</div>
					</div>
					<hr class="colorgraph">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<input type="submit" name="save"
								class="btn btn-lg btn-primary btn-block"
								value="<spring:message code="profile.customer.save" />" />
						</div>
					</div>
					<br />
				</fieldset>
			</form:form>
			<form:form action="profile/customer/edit.do" modelAttribute="cpForm"
				role="form">

				<fieldset>
					<hr class="colorgraph">
					<h3>
						<spring:message code="register.userInfoHeader" />
					</h3>
					<div class="form-group">
						<spring:message code="profile.customer.currentPassword" var="currentPassword" />
						<form:input path="currentPassword" type="password"
							name="currentPassword" id="currentPassword"
							class="form-control input-lg" placeholder="${currentPassword}" />
						<form:errors class="error" path="currentPassword" />
					</div>
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<spring:message code="profile.customer.password"
									var="newPassword" />
								<form:input path="newPassword" type="password"
									name="newPassword" id="newPassword"
									class="form-control input-lg" placeholder="${newPassword}" />
								<form:errors class="error" path="newPassword" />
							</div>
						</div>
						<div class="col-xs-6 col-md-6">
							<div class="form-group">
								<spring:message code="profile.customer.passwordRepeat" var="newPasswordConfirmation" />
								<form:input path="newPasswordConfirmation" type="password"
									name="newPasswordConfirmation" id="newPasswordConfirmation"
									class="form-control input-lg" placeholder="${newPasswordConfirmation}" />
								<form:errors class="error" path="newPasswordConfirmation" />
							</div>
						</div>
					</div>

					<hr class="colorgraph">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<input type="submit" name="changePassword"
								class="btn btn-lg btn-primary btn-block"
								value="<spring:message code="profile.customer.changePassword" />" />
						</div>
					</div>
					<br />
				</fieldset>
			</form:form>
		</div>
	</div>

</div>

<br />
<br />
<br />



















<!-- <div class="container"> -->
<!-- 	<div class="row"> -->
<!-- 		<div class="col-md-10 col-centered"> -->
<!-- 			<div class="col-md-7"> -->

<!-- 				<h2 class="text-center"> -->
<%-- 					<spring:message code="profile.customer.editInfoHeader" /> --%>
<!-- 				</h2> -->
<!-- 				<br /> -->
<%-- 				<form:form action="profile/customer/edit.do" --%>
<%-- 					modelAttribute="customer" role="form" class="form-horizontal"> --%>

<%-- 					<form:hidden path="id" /> --%>
<%-- 					<form:hidden path="version" /> --%>
<%-- 					<form:hidden path="userAccount" /> --%>
<%-- 					<form:hidden path="plan" /> --%>


<!-- 					<div class="form-group"> -->
<%-- 						<form:label for="cutomerName" path="name" --%>
<%-- 							class="col-md-3 control-label"> --%>
<%-- 							<spring:message code="profile.customer.name" /> --%>
<%-- 						</form:label> --%>
<!-- 						<div class="col-md-5"> -->
<%-- 							<form:input id="cutomerName" path="name" class="form-control" /> --%>
<!-- 						</div> -->
<%-- 						<form:errors path="name" cssClass="error" /> --%>
<!-- 					</div> -->

<!-- 					<div class="form-group"> -->
<%-- 						<form:label for="cutomerSurname" path="surname" --%>
<%-- 							class="col-md-3 control-label"> --%>
<%-- 							<spring:message code="profile.customer.surname" /> --%>
<%-- 						</form:label> --%>
<!-- 						<div class="col-md-5"> -->
<%-- 							<form:input id="cutomerSurname" path="surname" --%>
<%-- 								class="form-control" /> --%>
<!-- 						</div> -->
<%-- 						<form:errors path="surname" cssClass="error" /> --%>
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<%-- 						<form:label for="cutomerEmail" path="email" --%>
<%-- 							class="col-md-3 control-label"> --%>
<%-- 							<spring:message code="profile.customer.email" /> --%>
<%-- 						</form:label> --%>
<!-- 						<div class="col-md-5"> -->
<%-- 							<form:input id="cutomerEmail" path="email" class="form-control" /> --%>
<!-- 						</div> -->
<%-- 						<form:errors path="email" cssClass="error" /> --%>
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<%-- 						<form:label for="cutomerCity" path="city" --%>
<%-- 							class="col-md-3 control-label"> --%>
<%-- 							<spring:message code="profile.customer.city" /> --%>
<%-- 						</form:label> --%>
<!-- 						<div class="col-md-4"> -->
<%-- 							<form:input id="cutomerCity" path="city" class="form-control" /> --%>
<!-- 						</div> -->
<%-- 						<form:errors path="city" cssClass="error" /> --%>
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<%-- 						<form:label for="cutomerNationality" path="nationality" --%>
<%-- 							class="col-md-3 control-label"> --%>
<%-- 							<spring:message code="profile.customer.nationality" /> --%>
<%-- 						</form:label> --%>
<!-- 						<div class="col-md-4"> -->
<%-- 							<form:input id="cutomerNationality" path="nationality" --%>
<%-- 								class="form-control" /> --%>
<!-- 						</div> -->
<%-- 						<form:errors path="nationality" cssClass="error" /> --%>
<!-- 					</div> -->

<!-- 					<br /> -->
<!-- 					<h3 class="text-center"> -->
<%-- 						<spring:message code="profile.customer.editBodyHeader" /> --%>
<!-- 					</h3> -->
<!-- 					<br /> -->

<!-- 					<div class="row"> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="form-group"> -->
<%-- 								<form:label for="cutomerWeight" path="weight" --%>
<%-- 									class="col-md-3 control-label"> --%>
<%-- 									<spring:message code="profile.customer.weight" /> --%>
<%-- 								</form:label> --%>
<!-- 								<div class="col-md-6"> -->
<%-- 									<form:input id="cutomerWeight" path="weight" --%>
<%-- 										class="form-control" /> --%>
<!-- 								</div> -->
<%-- 								<form:errors path="weight" cssClass="error" /> --%>
<!-- 							</div> -->
<!-- 							<div class="form-group"> -->
<%-- 								<form:label for="cutomerBodyfat" path="bodyfat" --%>
<%-- 									class="col-md-3 control-label"> --%>
<%-- 									<spring:message code="profile.customer.bodyFat" /> --%>
<%-- 								</form:label> --%>
<!-- 								<div class="col-md-6"> -->
<%-- 									<form:input id="cutomerBodyfat" path="bodyfat" --%>
<%-- 										class="form-control" /> --%>
<!-- 								</div> -->
<%-- 								<form:errors path="bodyfat" cssClass="error" /> --%>
<!-- 							</div> -->
<!-- 							<div class="form-group"> -->
<%-- 								<form:label for="cutomerHipMeasure" path="hipMeasure" --%>
<%-- 									class="col-md-3 control-label"> --%>
<%-- 									<spring:message code="profile.customer.hipMeasure" /> --%>
<%-- 								</form:label> --%>
<!-- 								<div class="col-md-6"> -->
<%-- 									<form:input id="cutomerHipMeasure" path="hipMeasure" --%>
<%-- 										class="form-control" /> --%>
<!-- 								</div> -->
<%-- 								<form:errors path="hipMeasure" cssClass="error" /> --%>
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="form-group"> -->
<%-- 								<form:label for="cutomerHeight" path="height" --%>
<%-- 									class="col-md-3 col-md-offset-1 control-label"> --%>
<%-- 									<spring:message code="profile.customer.height" /> --%>
<%-- 								</form:label> --%>
<!-- 								<div class="col-md-6"> -->
<%-- 									<form:input id="cutomerHeight" path="height" --%>
<%-- 										class="form-control" /> --%>
<!-- 								</div> -->
<%-- 								<form:errors path="height" cssClass="error" /> --%>
<!-- 							</div> -->
<!-- 							<div class="form-group"> -->
<%-- 								<form:label for="cutomerWaistlineMeasure" --%>
<%-- 									path="waistlineMeasure" --%>
<%-- 									class="col-md-3 col-md-offset-1 control-label"> --%>
<%-- 									<spring:message code="profile.customer.waistlineMeasure" /> --%>
<%-- 								</form:label> --%>
<!-- 								<div class="col-md-6"> -->
<%-- 									<form:input id="cutomerWaistlineMeasure" --%>
<%-- 										path="waistlineMeasure" class="form-control" /> --%>
<!-- 								</div> -->
<%-- 								<form:errors path="waistlineMeasure" cssClass="error" /> --%>
<!-- 							</div> -->
<!-- 							<div class="form-group"> -->
<%-- 								<form:label for="cutomerChestMeasure" path="chestMeasure" --%>
<%-- 									class="col-md-3 col-md-offset-1 control-label"> --%>
<%-- 									<spring:message code="profile.customer.chestMeasure" /> --%>
<%-- 								</form:label> --%>
<!-- 								<div class="col-md-6"> -->
<%-- 									<form:input id="cutomerChestMeasure" path="chestMeasure" --%>
<%-- 										class="form-control" /> --%>
<!-- 								</div> -->
<%-- 								<form:errors path="chestMeasure" cssClass="error" /> --%>
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<!-- 						<div class="col-md-offset-4 col-md-5"> -->
<!-- 							<input type="submit" name="save" class="btn btn-default" -->
<%-- 								value="<spring:message code="profile.customer.save" />" /><a --%>
<!-- 								href="welcome/index.do"> <input type="button" -->
<!-- 								class="btn btn-default" -->
<%-- 								value="<spring:message code="profile.customer.cancel"/>" --%>
<!-- 								onclick="self.location.href = welcome/index.do" /></a> -->
<!-- 						</div> -->
<!-- 					</div> -->
<%-- 				</form:form> --%>
<!-- 			</div> -->

<!-- 			<div class="col-md-5"> -->

<!-- 				<h2 class="text-center"> -->
<%-- 					<spring:message code="profile.customer.passwordChangeHeader" /> --%>
<!-- 				</h2> -->
<!-- 				<br /> -->
<%-- 				<form:form action="profile/customer/edit.do" modelAttribute="cpForm" --%>
<%-- 					role="form" class="form-horizontal"> --%>
<!-- 					<div class="form-group"> -->
<%-- 						<form:label for="cutomerCurrentPassword" path="currentPassword" --%>
<%-- 							class="col-md-5 control-label"> --%>
<%-- 							<spring:message code="profile.customer.currentPassword" /> --%>
<%-- 						</form:label> --%>
<!-- 						<div class="col-md-5"> -->
<%-- 							<form:input type="password" id="customerCurrentPassword" --%>
<%-- 								path="currentPassword" class="form-control" /> --%>
<!-- 						</div> -->
<%-- 						<form:errors path="currentPassword" cssClass="error" /> --%>
<!-- 					</div> -->

<!-- 					<div class="form-group"> -->
<%-- 						<form:label for="cutomerNewPassword" path="newPassword" --%>
<%-- 							class="col-md-5 control-label"> --%>
<%-- 							<spring:message code="profile.customer.password" /> --%>
<%-- 						</form:label> --%>
<!-- 						<div class="col-md-5"> -->
<%-- 							<form:input type="password" id="customerNewPassword" --%>
<%-- 								path="newPassword" class="form-control" /> --%>
<!-- 						</div> -->
<%-- 						<form:errors path="newPassword" cssClass="error" /> --%>
<!-- 					</div> -->

<!-- 					<div class="form-group"> -->
<%-- 						<form:label for="cutomerNewPasswordConfirmation" --%>
<%-- 							path="newPasswordConfirmation" class="col-md-5 control-label"> --%>
<%-- 							<spring:message code="profile.customer.passwordRepeat" /> --%>
<%-- 						</form:label> --%>
<!-- 						<div class="col-md-5"> -->
<%-- 							<form:input type="password" id="customerNewPasswordConfirmation" --%>
<%-- 								path="newPasswordConfirmation" class="form-control" /> --%>
<!-- 						</div> -->
<%-- 						<form:errors path="newPasswordConfirmation" cssClass="error" /> --%>
<!-- 					</div> -->

<!-- 					<div class="form-group"> -->
<!-- 						<div class="col-md-offset-3 col-md-10"> -->
<!-- 							<input type="submit" name="changePassword" -->
<!-- 								class="btn btn-default" -->
<%-- 								value="<spring:message code="profile.customer.changePassword" />" /> --%>
<!-- 						</div> -->
<!-- 					</div> -->
<%-- 				</form:form> --%>
<!-- 			</div> -->

<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->
