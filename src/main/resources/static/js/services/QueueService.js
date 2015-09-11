appServices.factory('QueueService', function($log, $resource) {

	var queueService = {};
	queueService.getAll = function () {
        var eventResource = $resource('queue', {}, {
            query: {method: 'GET', params: {}, isArray: false}
        });
        return eventResource.query();
    };

	return queueService;
});

