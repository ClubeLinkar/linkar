(function () {
  'use strict';

  angular.module('services.config', [])
    .constant('configuration', {
      baseURL: '/sistema/linkar/',
      preferredLanguage: 'pt-BR'
    });

})();
