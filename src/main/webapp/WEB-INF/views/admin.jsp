<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<h1 class="pomarancz">
	Witaj administratorze ${pageContext.request.userPrincipal.name}!  
	<ul id="adminmenu">
		<li><a href="admin/movies">Zarządzanie filmami</a></li>
		<li><a href="admin/cinemahalls">Zarządzanie salami kinowymi</a></li>
		<li><a href="admin/showings">Zarządzanie seansami</a></li>
	</ul>

</h1>