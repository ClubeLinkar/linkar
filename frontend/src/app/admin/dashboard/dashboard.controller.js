(function() {
  'use strict';

  angular.module('linkar').controller('Dashboard', DashboardController);

  DashboardController.$inject = ['$scope', '$stateParams', 'ngToast', '$state', '$http'];

  function DashboardController($scope, $stateParams, ngToast, $state, $http) {

    var vm = this;

    vm.changePassword = changePassword;

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

    function changePassword() {

      if (vm.user.newPass === vm.user.newPassRetype) {

        var usr = {
          currentPass: vm.user.currentPass,
          newPass: vm.user.newPass,
          newPassRetype: vm.user.newPassRetype
        }

        $http.post('/linkar/api/user/password', usr).then(function (data) {
          ngToast.create('Sua senha foi alterada com sucesso.');
          $state.go("admin.main");
        })
      } else {
        ngToast.create('As senhas informadas não coincidem.');
      }

    }


  }

})();
