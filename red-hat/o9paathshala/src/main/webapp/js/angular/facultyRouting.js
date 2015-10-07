var mm;
var sampleApp = angular.module('facultyRouting', ['ui.router.state', 'ui.bootstrap', 'ncy-angular-breadcrumb']);

sampleApp.directive('a', function() {
	return {
		restrict: 'E',
		link: function(scope, elem, attrs) {
			if(attrs.href === '#'||attrs.href === '#mine'|| attrs.href === '#newest' || attrs.href === '#reputed' || attrs.href === '#answered'
				|| attrs.href === '#unanswered' || attrs.href === '#secondTab'|| attrs.href === '#secondTab'){
				elem.on('click', function(e){
					e.preventDefault();
				});
			}
		}
	};
}); 
sampleApp.controller('MainController', function($scope, $location){

	$scope.menuActive = function(page){
		var current = $location.path().substring(1);
		return page === current ? 'active' : '';
	}
});


sampleApp.config(function($breadcrumbProvider) {
	$breadcrumbProvider.setOptions({
		prefixStateName: 'dashboard',
		template: 'bootstrap3'
	});
});



sampleApp.config(function($stateProvider, $urlRouterProvider){
	$urlRouterProvider.otherwise("dashboard");
	$stateProvider
	.state('dashboard', {
		url: "/dashboard",
		templateUrl: "dashboard.html",
		controller : 'dashboardController',
			ncyBreadcrumb: {
			label: 'Dashboard'
			}
	}).
	state('help', {
		url: "/help",
		templateUrl: "help.html",
		ncyBreadcrumb: {
			label: 'Help',
			parent : 'dashboard'
		}
	}).
	state('newtest', {
		url: "/newtest",
		templateUrl: "../common/newtest.html",
		controller : 'newTestController',
			ncyBreadcrumb: {
			label: 'New test',
			parent : 'dashboard'
			}
	}).
	state('switch', {
        url: "/switchinstitute",
        templateUrl: "../common/switch.html",
        controller: 'switchController',
        ncyBreadcrumb: {
            label: 'Switch Institute',
            parent: 'dashboard'
        }
    }).
	state('profile', {
		url: "/myprofile",
		templateUrl: "profile.jsp",
		controller : 'profileController',
			ncyBreadcrumb: {
			label: 'My Profile',
			parent : 'dashboard'
			}
	}).
	state('myquestions', {
		url: "/myquestions",
		templateUrl: "../common/myquestions.html",
		controller : 'myQuestionsController',  
			ncyBreadcrumb: {
			label: 'My questions',
			parent : 'dashboard'
			}
	})
	.state('purchasedquestions', {
		url: "/purchasedquestions",
		templateUrl: "../common/purchasedquestions.html",
		controller : 'purchasedQuestionsController'  ,
			ncyBreadcrumb: {
			label: 'Purchased questions',
			parent : 'dashboard'
			}
	}).
	state('allstudents', {
		url: "/allstudents",
		templateUrl: "allstudents.html",
		controller : 'allStudentsController',
		ncyBreadcrumb: {
			label: 'All students',
			parent : 'dashboard'
		}
	}).
	state('addquestions', {
		url: "/addquestions",
		templateUrl: "../common/addquestions.html",
		controller : 'addQuestionsController',  
			ncyBreadcrumb: {
			label: 'Add questions',
			parent : 'dashboard'
			}
	})
	.state('studentgraph', {
		url: "/studentgraph",
		templateUrl: "../common/studentgraph.html",
		controller :"studentGraphController",
			ncyBreadcrumb: {
			label: 'Student graph',
			parent : 'results'
			}
	}).
	state('deletequestions', {
		url: "/deletequestions",
		templateUrl: "../common/deletequestions.html",
		controller : 'deleteQuestionsController',
			ncyBreadcrumb: {
			label: 'Delete questions',
			parent : 'mytest'
			}
	}).
	state('mytest', {
		url: "/mytest",
		templateUrl: "../common/alltest.html",
		controller : 'myTestController',
			ncyBreadcrumb: {
			label: 'My tests',
			parent : 'dashboard'
			}
	}).
	state('results', {
		url: "/results",
		templateUrl: "../common/results.html",
		controller : 'resultsController',
			ncyBreadcrumb: {
			label: 'Results',
			parent : 'dashboard'
			}
	})
	.state('addquestiontotest', {
		url: "/addquestiontotest",
		templateUrl:"../common/newtest.html",
		controller :"addQuestionToTestController",
			ncyBreadcrumb: {
			label: 'Add questions to test',
			parent : 'mytest'
			}
	}).
	state('edittest', {
		url: "/edittest",
		templateUrl: "../common/edittest.html",
		controller : 'editTestController',
			ncyBreadcrumb: {
			label: 'Edit test',
			parent : 'mytest'
			}
	})
	.state('forum/askquestion', {
		url: "/forum/askquestion",
		templateUrl: "askquestion.html",
		controller : 'askQuestionController',
		ncyBreadcrumb: {
			label: 'Forum > Ask question',
			parent : 'dashboard'
		}
	})
	.state('forum/questions', {
		url: "/forum/questions",
		templateUrl: "questions.html",
		ncyBreadcrumb: {
			label: 'Forum > Questions',
			parent : 'dashboard'
		}
	})
	.state('forum/questions/explored', {
		url: "/forum/questions/explored/:id",
		templateUrl: "question.html",
		ncyBreadcrumb: {
			label: 'Explored',
			parent : 'dashboard/forum/questions'
		}
	})
	.state('forum/tags', {
		url: "/forum/tags",
		templateUrl: "tags.html",
		ncyBreadcrumb: {
			label: 'Forum > Tags',
			parent : 'dashboard'
		}
	});
});
sampleApp.controller('MainController', function($scope, $location){
	$scope.menuActive = function(page){
		var current = $location.path().substring(1);
		return page === current ? 'active' : '';
	};
});
function getBatches($http,$scope){
	$http({
		method:'POST',
		url:'../GetMyBatches',
		headers:{
			'Content-Type':'application/x-www-form-urlencoded'
		}
	}
	).success(function(serverResponse, status, headers, config) {

		if(serverResponse.length>0){

			$scope.options =[];
			for(var i=0;i<serverResponse.length;i++){
				$scope.options.push({label:serverResponse[i].name,value:serverResponse[i].id});
			} 
		}else{
			notif({
				msg : 'You  have not created any batch yet',
				position:'center',
				type : 'success'
			});
		}

	}).error(function(serverResponse, status, headers, config) {
		notif({
			msg:"Some error occured,please try again!!!",
			position:'center'
				});
	}
	);
}
sampleApp.controller("newTestController", function($scope, $http, $location) {
	$scope.load = function() {
		$.getScript("../js/jsFiles/institute/newtest.js");
	};
	$scope.load();
	getBatches($http, $scope);

});
sampleApp.controller("dashboardController", function($scope, $http, $location) {
	$http({
		method:'POST',
		url:'../DashboardFaculty',
		headers:{
			'Content-Type':'application/x-www-form-urlencoded'
		}
	}
	).success(function(serverResponse, status, headers, config) {
		$scope.test=serverResponse.test;
		$scope.student=serverResponse.student;
	}).error(function(serverResponse, status, headers, config) {
		notif({ msg:"Some error occured,please try again!!!",position:'center'});
	}
	);
	
		$('#batchtable').dataTable({
			"bAutoWidth" : false,
			"bPagination":false,
			"bProcessing": true,
			"bDestroy": true,
			"bFilter":false,
			"bSort":false,
			"scrollY":"200px",
			"scrollCollapse":true,
			"paging":false,
			"bServerSide": true,
			"sAjaxSource": "../FacultyBatches",
			"bJQueryUI" : true,
			"bStateSave": true,
			"aoColumns": [
			                               { "mData": "name" },
			                               ],
			                               "oLanguage": {
			                            	   "sLengthMenu": "",
			                            	   "sZeroRecords": "Nothing found - sorry",
			                            	   "sInfo": "Showing _START_ to _END_ of _TOTAL_ records",
			                            	   "sInfoEmpty": "Showing 0 to 0 of 0 records",
			                            	   "sInfoFiltered": ""
			                               }

		});	
		
	});
sampleApp.controller("profileController", function($scope, $http, $location) {
	$scope.load = function() {
		$.getScript("../js/jsFiles/institute/profile.js");
	};
	$scope.load();
	$http({
		method:'POST',
		url:'../FacultyProfile',
		headers:{
			'Content-Type':'application/x-www-form-urlencoded'
		}
	}
	).success(function(serverResponse, status, headers, config) {
		$scope.name=serverResponse.name;
		$scope.email=serverResponse.email;
		$scope.contact=serverResponse.contact;
		$scope.address=serverResponse.address;
		$scope.dob=serverResponse.dob;
		$scope.gender=serverResponse.gender;
	}).error(function(serverResponse, status, headers, config) {
		notif({ msg:"Some error occured,please try again!!!",position:'center'});
	}
	);
});
sampleApp.controller("allStudentsController", function($scope, $http, $location) {

	$scope.load = function() {
		$.getScript("../js/jsFiles/institute/students.js");
	};
	$scope.load();
	$('#studentTable').dataTable({
		"bAutoWidth" : false,
		"bPagination":true,
		"bProcessing": true,
		"bServerSide": true,
		"bSort":false,
		"bDestroy": true,
		"sAjaxSource": "../GetAllStudents",
		"bJQueryUI" : true,
		"fnDrawCallback": function ( oSettings ) {
			/* Need to redo the counters if filtered or sorted */
			if ( oSettings.bSorted || oSettings.bFiltered )
			{
				for ( var i=0, iLen=oSettings.aiDisplay.length ; i<iLen ; i++ )
				{
					$('td:eq(0)', oSettings.aoData[ oSettings.aiDisplay[i] ].nTr ).html( i+1 );
				}
			}
		},
		"aoColumnDefs": [
		                 { "bSortable": true, "aTargets": [ 0 ] },
		                 {
		                	 "aTargets": [ 4 ], // Column to target
		                	 "mRender": function ( data, type, full ) {
		                		 // 'full' is the row's data object, and 'data' is this column's data
		                		 // e.g. 'full[0]' is the comic id, and 'data' is the comic title
		                		 return '<a data-toggle="modal"  data-target="#studentprofile" onclick="viewProfile(\''+full.id+'\')">  View</a>';
		                	 }
		                 }
		                 ],
		                 "aaSorting": [[ 0, 'asc' ]],
		                 "aoColumns": [
		                               { "mData": "email" },
		                               { "mData": "name" },
		                               { "mData": "email" },
		                               {"mData":  "batch"},
		                               { "sWidth": "10%","mData":"email"},
		                               { "mData": "contact"},
		                               ],
		                               "oLanguage": {
		                            	   "sLengthMenu": "Display _MENU_ records per page",
		                            	   "sZeroRecords": "Nothing found - sorry",
		                            	   "sInfo": "Showing _START_ to _END_ of _TOTAL_ records",
		                            	   "sInfoEmpty": "Showing 0 to 0 of 0 records",
		                            	   "sInfoFiltered": "(filtered from _MAX_ total records)"
		                               },
		                               "sDom": 'T<"clear">lfrtip' ,

		                               "oTableTools": {
		                            	   "aButtons": [
		                            	                {
		                            	                	"sExtends":    "collection",
		                            	                	"sButtonText": "Save",
		                            	                	"aButtons":    [ "csv", "xls", "pdf" ]
		                            	                }
		                            	                ],
		                            	                "sSwfPath": "../extras/TableTools/media/swf/copy_csv_xls_pdf.swf"
		                               }  
	});
	$('div.dataTables_filter input').attr("placeholder", "by student name");


});
sampleApp.controller("myTestController", function($scope, $http, $location) {
	$scope.load = function() {
		$.getScript("../js/jsFiles/institute/newtest.js");
	};
	$scope.load();
	$('#allTestTable').dataTable({
		"bAutoWidth" : false,
		"bPagination":true,
		"bProcessing": true,
		"bServerSide": true,
		"bSort":false,
		"bDestroy": true,
		"sAjaxSource": "../GetAllTests",
		"bJQueryUI" : true,
		"aoColumnDefs": [
{
	"aTargets": [ 3 ], // Column to target
	"mRender": function ( data, type, full ) {
               if(null!=full.enddate){
            	   return full.enddate;   
               }else{
            	   return "-";
               }
		
	}
},
{
	"aTargets": [ 8 ], // Column to target
	"mRender": function ( data, type, full ) {
         if(full.activated){
        	
        	 return '<input type="checkbox" class="flat-red" checked id='+full.id+'>';
         }else{
        	 return '<input type="checkbox" class="flat-red" id='+full.id+'>';
         }
	}
},
{
	"aTargets": [ 9 ], // Column to target
	"mRender": function ( data, type, full ) {
		// 'full' is the row's data object, and 'data' is this column's data
		// e.g. 'full[0]' is the comic id, and 'data' is the comic title
		return '<p data-placement="top" data-toggle="tooltip" title="" data-original-title="Edit"><a ui-sref="edittest" href="#/edittest" onclick="editTest(\''+full.id+'\',\''+full.testName+'\',\''+full.duration+'\',\''+full.positiveMark+'\',\''+full.negativeMark+'\',\''+full.startdate+'\',\''+full.enddate+'\')" class="btn btn-primary btn-xs" data-title="Add"><span class="glyphicon glyphicon-pencil"></span></a></p>';
		
	}
},
{
	"aTargets": [ 10 ], // Column to target
	"mRender": function ( data, type, full ) {

		// 'full' is the row's data object, and 'data' is this column's data
		// e.g. 'full[0]' is the comic id, and 'data' is the comic title
		//return '<p data-placement="top" data-toggle="tooltip" title="" data-original-title="add"><a ui-sref="addquestiontotest" href="#/addquestiontotest" onclick="addQuestionToTest(\''+full.testName+'\')" class="btn btn-success btn-xs" data-title="Add"><span class="glyphicon glyphicon-plus"></span></a></p>';
		return '<p data-placement="top" data-toggle="tooltip" title="" data-original-title="add"><a ui-sref="addquestiontotest" href="#/addquestiontotest" onclick="aT(\''+full.testName+'\')" class="btn btn-success btn-xs" data-title="Add"><span class="glyphicon glyphicon-plus"></span></a></p>';
		
	}
},
{
	"aTargets": [ 11 ], // Column to target
	"mRender": function ( data, type, full ) {
		// 'full' is the row's data object, and 'data' is this column's data
		// e.g. 'full[0]' is the comic id, and 'data' is the comic title
		return '<p data-placement="top" data-toggle="tooltip" title="" data-original-title="Delete"><a ui-sref="deletequestions" href="#/deletequestions" onclick="setTestId('+full.id+')" class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete"><span class="glyphicon glyphicon-trash"></span></a></p>';
	}
},
{
	"aTargets": [ 12 ], // Column to target
	"mRender": function ( data, type, full ) {
		// 'full' is the row's data object, and 'data' is this column's data
		// e.g. 'full[0]' is the comic id, and 'data' is the comic title
		return '<p data-placement="top" data-toggle="tooltip" title="" data-original-title="Delete"><button onclick="deleteConfirm('+full.id+')" class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete"><span class="glyphicon glyphicon-trash"></span></button></p>';
	}
}

],
"aaSorting": [[ 0, 'asc' ]],
"aoColumns": [
              { "mData": "testName" },
              { "sWidth": "5%","mData": "duration" },
              { "mData": "startdate" },
              { "mData": "id" },
              { "mData": "uploaddate" },
              {"mData":"createdBy"},
              {  "sWidth": "2%","mData": "positiveMark" },
              {  "sWidth": "2%","mData": "negativeMark" },
              {  "sWidth": "2%","mData": "id" },
              {  "sWidth": "2%","mData": "id" },
              {  "sWidth": "2%","mData": "id" },
              {  "sWidth": "2%","mData": "id" },
              {  "sWidth": "2%","mData": "id" }
              ],
              "oLanguage": {
            	  "sLengthMenu": "Display _MENU_ records per page",
            	  "sZeroRecords": "Nothing found - sorry",
            	  "sInfo": "Showing _START_ to _END_ of _TOTAL_ records",
            	  "sInfoEmpty": "Showing 0 to 0 of 0 records",
            	  "sInfoFiltered": "(filtered from _MAX_ total records)"
              },
              "sDom": 'T<"clear">lfrtip' ,

              "oTableTools": {
            	  "aButtons": [
            	               {
            	            	   "sExtends":    "collection",
            	            	   "sButtonText": "Save",
            	            	   "aButtons":    [ "csv", "xls", "pdf" ]
            	               }
            	               ],
            	               "sSwfPath": "../extras/TableTools/media/swf/copy_csv_xls_pdf.swf"
              }  
	});
	 $('#allTestTable tbody').on('click', ':checkbox', function () {
	        var id = this.id;
	        if($('#'+id).attr('checked')){
	        	updateActivation(id,true);
	        }else{
	        	updateActivation(id,false);
	        }
	       
	        
	    } );
	 $('div.dataTables_filter input').attr("placeholder", "by test name");
		
});
function updateActivation(data,val){
	try{	
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../UpdateActivation", //The URL from the form element where the AJAX request will be sent  
			data:"id="+data+"&value="+val,
			dataType: "json" //The type of response to expect from the server
		}); 
	}
	catch(e){
		notif({
			msg : e,
			position:'center'
		});
	}
}
sampleApp.controller("editTestController", function($scope, $http, $location) {
	$scope.load = function() {
		$.getScript("../js/jsFiles/institute/edittest.js");
	};
	$scope.load();
});
sampleApp.controller("addQuestionToTestController", function($scope, $http, $location) {
	$("#test").hide();
	$("#addq").show();
	$("#box1").hide();
	$("#box2").show();
	
	$.when(addQuestionToTest(mm)).done(function(){
			$scope.load = function() {
				$.getScript("../js/jsFiles/institute/newtest.js");
			};
			$scope.load();
	}
	);
});
sampleApp.controller("deleteQuestionsController", function($scope, $http, $location) {
	$scope.load = function() {
		$.getScript("../js/jsFiles/institute/deletequestions.js");
		
	};
	$scope.load();
});
sampleApp.controller("resultsController", function($scope, $http, $location) {
	$http({
		method:'POST',
		url:'../GetMyBatches',
		headers:{
			'Content-Type':'application/x-www-form-urlencoded'
		}
	}
	).success(function(serverResponse, status, headers, config) {

		if(serverResponse.length>0){
			$scope.options =[];
			for(var i=0;i<serverResponse.length;i++){
				$scope.options.push({label:serverResponse[i].name,value:serverResponse[i].id});
			} 
			$http({
				method:'POST',
				url:'../GetTests',
				headers:{
					'Content-Type':'application/x-www-form-urlencoded'
				}
			}
			).success(function(serverResponse, status, headers, config) {
				if(serverResponse.length>0){
					$scope.options1 =[];
					$scope.options1.push({label:"All",value:0});
					for(var i=0;i<serverResponse.length;i++){
						$scope.options1.push({label:serverResponse[i].testName,value:serverResponse[i].id});
					} 
					$scope.load = function() {
						$.getScript("../js/jsFiles/institute/results.js");
					};
					$scope.load();
				}

			}).error(function(serverResponse, status, headers, config) {
				notif({ msg:"Some error occured,please try again!!!",position:centre});
			}
			);
		}

	}).error(function(serverResponse, status, headers, config) {
		notif({ msg:"Some error occured,please try again!!!",position:centre});
	}
	);
	
	
	
});

sampleApp.controller("switchController", function($scope, $http, $location) {
	$http({
		method:'POST',
		url:'../GetMyInstitutes',
		headers:{
			'Content-Type':'application/x-www-form-urlencoded'
		}
	}
	).success(function(serverResponse, status, headers, config) {

		if(serverResponse.length>0){
			$scope.options =[];
			for(var i=0;i<serverResponse.length;i++){
				$scope.options.push({label:serverResponse[i].name,value:serverResponse[i].id});
			} 
		}
		$scope.load = function() {
			$.getScript("../js/jsFiles/institute/switch.js");
		};
		$scope.load();
	})
	.error(function(serverResponse, status, headers, config) {
		notif({ msg:"Some error occured,please try again!!!",position:centre});
	}
	);
	
	
	
});
sampleApp.controller("studentGraphController", function($scope, $http, $location) {
	$scope.load = function() {
		$.getScript("../js/jsFiles/institute/studentgraph.js");
		$.getScript("../js/plugins/canvas/Chart.js");
	};
	$scope.load();
	
});
sampleApp.controller("myQuestionsController", function($scope, $http, $location) {
	$http({
		method:'POST',
		url:'../GetSubjects',
		headers:{
			'Content-Type':'application/x-www-form-urlencoded'
		}
	}
	).success(function(serverResponse, status, headers, config) {
		if(serverResponse.length>0){
			$scope.options =[];
			for(var i=0;i<serverResponse.length;i++){
				$scope.options.push({label:serverResponse[i].name,value:serverResponse[i].id});
			} 
			$scope.load = function() {
				$.getScript("../js/jsFiles/institute/myquestion.js");
			};
			$scope.load();
		}else{
			notif({ msg:"You have no questions ...",position:centre});
		}

	}).error(function(serverResponse, status, headers, config) {
		notif({ msg:"Some error occured,please try again!!!",position:centre});
	}
	);
});
sampleApp.controller("purchasedQuestionsController", function($scope, $http, $location) {
	$http({
		method:'POST',
		url:'../Geto9Subjects',
		headers:{
			'Content-Type':'application/x-www-form-urlencoded'
		}
	}
	).success(function(serverResponse, status, headers, config) {
		if(serverResponse.length>0){
			$scope.options =[];
			for(var i=0;i<serverResponse.length;i++){
				$scope.options.push({label:serverResponse[i].name,value:serverResponse[i].id});
			} 
			$scope.load = function() {
				$.getScript("../js/jsFiles/institute/purchasedquestion.js");
			};
			$scope.load();
		}

	}).error(function(serverResponse, status, headers, config) {
		notif({ msg:"Some error occured,please try again!!!",position:centre});
	}
	);
});

sampleApp.controller("addQuestionsController", function($scope, $http, $location) {
	$http({
		method:'POST',
		url:'../GetSubjects',
		headers:{
			'Content-Type':'application/x-www-form-urlencoded'
		}
	}
	).success(function(serverResponse, status, headers, config) {
		if(serverResponse.length>0){
			$scope.options =[];
			for(var i=0;i<serverResponse.length;i++){
				$scope.options.push({label:serverResponse[i].name,value:serverResponse[i].id});
			} 
			$scope.load = function() {
				$.getScript("../js/jsFiles/institute/addquestion.js");
			};
			$scope.load();
		}

	}).error(function(serverResponse, status, headers, config) {
		notif({ msg:"Some error occured,please try again!!!",position:centre});
	}
	);
});



/**
 * discussion forum
 */


sampleApp.controller('questionController', function($scope,$http, $location, $modal){

	$scope.answers = [];
	$scope.noMoreAnswerFound = false;
	$scope.oneAtATime = false;
//	answer text area creator
	$(".answerTextarea").wysihtml5({
		"image": false
	});

	$scope.searchType = 'Title';
	$scope.changeSearchType = function(current){
		if(current == 'title'){
			$scope.searchType = 'Title';
		}
		else if(current == 'tags'){
			$scope.searchType = 'Tags';
		}
	};


	var currentTab = "mine";
	$scope.mineQuestions = [{
		index : 0,
		busy : false,
		after : 0,
		liked : false,
		noMoreQuestion : false,
		loadQuestionText : 'Load more questions...'
	}];
	$scope.answeredQuestions = [{
		index : 0,
		busy : false,
		after : 0,
		liked : false,
		noMoreQuestion : false,
		loadQuestionText : 'Load more questions...'
	}];
	$scope.unansweredQuestions = [{
		index : 0,
		busy : false,
		after : 0,
		liked : false,
		noMoreQuestion : false,
		loadQuestionText : 'Load more questions...'
	}];
	$scope.reputedQuestions = [{
		index : 0,
		busy : false,
		after : 0,
		liked : false,
		noMoreQuestion : false,
		loadQuestionText : 'Load more questions...'
	}];
	$scope.newestQuestions = [{
		index : 0,
		busy : false,
		after : 0,
		liked : false,
		noMoreQuestion : false,
		loadQuestionText : 'Load more questions...'
	}];

	var isFirstLoad = [true, true,true,true,true];
	$scope.tabChanged = function(tab){

		currentTab = tab;
		switch (tab) {
		case "mine":
			if(isFirstLoad[0]){
				$scope.mineQuestionNextPage();
				isFirstLoad[0] = false;
			}
			break;
		case "newest":
			if(isFirstLoad[1]){
				$scope.newestQuestionNextPage();
				isFirstLoad[1] = false;
			}
			break;
		case "answered":
			if(isFirstLoad[2]){
				$scope.answeredQuestionNextPage();
				isFirstLoad[2] = false;
			}
			break;
		case "unanswered":
			if(isFirstLoad[3]){
				$scope.unansweredQuestionNextPage();
				isFirstLoad[3] = false;
			}
			break;
		case "reputed":
			if(isFirstLoad[4]){
				$scope.reputedQuestionNextPage();
				isFirstLoad[4] = false;
				break;
			}
		}
	};


	$scope.loadMoreQuestions = function(tab){
		switch (tab) {
		case "mine":
			$scope.mineQuestionNextPage();
			break;
		case "newest":
			$scope.newestQuestionNextPage();
			break;
		case "answered":
			$scope.answeredQuestionNextPage();
			break; 
		case "unanswered":
			$scope.unansweredQuestionNextPage();
			break;
		case "reputed":
			$scope.reputedQuestionNextPage();
			break;
		}
	};
	
	$scope.mineQuestionNextPage = function(){
		if(!$scope.mineQuestions[0].busy && currentTab == "mine"){
			$scope.mineQuestions[0].busy = true;
			var target = document.getElementById('content');
			var spinner = new Spinner().spin(target);
			$scope.mineQuestions[0].loadQuestionText = 'loading...';
			$http({
				method: 'POST',
				url: '../GetQuestions',
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				data:  "sort="+currentTab+"&limit="+"10"+"&after="+$scope.mineQuestions[0].after+"&like="+""
			}).
			success(function(data, status, headers, config) {
				if(data == ""){
					$scope.mineQuestions[0].noMoreQuestion = true;
					$scope.mineQuestions[0].loadQuestionText = 'Load more questions...';
					spinner.stop();
					return;
				}
				var i =0 ;
				for(i; i < data.length; i++){
					$scope.mineQuestions.push({
						id : data[i].id,
						title : data[i].title,
						content : data[i].content,
						reputation : data[i].reputation,
						date : data[i].time ,
						tags : data[i].tags,
						user : data[i].userName,
						answers : data[i].answers,
						userId : data[i].userId,
						liked : (data[i].liked == true ? 1 : 0),
						index : $scope.mineQuestions[$scope.mineQuestions.length-1].index +1 
					});

				}
				$scope.mineQuestions[0].busy = false;
				$scope.mineQuestions[0].after = $scope.mineQuestions[0].after + 10;
				$scope.mineQuestions[0].loadQuestionText = 'Load more questions...';
				spinner.stop();
			}).
			error(function(data, status, headers, config) {
				$scope.mineQuestions[0].busy = false;
				$scope.mineQuestions[0].loadQuestionText = 'Load more questions...';
				spinner.stop();
			});
		}
	};
	$scope.answeredQuestionNextPage = function(){
		if(!$scope.answeredQuestions[0].busy && currentTab == "answered"){
			var target = document.getElementById('content');
			var spinner = new Spinner().spin(target);
			$scope.answeredQuestions[0].loadQuestionText = 'loading...';
			$scope.answeredQuestions[0].busy = true;
			$http({
				method: 'POST',
				url: '../GetQuestions',
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				data:  "sort="+currentTab+"&limit="+"10"+"&after="+$scope.answeredQuestions[0].after+"&like="+""
			}).
			success(function(data, status, headers, config) {
				if(data == ""){
					$scope.answeredQuestions[0].noMoreQuestion = true;
					$scope.answeredQuestions[0].loadQuestionText = 'Load more questions...';
					spinner.stop();
					return;
				}
				var i =0 ;
				for(i; i < data.length; i++){
					$scope.answeredQuestions.push({
						id : data[i].id,
						title : data[i].title,
						content : data[i].content,
						reputation : data[i].reputation,
						date : data[i].time ,
						tags : data[i].tags,
						user : data[i].userName,
						answers : data[i].answers,
						userId : data[i].userId,
						liked : (data[i].liked == true ? 1 : 0),
						index : $scope.answeredQuestions[$scope.answeredQuestions.length-1].index +1
					});

				}
				$scope.answeredQuestions[0].busy = false;
				$scope.answeredQuestions[0].after = $scope.answeredQuestions[0].after + 10;
				$scope.answeredQuestions[0].loadQuestionText = 'Load more questions...';
				spinner.stop();
			}).
			error(function(data, status, headers, config) {
				$scope.answeredQuestions[0].busy = false;
				$scope.answeredQuestions[0].loadQuestionText = 'Load more questions...';
				spinner.stop();
			});
		}
	};
	$scope.unansweredQuestionNextPage = function(){
		if(!$scope.unansweredQuestions[0].busy  && currentTab == "unanswered"){
			var target = document.getElementById('content');
			var spinner = new Spinner().spin(target);
			$scope.unansweredQuestions[0].loadQuestionText = 'loading...';
			$scope.unansweredQuestions[0].busy = true;
			$http({
				method: 'POST',
				url: '../GetQuestions',
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				data:  "sort="+currentTab+"&limit="+"10"+"&after="+$scope.unansweredQuestions[0].after+"&like="+""
			}).
			success(function(data, status, headers, config) {
				if(data == ""){
					$scope.unansweredQuestions[0].noMoreQuestion = true;
					$scope.unansweredQuestions[0].loadQuestionText = 'Load more questions...';
					spinner.stop();
					return;
				}
				var i =0 ;
				for(i; i < data.length; i++){
					$scope.unansweredQuestions.push({
						id : data[i].id,
						title : data[i].title,
						content : data[i].content,
						reputation : data[i].reputation,
						date : data[i].time ,
						tags : data[i].tags,
						user : data[i].userName,
						answers : data[i].answers,
						userId : data[i].userId,
						liked : (data[i].liked == true ? 1 : 0),
						index : $scope.unansweredQuestions[$scope.unansweredQuestions.length-1].index +1
					});
				}
				$scope.unansweredQuestions[0].busy = false;
				$scope.unansweredQuestions[0].after = $scope.unansweredQuestions[0].after + 10;
				$scope.unansweredQuestions[0].loadQuestionText = 'Load more questions...';
				spinner.stop();
			}).
			error(function(data, status, headers, config) {
				$scope.unansweredQuestions[0].busy = false;
				$scope.unansweredQuestions[0].loadQuestionText = 'Load more questions...';
				spinner.stop();
			});
		}
	};
	$scope.reputedQuestionNextPage = function(){
		if(!$scope.reputedQuestions[0].busy  && currentTab == "reputed"){
			var target = document.getElementById('content');
			var spinner = new Spinner().spin(target);
			$scope.reputedQuestions[0].loadQuestionText = 'loading...';
			$scope.reputedQuestions[0].busy = true;
			$http({
				method: 'POST',
				url: '../GetQuestions',
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				data:  "sort="+currentTab+"&limit="+"10"+"&after="+$scope.reputedQuestions[0].after+"&like="+""
			}).
			success(function(data, status, headers, config) {
				if(data == ""){
					$scope.reputedQuestions[0].noMoreQuestion = true;
					$scope.reputedQuestions[0].loadQuestionText = 'Load more questions...';
					spinner.stop();
					return;
				}
				var i =0 ;
				for(i; i < data.length; i++){
					$scope.reputedQuestions.push({
						id : data[i].id,
						title : data[i].title,
						content : data[i].content,
						reputation : data[i].reputation,
						date : data[i].time ,
						tags : data[i].tags,
						user : data[i].userName,
						answers : data[i].answers,
						userId : data[i].userId,
						liked : (data[i].liked == true ? 1 : 0),
						index : $scope.reputedQuestions[$scope.reputedQuestions.length-1].index +1
					});
				}
				$scope.reputedQuestions[0].busy = false;
				$scope.reputedQuestions[0].after = $scope.reputedQuestions[0].after + 10;
				$scope.reputedQuestions[0].loadQuestionText = 'Load more questions...';
				spinner.stop();
			}).
			error(function(data, status, headers, config) {
				$scope.reputedQuestions[0].busy = false;
				$scope.reputedQuestions[0].loadQuestionText = 'Load more questions...';
				spinner.stop();
			});
		}
	};
	$scope.newestQuestionNextPage = function(){
		if(!$scope.newestQuestions[0].busy  && currentTab == "newest"){
			var target = document.getElementById('content');
			var spinner = new Spinner().spin(target);
			$scope.newestQuestions[0].loadQuestionText = 'loading...';
			$scope.newestQuestions[0].busy = true;
			$http({
				method: 'POST',
				url: '../GetQuestions',
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				data:  "sort="+currentTab+"&limit="+"10"+"&after="+$scope.newestQuestions[0].after+"&like="+""
			}).
			success(function(data, status, headers, config) {
				if(data == ""){
					$scope.newestQuestions[0].noMoreQuestion = true;
					$scope.newestQuestions[0].loadQuestionText = 'Load more questions...';
					spinner.stop();
					return;
				}
				var i =0 ;
				for(i; i < data.length; i++){
					$scope.newestQuestions.push({
						id : data[i].id,
						title : data[i].title,
						content : data[i].content,
						reputation : data[i].reputation,
						date : data[i].time ,
						tags : data[i].tags,
						user : data[i].userName,
						answers : data[i].answers,
						userId : data[i].userId,
						liked : (data[i].liked == true ? 1 : 0),
						index :  $scope.newestQuestions[$scope.newestQuestions.length-1].index +1
					});
				}
				$scope.newestQuestions[0].busy = false;
				$scope.newestQuestions[0].after = $scope.newestQuestions[0].after + 10;
				$scope.newestQuestions[0].loadQuestionText = 'Load more questions...';
				spinner.stop();
			}).
			error(function(data, status, headers, config) {
				$scope.newestQuestions[0].busy = false;
				$scope.newestQuestions[0].loadQuestionText = 'Load more questions...';
				spinner.stop();
			});
		}
	};

	$scope.liked = function(question, index, tab,context){
		var changedValue = true;
		switch (tab) {
		case "mine":
			changedValue = !$scope.mineQuestions[index].liked;
			break;
		case "newest":
			changedValue= !$scope.newestQuestions[index].liked;
			break;
		case "answered":
			changedValue= !$scope.answeredQuestions[index].liked;
			break;
		case "unanswered":
			changedValue= !$scope.unansweredQuestions[index].liked;
			break;
		case "reputed":
			changedValue= !$scope.reputedQuestions[index].liked;
			break;
		default : 
			changedValue= !$scope.answers[index].liked;
		break;
		}
		var target = document.getElementById('content');
		var spinner = new Spinner().spin(target);
		$http({
			method: 'POST',
			url: '../UpdateLike',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			data:  "liked="+changedValue+"&questionId="+question+"&context="+context
		}).
		success(function(data, status, headers, config) {
			spinner.stop();
		}).
		error(function(data, status, headers, config) {
			spinner.stop();
		});
	};




	$scope.searchQuestion = function(){
		/*var target = document.getElementById('content');
		var spinner = new Spinner().spin(target);
		$scope.answeredQuestions[0].loadQuestionText = 'loading...';
		$scope.answeredQuestions[0].busy = true;
		$http({
		      method: 'POST',
		      url: 'GetQuestions',
		      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		      data:  "sort="+currentTab+"&limit="+"10"+"&after="+0+"&like="+$scope.search
		    }).
		  success(function(data, status, headers, config) {
			  if(data == ""){
				  $scope.answeredQuestions[0].noMoreQuestion = true;
				  $scope.answeredQuestions[0].loadQuestionText = 'Load more questions...';
				  spinner.stop();
				  $scope.answeredQuestions=[];
				  return;
			  }
			  var i =0 ;
			  for(i; i < data.length; i++){
				  $scope.answeredQuestions[i]=({
					  id : data[i].id,
					  title : data[i].title,
					  content : data[i].content,
					  reputation : data[i].reputation,
					  date : data[i].time ,
					  tags : ["ahdskjf", "aksjdfhk","askdjfjsdjfjsdf"],
					  user : data[i].userName,
					  answers : data[i].answers,
					  userId : data[i].userId,
					  liked : (data[i].liked == true ? 1 : 0),
					  index : $scope.answeredQuestions[$scope.answeredQuestions.length-1].index +1
				  });

			  }
			  $scope.answeredQuestions[0].busy = false;
			  $scope.answeredQuestions[0].after = $scope.answeredQuestions[0].after + 10;
			  $scope.answeredQuestions[0].loadQuestionText = 'Load more questions...';
			  spinner.stop();
		  }).
		  error(function(data, status, headers, config) {
			  $scope.answeredQuestions[0].busy = false;
			  $scope.answeredQuestions[0].loadQuestionText = 'Load more questions...';
			  spinner.stop();
		  });*/
	};

	var questionIdModal = "";

	$scope.reply = function() {
		var answer= $('.answerTextarea').val();
		var target = document.getElementById('content');
		var spinner = new Spinner().spin(target);
		$http({
			method: 'POST',
			url: '../PostAnswer',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			data:  "answer="+answer+"&question="+questionIdModal
		}).
		success(function(data, status, headers, config) {
			spinner.stop();
			notif({
				msg : data,
				position:'center',
				type : 'success'
			});
		}).
		error(function(data, status, headers, config) {
			spinner.stop();
			notif({
				msg : data,
				position:'center'
			});
		});

	};

	$scope.setQuestionForModal = function(question){
		questionIdModal = question;
	}

	$scope.refreshPage = function(){
		$scope.mineQuestions = [{
			index : 0,
			busy : false,
			after : 0,
			liked : false,
			noMoreQuestion : false,
			loadQuestionText : 'Load more questions...'
		}];
		$scope.answeredQuestions = [{
			index : 0,
			busy : false,
			after : 0,
			liked : false,
			noMoreQuestion : false,
			loadQuestionText : 'Load more questions...'
		}];
		$scope.unansweredQuestions = [{
			index : 0,
			busy : false,
			after : 0,
			liked : false,
			noMoreQuestion : false,
			loadQuestionText : 'Load more questions...'
		}];
		$scope.reputedQuestions = [{
			index : 0,
			busy : false,
			after : 0,
			liked : false,
			noMoreQuestion : false,
			loadQuestionText : 'Load more questions...'
		}];
		$scope.newestQuestions = [{
			index : 0,
			busy : false,
			after : 0,
			liked : false,
			noMoreQuestion : false,
			loadQuestionText : 'Load more questions...'
		}];
		switch (currentTab) {
		case "mine":
			$scope.mineQuestionNextPage();
			break;
		case "newest":
			$scope.newestQuestionNextPage();
			break;
		case "answered":
			$scope.answeredQuestionNextPage();
			break; 
		case "unanswered":
			$scope.unansweredQuestionNextPage();
			break;
		case "reputed":
			$scope.reputedQuestionNextPage();
			break;
		}
	}

	$scope.forAnswerModal = function(question){
		questionIdModal = question;
		var target = document.getElementById('content');
		var spinner = new Spinner().spin(target);
		$http({
			method: 'POST',
			url: '../GetAnswers',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			data:  "question="+question
		}).
		success(function(data, status, headers, config) {
			$scope.answers = [];
			if(data == ""){
				$scope.noMoreAnswerFound = true;
				spinner.stop();
				$('#showAnswerModal').modal({});
				return;
			}
			var i =0 ;
			$scope.noMoreAnswerFound = false;
			for(i; i < data.length; i++){
				$scope.answers.push({
					id : data[i].answerId,
					content : data[i].answer,
					reputation : data[i].reputation,
					date : data[i].time ,
					user : data[i].username,
					userId : data[i].userId,
					liked : (data[i].liked == true ? 1 : 0)
				});
			}
			spinner.stop();
			$('#showAnswerModal').modal({});
		}).
		error(function(data, status, headers, config) {
			spinner.stop();
			notif({
				msg : data,
				position:'center'
			});
		});
	};

	$scope.mineQuestionNextPage();

});

sampleApp.controller('exploredQuestion', function($scope,$http, $stateParams, $timeout){
	$(".answerTextarea").wysihtml5({
		"image": false
	});
	$scope.exploredQuestion = {
			id : '',
			title : '',
			content : '',
			reputation : 0,
			date : '',
			tags : [],
			user : '',
			answers : 0,
			userId : 0,
			liked : false,
			index : 0
	};

	$scope.answersForExplored = [];

	$scope.noAnyAnswerFound = false;
	$scope.getDataForExploredQuestion = function(){
		var target = document.getElementById('content');
		var spinner = new Spinner().spin(target);
		$http({
			method: 'POST',
			url: '../GetExploredQuestion',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			data:  "question="+$stateParams.id
		}).
		success(function(data, status, headers, config) {
			if((data.answers.length <= 0)){
				$scope.noAnyAnswerFound = true;
			}
			$scope.exploredQuestion = {
					id : data.question.id,
					title : data.question.title,
					content : data.question.content,
					reputation : data.question.reputation,
					date : data.question.time ,
					tags : data.question.tags,
					user : data.question.userName,
					answers : data.question.answers,
					userId : data.question.userId,
					liked : (data.question.liked == true ? 1 : 0)

			}
			var i =0 ;
			for(i; i < data.answers.length; i++){
				$scope.answersForExplored.push({
					id : data.answers[i].answerId,
					content : data.answers[i].answer,
					reputation : data.answers[i].reputation,
					date : data.answers[i].time ,
					user : data.answers[i].username,
					userId : data.answers[i].userId,
					liked : (data.answers[i].liked == true ? 1 : 0)
				});
			}

			spinner.stop();
		}).
		error(function(data, status, headers, config) {
			spinner.stop();
			notif({
				msg : data,
				position:'center'
			});
		});

		$scope.reply = function() {
			var answer= $('.answerTextarea').val();
			var target = document.getElementById('content');
			var spinner = new Spinner().spin(target);
			$http({
				method: 'POST',
				url: '../PostAnswer',
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				data:  "answer="+answer+"&question="+$scope.exploredQuestion.id
			}).
			success(function(data, status, headers, config) {
				spinner.stop();
				notif({
					msg : data,
					position:'center',
					type : 'success'
				});
			}).
			error(function(data, status, headers, config) {
				spinner.stop();
				notif({
					msg : data,
					position:'center'
				});
			});
		};

	};

	var changedValue = false;
	$scope.liked = function(question, index,context){
		if(context == 'question'){
			changedValue = !$scope.exploredQuestion.liked;
		}
		else{
			changedValue = !$scope.answersForExplored[index].liked;
		}

		var target = document.getElementById('content');
		var spinner = new Spinner().spin(target);
		$http({
			method: 'POST',
			url: '../UpdateLike',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			data:  "liked="+changedValue+"&questionId="+question+"&context="+context
		}).
		success(function(data, status, headers, config) {
			spinner.stop();
		}).
		error(function(data, status, headers, config) {
			spinner.stop();
		});
	};


	$timeout( function(){ $scope.getDataForExploredQuestion(); }, 0);
});

sampleApp.controller('askQuestionController', function($scope ,$window, $http, $timeout) {

	$(".questionTextarea").wysihtml5({
		"image": false
	});

	$scope.load = function() {
		$.getScript("js/group_config.js");
		//don't forget to call the load function
		$scope.load();
	}
	$scope.question = {};
	$scope.question.title = '';
//	function to submit the form after all validation has occurred			
	$scope.submitForm = function() {
		var content = $('.questionTextarea').val();
		var questionTitle = $scope.question.title;
		if(!questionTitle.length > 0){
			notif({
				msg : "Question Title is missing...",
				position:'center'
			});
			return;
		}

		if(!content.length > 0){
			notif({
				msg : "Question description is missing...",
				position:'center'
			});
			return;
		}
		// check to make sure the form is completely valid
		var target = document.getElementById('content');
		var spinner = new Spinner().spin(target);
		$http({
			method: 'POST',
			url: '../PostQuestion',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			data:  "title="+questionTitle+"&content="+content+"&tags="+$scope.tagIds
		}).
		success(function(data, status, headers, config) {
			spinner.stop();
			notif({
				msg : data,
				position:'center',
				type : 'success'
			});
		}).
		error(function(data, status, headers, config) {
			spinner.stop();
			notif({
				msg : data,
				position:'center'
			});
		});
	};
	$scope.changeInTag = function(tag){
		var target = document.getElementById('content');
		var spinner = new Spinner().spin(target);
		$http({
			method: 'POST',
			url: '../GetTags',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			data:  "like="+tag+"&limit=5"
		}).
		success(function(data, status, headers, config) {
			$('#tagPopover').popover("destroy");
			$('#tagPopover').popover({
				html : true,
				template: '<div class="popover "><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"> </div> </div>',
				trigger: 'manual',
				placement: 'top',

				content: function() {
					if(data.length==0){
						return "No tags found...";
					}
					var message = '<div class="list-group"> ';
					var theme = ["danger","info","warning","success"];
					var arrayLength = data.length;
					var i = 0;
					for (i; i < arrayLength; i++) {
						message = message + '<a  onclick="return attachTag(\''+data[i].tagName+'\',\''+theme[i%4]+'\',\''+data[i].tagId+'\');" \
						data-toggle="tooltip" data-placement="top" title="'+data[i].tagDesc+'" class=" fakeLink list-group-item list-group-item-'+theme[i%4]+'"> '+data[i].tagName+' <span class="badge">\
						'+data[i].tagReputation+'</span></a> ';
					}
					return message;
				}
			});
			spinner.stop();
			$('#tagPopover').popover("show");

		}).
		error(function(data, status, headers, config) {
			spinner.stop();
			notif({
				msg : data,
				position:'center'
			});
		});
	};

});

sampleApp.controller('tagsController', function($scope ,$window, $http, $timeout) {

	$scope.tags = {};
	$scope.loadTags = function(){
		$('#tagList').dataTable({
		"bAutoWidth" : false,
		"bPagination":true,
		"bProcessing": true,
		"bServerSide": true,
		"bDestroy": true,
		"bSort":false,
		"sAjaxSource": "../GetAllTags",
		"bJQueryUI" : true,
		"fnDrawCallback": function ( oSettings ) {
			/* Need to redo the counters if filtered or sorted */
			if ( oSettings.bSorted || oSettings.bFiltered )
			{
				for ( var i=0, iLen=oSettings.aiDisplay.length ; i<iLen ; i++ )
				{
					$('td:eq(0)', oSettings.aoData[ oSettings.aiDisplay[i] ].nTr ).html( i+1 );
				}
			}
		},
		"aoColumnDefs": [
		                 { "bSortable": false, "aTargets": [ 0 ] },
		                 {
		                	 "aTargets": [ 4 ], // Column to target
		                	 "mRender": function ( data, type, full ) {

		                		 // 'full' is the row's data object, and 'data' is this column's data
		                		 // e.g. 'full[0]' is the comic id, and 'data' is the comic title
		                		 return '<p data-placement="top" data-toggle="tooltip" title="" data-original-title="Edit"><button onclick="startUpdationOfTag(\''+full.tagName+'\',\''+full.tagId+'\',\''+full.tagDesc+'\')" class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#editTag"><span class="glyphicon glyphicon-pencil"></span></button></p>';
		                	 }
		                 },
		                 {
		                	 "aTargets": [ 5 ], // Column to target
		                	 "mRender": function ( data, type, full ) {
		                		 // 'full' is the row's data object, and 'data' is this column's data
		                		 // e.g. 'full[0]' is the comic id, and 'data' is the comic title
		                		 return '<p data-placement="top" data-toggle="tooltip" title="" data-original-title="Delete"><button onclick="setTagForDelete(\''+full.tagId+'\')" class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#deleteTag"><span class="glyphicon glyphicon-trash"></span></button></p>';
		                	 }
		                 }

		                 ],
		                 "aaSorting": [[ 0, 'asc' ]],
		                 "aoColumns": [
		                               { "mData": "tagId" },
		                               { "mData": "tagName" },
		                               { "mData": "tagDesc"},
		                               { "mData": "tagReputation"},
		                               { "mData": "tagReputation"},
		                               { "mData": "tagReputation"}
		                               ],
		                               "oLanguage": {
		                            	   "sLengthMenu": "Display _MENU_ records per page",
		                            	   "sZeroRecords": "Nothing found - sorry",
		                            	   "sInfo": "Showing _START_ to _END_ of _TOTAL_ records",
		                            	   "sInfoEmpty": "Showing 0 to 0 of 0 records",
		                            	   "sInfoFiltered": "(filtered from _MAX_ total records)"
		                               },
		                               "sDom": 'T<"clear">lfrtip' ,

		                               "oTableTools": {
		                            	   "aButtons": [
		                            	                {
		                            	                	"sExtends":    "collection",
		                            	                	"sButtonText": "Save",
		                            	                	"aButtons":    [ "csv", "xls", "pdf" ]
		                            	                }
		                            	                ],
		                            	                "sSwfPath": "../extras/TableTools/media/swf/copy_csv_xls_pdf.swf"
		                               }  
	});
		$('div.dataTables_filter input').attr("placeholder", "by tag");
		
	}

	$scope.loadTags();
	
	$scope.currentTagId = 0 ;
	//	function to submit the form after all validation has occurred	
	
	
	$scope.deleteTag = function(){
		var tagId = $('#tagIdForDelete').val();
		var target = document.getElementById('content');
		var spinner = new Spinner().spin(target);
		$http({
			method: 'POST',
			url: '../DeleteTag',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			data:  "id="+tagId
		}).
		success(function(data, status, headers, config) {
			spinner.stop();
			notif({
				msg : data,
				position:'center',
				type : 'success'
			});
			$scope.loadTags();
		}).
		error(function(data, status, headers, config) {
			spinner.stop();
			notif({
				msg : data,
				position:'center'
			});
		});
	}
	
	$scope.updateTag = function() {
		$scope.currentTagId = $('#id').val();
		var tagName = $('#tagName').val();
		var tagDesc = $('#tagDesc').val();
		var target = document.getElementById('content');
		var spinner = new Spinner().spin(target);
		$http({
			method: 'POST',
			url: '../UpdateTag',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			data:  "id="+$scope.currentTagId+"&tagName="+tagName+"&tagDesc="+tagDesc
		}).
		success(function(data, status, headers, config) {
			spinner.stop();
			notif({
				msg : data,
				position:'center',
				type : 'success'
			});
			$scope.loadTags();
		}).
		error(function(data, status, headers, config) {
			spinner.stop();
			notif({
				msg : data,
				position:'center'
			});
		});
	};
	
	$scope.addTag = function(){
		var tagName = $('#newTagName').val();
		var tagDesc = $('#newTagDesc').val();
		var target = document.getElementById('content');
		var spinner = new Spinner().spin(target);
		$http({
			method: 'POST',
			url: '../AddTag',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			data:  "tagName="+tagName+"&tagDesc="+tagDesc
		}).
		success(function(data, status, headers, config) {
			spinner.stop();
			notif({
				msg : data,
				position:'center',
				type : 'success'
			});
			$scope.loadTags();
		}).
		error(function(data, status, headers, config) {
			spinner.stop();
			notif({
				msg : data,
				position:'center'
			});
		});
	};
});
function editTest(id,name,duration,pmark,nmark,sdate,edate){
	$("#tid").val(id);
	$("#tname").val(name);
	$("#tduration").val(duration);
	$("#tpmark").val(pmark);
	$("#tnmark").val(nmark);
	$("#tsdate").val(sdate);
	$("#tedate").val(edate);


}
function aT(data){
	mm=data;
}
