var app = angular.module('app', []).controller('home', function($scope, $location, $http){
	
	$scope.todos = [];
	$scope.todo = "";
	
	$scope.getTodos = function(){
		var protocol = $location.protocol();
		var host = $location.host();
		var port = $location.port();
		var url = protocol+"://"+host+":"+port+"/todos";
		$http({
			method:'GET',
			url: url
		}).then(function(success){
			console.log(success);
			$scope.todos = success.data;
		}, function(err){
			console.log(err);
		});
	};
	
	$scope.addTodo = function(todo){
		var protocol = $location.protocol();
		var host = $location.host();
		var port = $location.port();
		var url = protocol+"://"+host+":"+port+"/todos/"+todo;
		$http({
			method:'PUT',
			url: url
		}).then(function(success){
			console.log(success);
			$scope.todos = success.data;
		}, function(err){
			console.log(err);
		});
	};
	
	$scope.rmTodo = function(todo){
		var protocol = $location.protocol();
		var host = $location.host();
		var port = $location.port();
		var url = protocol+"://"+host+":"+port+"/todos/"+todo;
		$http({
			method:'DELETE',
			url: url
		}).then(function(success){
			$scope.todos = success.data;
			console.log(success);
		}, function(err){
			console.log(err);
		});
	};
	
});
