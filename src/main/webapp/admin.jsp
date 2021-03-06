<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script src="/static/sortable/Sortable.min.js"></script>
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

				<div class="collapse navbar-collapse" id="navbar-main-collapse">

					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
						<a href="javascript:void(0)" class="dropdown-toggle button" data-toggle="dropdown" 
													role="button" aria-expanded="false">
							{{currentUser.username}}
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="/logout">Logout</a></li>
						</ul>
						</li>
					</ul>
				</div><!-- /.navbar-collapse -->
			</div><!-- /.container-fluid -->
		</div>
	</nav>





	<div class="content">
		<div class="row">
			<div class="col-lg-10 col-lg-offset-1" id="news-content">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 col-lg-offset-1">
						<div class="row">

							<div class="list-group col-xs-12 col-sm-3 col-md-2 col-lg-3 dashboard">
								<a href="#/dashboard-about" class="list-group-item title" ng-click="dashboardTab='dashboard'">
									<span class="glyphicon glyphicon-home"></span>
									Dashboard
								</a>
								<a href="#" class="list-group-item section" 
											ng-class="{active: dashboardTab === 'main-page'}" 
											ng-click="dashboardTab='main-page'">
									Main Page
								</a>
								<a href="#" class="list-group-item subsection" 
											ng-class="{active: dashboardTab === 'main-page-top-news'}" 
											ng-click="dashboardTab='main-page-top-news'">
									Top news
								</a>
								<a href="#/news/list-editor/" class="list-group-item subsection"  
											ng-class="{active: dashboardTab === 'main-page-layout'}" 
											ng-click="dashboardTab='main-page-layout'">
									<span class="glyphicon glyphicon-th"></span>
									Layout
								</a>
								<a href="#" class="list-group-item section"  
											ng-class="{active: dashboardTab === 'news'}" 
											ng-click="dashboardTab='news'">
									News
								</a>
								<a href="#/news/create" class="list-group-item subsection"  
											ng-class="{active: dashboardTab === 'news-add'}" 
											ng-click="dashboardTab='news-add'">
									<span class="glyphicon glyphicon-plus"></span>
									Add new
								</a>
								<a href="#" class="list-group-item subsection"  
											ng-class="{active: dashboardTab === 'news-edit'}" 
											ng-click="dashboardTab='news-edit'">
									<span class="glyphicon glyphicon-pencil"></span>
									Edit
								</a>
								<a href="#" class="list-group-item subsection" 
											ng-class="{active: dashboardTab === 'news-delete'}" 
											ng-click="dashboardTab='news-delete'">
									<span class="glyphicon glyphicon-trash"></span>
									Delete
								</a>
								<a href="#/users-about" class="list-group-item section"  
											ng-class="{active: dashboardTab === 'users'}" 
											ng-click="dashboardTab='users'">
									Users
								</a>
								<a href="#/users/create" class="list-group-item subsection" 
											ng-class="{active: dashboardTab === 'users-add'}" 
											ng-click="dashboardTab='users-add'">
									<span class="glyphicon glyphicon-plus"></span>
									Add new
								</a>
								<a href="#/users/" class="list-group-item subsection" 
												ng-class="{active: dashboardTab === 'users-edit-delete'}"  
												ng-click="dashboardTab='users-edit-delete'">
									<span class="glyphicon glyphicon-pencil"></span>
									Edit/Delete
								</a>
							</div>
							<div class="col-xs-12 col-sm-9 col-md-10 col-lg-9 dashboard-content">
								<div ng-view></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>




  </body>
</html>

