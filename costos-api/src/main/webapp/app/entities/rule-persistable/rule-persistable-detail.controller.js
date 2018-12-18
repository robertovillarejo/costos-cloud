(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('RulePersistableDetailController', RulePersistableDetailController);

    RulePersistableDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DataFrameType', 'RulePersistable'];

    function RulePersistableDetailController($scope, $rootScope, $stateParams, previousState, entity, DataFrameType, RulePersistable) {
        var vm = this;

        vm.rulePersistable = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('costosapiApp:rulePersistableUpdate', function(event, result) {
            vm.rulePersistable = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
