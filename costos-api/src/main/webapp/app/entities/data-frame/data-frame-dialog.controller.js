(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('DataFrameDialogController', DataFrameDialogController);

    DataFrameDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'DataUtils', 'entity', 'DataFrame'];

    function DataFrameDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, DataUtils, entity, DataFrame) {
        var vm = this;

        vm.dataFrame = entity;
        vm.clear = clear;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.dataFrame.id !== null) {
                DataFrame.update(vm.dataFrame, onSaveSuccess, onSaveError);
            } else {
                DataFrame.save(vm.dataFrame, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('costosapiApp:dataFrameUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.setFile = function ($file, dataFrame) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        dataFrame.file = base64Data;
                        dataFrame.fileContentType = $file.type;
                    });
                });
            }
        };

    }
})();
