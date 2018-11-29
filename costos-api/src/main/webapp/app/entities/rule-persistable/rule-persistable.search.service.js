(function() {
    'use strict';

    angular
        .module('costosapiApp')
        .factory('RulePersistableSearch', RulePersistableSearch);

    RulePersistableSearch.$inject = ['$resource'];

    function RulePersistableSearch($resource) {
        var resourceUrl =  'api/_search/rulePersistable/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
