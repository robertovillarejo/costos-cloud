(function() {
    'use strict';
    angular
        .module('costosapiApp')
        .factory('Proveedor', Proveedor);

    Proveedor.$inject = ['$resource'];

    function Proveedor ($resource) {
        var resourceUrl = 'api/proveedores/:id';

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