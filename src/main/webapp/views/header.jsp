<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<link href="<c:url value="/resources/css/header.css" />" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: db2admin
  Date: 2020-11-01
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<link href="<c:url value="/resources/css/carousel.css" />" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="<c:url value="/resources/bootstrap-4.5.3-dist/js/bootstrap.min.js"/>"></script>
<!-- Bootstrap core CSS -->
<link href="<c:url value="/resources/bootstrap-4.5.3-dist/css/bootstrap.min.css"/>" rel="stylesheet">
<!-- Custom styles for this template -->

<div id="myCarousel" class="carousel slide" data-ride="carousel">
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
		<li data-target="#myCarousel" data-slide-to="2"></li>
	</ol>
	<div class="carousel-inner" role="listbox">
		<div class="carousel-item active">
			<img class="first-slide"
				 src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
				 alt="First slide">
			<div class="container">
				<div class="carousel-caption d-none d-md-block text-left">
					<h1>MediCover</h1>
					<p>${serverTime}</p>
					<p><spring:message code="label.descryption"/></p>
					<p><a class="btn btn-lg btn-primary" href="/register" role="button">Sign up today</a></p>
				</div>
			</div>
		</div>
		<div class="carousel-item">
			<img class="second-slide"
				 src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
				 alt="Second slide">
			<div class="container">
				<div class="carousel-caption d-none d-md-block">
					<h1>MediCover</h1>
					<p>${message}</p>
					<p><spring:message code="label.descryption1"/></p><a class="btn btn-lg btn-primary" href="/login"
																		 role="button">Login</a></p>
				</div>
			</div>
		</div>
		<div class="carousel-item">
			<img class="third-slide"
				 src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
				 alt="Third slide">
			<div class="container">
				<div class="carousel-caption d-none d-md-block text-right">
					<h1>Covid-19</h1>
					<p><spring:message code="label.descryption2"/></p>
					<p><a class="btn btn-lg btn-primary" href="#" role="button">Browse gallery</a></p>
				</div>
			</div>
		</div>
	</div>
	<a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
		<span class="carousel-control-prev-icon" aria-hidden="true"></span>
		<span class="sr-only">Previous</span>
	</a>
	<a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
		<span class="carousel-control-next-icon" aria-hidden="true"></span>
		<span class="sr-only">Next</span>
	</a>
</div>


<%--
<div class="header">
    <h3><spring:message code="label.title" /></h3>
    <span style="float: right">
    	<a style="background-color: white" href="?lang=pl">pl</a> | <a style="background-color: white" href="?lang=eng">eng</a> | <a style="background-color: white" href="?lang=fr">fr</a>
	</span>
    <br>

    <form id="langForm" action="" method="get">
	<span style="float: right">
		<select size="1" name="lang" onchange="form.submit()">
	        <option>Language</option>
	        <option value ="eng">ENG</option>
            <option value ="pl">PL</option>
	        <option value ="fr">FR</option>
        </select>
	</span>
    </form>
</div>--%>
