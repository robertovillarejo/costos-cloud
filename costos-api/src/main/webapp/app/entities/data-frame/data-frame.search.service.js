(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .factory('DataFrameSearch', DataFrameSearch);

    DataFrameSearch.$inject = ['$resource'];

    function DataFrameSearch($resource) {
        var resourceUrl =  'api/_search/dataFrame/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
