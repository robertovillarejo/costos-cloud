(function() {
    'use strict';
    angular
        .module('costosapiApp')
        .factory('EquipoComputo', EquipoComputo);

    EquipoComputo.$inject = ['$resource'];

    function EquipoComputo ($resource) {
        var resourceUrl = 'api/equipoComputo/:id';

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