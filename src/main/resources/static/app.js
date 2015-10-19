angular.module("linkar", [])
    .controller("ProductController", function ($scope, $http) {


        $http.get("/api/v1/product").success(function (data) {
            $scope.productList = data;
        });

        $scope.alert = function() {
            alert("BLAH!");
        }

})
    .controller("StoreController", function ($scope, $http) {

        $scope.store = {};
        $scope.mensagem = "";

        $scope.salvar = function() {
            console.log(data);

            $http.post("/api/v1/store", $scope.store).success(function () {
                $scope.mensagem = "Loja adicionada com sucesso!";
            });
        }


    }
);