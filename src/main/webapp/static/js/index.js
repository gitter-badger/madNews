var THEMES={
	"original":['#f4f4f4', '#0d0d0d','#d55252', '#f52'],
	"dark":['#131313', 'white','#bfb836', '#de3765'],
	"grey":['#bdbdbd', 'black','#c96f6f', '#f52']
};

var app = angular.module("NW_MainPage", ['ngRoute']);

app.filter("sanitize", ['$sce', function($sce) {
  return function(htmlCode){
    return $sce.trustAsHtml(htmlCode);
  }
}]);

angular.module('NW_MainPage').filter('cut', function () {
        return function (value, wordwise, max, tail) {
            if (!value) return '';

            max = parseInt(max, 10);
            if (!max) return value;
            if (value.length <= max) return value;

            value = value.substr(0, max);
            if (wordwise) {
                var lastspace = value.lastIndexOf(' ');
                if (lastspace != -1) {
                    value = value.substr(0, lastspace);
                }
            }

            return value + (tail || ' …');
        };
});


angular.module("NW_MainPage").service("PageSettings", function() {
	if (Cookies.get('main-page-layout')){
		this.newsLayout=Cookies.get('main-page-layout');
	}
	else{
		this.newsLayout="line";
	}
	if (Cookies.get('theme')){
		this.theme=Cookies.get('theme');
	}	
	else{
		this.theme='original';
	}

	this.setNewsLayout=function(layout){
		this.newsLayout=layout;
		Cookies.set('main-page-layout', layout);
	}
	this.setTheme=function(theme_name){
		Cookies.set('theme', theme_name);
		$.fn.modifyStyle(THEMES[theme_name][0], THEMES[theme_name][1], 
						THEMES[theme_name][2], THEMES[theme_name][3]);
		this.theme=theme_name;
	}
});

app.controller("mainPageCtrl", ["$scope", "$location", "PageSettings", function($scope, $location, PageSettings) {
	$scope.settings=PageSettings;
}]);


//
//
//
app.controller("newsListCtrl", ["$scope", "$http", "PageSettings", function($scope, $http, PageSettings) {
	$scope.news_list=[];
	$http.get("/api/v1/public/news/").
		success(function(data, status, headers, config){
			console.log(data);
			$scope.news_list=data;
	});
	$scope.top_news_single=$.db_fixtures.top_news[1];
	$scope.settings=PageSettings;
}]);

app.controller("searchByTagCtrl", ["$scope", "$http","$routeParams", "PageSettings",
			   function($scope, $http, $routeParams, PageSettings) {
	var tagId=$routeParams.tagId;
	var pageN=$routeParams.pageN;
	$scope.tag={'id':tagId};
	$scope.news_list=[];
	$scope.settings=PageSettings;
	$scope.page={};


	$http.get("/api/v1/public/tags/"+tagId).
		success(function(data, status, headers, config){
			$scope.tag=data;
	});
	var url=tagId;
	if (pageN){
		url=url+"?page="+pageN;
	}
	$http.get("/api/v1/public/news/tag/"+url).
		success(function(data, status, headers, config){
			console.log(data);
			$scope.news_list=data.content;
			$scope.page=data.page;
			$scope.page.pages=Array.apply(null, Array($scope.page.totalPages)).
									map(function (_, i) {return i;});
	});
}]);




app.controller("newsDetailCtrl", ["$scope", "$http", "$routeParams", "PageSettings", 
									function($scope, $http, $routeParams, PageSettings) {
	var newsId=$routeParams.newsId;
	//var index=$.fn.getIndexFromJsonById($.db_fixtures.news, newsId);
	$scope.news_single={};//$.db_fixtures.news[index];
	$http.get("/api/v1/public/news/"+newsId+"/").
		success(function(data, status, headers, config){
			$scope.news_single=data;
	});
	
	$scope.settings=PageSettings;


	$.fn.expandContent();

	var disqus_shortname = 'k-gerasev-school-dataart';
	var disqus_identifier= 'news-tmp-'+newsId;

	/* * * DON'T EDIT BELOW THIS LINE * * */
	(function() {
		var dsq = document.createElement('script'); dsq.type = 'text/javascript'; dsq.async = true;
		dsq.src = '//' + disqus_shortname + '.disqus.com/embed.js';
		(document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq);
	})();

}]);




app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/', {
        templateUrl: '/partials-html/news-list.html',
        controller: 'newsListCtrl'
      }).
      when('/news/:newsId', {
        templateUrl: '/partials-html/news-detail.html',
        controller: 'newsDetailCtrl'
      }).
      when('/by-tag/:tagId', {
        templateUrl: '/partials-html/search-by-tag.html',
        controller: 'searchByTagCtrl'
      }).
      when('/by-tag/:tagId/page/:pageN', {
        templateUrl: '/partials-html/search-by-tag.html',
        controller: 'searchByTagCtrl'
      }).
      otherwise({
        redirectTo: '/'
      });
  }]);

