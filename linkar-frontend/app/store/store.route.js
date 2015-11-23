(function () {
  'use strict';

  angular.module('linkar').config(config);

  function config($routeProvider) {

    console.log('store::route');

    $routeProvider.when('/store', {
      templateUrl: 'store/store.html',
      controller: 'Store',
      controllerAs: 'vm'
    });
  }
})();
