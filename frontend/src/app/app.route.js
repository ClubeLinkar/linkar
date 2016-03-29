(function () {
  'use strict';

  angular.module('linkar')
    .config(function ($stateProvider, $urlRouterProvider) {
      $stateProvider

          .state('admin', {
              abstract: true,
              url: "/admin",
              templateUrl: "components/common/content.html"
          })
          .state('admin.main', {
              url: "/main",
              templateUrl: "app/main/main.html"
          })
          .state('admin.logout', {
              url: "/logout",
              templateUrl: "app/main/main.html",
              controller: 'Logout',
              controllerAs: 'vm'
          })
          ;

      $urlRouterProvider.otherwise('/admin/main');
    })
  ;
})();
