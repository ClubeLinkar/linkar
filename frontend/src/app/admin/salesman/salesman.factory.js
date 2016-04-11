(function () {
  'use strict';

  angular.module('linkar').factory('Salesman', Salesman);

  Salesman.$inject = ['$resource'];

  function Salesman($resource) {
    return $resource("/linkar/api/salesman/:id");
  }
})();
