(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('dataFrameType', {
            parent: 'entity',
            url: '/dataFrameType?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'costosapiApp.dataFrameType.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/data-frame-type/data-frame-type.html',
                    controller: 'DataFrameTypeController',
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
                    $translatePartialLoader.addPart('data-frame-type');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('dataFrameType-detail', {
            parent: 'dataFrameType',
            url: '/dataFrameType/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'costosapiApp.dataFrameType.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/data-frame-type/data-frame-type-detail.html',
                    controller: 'DataFrameTypeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('data-frame-type');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'DataFrameType', function($stateParams, DataFrameType) {
                    return DataFrameType.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'dataFrameType',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('dataFrameType-detail.edit', {
            parent: 'dataFrameType-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data-frame-type/data-frame-type-dialog.html',
                    controller: 'DataFrameTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DataFrameType', function(DataFrameType) {
                            return DataFrameType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dataFrameType.new', {
            parent: 'dataFrameType',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data-frame-type/data-frame-type-dialog.html',
                    controller: 'DataFrameTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                            	id: null,
                                name: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('dataFrameType', null, { reload: 'dataFrameType' });
                }, function() {
                    $state.go('dataFrameType');
                });
            }]
        })
        .state('dataFrameType.edit', {
            parent: 'dataFrameType',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data-frame-type/data-frame-type-dialog.html',
                    controller: 'DataFrameTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DataFrameType', function(DataFrameType) {
                            return DataFrameType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dataFrameType', null, { reload: 'dataFrameType' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dataFrameType.delete', {
            parent: 'dataFrameType',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data-frame-type/data-frame-type-delete-dialog.html',
                    controller: 'DataFrameTypeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DataFrameType', function(DataFrameType) {
                            return DataFrameType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dataFrameType', null, { reload: 'dataFrameType' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
