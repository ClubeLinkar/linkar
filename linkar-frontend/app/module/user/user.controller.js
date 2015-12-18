(function() {
  'use strict';

  angular.module('linkar').controller('User', UserController);

  UserController.$inject = ['$scope', 'User'];

  function UserController($scope, User) {

    console.log('user::controller:init');

    User.query(function (data) {
      console.log("user::controller:list");
      $scope.userList = data;
    });

    this.save = function () {
      User.save($scope.user, function (data) {
        console.log("user::controller:save");
        $scope.message = "Success!";
      });
    };
  }

})();
