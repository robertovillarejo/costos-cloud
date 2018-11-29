(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('CostoDetailController', CostoDetailController);

    CostoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Costo'];

    function CostoDetailController($scope, $rootScope, $stateParams, previousState, entity, Costo) {
        var vm = this;

        vm.costo = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('costosapiApp:costoUpdate', function(event, result) {
            vm.costo = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
