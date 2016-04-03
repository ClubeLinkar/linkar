(function () {
  'use strict';

  angular.module('linkar').factory('Category', Category);

  Category.$inject = ['$resource'];

  function Category($resource) {
    return $resource("/linkar/api/category/:id");
  }
})();
