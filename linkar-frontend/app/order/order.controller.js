(function() {
  'use strict'

  angular.module('linkar').controller('Order', OrderController);

  function OrderController($scope, $routeParams, Order, Store, Product) {

    console.log('order::controller:init');

    $scope.order = {};

    Product.get({id: $routeParams.productId}, function(data) {
      $scope.order.productId = data.id;
      $scope.order.productName = data.name;
      $scope.order.purchasePrice = data.price;
    });

    Store.get({id: $routeParams.storeId}, function(data) {
      $scope.order.storeId = data.id;
      $scope.order.storeName = data.name;
    });

    this.save = function () {
      Order.save($scope.order, function (data) {
        console.log("order::controller:save");
        $scope.message = "Success!";
      });
    }
  }

})();
