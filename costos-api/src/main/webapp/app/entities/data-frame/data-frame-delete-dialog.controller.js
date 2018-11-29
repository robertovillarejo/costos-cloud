(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('DataFrameDeleteController',DataFrameDeleteController);

    DataFrameDeleteController.$inject = ['$uibModalInstance', 'entity', 'DataFrame'];

    function DataFrameDeleteController($uibModalInstance, entity, DataFrame) {
        var vm = this;

        vm.dataFrame = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DataFrame.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
