<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1 class="pomarancz">REJESTRACJA</h1>
${users}
			<fieldset id="registerform">
				<form:form modelAttribute="user" commandName="user" method="POST" enctype="utf8" target="_self">
					<label for="name">Imię:</label>
					<form:input type="text" value="" path="name" name="name" id="name" placeholder="Imię" required="required" />
					<form:errors path="name" element="div" cssClass="error" /><br/>
					<label for="surname">Nazwisko:</label>
					<form:input type="text" value="" path="surname" name="surname" id="surname" placeholder="Nazwisko" required="required" />
					<form:errors path="surname" element="div" cssClass="error" />
					<label for="phone">Numer telefonu:</label>
					<form:input type="text" value="" path="phone" name="phone" id="phone" pattern="\d{3}[\-]\d{3}[\-]\d{3}" placeholder="___-___-___" required="required" />
					<form:errors path="phone" element="div" cssClass="error" />
					<label for="email">Adres email:</label>
					<form:input type="email" value="" path="email" name="email" id="email" placeholder="adres@email.pl" required="required" />
					<form:errors path="email" element="div" cssClass="error" />
					<label for="matchingEmail">Powtórz adres email:</label>
					<form:input type="email" value="" path="matchingEmail" name="matchingEmail" id="matchingEmail" placeholder="adres@email.pl" required="required" />
					<form:errors path="matchingEmail" element="div" cssClass="error" />
					<label for="password">Hasło:</label>
					<form:input type="password" value="" path="password" name="password" id="password" placeholder="Tajne hasło" required="required" />
					<form:errors path="password" element="div" cssClass="error" />
					<label for="matchingPassword">Powtórz hasło:</label>
					<form:input type="password" value="" path="matchingPassword" name="matchingPassword" id="matchingPassword" placeholder="Tajne hasło" required="required" />
					<form:errors path="matchingPassword" element="div" cssClass="error" />
					<div><input type="submit" id="login" value="zarejestruj się" /></div>
				</form:form>
			</fieldset>
			
			
			
			