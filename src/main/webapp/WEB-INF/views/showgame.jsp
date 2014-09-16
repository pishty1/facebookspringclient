<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:daddy pageNumber='2'>
	<div class="col-lg-12">
		<div class="page-header">
			<h1 id="navbar">State Your Availability</h1>
		</div>
	</div>
	<div class="bs-example table-responsive">
		<table class="table table-striped table-hover ">
			<tbody>
				<tr>
					<th>Player name</th>
					<th></th>
				</tr>
				<c:forEach var="availability" items="${game.availabilities}">
					<tr class="active"  >
					    <td>${availability.player.name}</td>
					    <td>${availability.status}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</t:daddy>