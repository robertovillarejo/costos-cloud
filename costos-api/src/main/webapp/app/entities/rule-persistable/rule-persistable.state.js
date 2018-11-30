(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('rulePersistable', {
            parent: 'entity',
            url: '/rulePersistable?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'costosapiApp.rulePersistable.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rule-persistable/rule-persistable.html',
                    controller: 'RulePersistableController',
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
                    $translatePartialLoader.addPart('rule-persistable');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('rulePersistable-detail', {
            parent: 'rulePersistable',
            url: '/rulePersistable/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'costosapiApp.rulePersistable.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rule-persistable/rule-persistable-detail.html',
                    controller: 'RulePersistableDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('rule-persistable');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'RulePersistable', function($stateParams, RulePersistable) {
                    return RulePersistable.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'rulePersistable',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('rulePersistable-detail.edit', {
            parent: 'rulePersistable-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rule-persistable/rule-persistable-dialog.html',
                    controller: 'RulePersistableDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RulePersistable', function(RulePersistable) {
                            return RulePersistable.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rulePersistable.new', {
            parent: 'rulePersistable',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rule-persistable/rule-persistable-dialog.html',
                    controller: 'RulePersistableDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                            	id: null,
                                name: null,
                                order: null,
                                condition: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('rulePersistable', null, { reload: 'rulePersistable' });
                }, function() {
                    $state.go('rulePersistable');
                });
            }]
        })
        .state('rulePersistable.edit', {
            parent: 'rulePersistable',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rule-persistable/rule-persistable-dialog.html',
                    controller: 'RulePersistableDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RulePersistable', function(RulePersistable) {
                            return RulePersistable.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rulePersistable', null, { reload: 'rulePersistable' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rulePersistable.delete', {
            parent: 'rulePersistable',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rule-persistable/rule-persistable-delete-dialog.html',
                    controller: 'RulePersistableDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['RulePersistable', function(RulePersistable) {
                            return RulePersistable.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rulePersistable', null, { reload: 'rulePersistable' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
