(function () {
  'use strict';

  angular.module('linkar').controller('Login', Login);

		function Login($rootScope, $http, $location, ngToast) {

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
            $rootScope.loggedUser = {
              email: data.principal.user.email,
              name: data.principal.user.name,
              role: data.principal.user.role
            }
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
				authenticate(self.credentials, function(authenticated) {
					if (authenticated) {
						ngToast.create('Autenticação bem sucedida.');
						$location.path("/");
						self.error = false;
						$rootScope.authenticated = true;
					} else {
						ngToast.danger('Falha de autenticação. Verifique se os dados informados estão corretos.');
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
