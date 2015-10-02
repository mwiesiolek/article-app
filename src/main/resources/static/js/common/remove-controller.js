app.controller('RemoveController', ['$scope', RemoveController]);

function RemoveController($scope) {

    $scope.confirm = function(msg, $event){

        var result = confirm(msg);
        if(!result){
            $event.preventDefault();
        }
    }
}
