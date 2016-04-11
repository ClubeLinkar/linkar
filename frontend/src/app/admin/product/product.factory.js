(function () {
  'use strict';

  angular.module('linkar').factory('Product', Product);

  Product.$inject = ['$resource'];

  function Product($resource) {
    return $resource("/linkar/api/product/:id");
  }
})();
