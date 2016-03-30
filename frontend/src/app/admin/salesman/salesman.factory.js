(function () {
  'use strict';

  angular.module('linkar').factory('Salesman', Salesman);

  Salesman.$inject = ['$resource'];

  function Salesman($resource) {
    console.log('salesman::factory');
    return $resource("/linkar/api/salesman/:id");
  }
})();
