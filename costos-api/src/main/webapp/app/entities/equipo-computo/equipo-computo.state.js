(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('equipoComputo', {
            parent: 'entity',
            url: '/equipoComputo?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'costosapiApp.equipoComputo.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/equipo-computo/equipo-computo.html',
                    controller: 'EquipoComputoController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                search: null,
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
                    $translatePartialLoader.addPart('equipo-computo');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('equipoComputo-detail', {
            parent: 'equipoComputo',
            url: '/equipoComputo/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'costosapiApp.equipoComputo.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/equipo-computo/equipo-computo-detail.html',
                    controller: 'EquipoComputoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('equipo-computo');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'EquipoComputo', function($stateParams, EquipoComputo) {
                    return EquipoComputo.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'equipoComputo',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('equipoComputo-detail.edit', {
            parent: 'equipoComputo-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/equipo-computo/equipo-computo-dialog.html',
                    controller: 'EquipoComputoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['EquipoComputo', function(EquipoComputo) {
                            return EquipoComputo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('equipoComputo.new', {
            parent: 'equipoComputo',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/equipo-computo/equipo-computo-dialog.html',
                    controller: 'EquipoComputoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                            	id: null,
                                tipoComputadora: null,
                                proveedor: null,
                                monto: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('equipoComputo', null, { reload: 'equipoComputo' });
                }, function() {
                    $state.go('equipoComputo');
                });
            }]
        })
        .state('equipoComputo.edit', {
            parent: 'equipoComputo',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/equipo-computo/equipo-computo-dialog.html',
                    controller: 'EquipoComputoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['EquipoComputo', function(EquipoComputo) {
                            return EquipoComputo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('equipoComputo', null, { reload: 'equipoComputo' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('equipoComputo.delete', {
            parent: 'equipoComputo',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/equipo-computo/equipo-computo-delete-dialog.html',
                    controller: 'EquipoComputoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['EquipoComputo', function(EquipoComputo) {
                            return EquipoComputo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('equipoComputo', null, { reload: 'equipoComputo' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
