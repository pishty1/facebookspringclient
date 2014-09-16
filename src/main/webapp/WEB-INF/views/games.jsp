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
	<div class="bs-example table-responsive">
		<table class="table table-striped table-hover ">
			<tbody>
				<tr>
					<th>Player name</th>
					<th></th>
				</tr>
				<c:forEach var="game" items="${team.games}">
					<tr class="active"  >
					    <td>${game.date}</td>
					    <td><a href="../../games/${game.id}" class="btn btn-primary">Primary</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</t:daddy>