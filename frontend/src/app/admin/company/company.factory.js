(function () {
  'use strict';

  angular.module('linkar').factory('Company', Company);

  Company.$inject = ['$resource'];

  function Company($resource) {
    return $resource("/linkar/api/company/:id");
  }
})();
