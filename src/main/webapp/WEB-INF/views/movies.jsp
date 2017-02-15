<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1 class="pomarancz">LISTA FILMÓW</h1>
<div id="right"><a href="admin/movies/add"><h1 class="zielony">+ Dodaj</h1></a></div>
<div class="error">${message}</div>
<table>
<thead>
	<tr>
		<th>ID</th>
		<th>Tytuł</th>
		<th>Operacje</th>
	</tr>
</thead>
<tbody>
<c:forEach items="${movies}" var="movie">
                    <tr>
                        <td>${movie.id}</td>
                        <td>${movie.title}</td>
                        <td><a href="admin/movies/edit/${movie.id}">Edytuj</a><a href="admin/movies/delete/${movie.id}" onclick="return confirm('Na pewno chcesz usunąć?')">Usuń</a></td>
                    </tr>
                </c:forEach>
</tbody>
</table>
