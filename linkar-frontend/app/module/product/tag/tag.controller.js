(function() {
  'use strict'

  angular.module('linkar').controller('Tag', TagController);

  TagController.$inject = ['$scope', 'Product', 'Store'];

  function TagController($scope, Product, Store) {

    console.log('tag::product::controller:init');

    Product.query(function (data) {
      console.log("product::controller:list");
      $scope.productList = data;
    });

    // Store.query(function (data) {
    //   console.log("product::controller:list:storeList");
    //   $scope.storeList = data;
    // });

  }

})();
