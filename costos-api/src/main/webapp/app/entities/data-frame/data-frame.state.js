(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('dataFrame', {
            parent: 'entity',
            url: '/dataFrame?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'costosapiApp.dataFrame.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/data-frame/data-frame.html',
                    controller: 'DataFrameController',
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
                    $translatePartialLoader.addPart('data-frame');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('dataFrame-detail', {
            parent: 'dataFrame',
            url: '/dataFrame/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'costosapiApp.dataFrame.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/data-frame/data-frame-detail.html',
                    controller: 'DataFrameDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('data-frame');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'DataFrame', function($stateParams, DataFrame) {
                    return DataFrame.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'dataFrame',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('dataFrame-detail.edit', {
            parent: 'dataFrame-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data-frame/data-frame-dialog.html',
                    controller: 'DataFrameDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DataFrame', function(DataFrame) {
                            return DataFrame.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dataFrame.new', {
            parent: 'dataFrame',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data-frame/data-frame-dialog.html',
                    controller: 'DataFrameDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                            	id: null,
                                fileName: null,
                                file: null,
                                fileContentType: null,
                                processed: false
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('dataFrame', null, { reload: 'dataFrame' });
                }, function() {
                    $state.go('dataFrame');
                });
            }]
        })
        .state('dataFrame.edit', {
            parent: 'dataFrame',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data-frame/data-frame-dialog.html',
                    controller: 'DataFrameDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DataFrame', function(DataFrame) {
                            return DataFrame.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dataFrame', null, { reload: 'dataFrame' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dataFrame.delete', {
            parent: 'dataFrame',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data-frame/data-frame-delete-dialog.html',
                    controller: 'DataFrameDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DataFrame', function(DataFrame) {
                            return DataFrame.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dataFrame', null, { reload: 'dataFrame' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
