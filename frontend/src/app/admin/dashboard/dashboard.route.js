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
          .state('dashboard.pontos', {
              url: "/pontos",
              templateUrl: "app/admin/dashboard/meuspontos.html",
              controller: 'Dashboard',
              controllerAs: 'vm'
          })
          .state('dashboard.transacoes', {
              url: "/transacoes",
              templateUrl: "app/admin/dashboard/minhascompras.html",
              controller: 'Dashboard',
              controllerAs: 'vm'
          })
          .state('dashboard.perfil', {
              url: "/perfil",
              templateUrl: "app/admin/dashboard/perfil.html",
              controller: 'Dashboard',
              controllerAs: 'vm'
          })
      ;

    })
  ;

})();
