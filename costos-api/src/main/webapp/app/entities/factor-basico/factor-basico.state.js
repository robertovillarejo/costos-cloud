(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('factorBasico', {
            parent: 'entity',
            url: '/factorBasico?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'costosapiApp.factorBasico.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/factor-basico/factor-basico.html',
                    controller: 'FactorBasicoController',
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
                    $translatePartialLoader.addPart('factor-basico');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('factorBasico-detail', {
            parent: 'factorBasico',
            url: '/factorBasico/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'costosapiApp.factorBasico.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/factor-basico/factor-basico-detail.html',
                    controller: 'FactorBasicoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('factor-basico');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'FactorBasico', function($stateParams, FactorBasico) {
                    return FactorBasico.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'factorBasico',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('factorBasico-detail.edit', {
            parent: 'factorBasico-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/factor-basico/factor-basico-dialog.html',
                    controller: 'FactorBasicoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['FactorBasico', function(FactorBasico) {
                            return FactorBasico.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('factorBasico.new', {
            parent: 'factorBasico',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/factor-basico/factor-basico-dialog.html',
                    controller: 'FactorBasicoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                            	id: null,
                                nombre: null,
                                sede: null,
                                mes: null,
                                anio: null,
                                valor: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('factorBasico', null, { reload: 'factorBasico' });
                }, function() {
                    $state.go('factorBasico');
                });
            }]
        })
        .state('factorBasico.edit', {
            parent: 'factorBasico',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/factor-basico/factor-basico-dialog.html',
                    controller: 'FactorBasicoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['FactorBasico', function(FactorBasico) {
                            return FactorBasico.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('factorBasico', null, { reload: 'factorBasico' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('factorBasico.delete', {
            parent: 'factorBasico',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/factor-basico/factor-basico-delete-dialog.html',
                    controller: 'FactorBasicoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['FactorBasico', function(FactorBasico) {
                            return FactorBasico.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('factorBasico', null, { reload: 'factorBasico' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
