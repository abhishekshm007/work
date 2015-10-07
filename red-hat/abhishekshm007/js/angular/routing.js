'use strict';
var angularApp = angular.module('homeAngular', ['ui.router.state', 'ui.bootstrap']);

angularApp.controller('mainController', function($scope, $location){
	$scope.menuActive = function(page){
		var current = $location.path().substring(1);
		return page === current ? 'active' : '';
	};
});

angularApp.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("profile");
    $stateProvider
        .state('profile', {
            url: "/profile",
            templateUrl: "profile.html",
        })
        .state('skills', {
            url: "/skills",
            templateUrl: "skills.html",
        })
        .state('work', {
            url: "/work",
            templateUrl: "work.html",
        })
        .state('resume', {
            url: "/resume",
            templateUrl: "resume.html",
        })
        ;
});
        