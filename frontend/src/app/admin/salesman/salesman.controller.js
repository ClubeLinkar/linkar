(function() {
  'use strict';

  angular.module('linkar').controller('Salesman', SalesmanController);

  SalesmanController.$inject = ['$scope', 'Salesman', 'ngToast', '$state', '$stateParams'];

  function SalesmanController($scope, Salesman, ngToast, $state, $stateParams) {

    console.log('salesman::controller:init');

    Salesman.query(function (data) {
      console.log("salesman::controller:list");
      $scope.salesmanList = data;
    });

    Salesman.get({ id: $stateParams.salesmanId }, function(data) {
      console.log("salesman::controller:detail");
      $scope.salesman = data;
    });

    this.save = function () {
      Salesman.save($scope.salesman, function (data) {
        console.log("salesman::controller:save");
        ngToast.create('Seu vendedor foi criado com sucesso, ' + $scope.salesman.name + '. Você já pode Linkar!');
        $state.go('salesman.main');
      }, function (erro) {
        console.log(erro);
        ngToast.danger("Houve um erro de validação dos dados. " + erro.data.exception);
      });
    };
  }

})();
