(function () {
  'use strict';

  angular.module('linkar').config(config);

  function config($routeProvider) {

    console.log('tag::route');

    $routeProvider.when('/product/tag/:storeId', {
      templateUrl: 'product/tag/tag.html',
      controller: 'Tag',
      controllerAs: 'vm'
    }).when('/product/tag', {
      templateUrl: 'product/tag/tag.html',
      controller: 'Tag',
      controllerAs: 'vm'
    });
  }
})();
