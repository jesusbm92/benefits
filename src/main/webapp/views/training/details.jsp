<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<jstl:forEach items="${training.trainingDays }" var="first">
<jstl:forEach items="${first.exerciseGroups }" var="second">
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>accordion demo</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</head>
<body>
 

<div id="accordion">
  <h3>${first.name }</h3>
  <div>
   <div id="accordion2">
  <h3>${second.name }</h3>
  <div>
  
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

 
<script>
$( "#accordion2" ).accordion();
</script>
  </div>
</div>

 
<script>
$( "#accordion" ).accordion();
</script>

</body>
</html>
</jstl:forEach>
</jstl:forEach>
