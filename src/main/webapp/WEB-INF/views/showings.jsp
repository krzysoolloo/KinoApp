<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1 class="pomarancz">LISTA SEANSÓW</h1>
<div id="right"><a href="admin/showings/add"><h1 class="zielony">+ Dodaj</h1></a></div>
<div class="error">${message}</div>
<table>
<thead>
	<tr>
		<th>ID</th>
		<th>Sala</th>
		<th>Tytuł</th>
		<th>Data</th>
		<th>Operacje</th>
	</tr>
</thead>
<tbody>
<c:forEach items="${showings}" var="show">
                    <tr>
                        <td>${show.id}</td>
                        <td>${show.cinemaHalls.number}</td>
                        <td>${show.movie.title}</td>
                        <td>${show.date}</td>
                        <td><a href="admin/showings/edit/${show.id}">Edytuj</a><a href="admin/showings/delete/${show.id}" onclick="return confirm('Na pewno chcesz usunąć?')">Usuń</a></td>
                    </tr>
                </c:forEach>
</tbody>
</table>
