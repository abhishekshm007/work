/**
 * 
 */'use strict'

var sampleApp = angular.module("o9paathshalaApp", ['ui.router.state', 'ui.bootstrap', 'ncy-angular-breadcrumb', 'infinite-scroll']);

sampleApp.config(function($breadcrumbProvider) {
    $breadcrumbProvider.setOptions({
      prefixStateName: 'dashboard',
      template: 'bootstrap3'
    });
  });

sampleApp.config(function($stateProvider, $urlRouterProvider) {
  //
  // For any unmatched url, redirect to /state1
  $urlRouterProvider.otherwise("dashboard");
  //
  // Now set up the states
  $stateProvider
  .state('dashboard', {
      url: "/dashboard",
    
      ncyBreadcrumb: {
          label: 'Dashboard'
        },
    })
    .state('dashboard/forum/askquestion', {
      url: "/dashboard/forum/askquestion",
      templateUrl: "dashboard/group/askquestion.jsp",
    	  controller : 'askQuestionController',
    	  ncyBreadcrumb: {
              label: 'Forum > Ask question',
              parent : 'dashboard'
            },
    })
    .state('dashboard/forum/questions', {
      url: "/dashboard/forum/questions",
      templateUrl: "dashboard/group/questions.jsp",
    	  ncyBreadcrumb: {
              label: 'Forum > Questions',
              parent : 'dashboard'
            }
    })
    .state('dashboard/forum/questions/explored', {
      url: "/dashboard/forum/questions/explored/:id",
      templateUrl: "dashboard/group/question.jsp",
    	  ncyBreadcrumb: {
              label: 'Explored',
              parent : 'dashboard/forum/questions'
            }
    })
    ;
});

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

sampleApp.controller('questionController', function($scope,$http, $location, $modal){

	$scope.answers = [];
	$scope.noMoreAnswerFound = false;
    $scope.oneAtATime = false;
	// answer text area creator
	$(".answerTextarea").wysihtml5({
			"image": false,
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
	
	$scope.mineQuestionNextPage = function(){
		if(!$scope.mineQuestions[0].busy && currentTab == "mine"){
		$scope.mineQuestions[0].busy = true;
		var target = document.getElementById('content');
		var spinner = new Spinner().spin(target);
		$scope.mineQuestions[0].loadQuestionText = 'loading...';
		$http({
		      method: 'POST',
		      url: 'GetQuestions',
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
		      url: 'GetQuestions',
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
		      url: 'GetQuestions',
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
		      url: 'GetQuestions',
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
		      url: 'GetQuestions',
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
		      url: 'UpdateLike',
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
		      url: 'PostAnswer',
		      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		      data:  "answer="+answer+"&question="+questionIdModal
		    }).
		  success(function(data, status, headers, config) {
			  spinner.stop();
			  notif({
					msg : data,
					type : 'success'
				});
		  }).
		  error(function(data, status, headers, config) {
			  spinner.stop();
			  notif({
					msg : data,
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
	
	$scope.showAnswerModal = function(question){
		questionIdModal = question;
		var target = document.getElementById('content');
		var spinner = new Spinner().spin(target);
		$http({
			method: 'POST',
			url: 'GetAnswers',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			data:  "question="+question
		}).
		success(function(data, status, headers, config) {
			$scope.answers = [];
			if(data == ""){
				$scope.noMoreAnswerFound = true;
				spinner.stop();
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
					liked : (data[i].liked == true ? 1 : 0),
				});
			}
			spinner.stop();
		}).
		error(function(data, status, headers, config) {
			spinner.stop();
			 notif({
					msg : data,
				});
		});
	};
	
	  $scope.mineQuestionNextPage();
	   
});

sampleApp.controller('exploredQuestion', function($scope,$http, $stateParams, $timeout){
	$(".answerTextarea").wysihtml5({
		"image": false,
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
			url: 'GetExploredQuestion',
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
					liked : (data.question.liked == true ? 1 : 0),

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
					liked : (data.answers[i].liked == true ? 1 : 0),
				});
			}
			
			spinner.stop();
		}).
		error(function(data, status, headers, config) {
			spinner.stop();
			 notif({
					msg : data,
				});
		});
		
		$scope.reply = function() {
			var answer= $('.answerTextarea').val();
			var target = document.getElementById('content');
			var spinner = new Spinner().spin(target);
			$http({
			      method: 'POST',
			      url: 'PostAnswer',
			      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			      data:  "answer="+answer+"&question="+$scope.exploredQuestion.id
			    }).
			  success(function(data, status, headers, config) {
				  spinner.stop();
				  notif({
						msg : data,
						type : 'success'
					});
			  }).
			  error(function(data, status, headers, config) {
				  spinner.stop();
				  notif({
						msg : data,
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
		      url: 'UpdateLike',
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
		"image": false,
	});
	/*$timeout(function () {
		  $scope.question.tagIds.$dirty = true;
		}, 0);*/
	
	
	$scope.load = function() {
		 $.getScript("js/group_config.js");
	   //don't forget to call the load function
	   $scope.load();
	}
	$scope.question = {};
	$scope.question.title = '';
	// function to submit the form after all validation has occurred			
	$scope.submitForm = function() {
		 var content = $('.questionTextarea').val();
		 var questionTitle = $scope.question.title;
		 if(!questionTitle.length > 0){
			 notif({
					msg : "Question Title is missing..."
				});
			 return;
		 }
		 
			 if(!content.length > 0){
			 notif({
					msg : "Question description is missing..."
				});
			 return;
			 }
		// check to make sure the form is completely valid
			var target = document.getElementById('content');
			var spinner = new Spinner().spin(target);
			$http({
			      method: 'POST',
			      url: 'PostQuestion',
			      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			      data:  "title="+questionTitle+"&content="+content+"&tags="+$scope.tagIds
			    }).
			  success(function(data, status, headers, config) {
				  spinner.stop();
				  notif({
						msg : data,
						type : 'success'
					});
			  }).
			  error(function(data, status, headers, config) {
				  spinner.stop();
				  notif({
						msg : data,
					});
			  });
	};
	$scope.changeInTag = function(tag){
		var target = document.getElementById('content');
		var spinner = new Spinner().spin(target);
		$http({
		      method: 'POST',
		      url: 'GetTags',
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
				});
		  });
	};

});


sampleApp.controller("getGroupsController", function($scope, $http) {
	var i = 1;
    $('#groupTable').dataTable({
	   	 "bPagination":true,
		 "bProcessing": true,
	     "bServerSide": true,
	     "bDestroy": true,
	     "sAjaxSource": "GetGroups",
	     
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
	                "aTargets": [ 2 ], // Column to target
	                "mRender": function ( data, type, full ) {
	                	
	                  // 'full' is the row's data object, and 'data' is this column's data
	                  // e.g. 'full[0]' is the comic id, and 'data' is the comic title
	                  return '<a data-toggle="modal" data-target="#myModal"" >' + data + '</a>';
	                }
	              },
	              {
		                "aTargets": [ 1 ], // Column to target
		                "mRender": function ( data, type, full ) {
		                  // 'full' is the row's data object, and 'data' is this column's data
		                  // e.g. 'full[0]' is the comic id, and 'data' is the comic title
		                  return '<a href="#/groups/">' + full.groupId + '</a>';
		                }
		              }
	        ],
	        "aaSorting": [[ 1, 'asc' ]],
	     "aoColumns": [
	                   	{ "mData": "groupId" },
	                    { "mData": "groupName" },
	                    { "mData": "adminName" },
	                    { "mData": "reputation" },
	                    { "mData": "noOfMembers" },
	                ],
	                "sDom": 'T<"clear">lfrtip' ,
		            
		            "oTableTools": {
		            	"aButtons": [
		            					{
		            						"sExtends":    "collection",
		            						"sButtonText": "Save",
		            						"aButtons":    [ "csv", "xls", "pdf" ]
		            					}
		            				],
		                "sSwfPath": "extras/TableTools/media/swf/copy_csv_xls_pdf.swf"
		            }  
	});
});