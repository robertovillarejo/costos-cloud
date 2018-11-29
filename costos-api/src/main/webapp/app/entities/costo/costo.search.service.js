(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .factory('CostoSearch', CostoSearch);

    CostoSearch.$inject = ['$resource'];

    function CostoSearch($resource) {
        var resourceUrl =  'api/_search/costos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
