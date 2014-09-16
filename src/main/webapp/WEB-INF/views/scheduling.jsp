<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:daddy pageNumber='2'>
	<div class="col-lg-12">
		<div class="page-header">
			<h1 id="navbar">
			games scheduling for
            <span class="label label-info">${team.name}</span>
			</h1>
		</div>
	</div>

    <div class="container">
        <div class="row">
            <div class="col-lg-6">
              <form:form class="bs-example form-horizontal"  modelAttribute="schedBean" action="create" method="POST">
                  <fieldset>
                    <form:hidden path="id" />
                    <div class="form-group">
                      <form:label for="label" class="col-lg-2 control-label" path="label">Label</form:label>
                      <div class="col-lg-4 input-group">
                        <form:input type="text" class="form-control" id="label" placeholder="Schedule label" path="label" />
                      </div>
                    </div>
                    <div class="form-group">
                      <form:label for="time" class="col-lg-2 control-label" path="time">Time</form:label>
                      <div class="col-lg-4 input-group date" id='datetimepicker1'>
                        <form:input type="text" class="form-control" id="time" path="time"/>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                      </div>
                    </div>
                    <div class="form-group">
                        <form:label for="mon" class="col-lg-2 control-label" path="repeat">Repeat</form:label>
                        <div class="checkbox col-lg-2">
                          <form:label for="mon" path="everyMod">
                            <form:checkbox id="mon" path="everyMod"/>Mondays
                          </form:label>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label for="tue" class="col-lg-2 control-label" path="repeat"></form:label>
                        <div class="checkbox col-lg-2">
                          <form:label for="tue" path="everyTue">
                            <form:checkbox id="tue" path="everyTue"/>Tuesdays
                          </form:label>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label for="wed" class="col-lg-2 control-label" path="repeat"></form:label>
                        <div class="checkbox col-lg-2">
                          <form:label for="wed" path="everyWed">
                            <form:checkbox id="wed" path="everyWed"/>Wednesdays
                          </form:label>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label for="thur" class="col-lg-2 control-label" path="repeat"></form:label>
                        <div class="checkbox col-lg-2">
                          <form:label for="thur" path="everyThur">
                            <form:checkbox id="thur" path="everyThur"/>Thursdays
                          </form:label>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label for="fri" class="col-lg-2 control-label" path="repeat"></form:label>
                        <div class="checkbox col-lg-2">
                          <form:label for="fri" path="everyFri">
                            <form:checkbox id="fri" path="everyFri"/>Fridays
                          </form:label>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label for="sat" class="col-lg-2 control-label" path="repeat"></form:label>
                        <div class="checkbox col-lg-2">
                          <form:label for="sat" path="everySat">
                            <form:checkbox id="sat" path="everySat"/>Saturdays
                          </form:label>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label for="sun" class="col-lg-2 control-label" path="repeat"></form:label>
                        <div class="checkbox col-lg-2">
                          <form:label for="sun" path="everySun">
                            <form:checkbox id="sun" path="everySun"/>Sundays
                          </form:label>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label for="nweeks" class="col-lg-2 control-label" path="nweeks"># Weeks</form:label>
                        <div class="col-lg-1 input-group">
                          <form:input type="text" class="form-control" id="nweeks" placeholder="#" path="nweeks" />
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label for="nweeks" class="col-lg-2 control-label" path="nweeks"></form:label>
                        <div class="col-lg-10 input-group">
                          <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                  </fieldset>
              </form:form>
            </div>
        </div>
    </div>
</t:daddy>