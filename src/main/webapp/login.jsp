<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>World News | Admin panel</title>
    <!-- Css/less -->
	<link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css"> 
	<link rel="stylesheet" href="/static/jasny-bootstrap/css/jasny-bootstrap.min.css"> 
	<link rel="stylesheet/less" type="text/css" href="static/css/style.less" />
	<!-- JavaScript -->
    <script src="/static/jquery/jquery-2.1.3.js"></script>
    <script src="/static/jquery/js.cookie.js"></script>
    <script src="/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="/static/jasny-bootstrap/js/jasny-bootstrap.min.js"></script>
    <script src="/static/less/less.min.js"></script>
    <script src="/static/angular/angular.js"></script>
    <script src="/static/angular/route.js"></script>
    <script src="/static/angular/angular-input-match.js"></script>
    <script src="/static/ckeditor/ckeditor.js"></script>
    <script src="/static/angular-ckeditor/angular-ckeditor.js"></script>
	<!-- World News Js -->
    <script src="/static/js/helpers.js"></script>
    <script src="/static/js/db_fixtures.js"></script>
    <script src="/static/js/admin.js"></script>

    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body ng-app="NW_AdminPage" ng-controller="defaultCtrl">




	<nav class="navbar navbar-default">
		<div class="col-lg-10 col-lg-offset-1" >
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
							data-target="#navbar-main-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<ul class="navbar-brand">
						<li>
						<img alt="Brand" src="static/img/logo.png">
						</li>
						<li>
						<a href="/">World News</a>
						</li>
					</ul>
				</div>

			</div><!-- /.container-fluid -->
		</div>
	</nav>





	<div class="content">
		<div class="row">
			<div class="col-md-6 col-md-offset-3 col-lg-4 col-lg-offset-4" id="news-content">
				<form role="form" action="/login" method="post">
				<div class="form-group">
					<label> Email </label>
					<input type="text" name="username" id="username" class="form-control">
				</div>
				<div class="form-group">
					<label> Password </label>
					<input type="password" name="password" id=password class="form-control">
				</div>
				<div class="form-group">
					<label>
						<input type="checkbox" name="remember-me" id="remember-me"/>
						Remember me
					</label>
				</div>
				<button class="btn btn-primary" type="submit">
					Sign in
				</button>
			</div>
		</div>
	</div>




  </body>
</html>

