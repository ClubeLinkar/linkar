(function() {
  'use strict';

  angular.module('linkar').controller('Product', ProductController);

  ProductController.$inject = ['$scope', 'Product', 'Store', 'ngToast', '$stateParams', '$state'];

  function ProductController($scope, Product, Store, ngToast, $stateParams, $state) {

    console.log('product::controller:init');

    Product.query(function (data) {
      console.log("product::controller:list");
      $scope.productList = data;
    });

    Product.get({ id: $stateParams.productId }, function(data) {
      console.log("product::controller:detail");
      $scope.product = data;
    });

    Store.query(function (data) {
      console.log("product::controller:list:storeList");
      $scope.storeList = data;
    });

    this.save = function () {

      $scope.product.photos = $scope.photos;

      Product.save($scope.product, function (data) {
        console.log("product::controller:save");

        ngToast.create('O produto ' + $scope.product.name + ' foi cadastrado com sucesso!');
        $state.go('product.main');

      }, function (erro) {
        console.log(erro);
        ngToast.danger("Houve um erro de validação dos dados. " + erro.data.exception);
      });
    };

    $scope.photos = [];

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

          $scope.photos.push(response.filePath);
        }
      }
    };
  }

})();
