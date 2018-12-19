(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('PartidaConceptoDialogController', PartidaConceptoDialogController);

    PartidaConceptoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'PartidaConcepto'];

    function PartidaConceptoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, PartidaConcepto) {
        var vm = this;

        vm.partidaConcepto = entity;
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
            if (vm.partidaConcepto.id !== null) {
                PartidaConcepto.update(vm.partidaConcepto, onSaveSuccess, onSaveError);
            } else {
                PartidaConcepto.save(vm.partidaConcepto, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('costosapiApp:partidaConceptoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
