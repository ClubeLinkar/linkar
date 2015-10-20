angular.module("linkar")
    .controller("StoreController", function ($scope, $http) {

        $scope.store = {};
        $scope.mensagem = "";
        $scope.storeProductList = [];

        $scope.salvar = function() {

            $http.post("/api/v1/store", $scope.store).success(function () {
                $scope.mensagem = "Loja adicionada com sucesso!";
            });
        }


        $http.get('/api/v1/store').success(function (data) {
            $scope.storeList = data;
        });

        $scope.detail = function (store) {
            $scope.store = store;

            $http.get('/api/v1/product/store/' + store.id).success(function (data) {
                $scope.storeProductList = data;
            });
            

        }

    })
;