angular.module("linkar", [])
    .controller("ProductController", function ($scope, $http) {

        $scope.product = {};
        $scope.mensagem = "";

        $scope.salvar = function() {

            $http.post("/api/v1/product", $scope.product).success(function () {
                $scope.mensagem = "product adicionada com sucesso!";
            });
        }

        $http.get("/api/v1/product").success(function (data) {
            $scope.productList = data;
        });

        $http.get("/api/v1/store").success(function (data) {
            $scope.storeList = data;
        });

    }).controller("StoreController", function ($scope, $http) {

        $scope.store = {};
        $scope.mensagem = "";

        $scope.salvar = function() {

            $http.post("/api/v1/store", $scope.store).success(function () {
                $scope.mensagem = "Loja adicionada com sucesso!";
            });
        }


        $http.get('/api/v1/store').success(function (data) {
            $scope.storeList = data;
        });


    })
;