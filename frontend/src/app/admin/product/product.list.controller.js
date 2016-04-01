(function() {
  'use strict';

  angular.module('linkar').controller('ProductList', ProductListController);

  ProductListController.$inject = ['Product', 'Company', 'ngToast', '$stateParams', '$state'];

  function ProductListController(Product, Company, ngToast, $stateParams, $state) {

    var vm = this;

    vm.init = init;
    vm.list = list;

    init();

    function init() {
      vm.list();
    }

    function list() {
      Product.query(function (data) {
        console.log("product::controller:list");
        vm.productList = data;
      });
    }

  }

})();
