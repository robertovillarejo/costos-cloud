(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('EquipoComputoDetailController', EquipoComputoDetailController);

    EquipoComputoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'EquipoComputo'];

    function EquipoComputoDetailController($scope, $rootScope, $stateParams, previousState, entity, EquipoComputo) {
        var vm = this;

        vm.equipoComputo = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('costosapiApp:equipoComputoUpdate', function(event, result) {
            vm.equipoComputo = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
