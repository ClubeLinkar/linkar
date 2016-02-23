(function () {
  'use strict';

  angular.module('linkar').factory('Category', Category);

  Category.$inject = ['$resource'];

  function Category($resource) {
    console.log('category::factory');
    return $resource("/linkar/api/category/:id");
  }
})();
