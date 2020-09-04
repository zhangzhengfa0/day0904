 //控制层 
app.controller('sellerController' ,function($scope,$controller,sellerService){

	$controller('baseController',{$scope:$scope});//继承
	$scope.checkSellerId = function () {
        sellerService.checkSellerId($scope.entity.sellerId).success(function (response) {
				$scope.message = response.message;
        });
    }
    $scope.add = function () {
        sellerService.add($scope.entity).success(function (response) {
            if (response.success){
                alert("入驻成功，请耐心等待平台审核");
                window.location.href = "shoplogin.html";
            } else {
                alert("注册失败");
            }
        })
    }
    
});	
