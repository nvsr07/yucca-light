appServices.factory('AttemptsService', function($log, $resource) {
// /attemptsGW
	var attemptService = {};
	
	return {
		getAll: function () {
			console.log('entro in getAll');
	        var summaryResource = $resource('attempts', {}, {
	            query: {
	            	method: 'GET', 
	            	params: {}, 
	            	isArray: true,
	            	transformResponse: function (data) {
	            		
	            		var result = angular.fromJson(data);
	            		
	            		console.log('result', result);
	            		
	            		/*
	        			angular.forEach(result, function(queue, key) {
	                    	var queuKV = key.split('.');
	                    	if (queuKV[0] == 'yucca_light'){
	
	                    		var tmpQueue = null;
	                    		var queueName = 'yucca_light.'+queuKV[1];
	                    		tmpQueue = result[queueName];
	                    		
	                    		angular.forEach(workflowStateObj, function(wf) {
	                    			if (wf.name == queueName){
	                    				wf.countMessages = tmpQueue.countMessages;
	                    				wf.lastmessage = tmpQueue.lastmessage;
	                    			}
	                            });
	                    	}
	        			});
	        			*/
	            	}
	            }
	        });
	        summaryResource.query();
	        return workflowStateObj;
		},
		
		getMessagesOfQueue: function (qn) {

			console.log('queueName => ', qn);
			var queue = qn.split('.');
	        var queueResource = $resource('queue?queueName=:nameQueue', {nameQueue:queue[1]}, {
	            query: {
	            	method: 'GET', 
	            	params: {}, 
	            	isArray: true,
	            	transformResponse: function (data) {
	            		
	            		var result = angular.fromJson(data);
	            		
	            		angular.forEach(result, function(queue, key) {
	            	        console.log('queue', queue);
	                    	var queuKV = key.split('.');
	                    	if (queuKV[0] == 'yucca_light'){
	                    		angular.forEach(workflowStateObj, function(wf) {
	                    			if (wf.name == key){
	                    				wf.messages = queue;
	                    			}
	                    		});
	                    	}
                        });
	            	}
	            }
	        });
	        queueResource.query();
	        console.log('workflowStateObj', workflowStateObj);
	        return true;
	    }
    };
    
});

