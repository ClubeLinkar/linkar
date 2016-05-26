(function() {
  'use strict';

  angular.module('linkar').controller('TransactionCreate', TransactionCreateController);

  TransactionCreateController.$inject = ['Transaction', 'Product', 'User', 'Company', 'ngToast', '$stateParams', '$state'];

  function TransactionCreateController(Transaction, Product, User, Company, ngToast, $stateParams, $state) {

    var vm = this;

    vm.init = init;
    vm.save = save;
    vm.cancel = cancel;

    vm.init();

    function init() {

      vm.transaction = {};

      Product.query(function (data) {
        vm.productList = data;
      });

      User.query(function (data) {
        vm.customerList = data;
      });

      Company.query(function (data) {
        vm.companyList = data;
      });
    }



    function save() {

      vm.transaction.productId = vm.productSelected.id;
      vm.transaction.productName = vm.productSelected.name;
      vm.transaction.productUnitPrice = vm.productSelected.price;

      vm.transaction.customerId = vm.customerSelected.id;
      vm.transaction.customerName = vm.customerSelected.name;
      vm.transaction.customerEmail = vm.customerSelected.email;

      vm.transaction.companyId = vm.productSelected.companyId;

      Transaction.save(vm.transaction, function (data) {

        ngToast.create('Transação registrada com sucesso!');
        $state.go('transaction.main');

      }, function (erro) {
        console.log(erro);
        ngToast.danger("Houve um erro de validação dos dados. " + erro.data.exception);
      });
    }

    function cancel() {
      $state.go("transaction.main");
    }

  }

})();
