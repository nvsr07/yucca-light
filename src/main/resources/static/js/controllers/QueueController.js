
appControllers.controller('QueueController', [ '$scope', "$log", 'QueueService', function($scope, $log, QueueService) {
	 var pippo = QueueService.getAll();
	    $scope.events = pippo;
} ]);
