<%@tag description="daddy tag" pageEncoding="UTF-8"%>
<%@attribute name="pageNumber" required="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/site.css">
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/bootstrap-datepicker.css">
<link rel="stylesheet/less" type="text/css" href="<%=request.getContextPath()%>/resources/css/template.less" />
<title>Whos in then</title>
</head>
<body>

	<div class="container">
		<div class="navbar navbar-default">
                <div class="navbar-header">
                  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                  </button>
                  <a class="navbar-brand" href="whosinthen">home</a>
                </div>
                <div class="navbar-collapse collapse navbar-responsive-collapse">
                  <ul class="nav navbar-nav">
                    <li class="${pageNumber == 1 ? 'active' : ''}"><a href="<%=request.getContextPath()%>/pen" >My Team</a></li>
                    <li class="${pageNumber == 2 ? 'active' : ''}"><a href="<%=request.getContextPath()%>/mygames" >My Games</a></li>
                  </ul>
                  <ul class="nav navbar-nav navbar-right">
                  	<li class="${pageNumber == 3 ? 'active' : ''}"><a href="<%=request.getContextPath()%>/imanage" >teams i manage</a></li>
                    <li class="${pageNumber == 4 ? 'active' : ''}"><a href="<%=request.getContextPath()%>/sign" >signin</a></li>
                    <li class="${pageNumber == 5 ? 'active' : ''}"><a href="<%=request.getContextPath()%>/signup" >signup</a></li>
                  </ul>
                </div><!-- /.nav-collapse -->
              </div>
	<jsp:doBody/>
	</div>
	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/resources/js/moment.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/resources/js/dtpicker.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/resources/js/less.js" type="text/javascript"></script>
	<script>
       $(function () {
                $('#datetimepicker1').datetimepicker({
                    pickDate: false,
                    format: 'HH:mm'
                });
            });
    </script>
</body>
</html>
