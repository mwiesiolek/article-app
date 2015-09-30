app.controller('CloseableAlertController', ['$scope', '$element', CloseableAlertController]);

function CloseableAlertController($scope, $element) {
    $scope.close = function(){
        $element.remove();
    }
}
