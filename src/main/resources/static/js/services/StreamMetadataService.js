appServices.factory('StreamMetadataService', function($log, $resource) {

	var streamMetadataService = {};
	streamMetadataService.getAll = function () {
        var streamMetadataResource = $resource('streammetadata', {}, {
            query: {method: 'GET', params: {}, isArray: true}
        });
        return streamMetadataResource.query();
    };

	return streamMetadataService;
});

