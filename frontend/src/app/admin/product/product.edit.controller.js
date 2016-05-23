(function() {
  'use strict';

  angular.module('linkar').controller('ProductEdit', ProductEditController);

  ProductEditController.$inject = ['$scope', 'Product', 'Company', 'ngToast', '$stateParams', '$state'];

  function ProductEditController($scope, Product, Company, ngToast, $stateParams, $state) {

    var vm = this;

    vm.init = init;
    vm.get = get;
    vm.save = save;
    vm.loadCompanies = loadCompanies;
    vm.cancel = cancel;

    vm.init();

    function init() {
      vm.get();
    }

    function get() {
      if ($stateParams.productId) {
        vm.loadCompanies(); // FIXME tah meio bugado isso aqui...

        Product.get({ id: $stateParams.productId }, function(data) {
          vm.product = data;
        });
      } else {
        vm.loadCompanies();
      }
    }

    function loadCompanies() {
      Company.query(function (data) {
        vm.companyList = data;
      });
    }

    function save() {

      vm.product.photos = vm.photos;

      Product.save(vm.product, function (data) {
        ngToast.create('O produto ' + vm.product.name + ' foi cadastrado com sucesso!');
        $state.go('product.main');

      }, function (erro) {
        console.log(erro);
        ngToast.danger("Houve um erro de validação dos dados. " + erro.data.exception);
      });
    }

    function cancel() {
      $state.go("product.main");
    }

    vm.photos = [];

    // FIXME DROPZONE OUT HERE
    $scope.dropzoneConfig = {
      'options': { // passed into the Dropzone constructor
        'url': '/linkar/api/upload'
      },
      'eventHandlers': {
        'sending': function (file, xhr, formData) {
          console.log('dropzone sending...');
        },
        'success': function (file, response) {
          console.log('dropzone success...');

          vm.photos.push(response.filePath);
        }
      }
    };
  }

})();
