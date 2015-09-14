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
                			var streamMetadataComplete = streamMetadataCompleteList[i]
            			    var streamMetadata = JSON.parse(streamMetadataComplete.metadataJson);
            			    if(streamMetadata.streamIcon || streamMetadata.streamIcon == null)
            			    	streamMetadata.streamIcon  = "img/stream-icon-default.png";
            
            			    streamMetadata.lastUpdateTimestamp = streamMetadataComplete.lastUpdateTimestamp;

            			    var exampleMessage = "{\"stream\": \""+streamMetadataComplete.streamCode+"\",\n";
            			    exampleMessage += "  \"sensor\": \"" + streamMetadataComplete.virtualEntityCode+ "\",\n";
            			    exampleMessage += "  \"values\":\n";
            			    exampleMessage += "    [{\"time\": "+ new Date().getTime() +",\n";
            			    exampleMessage += "      \"components\":{\n";
            			    for (var j = 0; j < streamMetadata.streams.stream.components.element.length; j++) {
            			    	var component = streamMetadata.streams.stream.components.element[j];
            			    	var componentExample = "\"any string\"";
            			    	if(component.dataType=='long' || component.dataType=='int')
            			    		componentExample = "14";
            			    	else if(component.dataType=='double' || component.dataType=='float')
            			    		componentExample = "3.2";
            			    	else if(component.dataType=='boolean')
            			    		componentExample = "true";
            			    	else if(component.dataType=='dateTime')
            			    		componentExample = "\"2015-09-14'T'18:48:34Z\"";
            			    	else if(component.dataType=='latitude')
            			    		componentExample = "45.0706029";
            			    	else if(component.dataType=='longitude')
            			    		componentExample = "7.6867102";
            			    	
            			    	
            			    	
            			    	exampleMessage += "        \""+ streamMetadata.streams.stream.components.element[j].componentName+"\":"+componentExample+"";
            			    	if(j<streamMetadata.streams.stream.components.element.length-1)
            			    		exampleMessage += ",\n";
            			    	else
            			    		exampleMessage += "\n";
							}
            			    exampleMessage += "       }\n";
            			    exampleMessage += "    }]\n";
            			    exampleMessage += "}";

            			    streamMetadata.exampleMessage = exampleMessage;
            			    
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
