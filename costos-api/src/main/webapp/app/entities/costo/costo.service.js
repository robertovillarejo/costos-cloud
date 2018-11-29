(function() {
    'use strict';
    angular
        .module('costosapiApp')
        .factory('Costo', Costo);

    Costo.$inject = ['$resource'];

    function Costo ($resource) {
        var resourceUrl = 'api/costos/:id';

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