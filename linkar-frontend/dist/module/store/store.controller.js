(function() {
  'use strict'

  angular.module('linkar').controller('Store', StoreController);

  function StoreController($scope, Store) {

    console.log('store::controller:init');

    Store.query(function (data) {
      console.log("store::controller:list");
      $scope.storeList = data;
    });

    this.save = function () {
      Store.save($scope.store, function (data) {
        console.log("store::controller:save");
        $scope.message = "Success!";
      });
    }
  }

})();
