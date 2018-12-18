(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('DataFrameTypeDetailController', DataFrameTypeDetailController);

    DataFrameTypeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DataFrameType', 'RulePersistable'];

    function DataFrameTypeDetailController($scope, $rootScope, $stateParams, previousState, entity, DataFrameType, RulePersistable) {
        var vm = this;

        vm.dataFrameType = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('costosapiApp:dataFrameTypeUpdate', function(event, result) {
            vm.dataFrameType = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
