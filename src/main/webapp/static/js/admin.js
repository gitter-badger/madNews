PERMISSIONS={'user-management':'User management (user can create/edit/delete users).',
			'news-add-edit-delete-own':"User can create, edit, delete own news.",
			'news-add-edit-delete-any':"User can create, edit, delete any news.",
			'news-edit-any':"User can edit any news."}
			
function jsonToFormData(json){
	var fd = new FormData();
	for (var key in json){
		fd.append(key, json[key]);
	}
	return fd;
}
var app = angular.module("NW_AdminPage", ['ngRoute', 'validation.match', 'ckeditor']);

app.config(['$httpProvider', function ($httpProvider) {
	//$httpProvider.defaults.headers.post['X-CSRFToken'] = Cookies.get('XSRF-TOKEN');
	//$httpProvider.defaults.headers.put['X-CSRFToken'] = Cookies.get('XSRF-TOKEN');
    $httpProvider.defaults.xsrfCookieName = 'X-XSRF-TOKEN';
    $httpProvider.defaults.xsrfHeaderName = 'X-CSRFToken';
}]);

app.filter("sanitize", ['$sce', function($sce) {
  return function(htmlCode){
    return $sce.trustAsHtml(htmlCode);
  }
}]);

app.directive('fileModel', ['$parse', function ($parse) {
	//
	//	Binding html input-file to ng-model
	//
	//
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);



app.directive('usernameAvailable', function($q) {
	return {
		restrict: 'AE',
		require: 'ngModel',
		link: function(scope, elm, attr, model) { 
			model.$validators.usernameExists = function() { 
				var user="";
				model.$setValidity('usernameExists', true); 
				for (var i=0; i<$.db_fixtures.admin_users.length; ++i){
					user=$.db_fixtures.admin_users[i];
					if (model.$$rawModelValue == user.username){
						model.$setValidity('usernameExists',true); //=)
							return false;
					}
				}
				return true;
			};
		}
	} 
});

app.controller('newsCreateCtrl', ["$scope", "$http", function ($scope, $http) {
	$scope.newsNew={tags:[]};
	$scope.currentTab='editor';
	$http.get("/api/v1/public/tags/").
		success(function(data, status, headers, config){
			$scope.availableTags=data;//$.db_fixtures.tags;
	});
  // Editor options.
	$scope.options = {
		language: 'en',
		allowedContent: true,
		entities: false,
		//uiColor: 'yellow',
		filebrowserUploadUrl: "/api/v1/private/image",
		toolbar:[
			{ name: 'document', items: [ 'Source', '-', 'NewPage'] },
			[ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ],
			{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker' ], 
					items: [ 'Find', 'Replace', '-', 'SelectAll', '-', 'Scayt' ] },
			'/',	
			{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ], 
					items: [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'RemoveFormat' ] },
			{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ], 
					items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', '-', 'BidiLtr', 'BidiRtl' ] },
			{ name: 'insert', items: [ 'Image', 'Flash', 'Table', 'HorizontalRule', 'SpecialChar', 'PageBreak' ] },
			{ name: 'styles', items: [ 'Styles', 'Format', 'FontSize' ] },
			{ name: 'colors', items: [ 'TextColor', 'BGColor' ] },
			{ name: 'tools', items: [ 'Maximize', 'ShowBlocks' ] },		
		]
	};

	$scope.addTag = function(tag){
		if ($.inArray(tag, $scope.newsNew.tags)==-1){
			$scope.newsNew.tags.push(tag);
		}
	}
	$scope.removeTag = function(tag){
		if ($.inArray(tag, $scope.newsNew.tags)>-1){
			var tagIndex=$.fn.getIndexFromJsonById($scope.newsNew.tags, tag.id);
			$scope.newsNew.tags.splice(tagIndex,1);			
		}
	}
	$scope.publish = function(){
		$http.get("/api/v1/private/users/1").
			success(function(data, status, headers, config){
				$scope.newsNew.user=data ;
				//delete $scope.newsNew.tags;
				console.log($scope.newsNew);
				$http.defaults.headers.post['X-XSRF-TOKEN'] = Cookies.get('XSRF-TOKEN');
				$http.defaults.headers.put['X-CSRFToken'] = Cookies.get('XSRF-TOKEN');

				$http.post("/api/v1/private/news/", jsonToFormData($scope.newsNew)).
					success(function(data, status, headers, config){
						$scope.newsNew = {"tags":[]} ;
				});
				
		});
	}
	$scope.publishAsJson = function(){
		$http.get("/api/v1/private/users/1").
			success(function(data, status, headers, config){
				$scope.newsNew.user=data ;
				//delete $scope.newsNew.tags;
				console.log($scope.newsNew);
				$http.defaults.headers.post['X-XSRF-TOKEN'] = Cookies.get('XSRF-TOKEN');
				$http.defaults.headers.put['X-CSRFToken'] = Cookies.get('XSRF-TOKEN');

				$http.post("/api/v1/private/news/", $scope.newsNew).
					success(function(data, status, headers, config){
						$scope.newsNew = {"tags":[]} ;
				});
				
		});
	}	
}]);





app.controller("defaultCtrl", ["$scope", function($scope) {
	$scope.dashboardTab='dashboard';
	$scope.currentUser=$.db_fixtures.admin_current_user;
}])

app.controller("usersListCtrl", ["$scope","$location",  function($scope, $location) {
	$scope.users=$.db_fixtures.admin_users;
}])


app.controller("usersCreateCtrl", ["$scope","$location",  function($scope, $location) {
	$scope.user={'permissions':[{'name':'user-management', 'value':false, 'full_name':'User management (user can create/edit/delete users).'},
								{'name':'news-add-edit-delete-own', 'value':false, 'full_name':"User can create, edit, delete own news."},
								{'name':'news-add-edit-delete-any', 'value':false, 'full_name':"User can create, edit, delete any news."},
								{'name':'news-edit-any', 'value':false, 'full_name':"User can edit any news."}
								]
				};
	
	$scope.save=function(){
		var new_user={};
		new_user.id=$.db_fixtures.admin_users.length+10;
		new_user.username=$scope.user.username;
		new_user.password=$scope.user.password;
		new_user.permissions=[];
		var permission={};
		for (var i=0;i<$scope.user.permissions.length; i++){
			permission=$scope.user.permissions[i];
			if (permission.value){
				new_user.permissions.push(permission.name);
			}
		}
		$.db_fixtures.admin_users.push(new_user);
		$location.path('/users/');
		this.$parent.dashboardTab="users-edit-delete";
	}

}])

app.controller("usersEditCtrl", ["$scope","$location", "$routeParams",  function($scope, $location, $routeParams) {
	var userId=$routeParams.userId;
	var userIndex=$.fn.getIndexFromJsonById($.db_fixtures.admin_users, userId);
	var user=$.db_fixtures.admin_users[userIndex];
	$scope.user={};
	$scope.user.username=user.username;
	$scope.user.id=user.id;
	var permissions=[];
	for (var prop in PERMISSIONS){
		var permission={};
		permission.name=prop;
		permission.full_name=PERMISSIONS[prop];
		permission.value=false;
		if (user.permissions.indexOf(prop)>-1){
			permission.value=true;
		}
		permissions.push(permission);
	}
	$scope.user.permissions=permissions;

	$scope.save=function(){
		var userIndex=$.fn.getIndexFromJsonById($.db_fixtures.admin_users, 
												$scope.user.id);
		$.db_fixtures.admin_users[userIndex].permissions=[];
		var permission={};
		for (var i=0;i<$scope.user.permissions.length; i++){
			permission=$scope.user.permissions[i];
			if (permission.value){
				$.db_fixtures.admin_users[userIndex].permissions.push(permission.name);
			}
		}
		$location.path('/users/');
		this.$parent.dashboardTab="users-edit-delete";
	}
}]);


app.controller("usersDeleteCtrl", ["$scope","$location", "$routeParams",  function($scope, $location, $routeParams) {
	var userId=$routeParams.userId;
	var userIndex=$.fn.getIndexFromJsonById($.db_fixtures.admin_users, userId);
	$scope.user=$.db_fixtures.admin_users[userIndex];

	$scope.delete_user=function(){
		var userIndex=$.fn.getIndexFromJsonById($.db_fixtures.admin_users, 
												$scope.user.id);
		$.db_fixtures.admin_users.splice(userIndex,1);
		$location.path('/users/');
		this.$parent.dashboardTab="users-edit-delete";
	}
}]);



app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/users-about', {
        templateUrl: '/partials-html/users-about.html'
      }).
      when('/users/', {
        templateUrl: '/partials-html/users-list.html',
        controller: 'usersListCtrl as usersCtrl'
      }).
      when('/users/create/', {
        templateUrl: '/partials-html/users-create.html',
        controller: 'usersCreateCtrl'
      }).
      when('/users/edit/:userId', {
        templateUrl: '/partials-html/user-edit.html',
        controller: 'usersEditCtrl'
      }).
      when('/users/delete/:userId', {
        templateUrl: '/partials-html/user-delete.html',
        controller: 'usersDeleteCtrl'
      }).


      when('/news/create/', {
        templateUrl: '/partials-html/news-create.html',
        controller: 'newsCreateCtrl'
      }).
      when('/dashboard-about/', {
        templateUrl: '/partials-html/dashboard-about.html'
      }).
      otherwise({
        redirectTo: '/dashboard-about'
      });
  }]);




