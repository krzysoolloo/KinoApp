<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1 class="pomarancz">LISTA REZERWACJI</h1>
<div class="error">${message}</div>
<table>
<thead>
	<tr>
		<th>Tytuł</th>
		<th>Data</th>
		<th>Sala</th>
		<th>Rząd</th>
		<th>Miejsce</th>
	</tr>
</thead>
<tbody>
<c:forEach items="${reservations}" var="res">
                    <tr>
                        <td>${res.showings.movie.title}</td>
                        <td>${res.showings.date}</td>
                        <td>${res.showings.cinemaHalls.number}</td>
                        <td>${res.row}</td>
                        <td>${res.cell}</td>
                    </tr>
                </c:forEach>
</tbody>
</table>
