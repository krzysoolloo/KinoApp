<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1 class="zielony">EDYTUJ SEANS</h1>
<div class="error">${message}</div>
<fieldset id="registerform">
	<form:form action="" method="post" enctype="utf8">
		<label for="movieid">Nazwa filmu:</label>
		<select name="movieid" id="movieid">
		<c:forEach items="${movieList}" var="item">
			<option value="${item.id}" ${showings.movie.id == item.id ? 'selected="selected"' : ''}>${item.title}</option>
		</c:forEach>
		</select><br/><br/><br/>
		
		<label for="hallid">Sala filmowa:</label>
		<select name="hallid" id="hallid">
		<c:forEach items="${hallsList}" var="item">
		   <option value="${item.id}" ${showings.cinemaHalls.id == item.id ? 'selected="selected"' : ''}>Numer ${item.number}</option>
		</c:forEach>
		</select><br/><br/><br/>
		
		<label for="datetimepicker">Data i czas:</label>
		<input id="datetimepicker" name="datetime" type="text" />
		
		<div><input type="submit" id="login" value="edytuj seans" /></div>
	</form:form>
</fieldset>

<script>
$.datetimepicker.setLocale('pl');
$('#datetimepicker').datetimepicker({
	dayOfWeekStart : 1,
	lang:'pl',
	minDate:0,
	startDate:new Date('${showings.date}'),
	value: new Date('${showings.date}'),
	step:10
	});

</script>