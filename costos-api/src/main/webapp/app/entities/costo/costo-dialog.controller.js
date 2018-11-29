(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('CostoDialogController', CostoDialogController);

    CostoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Costo'];

    function CostoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Costo) {
        var vm = this;

        vm.costo = entity;
        vm.clear = clear;
        vm.save = save;
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.costo.id !== null) {
                Costo.update(vm.costo, onSaveSuccess, onSaveError);
            } else {
                Costo.save(vm.costo, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('costosapiApp:costoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
