
appControllers.controller('AttemptsController', [ '$scope', "$log", 'AttemptsService', function($scope, $log, AttemptsService) {
	 var pippo = AttemptsService.getAll();
	    $scope.attempts = pippo;
} ]);
