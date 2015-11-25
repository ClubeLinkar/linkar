(function () {
  'use strict';

  angular.module('linkar').factory('Order', Order);

  function Order($resource) {
    console.log('order::factory');
    return $resource("/linkar/api/order/:id");
  }
})();
