<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1 class="pomarancz">LISTA SAL KINOWYCH</h1>
<div class="error">${message}</div>
<table>
<thead>
	<tr>
		<th>ID</th>
		<th>Numer</th>
		<th>Rzędów</th>
		<th>Miejsc</th>
		<th>Operacje</th>
	</tr>
</thead>
<tbody>
<c:forEach items="${cinemahallss}" var="cinemahall">
                    <tr>
                        <td>${cinemahall.id}</td>
                        <td>${cinemahall.number}</td>
                        <td>${cinemahall.rows}</td>
                        <td>${cinemahall.cells}</td>
                        <td><a href="admin/cinemahalls/delete/${cinemahall.id}" onclick="return confirm('Na pewno chcesz usunąć?')">Usuń</a></td>
                    </tr>
                </c:forEach>
</tbody>
</table>
<h1 class="zielony">DODAJ NOWĄ SALĘ</h1>
			<fieldset id="registerform">
				<form:form modelAttribute="cinemahalls" commandName="cinemahalls" method="POST" enctype="utf8" target="_self">
					<label for="number">Numer:</label>
					<form:input type="number" value="" path="number" name="number" id="number" placeholder="Numer sali" required="required" />
					<form:errors path="number" element="div" cssClass="error" /><br/>
					<label for="rows">Rzędów:</label>
					<form:input type="number" value="" path="rows" name="rows" id="rows" placeholder="Liczba rzędów" required="required" />
					<form:errors path="rows" element="div" cssClass="error" />
					<label for="cells">Miejsc:</label>
					<form:input type="number" value="" path="cells" name="cells" id="cells" placeholder="Liczba miejsc w rzędzie" required="required" />
					<form:errors path="cells" element="div" cssClass="error" />
					<div><input type="submit" id="login" value="dodaj salę" /></div>
				</form:form>
			</fieldset>
			
			
			
			