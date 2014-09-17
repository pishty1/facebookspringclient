<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:daddy pageNumber='2'>
	<div class="col-lg-12">
		<div class="page-header">
			<h1 id="navbar">Teams You Manage</h1>
		</div>
	</div>
	<div class="bs-example table-responsive">
		<table class="table table-striped table-hover ">
			<tbody>
				<tr>
					<th>Team name</th>
					<th>Password</th>
					<th></th>
				</tr>
				<c:forEach var="team" items="${teams}">
					<tr class="active">
					    <td>${team.name}</td>
					    <td>${team.tpass}</td>
					    <td><a href="teams/${team.id}" class="btn btn-primary">Manage</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</t:daddy>