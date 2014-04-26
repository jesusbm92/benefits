<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page import="domain.Plan"%>
<%@page import="domain.Comment"%>
<%@page import="java.util.Collection"%>


<style>
body {
	padding-top: 30px;
}

.widget .panel-body {
	padding: 0px;
}

.widget .list-group {
	margin-bottom: 0;
}

.widget .panel-title {
	display: inline
}

.widget .label-info {
	float: right;
}

.widget li.list-group-item {
	border-radius: 0;
	border: 0;
	border-top: 1px solid #ddd;
}

.widget li.list-group-item:hover {
	background-color: rgba(86, 61, 124, .1);
}

.widget .mic-info {
	color: #666666;
	font-size: 11px;
}

.widget .action {
	margin-top: 5px;
}

.widget .comment-text {
	font-size: 12px;
}

.widget .btn-block {
	border-top-left-radius: 0px;
	border-top-right-radius: 0px;
}
</style>

<div class="container">

	<jstl:if test="${res}">
		<%
			Plan plan = (Plan) request.getAttribute("plan");
				Collection<Comment> comments = plan.getComments();
		%>
		<div class="row">
			<h2 class="text-center">
				<spring:message code="plan.yourgoal" />
				:
				<spring:message code="plan.goal.${plan.getGoal()}" />
			</h2>
			<br /> <br />
		</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-2">
				<div class="row">
					<img src="images/diet_image.png" width="280px"/>
				</div>
				<br />
				<div class="row">
					<div class="col-sm-offset-3">

						<a
							href="diet/customer/details.do?dietId=${plan.getDiet().getId()}">
							<input type="button" class="btn btn-lg btn-primary"
							value="<spring:message code="plan.diet"/>"
							onclick="self.location.href = diet/customer/details.do?dietId=${plan.getDiet().getId()}" />
						</a>
					</div>
				</div>
			</div>
			<div class="col-md-4 col-md-offset-1">
				<div class="row">
					<img src="images/training.png" width="225px" />
				</div>
				<br />
				<div class="row">
					<div class="col-sm-offset-1">
						<a
							href="training/details.do?trainingId=${plan.getTraining().getId()}"><input
							type="button" class="btn btn-lg btn-primary"
							value="<spring:message code="plan.training"/>"
							onclick="self.location.href = training/details.do?trainingId=${plan.getTraining().getId()}" /></a>
					</div>
				</div>
			</div>
		</div>

		<br />
		<br />

		<div class="row">
			<div class="panel panel-default widget">
				<div class="panel-heading">
					<!-- 					<span class="glyphicon glyphicon-comment"></span> -->
					<h3 class="panel-title">Recent Comments</h3>
					<span class="label label-info"> <%=comments.size()%></span>
				</div>
				<div class="panel-body">
					<ul class="list-group">
						<jstl:if test="${comments.size==0 }">
							<li class="list-group-item">
								<div class="row">
									<div class="col-xs-10 col-md-11">
										<div>
											<div class="mic-info"></div>
										</div>
										<div class="comment-text">There are no comments for this
											plan</div>
									</div>
								</div>
							</li>
						</jstl:if>
						<jstl:if test="${thereAreComments}">
							<jstl:forEach items="${comments}" var="comment">
								<li class="list-group-item">
									<div class="row">
										<div class="col-xs-10 col-md-11">
											<div>
												<div class="mic-info">
													By: <a href="#">${comment.user.name}</a> on ${comment.date}
												</div>
											</div>
											<div class="comment-text">${comment.content}</div>
										</div>
									</div>
								</li>
							</jstl:forEach>
						</jstl:if>
					</ul>
				</div>
			</div>
		</div>
		<br />
		<h4 class="text-center">
			<spring:message code="plan.isthere" />
			<a href="issue/customer/create.do?planId=${plan.id}"><spring:message
					code="plan.tellus" /></a>
		</h4>
	</jstl:if>
	<jstl:if test="${!res}">
		<div class="row">
			<br /> <br />
			<h3 class="text-center">
				<spring:message code="plan.request.noplanyet" />
			</h3>
			<br /> <br /> <br />
		</div>
		<div class="row">
			<div class="col-md-2 col-md-offset-5">
				<a href="plan/customer/request.do"><input type="button"
					class="btn btn-default"
					value="<spring:message code="plan.request"/>"
					onclick="self.location.href = plan/customer/request.do" /></a>
			</div>
		</div>
	</jstl:if>
</div>
