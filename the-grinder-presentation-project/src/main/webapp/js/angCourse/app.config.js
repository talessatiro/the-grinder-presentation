angular
	.module('app')
	.config(config);

function config($routeProvider){
	$routeProvider
        .when('/',{
            templateUrl: 'home.html'
        })
		.when('/customers',{
            templateUrl: 'list.html',
            controller: 'CustomerController',
            controllerAs: 'customerCtrl'
        })
        .otherwise({redirectTo:'/'});
}