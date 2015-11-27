(function () {
  'use strict';

  angular.module('linkar').factory('Product', Product);

  function Product($resource) {
    console.log('product::factory');
    return $resource("/linkar/api/product/:id");
  }
})();
