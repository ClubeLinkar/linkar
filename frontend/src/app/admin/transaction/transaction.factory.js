(function () {
  'use strict';

  angular.module('linkar').factory('Transaction', Transaction);

  Transaction.$inject = ['$resource'];

  function Transaction($resource) {
    return $resource("/linkar/api/transaction/:id");
  }
})();
