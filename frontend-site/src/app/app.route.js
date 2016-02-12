(function () {
  'use strict';

  angular.module('shopLinkar').config(Config);

  Config.$inject = ['$stateProvider', '$urlRouterProvider'];

  function Config($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('index', {
          url: "/",
          templateUrl: "index.html",
          controller: 'Dummy',
          controllerAs: 'vm'

    });

    $urlRouterProvider.otherwise('/');

  }
})();
