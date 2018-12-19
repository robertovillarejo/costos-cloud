(function() {
    'use strict';
    angular
        .module('costosapiApp')
        .factory('PartidaConcepto', PartidaConcepto);

    PartidaConcepto.$inject = ['$resource'];

    function PartidaConcepto ($resource) {
        var resourceUrl = 'api/partidaConcepto/:id';

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