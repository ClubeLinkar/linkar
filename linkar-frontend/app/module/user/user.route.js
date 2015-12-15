(function () {
  'use strict';

  angular.module('linkar').config(config);

  config.$inject = ['$routeProvider'];

  function config($routeProvider) {

    console.log('user::route');

    $routeProvider.when('/user', {
      templateUrl: 'module/user/user.html',
      controller: 'User',
      controllerAs: 'vm'
    }).when('/user/new', {
      templateUrl: 'module/user/user_new.html',
      controller: 'User',
      controllerAs: 'vm'
    });
  }
})();
