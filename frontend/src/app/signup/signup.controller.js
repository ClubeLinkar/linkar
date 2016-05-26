(function() {
  'use strict';

  angular.module('linkar').controller('Signup', SignupController);

  SignupController.$inject = ['ngToast', '$state', '$stateParams', '$http'];

  function SignupController(ngToast, $state, $stateParams, $http) {

    var vm = this;

    vm.init = init;
    vm.save = save;
    vm.cancel = cancel;

    vm.init();

    function init() {

    }

    function save() {

      console.log(vm.user);

      $http.post('/linkar/api/signup', vm.user).then(function (data) {
        ngToast.create('Seu usuário foi criado com sucesso, ' + vm.user.name + '. Você já pode Linkar!');
        $state.go('user.welcome');
      }, function (erro) {
        console.log(erro);
        ngToast.danger("Houve um erro de validação dos dados. " + erro.data.exception);
      });
    };

    function cancel() {
      $state.go("admin.main");
    }
  }

})();
