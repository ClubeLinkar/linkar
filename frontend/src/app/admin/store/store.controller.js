(function() {
  'use strict';

  angular.module('linkar').controller('Store', StoreController);

  StoreController.$inject = ['$scope', 'Store', '$stateParams', 'ngToast'];

  function StoreController($scope, Store, $stateParams, ngToast) {

    console.log('store::controller:init');

    Store.get({ id: $stateParams.storeId }, function(data) {
      console.log("store::controller:detail");
      $scope.store = data;
    });

    Store.query(function (data) {
      console.log("store::controller:list");
      $scope.storeList = data;
    });

    this.save = function () {
      Store.save($scope.store, function (data) {
        console.log("store::controller:save");
        ngToast.create('A loja ' + $scope.store.name + ' foi cadastrada com sucesso!');
      }, function (erro) {
        console.log(erro);
        ngToast.danger("Houve um erro de validação dos dados. " + erro.data.exception);
      });
    };
  }

})();
