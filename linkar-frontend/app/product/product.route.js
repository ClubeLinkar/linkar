(function () {
  'use strict';

  angular.module('linkar').config(config);

  function config($routeProvider) {

    console.log('product::route');

    $routeProvider.when('/product', {
      templateUrl: 'product/product.html',
      controller: 'Product',
      controllerAs: 'vm'
    }).when('/product/new', {
      templateUrl: 'product/product_new.html',
      controller: 'Product',
      controllerAs: 'vm'
    });
  }
})();
