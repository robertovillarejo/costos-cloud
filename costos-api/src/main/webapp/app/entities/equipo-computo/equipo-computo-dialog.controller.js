(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('EquipoComputoDialogController', EquipoComputoDialogController);

    EquipoComputoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'EquipoComputo'];

    function EquipoComputoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, EquipoComputo) {
        var vm = this;

        vm.equipoComputo = entity;
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
            if (vm.equipoComputo.id !== null) {
                EquipoComputo.update(vm.equipoComputo, onSaveSuccess, onSaveError);
            } else {
                EquipoComputo.save(vm.equipoComputo, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('costosapiApp:equipoComputoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
