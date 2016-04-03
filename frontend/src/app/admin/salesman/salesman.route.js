(function () {
  'use strict';

  angular.module('linkar')
    .config(function ($stateProvider, $urlRouterProvider) {
      $stateProvider
          .state('salesman', {
              abstract: true,
              url: "/salesman",
              templateUrl: "components/common/content.html"
          })
          .state('salesman.main', {
              url: "/main",
              templateUrl: "app/admin/salesman/main.html",
              controller: 'SalesmanList',
              controllerAs: 'vm'
          })
          .state('salesman.form', {
              url: "/new",
              templateUrl: "app/admin/salesman/form.html",
              controller: 'SalesmanEdit',
              controllerAs: 'vm'
          })
          .state('salesman.detail', {
              url: "/detail/:salesmanId",
              templateUrl: "app/admin/salesman/form.html",
              controller: 'SalesmanEdit',
              controllerAs: 'vm'
          })
      ;

    })
  ;

})();
