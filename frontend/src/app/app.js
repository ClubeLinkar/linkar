'use strict';

angular.module('linkar', [
  'ngAnimate', 'ngCookies', 'ngTouch', 'ngSanitize', 'ngResource', 'ui.router', 'ui.bootstrap', 'ngToast'
])
  .config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider

        .state('index', {
            abstract: true,
            url: "/index",
            templateUrl: "components/common/content.html"
        })
        .state('index.main', {
            url: "/main",
            templateUrl: "app/main/main.html",
            data: { pageTitle: 'Example view' }
        })
        .state('index.minor', {
            url: "/minor",
            templateUrl: "app/minor/minor.html",
            data: { pageTitle: 'Example view' }
        })

        .state('product', {
            abstract: true,
            url: "/product",
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

        .state('store', {
            abstract: true,
            url: "/store",
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

        .state('user', {
            abstract: true,
            url: "/user",
            templateUrl: "components/common/content.html"
        })
        .state('user.main', {
            url: "/main",
            templateUrl: "app/user/main.html",
            data: { pageTitle: 'Usuários' }
        })


        ;

    $urlRouterProvider.otherwise('/index/main');
  })
;
