(function() {
  'use strict';

  angular.module('linkar').controller('SalesmanList', SalesmanListController);

  SalesmanListController.$inject = ['Salesman', 'ngToast', '$state', '$stateParams'];

  function SalesmanListController( Salesman, ngToast, $state, $stateParams) {

    var vm = this;

    vm.init = init;
    vm.list = list;

    init();

    function init() {
      vm.list();
    }

    function list() {

      Salesman.query(function (data) {
        vm.salesmanList = data;
      });
    }

  }

})();
