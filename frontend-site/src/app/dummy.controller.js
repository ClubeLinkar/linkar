(function () {
  'use strict';

  angular.module('shopLinkar').controller('Dummy', Dummy);

  // Dummy.$inject = [];

  function Dummy() {
    var vm = this;

    vm.dummyMessage = "Hello! Shop!!!";
  }
})();
