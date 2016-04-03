(function() {
  'use strict';

  angular.module('linkar').controller('UserList', UserListController);

  UserListController.$inject = ['User', 'ngToast', '$state', '$stateParams'];

  function UserListController(User, ngToast, $state, $stateParams) {

    var vm = this;

    vm.init = init;
    vm.list = list;

    init();

    function init() {
      vm.list();
    }

    function list() {
      User.query(function (data) {
        vm.userList = data;
      });
    }

  }

})();
