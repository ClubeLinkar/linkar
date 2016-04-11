(function() {
  'use strict';

  angular.module('linkar').controller('CompanyList', CompanyListController);

  CompanyListController.$inject = ['Company', '$stateParams', 'ngToast', '$state'];

  function CompanyListController(Company, $stateParams, ngToast, $state) {

    var vm = this;

    vm.init = init;
    vm.list = list;

    init();

    function init() {
      vm.list();
    }

    function list() {
      Company.query(function (data) {
        vm.companyList = data;
      });
    }

  }

})();
