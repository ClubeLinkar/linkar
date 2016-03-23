(function () {
  'use strict';

  angular.module('linkar')
    .config(function ($stateProvider, $urlRouterProvider, $httpProvider) {
      $stateProvider

      // .state('auth', {
      //     abstract: true,
      //     url: "/auth",
      //     templateUrl: "components/common/content.html"
      // })
      .state('login', {
          url: "/login",
          templateUrl: "app/login/login.html",
          controller: 'Login',
          controllerAs: 'vm'
      });

      $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    })
  ;
})();
