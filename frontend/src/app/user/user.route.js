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
              controller: 'User',
              controllerAs: 'vm'
          })
          .state('user.form', {
              url: "/new",
              templateUrl: "app/user/form.html",
              controller: 'User',
              controllerAs: 'vm'
          })
          .state('user.detail', {
              url: "/detail/:userId",
              templateUrl: "app/user/form.html",
              controller: 'User',
              controllerAs: 'vm'
          })
      ;

    })
  ;

})();
