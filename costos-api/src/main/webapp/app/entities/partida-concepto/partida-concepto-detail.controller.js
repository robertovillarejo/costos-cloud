(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .controller('PartidaConceptoDetailController', PartidaConceptoDetailController);

    PartidaConceptoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PartidaConcepto'];

    function PartidaConceptoDetailController($scope, $rootScope, $stateParams, previousState, entity, PartidaConcepto) {
        var vm = this;

        vm.partidaConcepto = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('costosapiApp:partidaConceptoUpdate', function(event, result) {
            vm.partidaConcepto = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
