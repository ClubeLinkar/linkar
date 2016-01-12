'use strict';

angular.module('inspinia', ['ngAnimate', 'ngCookies', 'ngTouch', 'ngSanitize', 'ngResource', 'ui.router', 'ui.bootstrap' ])
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
            controller: 'Product'
        })

        .state('store', {
            abstract: true,
            url: "/store",
            templateUrl: "components/common/content.html"
        })
        .state('store.main', {
            url: "/main",
            templateUrl: "app/store/main.html",
            data: { pageTitle: 'Lojas' }
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
