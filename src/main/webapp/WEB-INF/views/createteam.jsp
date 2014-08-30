<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:daddy pageNumber='1'>
	<div class="col-lg-12">
		<div class="page-header">
			<h1 id="navbar">Create Your Team</h1>
		</div>
		<div class="row">
			<div class="col-lg-12">
            <div class="well">
              <form:form class="bs-example form-horizontal" modelAttribute="Team" action="add" method="POST">
                <fieldset>
                  <div class="form-group">
                    <form:label for="name" class="col-lg-2 control-label" path="name">Team name</form:label>
                    <div class="col-lg-2">
                      <form:input type="text" class="form-control" id="name" placeholder="team name here" path="name" />
                    </div>
                  </div>
                  <div class="form-group">
                    <form:label for="tpass" class="col-lg-2 control-label" path="tpass">Team password</form:label>
                    <div class="col-lg-2">
                      <form:input type="text" class="form-control" id="tpass" placeholder="team pass phrase"  path="tpass"/>
                    </div>
                  </div>
                  <div class="form-group">
                      <form:label for="teamType" class="col-lg-2 control-label" path="teamType"># Players</form:label>
                      <div class="col-lg-2">
                        <form:input type="text" class="form-control" id="teamType" placeholder="number of player"  path="teamType"/>
                      </div>
                  </div>
                  <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                      <button class="btn btn-default">Cancel</button> 
                      <button type="submit" class="btn btn-primary">Submit</button> 
                    </div>
                  </div>
                </fieldset>
              </form:form>
            </div>
          </div>
		</div>
	</div>
</t:daddy>