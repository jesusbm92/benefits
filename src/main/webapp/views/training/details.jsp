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
		<div class="col-md-8 col-md-offset-1">
			<h1 class="text-center">
				<spring:message code="training.mytraining" />
			</h1>
			<br />
			<jstl:forEach items="${training.trainingDays }" var="first">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseOne${first.id }"><spring:message
										code="training.day.${first.name}" /></a>
							</h4>
						</div>
						<div id="collapseOne${first.id}" class="panel-collapse collapse ">
							<div class="panel-body">
								<jstl:forEach items="${first.exerciseGroups }" var="second">
									<div id="collapseOne${first.id}"
										class="panel-collapse collapse in">
										<div class="panel-body">
											<div class="panel-group" id="accordion2">
												<div class="panel panel-default">
													<div class="panel-heading">
														<h4 class="panel-title">
															<a data-toggle="collapse" data-parent="#accordion2"
																href="#collapseOne${second.id}${first.id}">
																${second.name } </a>
														</h4>
													</div>
													<div id="collapseOne${second.id}${first.id}"
														class="panel-collapse collapse">
														<div class="panel-body">
															<h4>
																<spring:message code="training.exercises" />
															</h4>
															<jstl:forEach items="${second.exercises }" var="thirt">
																<div class="panel panel-default">
																	<div class="panel-body">
																		<div class="row">
																			<div class="col-md-5">
																				<p style="font-size: 17px;">
																					<spring:message code="training.name" />
																					: ${thirt.name }
																				</p>

																				<ul style="font-size: 17px;">
																					<li><spring:message code="training.repetition" />:
																						${thirt.repetitions }</li>
																					<li><spring:message code="training.cycles" />:
																						${thirt.cycles }</li>
																					<li><spring:message code="training.muscle" />:
																						${thirt.muscle.name }</li>
																				</ul>
																			</div>
																			<div class="col-md-6 col-md-offset-1">
																				<iframe width="300" height="200"
																					src="https://www.youtube.com/embed/${thirt.urlYoutube}"></iframe>
																			</div>
																		</div>
																	</div>
																</div>
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

			<input type="button" class="btn btn-default"
				value="<spring:message code="training.cancel"/>"
				onclick="history.back()" /> <a
				href="training/export.do?trainingId=${training.id}"><input
				type="image" class="pull-right" id="export" name="export"
				src="images/pdf_icon.gif"
				onclick="self.location.href = training/export.do?trainingId=${training.id}" /></a>
		</div>
		<div class="col-md-2 col-md-offset-1">
			<img class="media-object img-rounded img-responsive"
				src="http://placehold.it/200x100" alt="placehold.it/200x100"><br>
			<img class="media-object img-rounded img-responsive"
				src="http://placehold.it/200x100" alt="placehold.it/200x100"><br>
			<img class="media-object img-rounded img-responsive"
				src="http://placehold.it/200x100" alt="placehold.it/200x100"><br>
			<img class="media-object img-rounded img-responsive"
				src="http://placehold.it/200x100" alt="placehold.it/200x100"><br>
			<img class="media-object img-rounded img-responsive"
				src="http://placehold.it/200x100" alt="placehold.it/200x100"><br>
			<img class="media-object img-rounded img-responsive"
				src="http://placehold.it/200x100" alt="placehold.it/200x100"><br>
		</div>
	</div>
</div>
