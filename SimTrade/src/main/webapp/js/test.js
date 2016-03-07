/**
 * 
 */
function Hello($scope, $http) {
    $http.get('http://localhost:8080/fest').
        success(function(data) {
            $scope.greeting = data;
            alert(data);
        });
}

var app = angular.module('formSubmit', []);

app.controller('FormSubmitController',[ '$scope', '$http', function($scope, $http) {
   
  $scope.list = [];
   $scope.headerText = 'AngularJS Pass JSON Map Spring MVC Controller';
   $scope.submit = function() {
    
    var test ="CANADA";
    var formData = {
      "FullName" : $scope.FullName,
      "City" : $scope.City,
      "Zip" : $scope.Zip 
    };
    
    var response = $http.post('checkAvailable', test);
    response.success(function(data, status, headers, config) {
     $scope.list.push(data);
    });
    response.error(function(data, status, headers, config) {
     alert( "Exception details: " + JSON.stringify({data: data}));
    });
    
    //Empty list data after process
    $scope.list = [];
    
   };
  }]);