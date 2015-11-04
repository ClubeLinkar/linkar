angular.module("linkar")
    .controller("PurchaseController", function ($scope, $http, $location) {

        var params = $location.absUrl().split("/");
        var storeId = params[4];
        var productId = params[5];

        $scope.purchase = {};

        $http.get("/api/v1/product/" + productId).success(function (data) {
            console.log(data.name + data.id);
            $scope.purchase.productId = data.id;
            $scope.purchase.productName = data.name;
            $scope.purchase.purchasePrice = data.price;
            $scope.purchase.earnedPoints = Math.ceil(data.price / 1.3);

        });

        $http.get("/api/v1/store/" + storeId).success(function (data) {
            $scope.purchase.storeId = data.id;
            $scope.purchase.storeName = data.name;
        });


        $scope.checkout = function () {
            // verificar se userId e userPass coincidem
            // verificar se storeId e storePass coincidem
            // verificar se storeId e productStoreId coincidem
            // verificar se nao existe um purchase by userId + storeId + productId

            $http.post("/api/v1/checkout", $scope.purchase).then(function () {
                $scope.mensagem = "Purchase adicionada com sucesso!";
            }, function (error) {
                $scope.mensagem = error.data.message;
            });

        }



    })
;