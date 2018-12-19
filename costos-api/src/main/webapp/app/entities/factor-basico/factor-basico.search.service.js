(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .factory('FactorBasicoSearch', FactorBasicoSearch);

    FactorBasicoSearch.$inject = ['$resource'];

    function FactorBasicoSearch($resource) {
        var resourceUrl =  'api/_search/factorBasico/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
