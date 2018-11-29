(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('CostoDeleteController',CostoDeleteController);

    CostoDeleteController.$inject = ['$uibModalInstance', 'entity', 'Costo'];

    function CostoDeleteController($uibModalInstance, entity, Costo) {
        var vm = this;

        vm.costo = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Costo.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
