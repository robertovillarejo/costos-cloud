(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('PartidaConceptoDeleteController',PartidaConceptoDeleteController);

    PartidaConceptoDeleteController.$inject = ['$uibModalInstance', 'entity', 'PartidaConcepto'];

    function PartidaConceptoDeleteController($uibModalInstance, entity, PartidaConcepto) {
        var vm = this;

        vm.partidaConcepto = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PartidaConcepto.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
