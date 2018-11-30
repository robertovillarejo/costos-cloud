(function() {
    'use strict';
    angular
        .module('costosapiApp')
        .factory('Costo', Costo);

    Costo.$inject = ['$resource', 'DateUtils'];

    function Costo ($resource, DateUtils) {
        var resourceUrl = 'api/costos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
						data.fechaRegistro = DateUtils.convertLocalDateFromServer(data.fechaRegistro);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
				    copy.fechaRegistro = DateUtils.convertLocalDateToServer(copy.fechaRegistro);
        		return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
				    copy.fechaRegistro = DateUtils.convertLocalDateToServer(copy.fechaRegistro);
                    return angular.toJson(copy);
                }
            }
,
            'download': {
                method: 'GET',
                url: 'api/costos/workbook',
                responseType: 'blob',
                transformResponse: function (data) {
                    return {
                        blob: new Blob([data], {
                            type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
                        })
                    }
                }
            }
        });
    }
})();