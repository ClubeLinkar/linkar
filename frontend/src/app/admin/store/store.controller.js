(function() {
  'use strict';

  angular.module('linkar').controller('Store', StoreController);

  StoreController.$inject = ['$scope', 'Store', '$stateParams'];

  function StoreController($scope, Store, $stateParams) {

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
        $scope.message = "Success!";
      });
    };
  }

})();
