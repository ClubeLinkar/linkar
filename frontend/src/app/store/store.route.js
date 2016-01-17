(function () {
  'use strict';

  angular.module('linkar')
    .config(function ($stateProvider, $urlRouterProvider) {
      $stateProvider
          .state('store', {
              abstract: true,
              url: "/admin/store",
              templateUrl: "components/common/content.html"
          })
          .state('store.main', {
              url: "/main",
              templateUrl: "app/store/main.html",
              controller: 'Store',
              controllerAs: 'vm'
          })
          .state('store.form', {
              url: "/form",
              templateUrl: "app/store/form.html",
              controller: 'Store',
              controllerAs: 'vm'
          })
          .state('store.detail', {
              url: "/detail/:storeId",
              templateUrl: "app/store/form.html",
              controller: 'Store',
              controllerAs: 'vm'
          })

      ;

    })
  ;

})();
