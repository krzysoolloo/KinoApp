<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<h1 class="pomarancz">
	Witaj użytkowniku ${pageContext.request.userPrincipal.name}!  
</h1>