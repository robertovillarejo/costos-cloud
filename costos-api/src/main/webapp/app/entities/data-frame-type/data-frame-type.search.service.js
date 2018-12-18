(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .factory('DataFrameTypeSearch', DataFrameTypeSearch);

    DataFrameTypeSearch.$inject = ['$resource'];

    function DataFrameTypeSearch($resource) {
        var resourceUrl =  'api/_search/dataFrameType/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
