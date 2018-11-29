(function() {
    'use strict';
    angular
        .module('costosapiApp')
        .factory('DataFrame', DataFrame);

    DataFrame.$inject = ['$resource'];

    function DataFrame ($resource) {
        var resourceUrl = 'api/dataFrame/:id';

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