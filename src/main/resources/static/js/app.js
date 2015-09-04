angular
    .module('myApp', ['ngResource'])
    .service('EventsService', function ($log, $resource) {
        return {
            getAll: function () {
                var eventResource = $resource('events', {}, {
                    query: {method: 'GET', params: {}, isArray: true}
                });
                return eventResource.query();
            }
        }
    })
    .controller('EventsController', function ($scope, $log, EventsService) {
        $scope.events = EventsService.getAll();
    });
