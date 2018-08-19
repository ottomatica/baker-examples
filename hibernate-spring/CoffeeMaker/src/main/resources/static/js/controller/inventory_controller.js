'use strict';
 
angular.module('myApp').controller('InventoryController', ['$scope', 'InventoryService', function($scope, InventoryService) {
    var self = this;
    self.inventory={coffee:'', milk:'', sugar:'', chocolate:''};
    self.inventoryMaster = {coffee:'', milk:'', sugar:'', chocolate:''};
 
    self.submit = submit;
    self.reset = reset;
 
    getInventory();
 
    function getInventory(){
    		InventoryService.getInventory()
            .then(
            function(data) {
                self.inventoryMaster = data;
            },
            function(errResponse){
                console.error('Error while getting Inventory');
            }
        );
    }
 
    function updateInventory(inventory){
    		$scope.success = false;
    		$scope.failure = false;
    		InventoryService.updateInventory(inventory)
            .then(
            		getInventory,
            function(errResponse){
            		$scope.failure = true;
            		$scope.success = false;
                console.error('Error while updating Inventory');
            }
        );
    		
    		$scope.success = !($scope.failure);
    }
 
    function submit() {
    		updateInventory(self.inventory);
        console.log('Inventory updated');
        
        reset();
    }
 
 
    function reset(){
    		self.inventory={coffee:'', milk:'', sugar:'', chocolate:''};
        $scope.addInventoryForm.$setPristine(); //reset Form
    }
 
}]);