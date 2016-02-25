(function () {
  'use strict';

  angular.module('linkar')
    .config(function ($stateProvider, $urlRouterProvider) {
      $stateProvider
          .state('dashboard', {
              abstract: true,
              url: "/admin/dashboard",
              templateUrl: "components/common/content.html"
          })
          .state('dashboard.usuarios', {
              url: "/usuarios",
              templateUrl: "app/admin/dashboard/usuarios.html",
              controller: 'Dashboard',
              controllerAs: 'vm'
          })
          .state('dashboard.vendas', {
              url: "/vendas",
              templateUrl: "app/admin/dashboard/vendas.html",
              controller: 'Dashboard',
              controllerAs: 'vm'
          })
      ;

    })
  ;

})();
