<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:daddy pageNumber='2'>
	<div class="col-lg-12">
		<div class="page-header">
			<h1 id="navbar">State Your Availability</h1>
		</div>
	</div>
	<div class="bs-example table-responsive">
		<table class="table table-striped table-hover">
			<tbody>
				<tr>
				    <th class="col-sm-1"></th>
					<th class="col-lg-2">Player name</th>
					<th class="col-lg-2">Status</th>
					<th class="col-lg-2"></th>
				</tr>
				<c:forEach var="ava" items="${game.availabilities}">
					<tr class="active">
					    <c:if test="${ava.player.fbId eq fbId}">
                            <form:form class="bs-example form-horizontal"  modelAttribute="availability" action="${game.id}/ava" method="POST">
					            <td><form:input type="hidden" class="form-control" id="name" path="id" value="${ava.id}"/></td>
                                <td>${ava.player.name}</td>
                                <td>
                                    <form:select path="status" class="form-control" multiple="false">
                                       <c:forEach var="sta" items="${statuses}">
                                           <c:if test="${sta eq ava.status}">
                                               <form:option value="${sta}" label="${sta}" selected="selected" />
                                           </c:if>
                                           <c:if test="${sta ne ava.status}">
                                               <form:option value="${sta}" label="${sta}" />
                                          </c:if>
                                       </c:forEach>
                                    </form:select>
                                </td>
                                <td>
                                    <div class="col-lg-10 input-group">
                                      <button type="submit" class="btn btn-primary">Submit</button>
                                    </div>
                                </td>
                            </form:form>
					    </c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</t:daddy>