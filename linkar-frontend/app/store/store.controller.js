(function() {
  'use strict'

  angular.module('linkar').controller('Store', StoreController);

  function StoreController($scope, Store) {

    console.log('store::controller');

    Store.query(function (data) {
      console.log(data);
      $scope.storeList = data;
    });
  }

})();
