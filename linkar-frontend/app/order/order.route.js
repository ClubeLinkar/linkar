(function () {
  'use strict';

  angular.module('linkar').config(config);

  function config($routeProvider) {

    console.log('order::route');

    $routeProvider.when('/order/:storeId/:productId', {
      templateUrl: 'order/order_new.html',
      controller: 'Order',
      controllerAs: 'vm'
    });
  }
})();
