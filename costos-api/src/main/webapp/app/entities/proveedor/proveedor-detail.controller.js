(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('ProveedorDetailController', ProveedorDetailController);

    ProveedorDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Proveedor'];

    function ProveedorDetailController($scope, $rootScope, $stateParams, previousState, entity, Proveedor) {
        var vm = this;

        vm.proveedor = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('costosapiApp:proveedorUpdate', function(event, result) {
            vm.proveedor = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
