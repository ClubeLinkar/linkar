(function() {
  'use strict';

  angular.module('linkar').controller('CompanyEdit', CompanyEditController);

  CompanyEditController.$inject = ['Company', '$stateParams', 'ngToast', '$state'];

  function CompanyEditController(Company, $stateParams, ngToast, $state) {

    var vm = this;

    vm.init = init;
    vm.get = get;
    vm.save = save;

    vm.init();

    function init() {
      vm.get();
    }

    function get() {
      if ($stateParams.companyId) {
        Company.get({ id: $stateParams.companyId }, function(data) {
          vm.company = data;
        });
      }
    }

    function save() {

        Company.save(vm.company, function (data) {
          console.log("company::controller:save");
          ngToast.create('A loja ' + vm.company.name + ' foi cadastrada com sucesso!');
          $state.go('company.main');
        }, function (erro) {
          console.log(erro);
          ngToast.danger("Houve um erro de validação dos dados. " + erro.data.exception);
        });

    }

  }

})();
