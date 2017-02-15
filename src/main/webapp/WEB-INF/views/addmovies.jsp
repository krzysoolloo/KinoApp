<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1 class="zielony">DODAJ NOWY FILM</h1>
<fieldset id="registerform">
	<form:form modelAttribute="movies" commandName="movies" method="POST" enctype="utf8" target="_self">
		<label for="title">Tytuł:</label>
		<form:input type="text" value="" path="title" name="title" class="forminput" id="title" placeholder="Tytuł filmu" required="required" />
		<form:errors path="title" element="div" cssClass="error" />
		<label for="direction">Reżyser:</label>
		<form:input type="text" value="" path="direction" name="direction" class="forminput" id="direction" placeholder="Reżyser filmu" required="required" />
		<form:errors path="direction" element="div" cssClass="error" />
		<label for="scenario">Scenariusz:</label>
		<form:input type="text" value="" path="scenario" name="scenario" class="forminput" id="scenario" placeholder="Autor scenariusza" required="required" />
		<form:errors path="scenario" element="div" cssClass="error" />
		<label for="type">Gatunek:</label>
		<form:input type="text" value="" path="type" name="type" class="forminput" id="type" placeholder="Gatunek filmu" required="required" />
		<form:errors path="type" element="div" cssClass="error" />
		<label for="production">Produkcja:</label>
		<form:input type="text" value="" path="production" name="production" class="forminput" id="production" placeholder="Kraj produkcji" required="required" />
		<form:errors path="production" element="div" cssClass="error" />
		<label for="premiere">Premiera:</label>
		<form:input type="text" value="" path="premiere" name="premiere" class="forminput" id="premiere" placeholder="Premiera filmu" required="required" />
		<form:errors path="premiere" element="div" cssClass="error" />
		<label for="description">Opis:</label>
		<form:textarea path="description" name="description" id="description" class="forminput" required="required" />
		<form:errors path="description" element="div" cssClass="error" />
		<label for="time">Czas:</label>
		<form:input type="number" value="" path="time" name="time" class="forminput"  id="time" placeholder="0" required="required" />
		<form:errors path="time" element="div" cssClass="error" />
		<label for="trailer">Trailer:</label>
		<form:input type="text" value="" path="trailer" name="trailer" class="forminput"  id="trailer" placeholder="Adres youtube" required="required" />
		<form:errors path="trailer" element="div" cssClass="error" />
		<label for="cells">Obrazek:</label>
		<form:input type="text" value="" path="image" name="image" id="image" class="forminput"  placeholder="Adres obrazka" required="required" />
		<form:errors path="image" element="div" cssClass="error" />
		<label for="recommended">Polecane:</label>
		<form:checkbox value="1" path="recommended" name="recommended" id="recommended"  class="forminput" />
		<form:errors path="recommended" element="div" cssClass="error" />
		<div><input type="submit" id="login" value="dodaj film" /></div>
	</form:form>
</fieldset>
			
			
			
			