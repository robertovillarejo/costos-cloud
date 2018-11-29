(function() {
    'use strict';
    angular
        .module('costosapiApp')
        .factory('RulePersistable', RulePersistable);

    RulePersistable.$inject = ['$resource'];

    function RulePersistable ($resource) {
        var resourceUrl = 'api/rules/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }        });
    }
})();