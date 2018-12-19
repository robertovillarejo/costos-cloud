(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('EquipoComputoDeleteController',EquipoComputoDeleteController);

    EquipoComputoDeleteController.$inject = ['$uibModalInstance', 'entity', 'EquipoComputo'];

    function EquipoComputoDeleteController($uibModalInstance, entity, EquipoComputo) {
        var vm = this;

        vm.equipoComputo = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            EquipoComputo.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
