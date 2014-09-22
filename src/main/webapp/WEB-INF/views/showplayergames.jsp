<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:daddy pageNumber='2'>
	<div class="col-lg-12">
		<div class="page-header">
			<h1 id="navbar">Your games</h1>
		</div>
	</div>
	<div class="bs-example table-responsive">
		<table class="table table-striped table-hover">
			<tbody>
				<tr>
				    <th class="col-sm-1">Date</th>
					<th class="col-lg-2">Team name</th>
					<th class="col-lg-2"># of available players</th>
					<th class="col-lg-2">Your status</th>
					<th class="col-lg-2"></th>
				</tr>
				<c:forEach var="game" items="${games}">
					<tr class="active">
                        <td>${game.fd}</td>
                        <td>${game.team.name}</td>
                        <td>${game.availablePlayers}</td>
                        <td>
                            <c:forEach var="availability" items="${game.availabilities}">
                                <c:if test="${availability.player.fbId eq fbid}">
                                    ${availability.status}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td><a href="games/${game.id}" class="btn btn-success">Availability</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</t:daddy>