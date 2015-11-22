angular.module("linkar")
    .controller("CustomerController", function ($scope, $http) {

        $scope.customer = {};
        $scope.mensagem = "";

        $scope.salvar = function() {

            $http.post("/api/v1/customer", $scope.customer).success(function () {
                $scope.mensagem = "customer adicionada com sucesso!";
            });
        }

        $http.get("/api/v1/customer").success(function (data) {
            $scope.customerList = data;
        });


    })
;