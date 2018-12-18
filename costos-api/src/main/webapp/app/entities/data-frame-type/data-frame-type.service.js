(function() {
    'use strict';
    angular
        .module('costosapiApp')
        .factory('DataFrameType', DataFrameType);

    DataFrameType.$inject = ['$resource'];

    function DataFrameType ($resource) {
        var resourceUrl = 'api/dataFrameType/:id';

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