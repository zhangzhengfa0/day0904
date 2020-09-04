app.service('typeTemplateService',function ($http) {
    this.search = function (searchEntity,page,rows) {
        return $http.post('../typeTemplate/search.do?rows='+rows+"&page="+page,searchEntity);
    }
    this.dele = function (selectids) {
        return $http.post('../typeTemplate/dele.do',selectids);
    }
    this.save=function (entity) {
        return $http.post('../typeTemplate/save.do',entity);
    }
    this.findOne=function (id) {
        return $http.get('../typeTemplate/findOne.do?id='+id);
    }
    this.getTypeList = function () {
        return $http.get('../typeTemplate/getTypeList.do');
    }
})