(function () {
  'use strict';

  angular.module('linkar').factory('Order', Order);

  Order.$inject = ['$resource'];

  function Order($resource) {
    console.log('order::factory');
    return $resource("/linkar/api/order/:id");
  }
})();
