<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1 class="pomarancz">BAZA FILMÓW</h1>
<div class="error">${message}</div>
<table>
<thead>
	<tr>
		<th>Tytuł</th>
		<th>Zobacz szczegóły</th>
	</tr>
</thead>
<tbody>
<c:forEach items="${movies}" var="movie">
                    <tr>
                        <td>${movie.title}</td>
                        <td><a href="movie/${movie.id}">info</a></td>
                    </tr>
</c:forEach>
</tbody>
</table>
