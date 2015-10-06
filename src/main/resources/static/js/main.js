angular.module('linkar-admin', ['ngRoute'])
	.config(function($routeProvider) {

		$routeProvider.when('/home', {
			templateUrl: 'partials/home.html',
			controller: 'HomeController'
		}).when('/store', {
				templateUrl: 'partials/store.html',
				controller: 'StoreController'
		}).when('/product', {
			templateUrl: 'partials/product.html',
			controller: 'ProductController'
		});

		$routeProvider.otherwise({redirectTo: '/home'});

	});