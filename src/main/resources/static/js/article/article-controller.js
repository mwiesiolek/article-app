/**
 * Created by mwiesiolek on 30/09/2015.
 */
app.controller('ArticlePipeController', ['$scope', '$filter', '$http', ArticlePipeController]);

function ArticlePipeController($scope, $filter, http) {
    $scope.data = [];

    $scope.callServer = function callServer(tableState) {
        $scope.isLoading = true;

        var start = tableState.pagination.start || 0;
        var number = tableState.pagination.number || 10;

        var url = '/rest/article/find?start=' + start + '&number=' + number;
        try{
            if($scope.startDate){
                url += '&sStartDate=' + $filter('date')($scope.startDate, "yyyy-MM-dd");;
            }

            if($scope.endDate){
                url += '&sEndDate=' + $filter('date')($scope.endDate, "yyyy-MM-dd");;
            }

            if(tableState.search.predicateObject.authorFirstName){
                url += '&authorFirstName=' + tableState.search.predicateObject.authorFirstName;
            }

            if(tableState.search.predicateObject.authorSurname){
                url += '&authorSurname=' + tableState.search.predicateObject.authorSurname;
            }

            if(tableState.search.predicateObject.keyword){
                url += '&keyword=' + tableState.search.predicateObject.keyword;
            }

        }catch(err){
            //do nothing
        }

        var req = {
            method: 'GET',
            url: url
        }

        http(req)
            .success(function (data, status, headers, config) {
                $scope.data = data.articles;
                tableState.pagination.numberOfPages = data.numberOfPages;

                $scope.isLoading = false;
            })
            .error(function (data, status, headers, config) {
                $scope.data = [];
                tableState.pagination.numberOfPages = 0;

                $scope.isLoading = false;
            });
    }

    $scope.today = function() {
        $scope.startDate = new Date();
    };

    $scope.clear = function () {
        $scope.startDate = null;
    };

    $scope.minDate = new Date(100, 5, 22);
    $scope.maxDate = new Date(3020, 5, 22);

    $scope.sopen = function() {
        $scope.status.sopened = true;
    };

    $scope.eopen = function() {
        $scope.status.eopened = true;
    };

    $scope.format = 'yyyy-MM-dd';

    $scope.status = {
        sopened: false,
        eopened: false
    };
}