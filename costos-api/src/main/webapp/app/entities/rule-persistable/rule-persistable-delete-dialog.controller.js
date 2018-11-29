(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('RulePersistableDeleteController',RulePersistableDeleteController);

    RulePersistableDeleteController.$inject = ['$uibModalInstance', 'entity', 'RulePersistable'];

    function RulePersistableDeleteController($uibModalInstance, entity, RulePersistable) {
        var vm = this;

        vm.rulePersistable = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            RulePersistable.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
