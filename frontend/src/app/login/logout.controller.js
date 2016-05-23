(function () {
  'use strict';

  angular.module('linkar').controller('Logout', Logout);

  function Logout($state, $http, $rootScope) {

    var vm = this;

    vm.logout = logout;

    logout();

    function logout() {
      $http.get('/linkar/api/logout').success(function(data) {
        if (data.name) {
          $rootScope.authenticated = true;
        } else {
          $rootScope.authenticated = false;
          $rootScope.loggedUser = null;
        }
      }).error(function() {
        $rootScope.authenticated = true;
      });
      // $http.post('/linkar/api/logout', {}).finally(function() {
      //   $rootScope.authenticated = false;
      //   $location.path("/");
      // });
    }

  }
})();
