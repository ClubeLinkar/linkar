(function () {
  'use strict';

  angular.module('linkar').controller('HomeController', HomeController);

  function HomeController($scope, $location, $anchorScroll) {

    $scope.scrollPageTo = function (anchor) {

      $location.hash(anchor);

      $anchorScroll();
    }

  }
})();
