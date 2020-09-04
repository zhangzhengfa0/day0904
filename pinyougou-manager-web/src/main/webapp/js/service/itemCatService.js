app.service('itemCatService',function($http){
    this.findByParentId = function (parentId) {
        return $http.get('../itemCat/findByParentId.do?parentId='+parentId);
    }
    this.deleteByIds = function (selectIds) {
        return $http.post('../itemCat/deleteByIds.do',selectIds);
    }
    this.save = function (entity) {
        return $http.post('../itemCat/save.do',entity);
    }
    this.findOne = function (id) {
        return $http.get('../itemCat/findOne.do?id='+id);
    }
});