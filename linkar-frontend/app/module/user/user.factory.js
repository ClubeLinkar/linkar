(function () {
  'use strict';

  angular.module('linkar').factory('User', User);

  function User($resource) {
    console.log('user::factory');
    return $resource("/linkar/api/user/:id");
  }
})();
