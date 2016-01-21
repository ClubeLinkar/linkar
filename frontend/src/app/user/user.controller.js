(function() {
  'use strict';

  angular.module('linkar').controller('User', UserController);

  UserController.$inject = ['$scope', 'User', 'ngToast', '$state', '$stateParams'];

  function UserController($scope, User, ngToast, $state, $stateParams) {

    console.log('user::controller:init');

    User.query(function (data) {
      console.log("user::controller:list");
      $scope.userList = data;
    });

    User.get({ id: $stateParams.userId }, function(data) {
      console.log("user::controller:detail");
      $scope.user = data;
    });

    this.save = function () {
      User.save($scope.user, function (data) {
        console.log("user::controller:save");
        ngToast.create('Seu usuário foi criado com sucesso, ' + $scope.user.name + '. Você já pode Linkar!');
        $state.go('user.main');
      }, function (erro) {
        console.log(erro);
        ngToast.danger("Houve um erro de validação dos dados. " + erro.data.exception);
      });
    };
  }

})();
