<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:daddy pageNumber='2'>
	<div class="col-lg-12">
		<div class="page-header">
			<h1 id="navbar">${team.name} - managed by ${team.manager.name}</h1>
			<a href="${team.id}/schedule" class="label label-default">Schedule game dates</a>
			<a href="${team.id}/games" class="label label-success">See games</a>
		</div>
	</div>
	<div class="bs-example table-responsive">
		<table class="table table-striped table-hover ">
			<tbody>
				<tr>
					<th>Player name</th>
				</tr>
				<c:forEach var="player" items="${team.players}">
					<tr class="active" onclick="document.location = 'players/${player.id}';">
						<td><c:out value="${player.name}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</t:daddy>