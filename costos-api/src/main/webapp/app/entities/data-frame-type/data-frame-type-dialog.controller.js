(function () {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('DataFrameTypeDialogController', DataFrameTypeDialogController);

    DataFrameTypeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'DataFrameType'];

    function DataFrameTypeDialogController($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, DataFrameType) {
        var vm = this;

        vm.dataFrameType = entity;
        vm.clear = clear;
        vm.save = save;
        $timeout(function () {
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function save() {
            vm.isSaving = true;
            if (vm.dataFrameType.id !== null) {
                DataFrameType.update(vm.dataFrameType, onSaveSuccess, onSaveError);
            } else {
                DataFrameType.save(vm.dataFrameType, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess(result) {
            $scope.$emit('costosapiApp:dataFrameTypeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError() {
            vm.isSaving = false;
        }


    }
})();
