
var app = angular.module("ItemCategoryManagement", []);

app.controller("ItemCategoryController", function ($scope, $http) {

    /*****************
     * Public data
     *****************/
    $scope.welcome = "AngularJS http service allows me to do all cool staff." +
        "Integrating AngularJS with Spring MVC.";

    $scope.categories = [];
    $scope.categoryForm ={
        id : -1,
        categoryName : ""
    };


    /***************************************************************************
     * load data from SERVER
     * HTTP POST - get all categories collection
     ***************************************************************************/

    function  _refreshCategory() {
        $http({
            method : 'POST',
            url : '/category/categories'
            /* OR
            url : 'http://localhost:8080/category/categories
             Achieves the same thing
             */

        }).then(function successCallBack(response) {
            $scope.categories = response.data;
        }, function errorCallBack(response) {
            console.log(response.statusText);
        });
    }
    // Load the data
    _refreshCategory();

    /***************************************************************************
     * Remove Category
     * HTTP DELETE
     ***************************************************************************/

    $scope.deleteCategory = function (category, response) {
        $http({
           method : 'POST',
            url : '/category/remove/' + category.id
        }).then(_success(response), _error(response));
    }

    /***************************************************************************
     * Helper Methods
     ***************************************************************************/

    function _success(response) {
        _refreshCategory();
        _clearFormData()
    }

    function _error(response) {
        console.log(response.statusText);
    }

    function _clearFormData() {
        $scope.categoryForm.id = -1;
        $scope.categoryForm.categoryName = "";
    }


    /***************************************************************************
     * Add new or Update category
     * HTTP POST for add and HTTP POST for update
     ***************************************************************************/
    $scope.submitCategory = function (response) {

        var method = "";
        var url = "";

        /* IF ID is -1 the this is a new Category to be added
            This scenaria can only happen when no value has been set for id
            e.g no element for edit and no prior contact with the application
        */
        if($scope.categoryForm.id == -1){
            method = "POST";
            url = '/category/add';
        } else{ /* if the value of if is not negative one then there is a new value to be added to category table */
            method = "POST";
            url = '/category/update';
        }

        $http({
            method : method,
            url : url,
            data : angular.toJson($scope.categoryForm),
            headers : {
                'Content-Type' : 'application/json'
            }
        }).then(_success(response), _error(response))
    }

    /***************************************************************************
     * Update category
     * HTTP POST to update category table
     ***************************************************************************/

    $scope.editCategory = function (category) {

        /* Due to the binding mechanism, these values would be
        update for the input text emediately
         */
        $scope.categoryForm.id = category.id;
        $scope.categoryForm.categoryName = category.categoryName;

    }
});