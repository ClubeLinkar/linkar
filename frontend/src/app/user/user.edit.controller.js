(function() {
  'use strict';

  angular.module('linkar').controller('UserEdit', UserEditController);

  UserEditController.$inject = ['User', 'ngToast', '$state', '$stateParams'];

  function UserEditController(User, ngToast, $state, $stateParams) {

    var vm = this;

    vm.init = init;
    vm.get = get;
    vm.save = save;

    vm.init();

    function init() {
      vm.get();
    }

    function get() {
      if ($stateParams.userId) {
        User.get({ id: $stateParams.userId }, function(data) {
          vm.user = data;
        });
      }
    }

    function save() {
      User.save(vm.user, function (data) {
        ngToast.create('Seu usuário foi criado com sucesso, ' + vm.user.name + '. Você já pode Linkar!');
        $state.go('user.main');
      }, function (erro) {
        console.log(erro);
        ngToast.danger("Houve um erro de validação dos dados. " + erro.data.exception);
      });
    };
  }

})();
