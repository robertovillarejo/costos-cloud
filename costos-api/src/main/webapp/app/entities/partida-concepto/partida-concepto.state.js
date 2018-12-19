(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('partidaConcepto', {
            parent: 'entity',
            url: '/partidaConcepto?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'costosapiApp.partidaConcepto.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/partida-concepto/partida-concepto.html',
                    controller: 'PartidaConceptoController',
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
                    $translatePartialLoader.addPart('partida-concepto');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('partidaConcepto-detail', {
            parent: 'partidaConcepto',
            url: '/partidaConcepto/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'costosapiApp.partidaConcepto.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/partida-concepto/partida-concepto-detail.html',
                    controller: 'PartidaConceptoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('partida-concepto');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'PartidaConcepto', function($stateParams, PartidaConcepto) {
                    return PartidaConcepto.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'partidaConcepto',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('partidaConcepto-detail.edit', {
            parent: 'partidaConcepto-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/partida-concepto/partida-concepto-dialog.html',
                    controller: 'PartidaConceptoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PartidaConcepto', function(PartidaConcepto) {
                            return PartidaConcepto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('partidaConcepto.new', {
            parent: 'partidaConcepto',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/partida-concepto/partida-concepto-dialog.html',
                    controller: 'PartidaConceptoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                            	id: null,
                                tipoCosto: null,
                                subtipoCosto: null,
                                partida: null,
                                subPartida: null,
                                subSubPartida: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('partidaConcepto', null, { reload: 'partidaConcepto' });
                }, function() {
                    $state.go('partidaConcepto');
                });
            }]
        })
        .state('partidaConcepto.edit', {
            parent: 'partidaConcepto',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/partida-concepto/partida-concepto-dialog.html',
                    controller: 'PartidaConceptoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PartidaConcepto', function(PartidaConcepto) {
                            return PartidaConcepto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('partidaConcepto', null, { reload: 'partidaConcepto' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('partidaConcepto.delete', {
            parent: 'partidaConcepto',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/partida-concepto/partida-concepto-delete-dialog.html',
                    controller: 'PartidaConceptoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PartidaConcepto', function(PartidaConcepto) {
                            return PartidaConcepto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('partidaConcepto', null, { reload: 'partidaConcepto' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
