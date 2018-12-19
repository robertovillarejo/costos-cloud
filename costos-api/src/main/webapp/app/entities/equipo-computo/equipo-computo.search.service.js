(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .factory('EquipoComputoSearch', EquipoComputoSearch);

    EquipoComputoSearch.$inject = ['$resource'];

    function EquipoComputoSearch($resource) {
        var resourceUrl =  'api/_search/equipoComputo/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
