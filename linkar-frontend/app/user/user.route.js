(function () {
  'use strict';

  angular.module('linkar').config(config);

  function config($routeProvider) {

    console.log('user::route');

    $routeProvider.when('/user', {
      templateUrl: 'user/user.html',
      controller: 'User',
      controllerAs: 'vm'
    }).when('/user/new', {
      templateUrl: 'user/user_new.html',
      controller: 'User',
      controllerAs: 'vm'
    });
  }
})();
