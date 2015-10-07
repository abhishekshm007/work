'use strict'

var sampleApp = angular.module("o9paathshalaHome", []);

sampleApp.run(function($rootScope, $location) {
    $rootScope.location = $location;
});
sampleApp.directive("passwordVerify", function() {
	return {
		require: "ngModel",
		scope: {
			passwordVerify: '='
		},
		link: function(scope, element, attrs, StudentRegistrationController) {
			scope.$watch(function() {
				var combined;

				if (scope.passwordVerify || StudentRegistrationController.$viewValue) {
					combined = scope.passwordVerify + '_' + StudentRegistrationController.$viewValue; 
				}                    
				return combined;
			}, function(value) {
				if (value) {
					StudentRegistrationController.$parsers.unshift(function(viewValue) {
						var origin = scope.passwordVerify;
						if (origin !== viewValue) {
							StudentRegistrationController.$setValidity("passwordVerify", false);
							return undefined;
						} else {
							StudentRegistrationController.$setValidity("passwordVerify", true);
							return viewValue;
						}
					});
				}
			});
		}
	};
});

sampleApp.directive("setpasswordVerify", function() {
	return {
		require: "ngModel",
		scope: {
			setpasswordVerify: '='
		},
		link: function(scope, element, attrs, SetPasswordController) {
			scope.$watch(function() {
				var combined;

				if (scope.setpasswordVerify || SetPasswordController.$viewValue) {
					combined = scope.setpasswordVerify + '_' + SetPasswordController.$viewValue; 
				}                    
				return combined;
			}, function(value) {
				if (value) {
					SetPasswordController.$parsers.unshift(function(viewValue) {
						var origin = scope.setpasswordVerify;
						if (origin !== viewValue) {
							SetPasswordController.$setValidity("setpasswordVerify", false);
							return undefined;
						} else {
							SetPasswordController.$setValidity("setpasswordVerify", true);
							return viewValue;
						}
					});
				}
			});
		}
	};
});

sampleApp.directive("emailConfirm", function() {
	return {
		require: "ngModel",
		scope: {
			emailConfirm: '='
		},
		link: function(scope, element, attrs, InstituteRegistrationController) {
			scope.$watch(function() {
				var combined;

				if (scope.passwordVerify || InstituteRegistrationController.$viewValue) {
					combined = scope.emailConfirm + '_' + InstituteRegistrationController.$viewValue; 
				}                    
				return combined;
			}, function(value) {
				if (value) {
					InstituteRegistrationController.$parsers.unshift(function(viewValue) {
						var origin = scope.emailConfirm;
						if (origin !== viewValue) {
							InstituteRegistrationController.$setValidity("emailConfirm", false);
							return undefined;
						} else {
							InstituteRegistrationController.$setValidity("emailConfirm", true);
							return viewValue;
						}
					});
				}
			});
		}
	};
});

sampleApp.controller('StudentRegistrationController', function($scope, $http, $window){

	$scope.student = {}
	$scope.submitForm = function(){
		if ($scope.studentRegistrationForm.$valid) {
			var d=$("#studentForm").serialize();
			var target = document.getElementById('studentForm');
			var spinner = new Spinner().spin(target);
			$http({
				method: 'POST',
				url: 'StudentRegistration',
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				data:  d
			}).
			success(function(data, status, headers, config) {
				$window.location='StudentRegistrationSession?req='+data;
				spinner.stop();
			}).
			error(function(data, status, headers, config) {
				notif({
					msg : data,
					type : 'error',
					position : 'center'
				});
				spinner.stop();
			});
		} else {
			$scope.studentRegistrationForm.submitted = true;
		}
	}
});

sampleApp.controller('InstituteRegistrationController', function($scope, $http, $window){

	
	$scope.institute = {}
	$scope.submitForm = function(){
		if ($scope.instituteRegistrationForm.$valid) {
			var d=$("#instituteForm").serialize();
			var target = document.getElementById('instituteForm');
			var spinner = new Spinner().spin(target);
			$http({
				method: 'POST',
				url: 'InstituteRegistration',
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				data:  d
			}).
			success(function(data, status, headers, config) {
				spinner.stop();

				$window.location='confirmRegistration.jsp?req='+data.email+'&lk='+data.name;
			}).
			error(function(data, status, headers, config) {
				notif({
					msg : data,
					type : 'error',
					position : 'center'
				});
				spinner.stop();
			});
		} else {
			$scope.instituteRegistrationForm.submitted = true;
		}
	}
});


sampleApp.controller('LoginController', function($scope, $http, $window){
	$scope.user = {}
	
	$scope.submitForm = function(){
		if ($scope.loginForm.$valid) {
			var target = document.getElementById('loginForm');
			var spinner = new Spinner().spin(target);
			$http({
				method: 'POST',
				url: 'CheckLogin',
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				data:  "user="+JSON.stringify($scope.user)
			}).
			success(function(data, status, headers, config) {
				spinner.stop();
				$window.location='Login?req='+data+'&remember='+$scope.user.remember;
			}).
			error(function(data, status, headers, config) {
				notif({
					msg : data,
					type : 'error',
					position : 'center'
				});
				spinner.stop();
			});
		} else {
			$scope.loginForm.submitted = true;
		}
	}
});

sampleApp.controller('ForgotPasswordController', function($scope, $http, $window){

	
	$scope.submitForm = function(){
		if ($scope.forgotPasswordForm.$valid) {
			var target = document.getElementById('login-box');
			var spinner = new Spinner().spin(target);
			$http({
				method: 'POST',
				url: 'ForgotPassword',
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				data:  "req="+$scope.email
			}).
			success(function(data, status, headers, config) {
				spinner.stop();
				notif({
					msg : data,
					type:"success",
					position : 'center',
				});
			}).
			error(function(data, status, headers, config) {
				notif({
					msg : data,
					type : 'error',
					position : 'center'
				});
				spinner.stop();
			});
		} else {
			$scope.instituteRegistrationForm.submitted = true;
		}
	}
});


/*sampleApp.controller('SetPasswordController', function($scope, $http, $window, $location){

	
	$scope.password = {};
	$scope.submitForm = function(){
		if ($scope.setPasswordForm.$valid) {
			var prm = ($scope.parseURL($location.absUrl()));
			var target = document.getElementById('login-box');
			var spinner = new Spinner().spin(target);
			$http({
				method: 'POST',
				url: 'InstituteRegistrationConfirmation',
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				data:  "data="+JSON.stringify($scope.password)+"&id="+prm.id
			}).
			success(function(data, status, headers, config) {
				$window.location='institute/dashboard';
				spinner.stop();
			}).
			error(function(data, status, headers, config) {
				notif({
					msg : data,
					type : 'error',
					position : 'center'
				});
				spinner.stop();
			});
		} else {
			$scope.setPasswordForm.submitted = true;
		}
	}

	$scope.parseURL = function(url) {
		var queryStart = url.indexOf("?") + 1,
		queryEnd   = url.indexOf("#") + 1 || url.length + 1,
		query = url.slice(queryStart, queryEnd - 1),
		pairs = query.replace(/\+/g, " ").split("&"),
		parms = {}, i, n, v, nv;

		if (query === url || query === "") {
			return;
		}

		for (i = 0; i < pairs.length; i++) {
			nv = pairs[i].split("=");
			n = decodeURIComponent(nv[0]);
			v = decodeURIComponent(nv[1]);

			if (!parms.hasOwnProperty(n)) {
				parms[n] = [];
			}

			parms[n].push(nv.length === 2 ? v : null);
		}
		return parms;
	}
});*/


