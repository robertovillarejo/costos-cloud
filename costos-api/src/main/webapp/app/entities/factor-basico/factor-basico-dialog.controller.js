(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('FactorBasicoDialogController', FactorBasicoDialogController);

    FactorBasicoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'FactorBasico'];

    function FactorBasicoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, FactorBasico) {
        var vm = this;

        vm.factorBasico = entity;
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
            if (vm.factorBasico.id !== null) {
                FactorBasico.update(vm.factorBasico, onSaveSuccess, onSaveError);
            } else {
                FactorBasico.save(vm.factorBasico, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('costosapiApp:factorBasicoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
