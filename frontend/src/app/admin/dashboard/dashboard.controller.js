(function() {
  'use strict';

  angular.module('linkar').controller('Dashboard', DashboardController);

  DashboardController.$inject = ['$scope', '$stateParams', 'ngToast', '$state'];

  function DashboardController($scope, $stateParams, ngToast, $state) {

    var vm = this;

    vm.incomingMessagesList = [
      {
        from: 'Lennon Jesus',
        message: 'Vocês tem peças de reposição para capacetes Shark?',
        timeAgo: '2 mins',
        date: '25 de fevereiro de 2016'
      },
      {
        from: 'Carla Vidal',
        message: 'Comprei uma segunda pele mas gostaria de trocar por um número maior. É possível?',
        timeAgo: '17 mins',
        date: '25 de fevereiro de 2016'
      }
    ];

    vm.rankCategorias = [
      {
        name: 'Capacetes',
        quant: 20,
        totalValue: 9879
      },
      {
        name: 'Capas de chuva',
        quant: 7,
        totalValue: 765
      },
      {
        name: 'Luvas',
        quant: 2,
        totalValue: 345
      },
      {
        name: 'Botas',
        quant: 1,
        totalValue: 210
      }

    ];


    vm.init = init;

    function init() {
      console.log("init");
    }


  }

})();
