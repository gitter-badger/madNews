<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" ng-app="NW_MainPage" ng-controller="mainPageCtrl">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>World News | Main</title>
    <!-- Css/less -->
	<link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css"> 
	<link rel="stylesheet/less" type="text/css" href="static/css/style.less" />
	<!-- JavaScript -->
    <script src="/static/jquery/jquery-2.1.3.js"></script>
    <script src="/static/jquery/js.cookie.js"></script>
    <script src="/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="/static/less/less.min.js"></script>
    <script src="/static/tagcloud/jquery.tagcloud.js"></script>
	<script src="/static/angular/angular.js"></script>
	<script src="/static/angular/route.js"></script>
	<!-- World News Js -->
    <script src="/static/js/helpers.js"></script>
    <script src="/static/js/db_fixtures.js"></script>
    <script src="/static/js/index.js"></script>

    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>



	<nav class="navbar navbar-default">
		<div class="col-lg-10 col-lg-offset-1" >

			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-main-collapse">
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
						<a href="/#/">World News</a>
						</li>
					</ul>
				</div>

				<div class="collapse navbar-collapse" id="navbar-main-collapse">
					<ul class="nav navbar-nav navbar-right">
						<li>
							<form class="navbar-right navbar-form" role="search">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term">
									<div class="input-group-btn">
										<button class="btn btn-default" type="submit">
											<i class="glyphicon glyphicon-search"></i>
										</button>
									</div>
								</div>
							</form>
						</li>
						<li class="hidden-xs">
							<a class="button glyphicon glyphicon-cog" data-toggle="dropdown" aria-expanded="false"></a>
							<ul class="dropdown-menu" role="menu">
								<li>
									<a href="javascript:void(0)" 
										ng-click="settings.setTheme('original')"
										ng-class="{active:settings.theme === 'original'}">
										Original  Theme
									</a>
								</li>
								<li>
									<a href="javascript:void(0)" 
										ng-click="settings.setTheme('dark')"
										ng-class="{active:settings.theme === 'dark'}">
										Dark  Theme
									</a>
								</li>
								<li>
									<a href="javascript:void(0)"  
										ng-click="settings.setTheme('grey')"
										ng-class="{active:settings.theme === 'grey'}">
										Grey Theme
									</a>
								</li>
								<li class="divider"></li>
								<li>
									<a href="javascript:void(0)" ng-click="settings.setNewsLayout('line')"
										ng-class="{active:settings.newsLayout === 'line'}">
										<span class="glyphicon glyphicon-option-vertical"></span>
										Line layout
									</a>
								</li>
								<li>
									<a href="javascript:void(0)" ng-click="settings.setNewsLayout('grid')"
										ng-class="{active:settings.newsLayout === 'grid'}">
										<span class="glyphicon glyphicon-th"></span>
										Grid layout
									</a>
								</li>
								
							</ul>
						</li>
						<li>
							<a href="javascript:void(0)" class="visible-xs" 
								ng-click="settings.setTheme('original')"
								ng-class="{active:settings.theme === 'original'}">
								Original  Theme
							</a>
						</li>
						<li>
							<a href="javascript:void(0)" class="visible-xs" 
								ng-click="settings.setTheme('dark')"
								ng-class="{active:settings.theme === 'dark'}">
								Dark  Theme
							</a>
						</li>
						<li>
							<a href="javascript:void(0)" class="visible-xs"
								ng-click="settings.setTheme('grey')"
								ng-class="{active:settings.theme === 'grey'}">
								Grey Theme
							</a>
						</li>
					</ul>
				</div><!-- /.navbar-collapse -->
			</div><!-- /.container-fluid -->
		</div>
	</nav>





	<div class="content">
		<div class="row">
			<div class="col-lg-10 col-lg-offset-1" id="news-content">
				<div ng-view></div>
			</div>
		</div>
	</div>


	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 footer">
		<div class="col-lg-10 col-lg-offset-1">
			<div class="col-sm-5 col-md-6 col-lg-8">
				<div class="footer-logo">
					<h4>Copyright © 2015 WorldNews.</h4>
					<h5>The World News is bla-bla. <a href="#">Read more.</a></h5>
				</div>
			</div>
			<div class="col-sm-7 col-md-6 col-lg-4">
				<div class="footer-contacts">
						<ul class="col-xs-6 col-sm-6 col-md-6">
							<li><a href="#">Advertise</a></li>
							<li><a href="#">Terms of Use</a></li>
						</ul>
						<ul class="col-xs-6 col-sm-6 col-md-6">
							<li><a href="#">Privacy</a></li>
							<li><a href="#">About site</a></li>
						</ul>
				</div>
			</div>
		</div>
	</div>




  </body>
</html>

