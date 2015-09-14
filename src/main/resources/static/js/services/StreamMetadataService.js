appServices.factory('StreamMetadataService', function($log, $resource) {

	var streamMetadataService = {};
	streamMetadataService.getAll = function () {
        var streamMetadataResource = $resource('streammetadata', {}, {
            query: {
        	method: 'GET', 
        	params: {}, 
        	isArray: true,
        	transformResponse: function (data) {
        	    var allStreamMetadata = [];
        	    if(data!=null){
        		var streamMetadataCompleteList = JSON.parse(data);
        		for (var i = 0; i < streamMetadataCompleteList.length; i++) {
			    var streamMetadata = JSON.parse(streamMetadataCompleteList[i].metadataJson);
			    //streamMetadata.statusIcon = Helpers.stream.statusIcon($scope.streamsList[i]);
			    if(streamMetadata.streamIcon || streamMetadata.streamIcon == null)
				streamMetadata.streamIcon  = "img/stream-icon-default.png";

			    allStreamMetadata.push(streamMetadata);
        		}
        	    }
        	    return allStreamMetadata;
        	}
            }
        });
        return streamMetadataResource.query();
    };

    return streamMetadataService;
});

