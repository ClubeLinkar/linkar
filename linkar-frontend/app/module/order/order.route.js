(function () {
  'use strict';

  angular.module('linkar').config(config);

  config.$inject = ['$routeProvider'];

  function config($routeProvider) {

    console.log('order::route');

    $routeProvider.when('/order/:storeId/:productId', {
      templateUrl: 'module/order/order_new.html',
      controller: 'Order',
      controllerAs: 'vm'
    });
  }
})();
