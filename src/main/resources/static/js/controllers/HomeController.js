angular.module('linkar-admin').controller('HomeController', function($scope, $http) {



	$http.get('/store').success(function(data) {
		$scope.welcomeMessage = data;
	});

});