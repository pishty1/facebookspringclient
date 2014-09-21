<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:daddy pageNumber='2'>
	<div class="col-lg-12">
		<div class="page-header">
			<h1 id="navbar">${team.name} - managed by ${team.manager.name}</h1>
			<a href="schedule/${team.id}" class="label label-default">Schedule game dates</a>
		</div>
	</div>

	<div class="row">
                <div class="col-sm-3 col-md-3 col-lg-3">Game date and time</div>
                <div class="col-sm-3 col-md-3 col-lg-3"># of players</div>
                <div class="col-sm-3 col-md-3 col-lg-3"></div>
                <div class="col-sm-3 col-md-3 col-lg-3"></div>
            </div>
    <c:forEach var="game" items="${team.games}">
        <div class="row">
            <div class="col-sm-3 col-md-3 col-lg-3">${game.fd}</div>
            <div class="col-sm-1 col-md-1 col-lg-1">${game.availablePlayers}</div>
            <div class="col-sm-3 col-md-3 col-lg-3"><a href="../../games/${game.id}" class="btn btn-primary">See availability</a></div>
            <div class="col-sm-3 col-md-3 col-lg-3"><a href="../../games/${game.id}/edit" class="btn btn-success">Edit game</a></div>
        </div>
    </c:forEach>
</t:daddy>