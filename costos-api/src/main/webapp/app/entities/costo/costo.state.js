(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('costo', {
            parent: 'entity',
            url: '/costos?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'costosapiApp.costo.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/costo/costos.html',
                    controller: 'CostoController',
                    controllerAs: 'vm'
                }
            },
            params: {
                sort: {
                    value: 'id,asc',
                    squash: true
                }
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('costo');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('costo-detail', {
            parent: 'costo',
            url: '/costos/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'costosapiApp.costo.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/costo/costo-detail.html',
                    controller: 'CostoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('costo');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Costo', function($stateParams, Costo) {
                    return Costo.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'costo',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('costo-detail.edit', {
            parent: 'costo-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/costo/costo-dialog.html',
                    controller: 'CostoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Costo', function(Costo) {
                            return Costo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('costo.new', {
            parent: 'costo',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/costo/costo-dialog.html',
                    controller: 'CostoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                            	id: null,
                                processed: false,
                                monto: null,
                                porcentaje: null,
                                anio: null,
                                mes: null,
                                fechaRegistro: null,
                                area: null,
                                proyectoOperativo: null,
                                proyectoPresupuestal: null,
                                servicio: null,
                                tipoCosto: null,
                                partida: null,
                                proveedor: null,
                                user: null,
                                subpartida: null,
                                subSubPartida: null,
                                numeroFactura: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('costo', null, { reload: 'costo' });
                }, function() {
                    $state.go('costo');
                });
            }]
        })
        .state('costo.edit', {
            parent: 'costo',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/costo/costo-dialog.html',
                    controller: 'CostoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Costo', function(Costo) {
                            return Costo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('costo', null, { reload: 'costo' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('costo.delete', {
            parent: 'costo',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/costo/costo-delete-dialog.html',
                    controller: 'CostoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Costo', function(Costo) {
                            return Costo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('costo', null, { reload: 'costo' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
