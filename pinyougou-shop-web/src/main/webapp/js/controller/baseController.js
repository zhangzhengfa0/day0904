app.controller('baseController',function ($scope) {
    $scope.selectIds=[];
    $scope.searchEntity={};
    //分页控件配置
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [5,10,20,30,40,50],
        onChange: function(){
            $scope.reloadList();//重新加载
        }
    };
    $scope.updateSelection = function($event,id){
        if ($event.target.checked){
            $scope.selectIds.push(id);
        } else {
            var index = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(index,1);
        }
    };
    $scope.reloadList = function(){
        /**
         * $scope.paginationConf.currentPage当前页（默认是1）
         * $scope.paginationConf.itemsPerPage页面大小（默认是10）
         */
        //$scope.findPage($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
        $scope.search($scope.searchEntity,$scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage)
    }
})