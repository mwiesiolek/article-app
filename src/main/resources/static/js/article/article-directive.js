app.directive('refreshTable', function () {
    return {
        require: '^stTable',
        link: function (scope, element, attr, ctrl) {
            scope.$watch('startDate', function (newValue, oldValue) {
                scope.startDate = newValue;
                scope.callServer(ctrl.tableState());
            });
            scope.$watch('endDate', function (newValue, oldValue) {
                scope.endDate = newValue;
                scope.callServer(ctrl.tableState());
            });
        }
    };
});