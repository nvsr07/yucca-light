
appControllers.controller('StreamMetadataController', [ '$scope', "$log", 'StreamMetadataService', function($scope, $log, StreamMetadataService) {

	$scope.streamMetadataList = StreamMetadataService.getAll();
} ]);
