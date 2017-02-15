<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="pl">
<head>
<meta charset="utf-8" />
<link rel="icon" href="<c:url value="/resources/images/logo.png" />" type="image/png" sizes="32x30">
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
<link rel="stylesheet" href="<c:url value="/resources/js/jquery-ui.min.css" />">
  <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
  <script src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
  <script src="<c:url value="/resources/js/jquery.raty.min.js" />"></script>
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.datetimepicker.min.css" />" />
  <script src="<c:url value="/resources/js/jquery.datetimepicker.full.min.js" />"></script>
  <script src="<c:url value="/resources/js/scripts.js" />"></script>
  <script src="<c:url value="/resources/js/jquery.seat-charts.min.js" />"></script>
  <title><tiles:insertAttribute name="title" /></title>
  <base href="/kinoapp/">
</head>

<body> 
	<div id="belka"></div>
	<header>
		<span id="logo">SuperKino</span>
		<ul id="menu">
			<li id="home"><a href=""><div></div> STRONA GŁÓWNA</a></li>
			<li id="movie"><a href="<c:url value="/movies" />"><div></div> BAZA FILMÓW</a></li>
			<c:choose>
  <c:when test="${pageContext.request.userPrincipal.name == null}" >
				<li id="people"><a href="#"><div></div> ZALOGUJ SIĘ</a>
				<fieldset id="loginform">
					<form:form name="f" action="user/login" method="POST" target="_self">
						<label for="email">Adres email:</label>
						<input type="email" value="" name="username" id="email" placeholder="adres@email.pl" required />
						<label for="haslo">Hasło:</label>
						<input type="password" value="" name="password" id="haslo" placeholder="Tajne hasło" required />
						<button type="button" onclick="window.location.href='/kinoapp/user/registration'" id="register">rejestracja</button>
						<input type="submit" id="login" value="zaloguj się" />
						<a href="user/forgotpassword" id="forgot">&rarr; Przypomnij hasło</a>
					</form:form>
				</fieldset>
			</li>
		</c:when>
  <c:otherwise>		<li id="people"><a href="user/logout"><div></div> WYLOGUJ SIĘ</a></li></c:otherwise>
</c:choose>
		</ul>
	</header>
	<c:if test="${fn:endsWith(requestScope['javax.servlet.forward.request_uri'], '/kinoapp/')}">
	<div id="polecamy">
		<div>POLECAMY</div>
		<c:forEach items="${polecane}" var="p">
			<a href="movie/${p.id}"><img src="${p.image}" alt="" /></a>
		</c:forEach>
		<!-- <a href="#"><img src="resources/images/pitbull.png" alt="" /></a>
		<a href="#"><img src="resources/images/deadpool.png" alt="" /></a>
		<a href="#"><img src="resources/images/super.png" alt="" /></a>
		<a href="#"><img src="resources/images/kosoglos.png" alt="" /></a>
		<a href="#"><img src="resources/images/zjawa.png" alt="" /></a>
		<a href="#"><img src="resources/images/thor.png" alt="" /></a>
		<a href="#"><img src="resources/images/grawitacja.png" alt="" /></a>
		<a href="#"><img src="resources/images/dress.png" alt="" /></a>
		<a href="#"><img src="resources/images/pokoj.png" alt="" /></a> -->
	</div>
	</c:if>
	
	<section>
		<tiles:insertAttribute name="body" />
		<!-- <h1 class="zolty">REPERTUAR</h1>
		<input type="text" id="calendar">
		<table>
		<thead>
			<tr>
				<td id="szeproc">TYTUŁ FILMU</td>
				<td>GODZINY SEANSÓW</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><a href="#" class="tytul">Batman v Superman: Świt sprawiedliwości</a></td>
				<td><a href="#">12:00</a><a href="#">12:00</a><a href="#">12:00</a><a href="#">12:00</a></td>
			</tr>
			<tr>
				<td><a href="#" class="tytul">Batman v Superman: Świt sprawiedliwości</a></td>
				<td><a href="#">12:00</a><a href="#">12:00</a><a href="#">12:00</a><a href="#">12:00</a><a href="#">12:00</a></td>
			</tr>
			<tr>
				<td><a href="#" class="tytul">Batman v Superman: Świt sprawiedliwości</a></td>
				<td><a href="#">12:00</a><a href="#">12:00</a></td>
			</tr>
			<tr>
				<td><a href="#" class="tytul">Batman v Superman: Świt sprawiedliwości</a></td>
				<td><a href="#">12:00</a><a href="#">12:00</a><a href="#">12:00</a></td>
			</tr>
		</tbody>
		</table> -->
	</section>

</body>

</html>