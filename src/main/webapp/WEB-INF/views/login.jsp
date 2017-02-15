<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1 class="pomarancz">PANEL LOGOWANIA</h1>
<div id="msg">${msg}</div>
<fieldset id="registerform">
	<form:form name="f" action="user/login" method="POST" target="_self">
		<label for="username">Adres email:</label>
		<input type="email" name="username" value="" id="name" placeholder="adres@email.pl" required />
		<label for="password">Hasło:</label>
		<input type="password" name="password" id="name" placeholder="Tajne hasło" required />

		<div><input type="submit" id="login" value="zaloguj się" /></div>
	</form:form>
</fieldset>