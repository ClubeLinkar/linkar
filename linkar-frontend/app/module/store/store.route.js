(function () {
  'use strict';

  angular.module('linkar').config(config);

  function config($routeProvider) {

    console.log('store::route');

    $routeProvider.when('/store', {
      templateUrl: 'module/store/store.html',
      controller: 'Store',
      controllerAs: 'vm'
    }).when('/store/new', {
      templateUrl: 'module/store/store_new.html',
      controller: 'Store',
      controllerAs: 'vm'
    });
  }
})();
