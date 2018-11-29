(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('DataFrameDetailController', DataFrameDetailController);

    DataFrameDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'DataFrame'];

    function DataFrameDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, DataFrame) {
        var vm = this;

        vm.dataFrame = entity;
        vm.previousState = previousState.name;
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        var unsubscribe = $rootScope.$on('costosapiApp:dataFrameUpdate', function(event, result) {
            vm.dataFrame = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
