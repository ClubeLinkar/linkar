(function () {
  'use strict';

  angular.module('linkar')
    .config(function ($stateProvider, $urlRouterProvider) {
      $stateProvider
          .state('category', {
              abstract: true,
              url: "/admin/category",
              templateUrl: "components/common/content.html"
          })
          .state('category.main', {
              url: "/main",
              templateUrl: "app/admin/category/main.html",
              controller: 'CategoryList',
              controllerAs: 'vm'
          })
          .state('category.form', {
              url: "/form",
              templateUrl: "app/admin/category/form.html",
              controller: 'CategoryEdit',
              controllerAs: 'vm'
          })
          .state('category.detail', {
              url: "/detail/:categoryId",
              templateUrl: "app/admin/category/form.html",
              controller: 'CategoryEdit',
              controllerAs: 'vm'
          })

      ;

    })
  ;

})();
