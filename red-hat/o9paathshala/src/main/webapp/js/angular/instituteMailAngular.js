var sampleApp2 = angular.module("o9paathshalaMail", []);
sampleApp2.controller('InstituteResendConfirmationMail', function($scope, $http){
	$scope.resend = function(req1, lk1){
			var target = document.getElementById('body');
			var spinner = new Spinner().spin(target);
			$http({
			      method: 'POST',
			      url: 'InstituteResendConfirmationMail',
			      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			      data:  "req="+req1+"&lk="+lk1
			    }).
			  success(function(data, status, headers, config) {
				  spinner.stop();
				  notif({
						msg : data,
						type : 'success',
						position : 'center'
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
	}
});