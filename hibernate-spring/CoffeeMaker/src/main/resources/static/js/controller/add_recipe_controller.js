'use strict';
 
angular.module('myApp').controller('AddRecipeController', ['$scope', 'AddRecipeService', function($scope, AddRecipeService) {
    var self = this;
    self.recipe={name:'', price:'', coffee:'', milk:'', sugar:'', chocolate:''};
 
    self.submit = submit;
    self.reset = reset;
 
    function addRecipe(recipe){
    		$scope.success = false;
    		$scope.failure = false;
    		AddRecipeService.addRecipe(recipe)
            .then(
            		$scope.success = true,
            function(errResponse){
            		$scope.failure = true;
            		$scope.success = false;
                console.error('Error while adding recipe');
            }
        );
    		
    		$scope.success = !($scope.failure);
    }
 
    function submit() {
    		addRecipe(self.recipe);
        
        reset();
    }
 
 
    function reset(){
    		self.recipe={name:'', price:'', coffee:'', milk:'', sugar:'', chocolate:''};
        $scope.addRecipeForm.$setPristine(); //reset Form
    }
 
}]);