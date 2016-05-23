(function() {
  'use strict';

  angular.module('linkar').controller('SalesmanEdit', SalesmanEditController);

  SalesmanEditController.$inject = ['Salesman', 'ngToast', '$state', '$stateParams'];

  function SalesmanEditController(Salesman, ngToast, $state, $stateParams) {

    var vm = this;

    vm.init = init;
    vm.get = get;
    vm.save = save;
    vm.cancel = cancel;

    vm.init();

    function init() {
      vm.get();
    }

    function get() {
      if ($stateParams.salesmanId) {
        Salesman.get({ id: $stateParams.salesmanId }, function(data) {
          vm.salesman = data;
        });
      }
    }

    function save() {
      Salesman.save(vm.salesman, function (data) {
        ngToast.create('Seu vendedor foi criado com sucesso, ' + vm.salesman.name + '. Você já pode Linkar!');
        $state.go('salesman.main');
      }, function (erro) {
        console.log(erro);
        ngToast.danger("Houve um erro de validação dos dados. " + erro.data.exception);
      });
    }

    function cancel() {
      $state.go("salesman.main");
    }

  }

})();
