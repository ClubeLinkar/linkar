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
              data: { pageTitle: 'Usuários' }
          })
      ;

    })
  ;

})();
