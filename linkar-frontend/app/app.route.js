(function() {
    'use strict';

    angular.module('linkar').config(config);

    function config($routeProvider) {
        console.log('app::route');

        $routeProvider.when('/', {
            templateUrl: 'home.html'
        }).otherwise({
            redirectTo: '/'
        });
    }
})();