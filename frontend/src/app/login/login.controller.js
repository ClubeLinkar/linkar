(function () {
  'use strict';

  angular.module('linkar').controller('Login', Login);

		function Login($rootScope, $http, $location) {

			var self = this;

			// self.tab = function(route) {
			// 	return $route.current && route === $route.current.controller;
			// };

			var authenticate = function(credentials, callback) {

				var headers = credentials ? {
					authorization : "Basic "
							+ btoa(credentials.username + ":"
									+ credentials.password)
				} : {};

				$http.get('/linkar/api/auth/user', {
					headers : headers
				}).success(function(data) {
					if (data.name) {
						$rootScope.authenticated = true;
					} else {
						$rootScope.authenticated = false;
					}
					callback && callback($rootScope.authenticated);
				}).error(function() {
					$rootScope.authenticated = false;
					callback && callback(false);
				});

			}

			authenticate();

			self.credentials = {};
			self.login = function() {

        console.log(self.credentials);

				authenticate(self.credentials, function(authenticated) {
					if (authenticated) {
						console.log("Login succeeded")
						$location.path("/");
						self.error = false;
						$rootScope.authenticated = true;
					} else {
						console.log("Login failed")
						$location.path("/login");
						self.error = true;
						$rootScope.authenticated = false;
					}
				})
			};

			self.logout = function() {
				$http.post('/linkar/api/logout', {}).finally(function() {
					$rootScope.authenticated = false;
					$location.path("/");
				});
			}

		}
})();
