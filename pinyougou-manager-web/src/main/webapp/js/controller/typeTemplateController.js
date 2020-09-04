app.controller('typeTemplateController',function ($scope,$controller,typeTemplateService,brandService,specificationService) {
    $controller('baseController',{$scope:$scope});

    //{data:[{id:1,text:'联想'},{id:2,text:'华为'},{id:3,text:'小米'}]};
    $scope.brandList= function () {
        brandService.getBrandList().success(function (response) {
            $scope.brandList = {data:response};
        });
    }

    $scope.addTableRow=function(){
        $scope.entity.customAttributeItems.push({});
    }

    $scope.deleTableRow=function(index){
        $scope.entity.customAttributeItems.splice(index,1);
    }



    $scope.specList=function(){
        specificationService.getSpecList().success(function (response) {
            $scope.specList={data:response};
        })
    }


    $scope.search = function (searchEntity,page,rows) {
        typeTemplateService.search(searchEntity,page,rows).success(function (response) {
            $scope.list = response.rows;
            $scope.paginationConf.totalItems=response.total;
        });
    }
    $scope.dele = function () {
        typeTemplateService.dele($scope.selectIds).success(function (response) {
            if(response.success){
                $scope.reloadList();
            }else{
                alert(response.message);
            }
        })
    }

    $scope.save=function(){
        typeTemplateService.save($scope.entity).success(function (response) {
            if (response.success){
                $scope.reloadList();
            } else {
                alert(response.message);
            }
        })
    }

    $scope.findOne=function(id){
        typeTemplateService.findOne(id).success(function (response) {
           $scope.entity=response;
           $scope.entity.brandIds=JSON.parse($scope.entity.brandIds);
           $scope.entity.specIds=JSON.parse($scope.entity.specIds);
            if($scope.entity.customAttributeItems==null){
                $scope.entity.customAttributeItems=[];
            }else{
                $scope.entity.customAttributeItems= JSON.parse($scope.entity.customAttributeItems);
            }
        });
    }


})