(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('FactorBasicoDeleteController',FactorBasicoDeleteController);

    FactorBasicoDeleteController.$inject = ['$uibModalInstance', 'entity', 'FactorBasico'];

    function FactorBasicoDeleteController($uibModalInstance, entity, FactorBasico) {
        var vm = this;

        vm.factorBasico = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            FactorBasico.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
