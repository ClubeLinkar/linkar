(function () {
  'use strict';

  angular.module('linkar')
    .config(function ($stateProvider, $urlRouterProvider) {
      $stateProvider
          .state('product', {
              abstract: true,
              url: "/admin/product",
              templateUrl: "components/common/content.html"
          })
          .state('product.main', {
              url: "/main",
              templateUrl: "app/product/main.html",
              controller: 'Product',
              controllerAs: 'vm'
          })
          .state('product.form', {
              url: "/form",
              templateUrl: "app/product/form.html",
              controller: 'Product',
              controllerAs: 'vm'
          })
          .state('product.detail', {
              url: "/detail/:productId",
              templateUrl: "app/product/form.html",
              controller: 'Product',
              controllerAs: 'vm'
          })

      ;

    })
  ;

})();
