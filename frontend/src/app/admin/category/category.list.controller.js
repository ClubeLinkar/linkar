(function() {
  'use strict';

  angular.module('linkar').controller('CategoryList', CategoryListController);

  CategoryListController.$inject = ['Category', 'ngToast', '$stateParams', '$state'];

  function CategoryListController(Category, ngToast, $stateParams, $state) {

    var vm = this;

    vm.init = init;
    vm.list = list;

    init();

    function init() {
      vm.list();
    }

    function list() {
      Category.query(function (data) {
        console.log("category::controller:list");
        vm.categoryList = data;
      });
    }

  }

})();
