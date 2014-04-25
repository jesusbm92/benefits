<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div class="container">
	<div class="row">

		<jstl:forEach items="${diet.days }" var="first">
			<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne${first.id }"><spring:message
									code="diet.day.${first.name}" /></a>
						</h4>
					</div>
					<div id="collapseOne${first.id}" class="panel-collapse collapse ">
						<div class="panel-body">
							<jstl:forEach items="${first.meals }" var="second">
								<div id="collapseOne${first.id}"
									class="panel-collapse collapse in">
									<div class="panel-body">
										<div class="panel-group" id="accordion2">
											<div class="panel panel-default">
												<div class="panel-heading">
													<h4 class="panel-title">
														<a data-toggle="collapse" data-parent="#accordion2"
															href="#collapseOne${second.id}${first.id}"> <spring:message
																code="diet.meal.${second.name}" /></a>
													</h4>
												</div>
												<div id="collapseOne${second.id}${first.id}"
													class="panel-collapse collapse">
													<div class="panel-body">


														<h4>
															<spring:message code="diet.amounts" />
														</h4>
														<jstl:forEach items="${second.amounts }" var="thirt">

															<p>
																<spring:message code="diet.food" />
																: ${thirt.food.name }
															</p>
															<ul>
																<li><spring:message code="diet.description" />:
																	${thirt.food.description }</li>
																<li><spring:message code="diet.quantity" />:
																	${thirt.quantity }</li>
																<li><spring:message code="diet.measure" />:
																	${thirt.measure }</li>

															</ul>
														</jstl:forEach>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</jstl:forEach>
						</div>
					</div>

				</div>
			</div>
		</jstl:forEach>

	</div>
</div>