/**
 * Created by mwiesiolek on 30/09/2015.
 */
app.controller('ArticlePipeController', ['$scope', '$http', ArticlePipeController]);

function ArticlePipeController(scope, http) {
    scope.data = [];

    scope.callServer = function callServer(tableState) {
        scope.isLoading = true;

        var start = tableState.pagination.start || 0;
        var number = tableState.pagination.number || 10;

        var url = '';
        try{
            if(tableState.search.predicateObject.$) {
                url = '/rest/article/find?start=' + start + '&number=' + number + '&search=' + tableState.search.predicateObject.$;
            }else{
                url = '/rest/article/find?start=' + start + '&number=' + number;
            }
        }catch(err){
            url = '/rest/article/find?start=' + start + '&number=' + number;
        }

        var req = {
            method: 'GET',
            url: url
        }

        http(req)
            .success(function (data, status, headers, config) {
                scope.data = data.products;
                tableState.pagination.numberOfPages = data.numberOfPages;

                scope.isLoading = false;
            })
            .error(function (data, status, headers, config) {
                scope.data = [];
                tableState.pagination.numberOfPages = 0;

                scope.isLoading = false;
            });
    }
}