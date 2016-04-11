(function () {
  'use strict';

  angular.module('linkar').factory('User', User);

  User.$inject = ['$resource'];

  function User($resource) {
    return $resource("/linkar/api/user/:id");
  }
})();
