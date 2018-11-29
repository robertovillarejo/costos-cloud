(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('RulePersistableDialogController', RulePersistableDialogController);

    RulePersistableDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'RulePersistable'];

    function RulePersistableDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, RulePersistable) {
        var vm = this;

        vm.rulePersistable = entity;
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
            if (vm.rulePersistable.id !== null) {
                RulePersistable.update(vm.rulePersistable, onSaveSuccess, onSaveError);
            } else {
                RulePersistable.save(vm.rulePersistable, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('costosapiApp:rulePersistableUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
