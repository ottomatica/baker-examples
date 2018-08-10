'use strict';
 
angular.module('myApp').factory('InventoryService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = '/api/v1/inventory';
 
    var factory = {
    		getInventory: getInventory,
    		updateInventory: updateInventory
    };
 
    return factory;
 
    function getInventory() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while getting inventory');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    } 
 
    function updateInventory(inventory) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI, inventory)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Inventory');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
}]);