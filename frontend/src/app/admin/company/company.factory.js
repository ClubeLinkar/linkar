(function () {
  'use strict';

  angular.module('linkar').factory('Company', Company);

  Company.$inject = ['$resource'];

  function Company($resource) {
    console.log('company::factory');
    return $resource("/linkar/api/company/:id");
  }
})();
