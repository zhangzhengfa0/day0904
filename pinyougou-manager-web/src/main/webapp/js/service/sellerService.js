//服务层
app.service('sellerService',function($http){
    this.search = function (searchEntity,page,rows) {
        return $http.post("../seller/search.do?page="+page+"&rows="+rows,searchEntity);
    }
    this.findOne = function (sellerId) {
        return $http.get("../seller/findOne.do?sellerId="+sellerId);
    }
    this.updateStatus  =function (sellerId,status) {
        return $http.get('../seller/updateStatus.do?sellerId='+sellerId+"&status="+status);
    }
});
