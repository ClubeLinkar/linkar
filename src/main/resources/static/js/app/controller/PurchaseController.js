angular.module("linkar")
    .controller("PurchaseController", function ($scope, $http, $location) {

        var params = $location.absUrl().split("/");
        var storeId = params[4];
        var productId = params[5];

        $scope.purchase = {};

        $http.get("/api/v1/product/" + productId).success(function (data) {
            $scope.purchase.product = data;
        });

        $http.get("/api/v1/store/" + storeId).success(function (data) {
            $scope.purchase.store = data;
        });


        $scope.checkout = function () {
            // verificar se userId e userPass coincidem
            // verificar se storeId e storePass coincidem
            // verificar se storeId e productStoreId coincidem
            // verificar se nao existe um purchase by userId + storeId + productId
        }



    })
;