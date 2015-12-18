(function () {
  'use strict';

  angular.module('linkar').config(config);

  config.$inject = ['$routeProvider'];

  function config($routeProvider) {

    console.log('product::route');

    $routeProvider.when('/product', {
      templateUrl: 'module/product/product.html',
      controller: 'Product',
      controllerAs: 'vm'
    }).when('/product/new', {
      templateUrl: 'module/product/product_new.html',
      controller: 'Product',
      controllerAs: 'vm'
    });
  }
})();
