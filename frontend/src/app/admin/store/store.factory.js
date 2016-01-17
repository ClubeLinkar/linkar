(function () {
  'use strict';

  angular.module('linkar').factory('Store', Store);

  Store.$inject = ['$resource'];

  function Store($resource) {
    console.log('store::factory');
    return $resource("/linkar/api/store/:id");
  }
})();
