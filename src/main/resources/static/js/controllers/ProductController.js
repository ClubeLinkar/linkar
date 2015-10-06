angular.module('linkar-admin').controller(function ($http, $scope) {

    $http.get('/product').success(function(data) {
        $scope.products = data;
    });

});