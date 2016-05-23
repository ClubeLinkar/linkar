(function() {
  'use strict';

  angular.module('linkar').controller('CategoryEdit', CategoryEditController);

  CategoryEditController.$inject = ['Category', 'ngToast', '$stateParams', '$state'];

  function CategoryEditController(Category, ngToast, $stateParams, $state) {

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

      if ($stateParams.categoryId) {
        Category.get({ id: $stateParams.categoryId }, function(data) {
          vm.category = data;
        });
      }
    }

    function save() {
      Category.save(vm.category, function (data) {

        ngToast.create('A categoria ' + vm.category.name + ' foi cadastrada com sucesso!');
        $state.go('category.main');

      }, function (erro) {
        console.log(erro);
        ngToast.danger("Houve um erro de validação dos dados. " + erro.data.exception);
      });
    }

    function cancel() {
      $state.go("category.main");
    }

  }

})();
