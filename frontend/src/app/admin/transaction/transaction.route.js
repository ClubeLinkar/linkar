(function () {
  'use strict';

  angular.module('linkar')
    .config(function ($stateProvider, $urlRouterProvider) {
      $stateProvider
          .state('transaction', {
              abstract: true,
              url: "/admin/transaction",
              templateUrl: "components/common/content.html"
          })
          .state('transaction.main', {
              url: "/main",
              templateUrl: "app/admin/transaction/main.html",
              controller: 'TransactionList',
              controllerAs: 'vm'
          })
          .state('transaction.form', {
              url: "/form",
              templateUrl: "app/admin/transaction/form.html",
              controller: 'TransactionCreate',
              controllerAs: 'vm'
          })
          .state('transaction.detail', {
              url: "/detail/:transactionId",
              templateUrl: "app/admin/transaction/form.html",
              controller: 'TransactionCreate',
              controllerAs: 'vm'
          })

      ;

    })
  ;

})();
