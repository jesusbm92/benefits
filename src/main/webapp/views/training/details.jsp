<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<jstl:forEach items="${training.trainingDays }" var="first">
	<div class="panel-group" id="accordion">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne${first.id }"> ${first.name } </a>
				</h4>
			</div>
			<div id="collapseOne${first.id}" class="panel-collapse collapse ">
			<div class="panel-body">
			<jstl:forEach items="${first.exerciseGroups }" var="second">
				<div id="collapseOne${first.id}" class="panel-collapse collapse in">
					<div class="panel-body">
						<div class="panel-group" id="accordion2">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion2"
											href="#collapseOne${second.id}${first.id}"> ${second.name } </a>
									</h4>
									</div>
									<div id="collapseOne${second.id}${first.id}" class="panel-collapse collapse">
										<div class="panel-body">


											<h4>Exercises</h4>
											<jstl:forEach items="${second.exercises }" var="thirt">

												<p>Name: ${thirt.name }</p>
												<ul>
													<li>Repetition: ${thirt.repetitions }</li>
													<li>Cycles: ${thirt.cycles }</li>
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