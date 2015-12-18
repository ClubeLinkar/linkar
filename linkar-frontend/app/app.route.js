(function() {
    'use strict';

    angular.module('linkar').config(config);

    config.$inject = ['$routeProvider'];

    function config($routeProvider) {
        console.log('app::route');

        $routeProvider.when('/', {
            templateUrl: 'home.html'
        }).otherwise({
            redirectTo: '/'
        });
    }
})();
