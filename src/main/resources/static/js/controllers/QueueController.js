appControllers.controller('QueueController', [ 
        '$scope', 
        '$log', 
        'QueueService', 
        function($scope, $log, QueueService) {
        	$scope.queueList = [];
			console.log("QueueController - getAll", QueueService.getAll().$promise);

        	$scope.queueList = QueueService.getAll();

			$scope.filteredStreamsList = [];
			$scope.codeFilter = null;
			$scope.currentPage = 1;
			$scope.pageSize = 10;
			$scope.totalItems = $scope.queueList.length;
			$scope.predicate = '';
			$scope.showLoading = false;

			$scope.searchCodeFilter = function(stream) {
				var keyword = new RegExp($scope.codeFilter, 'i');
				return !$scope.codeFilter || keyword.test(stream.streamCode) || keyword.test(stream.streamName);
			};

			$scope.$watch('codeFilter', function(newCode) {
				$scope.currentPage = 1;
				$scope.totalItems = $scope.filteredStreamsList.length;
			});
} ]);
