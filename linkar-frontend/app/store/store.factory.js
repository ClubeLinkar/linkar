(function () {
  'use strict';

  angular.module('linkar').factory('Store', Store);

  function Store($resource) {
    console.log('store::factory');
    return $resource("/linkar/api/store/:id");
  }
})();
