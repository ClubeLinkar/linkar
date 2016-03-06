(function() {
  'use strict';

  angular.module('linkar').controller('Company', CompanyController);

  CompanyController.$inject = ['$scope', 'Company', '$stateParams', 'ngToast', '$state'];

  function CompanyController($scope, Company, $stateParams, ngToast, $state) {

    console.log('company::controller:init');

    Company.get({ id: $stateParams.companyId }, function(data) {
      console.log("company::controller:detail");
      $scope.company = data;
    });

    Company.query(function (data) {
      console.log("company::controller:list");
      $scope.companyList = data;
    });

    this.save = function () {
      Company.save($scope.company, function (data) {
        console.log("company::controller:save");
        ngToast.create('A loja ' + $scope.company.name + ' foi cadastrada com sucesso!');
        $state.go('company.main');
      }, function (erro) {
        console.log(erro);
        ngToast.danger("Houve um erro de validação dos dados. " + erro.data.exception);
      });
    };
  }

})();
