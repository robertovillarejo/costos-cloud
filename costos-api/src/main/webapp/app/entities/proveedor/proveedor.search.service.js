(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .factory('ProveedorSearch', ProveedorSearch);

    ProveedorSearch.$inject = ['$resource'];

    function ProveedorSearch($resource) {
        var resourceUrl =  'api/_search/proveedores/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
