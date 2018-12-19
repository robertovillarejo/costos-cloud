(function() {
    'use strict';
    angular
        .module('costosapiApp')
        .factory('FactorBasico', FactorBasico);

    FactorBasico.$inject = ['$resource'];

    function FactorBasico ($resource) {
        var resourceUrl = 'api/factorBasico/:id';

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