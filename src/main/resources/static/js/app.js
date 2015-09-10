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
    .service('QueueService', function ($log, $resource) {
        return {
            getAll: function () {
                var eventResource = $resource('queue', {}, {
                    query: {method: 'GET', params: {}, isArray: false}
                });
                return eventResource.query();
            }
        }
    })
    .controller('EventsController', function ($scope, $log, EventsService) {
        $scope.events = EventsService.getAll();
    })
    .controller('QueueController', function ($scope, $log, QueueService) {
        var pippo = QueueService.getAll();
        $scope.events = pippo;
    });
