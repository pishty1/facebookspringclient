<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:daddy pageNumber='2'>
	<div class="col-lg-12">
		<div class="page-header">
			<h1 id="navbar">Players Availability</h1>
			<a href="../teams/${game.team.id}/games" class="label label-success">See games</a>
		</div>
	</div>
	<div class="bs-example table-responsive">
		<table class="table table-striped table-hover">
			<tbody>
				<tr>
					<th class="col-lg-2">Player name</th>
					<th class="col-lg-2">Status</th>
				</tr>
				<c:forEach var="availability" items="${game.availabilities}">
					<tr class="active">
					    <c:if test="${availability.player.fbId eq fbId}">
                            <form:form class="bs-example form-horizontal"  modelAttribute="availability" action="${game.id}/ava" method="POST">
                                <td>${availability.player.name}</td>
                                <td>${availability.status}</td>
                            </form:form>
					    </c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</t:daddy>