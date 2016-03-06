(function () {
  'use strict';

  angular.module('linkar')
    .config(function ($stateProvider, $urlRouterProvider) {
      $stateProvider
          .state('company', {
              abstract: true,
              url: "/admin/company",
              templateUrl: "components/common/content.html"
          })
          .state('company.main', {
              url: "/main",
              templateUrl: "app/admin/company/main.html",
              controller: 'Company',
              controllerAs: 'vm'
          })
          .state('company.form', {
              url: "/form",
              templateUrl: "app/admin/company/form.html",
              controller: 'Company',
              controllerAs: 'vm'
          })
          .state('company.detail', {
              url: "/detail/:companyId",
              templateUrl: "app/admin/company/form.html",
              controller: 'Company',
              controllerAs: 'vm'
          })

      ;

    })
  ;

})();
