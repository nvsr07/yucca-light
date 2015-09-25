appControllers.controller('QueueController', [ 
        '$scope', 
        '$log', 
        'QueueService', 
        function($scope, $log, QueueService) {
        	$scope.queueList = [];
			console.log("QueueController - getAll", QueueService.getAll());

        	$scope.queueList = QueueService.getAll();

			$scope.filteredQueuesList = [];
			$scope.codeFilter = null;
			$scope.currentPage = 1;
			$scope.pageSize = 10;
			$scope.totalItems = $scope.queueList.length;
			$scope.predicate = '';
			$scope.showLoading = false;

			$scope.searchCodeFilter = function(queue) {
				var keyword = new RegExp($scope.codeFilter, 'i');
				return !$scope.codeFilter || keyword.test(queue.streamCode) || keyword.test(queue.sourceCode);
			};

			$scope.$watch('codeFilter', function(newCode) {
				$scope.currentPage = 1;
				$scope.totalItems = $scope.filteredQueuesList.length;
			});
			
			$scope.loadMessagesOfQueue = function(queueName){
				console.log('queueName', queueName);
				QueueService.getMessagesOfQueue(queueName);
			}
} ]);
