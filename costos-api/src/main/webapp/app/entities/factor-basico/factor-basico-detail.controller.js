(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('FactorBasicoDetailController', FactorBasicoDetailController);

    FactorBasicoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'FactorBasico'];

    function FactorBasicoDetailController($scope, $rootScope, $stateParams, previousState, entity, FactorBasico) {
        var vm = this;

        vm.factorBasico = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('costosapiApp:factorBasicoUpdate', function(event, result) {
            vm.factorBasico = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
