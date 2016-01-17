(function() {
  'use strict';

  angular.module('linkar').controller('User', UserController);

  UserController.$inject = ['$scope', 'User', 'ngToast'];

  function UserController($scope, User, ngToast) {

    console.log('user::controller:init');

    User.query(function (data) {
      console.log("user::controller:list");
      $scope.userList = data;
    });

    this.save = function () {
      User.save($scope.user, function (data) {
        console.log("user::controller:save");
        ngToast.create('Seu usuário foi criado com sucesso, ' + $scope.user.name + '. Você já pode Linkar!');
      });
    };
  }

})();
