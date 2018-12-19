(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .factory('PartidaConceptoSearch', PartidaConceptoSearch);

    PartidaConceptoSearch.$inject = ['$resource'];

    function PartidaConceptoSearch($resource) {
        var resourceUrl =  'api/_search/partidaConcepto/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
