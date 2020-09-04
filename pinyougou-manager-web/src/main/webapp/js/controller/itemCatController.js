app.controller('itemCatController',function ($scope,$controller,itemCatService,typeTemplateService) {
    $controller('baseController',{$scope:$scope});//继承
    $scope.grade=1;//默认为1级

    $scope.parentId=0;

    //设置级别,直接替换grade的
    $scope.setGrade=function(value){
        $scope.grade=value;
    }


    $scope.findByParentId = function (parentId) {
        itemCatService.findByParentId(parentId).success(function (response) {
            $scope.list =response;
        });
    }

    /**
     * 定义两个实体，用来记录一级的实体和二级的实体
     * @param entity
     */
    $scope.entity_1=null;
    $scope.entity_2=null;

    $scope.selectList = function (entity) {
        if ($scope.grade==1){
            $scope.parentId=0;
            $scope.entity_1=null;
            $scope.entity_2=null;
        } else if ($scope.grade==2){
            $scope.parentId=entity.id;
            $scope.entity_1=entity;
            $scope.entity_2=null;
        } else if ($scope.grade==3){
            $scope.parentId=entity.id;
            $scope.entity_2=entity;
        }
    }


    $scope.deleteByIds = function () {
        itemCatService.deleteByIds($scope.selectIds).success(function (response) {
            if (response.success){
                if ($scope.grade==1){
                    $scope.findByParentId(0);
                } else if ($scope.grade==2){
                    $scope.findByParentId($scope.entity_1.id);
                } else if ($scope.grade==3){
                    $scope.findByParentId($scope.entity_2.id);
                }
            } else {
                alert(response.message);
            }
        });
    }

    $scope.getTypeList = function () {
        typeTemplateService.getTypeList().success(function (response) {
           $scope.typeList = {data:response};
        });

    }

    $scope.save = function () {
        $scope.entity.parentId=$scope.parentId;
        $scope.entity.typeId = $scope.entity.typeId.id;

        itemCatService.save($scope.entity).success(function (response) {
            if (response.success){
                if ($scope.grade==1){
                    $scope.findByParentId(0);
                } else if ($scope.grade==2){
                    $scope.findByParentId($scope.entity_1.id);
                } else if ($scope.grade==3){
                    $scope.findByParentId($scope.entity_2.id);
                }
            } else {
                alert(response.message);
            }
        })
    }

    $scope.findOne = function (id) {
        itemCatService.findOne(id).success(function (response) {
            $scope.entity = response;
            var object = $scope.getObject($scope.typeList.data,$scope.entity.typeId);
            $scope.entity.typeId = object;
        });
    }
    $scope.getObject = function (arr,id) {
        for (var x=0;x<arr.length;x++){
            if (arr[x].id==id){
                return arr[x];
            }
        }
    }
})