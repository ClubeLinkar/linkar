(function () {
  'use strict';

  angular.module('linkar')
    .config(function ($stateProvider, $urlRouterProvider) {
      $stateProvider
          .state('user', {
              abstract: true,
              url: "/user",
              templateUrl: "components/common/content.html"
          })
          .state('user.main', {
              url: "/main",
              templateUrl: "app/user/main.html",
              controller: 'UserList',
              controllerAs: 'vm'
          })
          .state('user.form', {
              url: "/new",
              templateUrl: "app/user/form.html",
              controller: 'UserEdit',
              controllerAs: 'vm'
          })
          .state('user.detail', {
              url: "/detail/:userId",
              templateUrl: "app/user/form.html",
              controller: 'UserEdit',
              controllerAs: 'vm'
          })
          .state('user.signup', {
              url: "/signup",
              templateUrl: "app/user/form.html",
              controller: 'Signup',
              controllerAs: 'vm'
          })
          .state('user.welcome', {
              url: "/welcome",
              templateUrl: "app/signup/success.html"
          })
      ;

    })
  ;

})();
