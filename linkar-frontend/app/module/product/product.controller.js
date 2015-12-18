(function() {
  'use strict'

  angular.module('linkar').controller('Product', ProductController);

  ProductController.$inject = ['$scope', 'Product', 'Store'];

  function ProductController($scope, Product, Store) {

    console.log('product::controller:init');

    Product.query(function (data) {
      console.log("product::controller:list");
      $scope.productList = data;
    });

    Store.query(function (data) {
      console.log("product::controller:list:storeList");
      $scope.storeList = data;
    });

    this.save = function () {
      Product.save($scope.product, function (data) {
        console.log("product::controller:save");
        $scope.message = "Success!";
      });
    }
  }

})();
