/**
 * 
 */

/*angular
	.module('MyApp' , [ngRoute])
	.config($routeProvider)
	.controller("MyCtrl",function Hello($scope, $http) {
	    $http.get('http://localhost:8080/home/gettable').
        success(function(data) {
            $scope.greeting = data;
        });
});*/
	

function Hello($scope, $http) {
    $http.get('http://localhost:8080/example').
    alert("hi");
        success(function(data) {
            $scope.greeting = data;
            alert(data);
        });
}


/*angular.module('todoApp', [])
  .controller('TodoListController', function() {
    var todoList = this;
    todoList.todos = [
      {text:'learn angular', done:true},
      {text:'build an angular app', done:false}];
 
    todoList.addTodo = function() {
      todoList.todos.push({text:todoList.todoText, done:false});
      todoList.todoText = '';
    };
 
    todoList.remaining = function() {
      var count = 0;
      angular.forEach(todoList.todos, function(todo) {
        count += todo.done ? 0 : 1;
      });
      return count;
    };
 
    todoList.archive = function() {
      var oldTodos = todoList.todos;
      todoList.todos = [];
      angular.forEach(oldTodos, function(todo) {
        if (!todo.done) todoList.todos.push(todo);
      });
    };
  });*/


var sampleApp = angular.module('sampleApp', []); 
sampleApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/AddNewOrder', {
        templateUrl: 'templates/add.html',
        controller: 'AddOrderController'
    }).
      when('/ShowOrders', {
        templateUrl: 'templates/show.html',
        controller: 'ShowOrdersController'
      }).
      otherwise({
        redirectTo: '/home'
      });
}]);
 
 
sampleApp.controller('AddOrderController', function($scope) {
     
    $scope.message = 'This is Add new order screen';
     
});
 
 
sampleApp.controller('ShowOrdersController', function($scope) {
 
    $scope.message = 'This is Show orders screen';
 
});




sampleApp.controller('myCtrl', function ($scope, $http) {
    $scope.hello = {name: "Boaz"};
    $scope.newName = "";
    $scope.sendPost = function() {
        /*var data = $.param({
            json: JSON.stringify({
                name: $scope.newName
            })
        });*/
    	var data = "Hello!"
        $http.post("http://localhost:8080/example", data).success(function(data, status) {
            $scope.hello = {name: "Sam"};
        })
    }                   
})

/*var mainApp = angular.module("mainApp", ['ngRoute']);
 
mainApp.config(function($routeProvider) {
    $routeProvider
        .when('/Add', {
            templateUrl: 'add.html',
            controller: 'StudentController'
        })
        .when('/viewStudents', {
            templateUrl: 'viewStudents.html',
            controller: 'StudentController'
        })
        .otherwise({
            redirectTo: '/htmltest'
        });
});
 
mainApp.controller('StudentController', function($scope) {
    $scope.students = [
        {name: 'Mark Waugh', city:'New York'},
        {name: 'Steve Jonathan', city:'London'},
        {name: 'John Marcus', city:'Paris'}
    ];
 
    $scope.message = "Click on the hyper link to view the students list.";
});*/