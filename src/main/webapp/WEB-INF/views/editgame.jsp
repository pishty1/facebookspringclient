<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:daddy pageNumber='2'>
	<div class="col-lg-12">
		<div class="page-header">
			<h1 id="navbar">Edit Game</h1>
			<a href="../../teams/${game.team.id}/games" class="label label-success">See games</a>
		</div>
	</div>
	<div class="row">
        <div class="col-lg-12">
            <div class="well">
                <form:form class="bs-example form-horizontal" modelAttribute="game" action="editgame" method="POST">
                    <fieldset>
                        <form:input type="hidden" class="form-control" id="tid" path="team.id"/>
                        <form:input type="hidden" class="form-control" id="id" path="id"/>
                        <div class="form-group">
                          <form:label for="time" class="col-lg-2 control-label" path="date">Time</form:label>
                          <div class="col-lg-4 input-group date" id='datetimepicker2'>
                            <form:input type="text" class="form-control" id="time" path="date"/>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                          </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-offset-2">
                              <button type="submit" class="btn btn-primary">Submit</button>
                            </div>
                        </div>
                    </fieldset>
                </form:form>
            </div>
		</div>
	</div>
</t:daddy>