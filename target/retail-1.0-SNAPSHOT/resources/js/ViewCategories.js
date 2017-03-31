
/// <reference path="/resources/angular.mi.js"/>

var myApp = angular
    .module("restModule", [])
    .controller("restController", function($scope, $http) {
        $scope.about = "We produce quality made fashion wear just for you, Category List : ";
        $http.post('/category/categories')
             .then(function (response) {
                $scope.categories = response.data;
             });
    });
