angular.module('linkar-admin').controller('StoreController', function($http, $scope) {

    $http.get('/store').success(function(data) {
        $scope.stores = data;
    });

});