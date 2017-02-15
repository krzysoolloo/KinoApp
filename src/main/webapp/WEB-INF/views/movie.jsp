<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
		<h1 class="rozowy">OPIS FILMU: ${movie.title} <small> (${movie.time}min)</small></h1>
		<!-- <div id="right">oceń: <div id="star"></div></div> -->
		<article>
			<img src="${movie.image}" alt="" />
			<div id="details"><b>reżyseria:</b>	${movie.direction}<br/>
			<b>scenariusz:</b> ${movie.scenario}<br/>
			<b>gatunek:</b>	${movie.type}<br/>
			<b>produkcja:</b> ${movie.production}<br/>
			<b>premiera:</b> ${movie.premiere}<br/>
			</div>
			${movie.description}
		</article>
		<h1 class="zielony">TRAILER</h1>
		<div style="float: left; width: 100%; text-align: center;">
			<iframe width="560" height="315" src="https://www.youtube.com/embed/${movie.trailer}" frameborder="0" allowfullscreen></iframe>
		</div>
		<h1 class="niebieski">REZERWUJ MIEJSCE</h1>
		<table>
		<thead>
			<tr>
				<td id="szeproc">DATA SEANSU</td>
				<td>GODZINY SEANSÓW</td>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach items="${showings}" var="show">
				
                <tr>
                    <td>${show.date}</td>
                    <td><c:forEach items="${show.time}" var="tim"><a href="show/${tim.id}">${tim.time}</a></c:forEach></td>
                </tr>
                
            </c:forEach>
		</tbody>
		</table>
		