(function() {
  'use strict';

  angular.module('linkar').controller('Category', CategoryController);

  CategoryController.$inject = ['$scope', 'Category', 'ngToast', '$stateParams', '$state'];

  function CategoryController($scope, Category, ngToast, $stateParams, $state) {

    console.log('category::controller:init');

    Category.query(function (data) {
      console.log("category::controller:list");
      $scope.categoryList = data;
    });

    Category.get({ id: $stateParams.categoryId }, function(data) {
      console.log("category::controller:detail");
      $scope.category = data;
    });

    this.save = function () {

      Category.save($scope.category, function (data) {
        console.log("category::controller:save");

        ngToast.create('A categoria ' + $scope.category.name + ' foi cadastrada com sucesso!');
        $state.go('category.main');

      }, function (erro) {
        console.log(erro);
        ngToast.danger("Houve um erro de validação dos dados. " + erro.data.exception);
      });
    };


  }

})();
