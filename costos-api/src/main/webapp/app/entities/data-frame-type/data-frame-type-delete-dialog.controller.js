(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('DataFrameTypeDeleteController',DataFrameTypeDeleteController);

    DataFrameTypeDeleteController.$inject = ['$uibModalInstance', 'entity', 'DataFrameType'];

    function DataFrameTypeDeleteController($uibModalInstance, entity, DataFrameType) {
        var vm = this;

        vm.dataFrameType = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DataFrameType.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
