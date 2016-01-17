(function () {
  'use strict';

  angular.module('linkar').factory('User', User);

  User.$inject = ['$resource'];

  function User($resource) {
    console.log('user::factory');
    return $resource("/linkar/api/user/:id");
  }
})();
