//var d;
var object;
var sectionNumber=0;
var questionNumber=0;
var totalQuestion;
var questionAttempted=0;
var questionMarked;
var time;
var k;
var submit=false;
$(function() {
	if(null!=localStorage.getItem('0100000001')){
		$.ajax({
			type: "POST", //The type of HTTP method (post, get, etc)
			url: "../Security", //The URL from the form element where the AJAX request will be sent
			dataType: "json", //The type of response to expect from the server
			success: function (data, statusCode, xhr ) { //Triggered after a successful response from server
				k=data;
				object=JSON.parse(CryptoJS.AES.decrypt(localStorage.getItem('0100000001'),k).toString(CryptoJS.enc.Utf8));
				time=object.duration;
				if(time<=0){
					var message='Time up!!!';
					notif({
						msg:message,
						type:'error',
						position:'center'
						});
					makeResult();
					submit=true;
				}
				displayWatch(time);
				displayQuestion(sectionNumber,questionNumber);
				displayQuestionsChart();
				getTotalQuestions();
				getQuestionAttempted();
				getQuestionMarked();
				window.setInterval(function () {
					if(object.duration>0){
						object.duration=object.duration-1;
					}else{
						object.duration=0;
					}
					localStorage.setItem("0100000001",CryptoJS.AES.encrypt(JSON.stringify(object),k));
				}, 1000);
				window.clearInterval(time);
			},
			error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server
				var errorMessage = exception || xhr.statusText; //If exception null, then default to xhr.statusText

				notif({
					msg:errorMessage,
					type:'error',
					position:'center'
					});
			}
		}); // Ajax close
	$('#next').click(function(){
		if(object.sections[sectionNumber].questions.length==(questionNumber+1)){
			displayQuestion(sectionNumber+1, 0);
		}else{
			displayQuestion(sectionNumber, questionNumber+1);
		}
	});
	$('#previous').click(function(){

		if(questionNumber==0){
			displayQuestion(sectionNumber-1,object.sections[sectionNumber-1].questions.length-1);
		}else{
			displayQuestion(sectionNumber, questionNumber-1);
		}
	});

	$('#submit').click(function(){
		bootbox.confirm("Are you sure you want to submit your test?",function(result){
			if(result){
				$("#submit").prop('disabled', true);
			makeResult();
			submit=true;
		}
	});
	});

	$('#reset').click(function(){
		if(object.sections[sectionNumber].questions[questionNumber].userAnswers.length>0){
			object.sections[sectionNumber].questions[questionNumber].userAnswers=[];
			localStorage.setItem("0100000001",CryptoJS.AES.encrypt(JSON.stringify(object),k));
			if(!checkMark()){
				$("#"+sectionNumber+questionNumber).removeClass($("#"+sectionNumber+questionNumber).attr("class")).addClass("dark-red");
			}
			$('input:checkbox').attr('checked',false);
			$('input:radio').attr('checked',false);
			questionAttempted--;
			displayAttempted();
		}
	});
	$('#mark').click(function(){

		var mark=JSON.parse(localStorage.getItem('markArray'));
		mark.push([sectionNumber,questionNumber]);
		localStorage.setItem('markArray',JSON.stringify(mark));
		$("#"+sectionNumber+questionNumber).removeClass($("#"+sectionNumber+questionNumber).attr("class")).addClass("dark-blue");
		$('#mark').attr("disabled",true);
		$('#unmark').attr("disabled",false);
		getQuestionMarked();
	});
	$('#unmark').click(function(){

		var mark=JSON.parse(localStorage.getItem('markArray'));
		$.each(mark, function( key, value ) {
			if(value[0]==sectionNumber&&value[1]==questionNumber){
				mark.splice(key,1);
				return false;
			}	
		});
		localStorage.setItem('markArray',JSON.stringify(mark));
		if(object.sections[sectionNumber].questions[questionNumber].userAnswers.length>0){
			$("#"+sectionNumber+questionNumber).removeClass($("#"+sectionNumber+questionNumber).attr("class")).addClass("dark-green");
		}else{
			$("#"+sectionNumber+questionNumber).removeClass($("#"+sectionNumber+questionNumber).attr("class")).addClass("dark-red");
		}
		$('#mark').attr("disabled",false);
		$('#unmark').attr("disabled",true);
		getQuestionMarked();
	});
	}else{
		$("#all").hide();
	}
});
function displayQuestionsChart(){
	var data="";
	var s=0;
	$.each( object.sections, function( key, value ) {
		data=data+"<div class=\"panel panel-default\"><div class=\"panel-heading\">"+value.sectionName +" - "+value.questions.length +"</div><div class=\"panel-body\"><table style=\"border-collapse: separate\" class=\"table table-bordered \" ><tbody>";
		var leng=value.questions.length;
		var r=0;
		while(leng/4>1){
			data=data+"<tr><td class=\"dark-red \" onclick=\" displayQuestion("+ s +","+ r +");\" id=\""+ s  + r +"\">"+ ++r +"</td><td class=\"dark-red\" onclick=\"displayQuestion("+s+","+ r +")\" id=\""+ s + r +"\">"+ ++r +"</td><td class=\"dark-red\" onclick=\"displayQuestion("+s+","+ r +")\" id=\""+ s + r +"\">"+ ++r +"</td><td class=\"dark-red\" onclick=\"displayQuestion("+s+","+ r +")\" id=\""+ s + r +"\">"+ ++r +"</td></tr>";
			leng=leng-4;
		}
		data=data+"<tr>";
		for(var i=0;i<leng;i++){
			data=data+"<td class=\"dark-red \" onclick=\"displayQuestion("+ s +","+ r +")\" id=\""+ s + r +"\">"+ ++r+"</td>";
		}
		data=data+"</tr></tbody></table></div></div>";   
		s++;
	});
	$("#questionList").html(data);
	$.each( object.sections, function( key, value ) {
		$.each( value.questions, function( k, v ) {
			if(v.userAnswers.length>0){
				$("#"+key+k).removeClass($("#"+key+k).attr("class")).addClass("dark-green");
			}
		});
	});
	$.each( JSON.parse(localStorage.getItem('markArray')), function( key, value ) {

		$("#"+value[0]+value[1]).removeClass($("#"+value[0]+value[1]).attr("class")).addClass("dark-blue");

	});
}
function displayWatch(time){
	$('#countdown-1').timeTo({
		seconds: time,
		theme: "black",
		displayCaptions: true
	},function() {
		message='Time up!!!';
		notif({
			msg:message,
			type:'error',
			position:'center'
			});
		makeResult();
		submit=true;
	});
}
function displayQuestion(x,y){
	$('#previous').attr("disabled",false);
	$('#next').attr("disabled",false);
	if(y==0&&x==0){
		$('#previous').attr("disabled",true);
	}else{
		if(object.sections[x].questions.length==(y+1)&&object.sections.length==(x+1)){
			$('#next').attr("disabled",true);
		}
	}
	sectionNumber=x;
	questionNumber=y;
	$("#sectionName").text(object.sections[x].sectionName);
	$("#questionContent").html("Q"+(y+1)+".<pre> "+object.sections[x].questions[y].content+"</pre>");
	var dataoptions="<table class=\"table\" style=\"border: none;\" ><tbody>";
	if(object.sections[x].questions[y].correctOptions.length>1){
		$.each(object.sections[x].questions[y].options, function( key, value ) {
			dataoptions=dataoptions+"<tr><td style=\"width :5px;\"><input type=\"checkbox\" class=\"flat-red\" name=\"optionscheckbox\" id=\""+(key+1)+"\" value=\""+(key+1)+"\" onclick=\"updateCheckBoxAnswer(this)\"></td><td>"+value+"</td></tr>";
		});
	}else{
		$.each(object.sections[x].questions[y].options, function( key, value ) {
			dataoptions=dataoptions+"<tr><td style=\"width :5px;\"><input type=\"radio\" class=\"flat-red\" name=\"optionsRadios\" id=\""+(key+1)+"\" value=\""+(key+1)+"\" onclick=\"updateRadioAnswer(this)\" ></td><td>"+value+"</td></tr>";
		});
	}
	dataoptions=dataoptions+("</tbody></table>");
	$("#options").html(dataoptions);

	if(checkMark()){
		$('#mark').attr("disabled",true);
		$('#unmark').attr("disabled",false); 
	}else{
		$('#mark').attr("disabled",false);
		$('#unmark').attr("disabled",true);
	}

	$.each(object.sections[x].questions[y].userAnswers, function( key, value ) {
		document.getElementById(value).checked="true";
	}); 
}
function updateRadioAnswer(answer){
	if(object.sections[sectionNumber].questions[questionNumber].userAnswers.length==0){
	object.sections[sectionNumber].questions[questionNumber].userAnswers[0]=answer.value;
	if(!checkMark()){

		$("#"+sectionNumber+questionNumber).removeClass($("#"+sectionNumber+questionNumber).attr("class")).addClass("dark-green");
	}

	localStorage.setItem("0100000001",CryptoJS.AES.encrypt(JSON.stringify(object),k));
	questionAttempted++;
	displayAttempted();
	}else{
		object.sections[sectionNumber].questions[questionNumber].userAnswers[0]=answer.value;
	}
}
function updateCheckBoxAnswer(answer){
	if(answer.checked){
		if(object.sections[sectionNumber].questions[questionNumber].userAnswers.length==0){
			questionAttempted++;
		}
		object.sections[sectionNumber].questions[questionNumber].userAnswers.push(answer.value);
		if(!checkMark()){
			$("#"+sectionNumber+questionNumber).removeClass($("#"+sectionNumber+questionNumber).attr("class")).addClass("dark-green");
		}
	}else{
		var i=object.sections[sectionNumber].questions[questionNumber].userAnswers.indexOf(answer.value);
		if(i>-1){
			object.sections[sectionNumber].questions[questionNumber].userAnswers.splice(i,1);
			if(object.sections[sectionNumber].questions[questionNumber].userAnswers.length==0){
				questionAttempted--;
				if(!checkMark()){
					$("#"+sectionNumber+questionNumber).removeClass($("#"+sectionNumber+questionNumber).attr("class")).addClass("dark-red");
				}
			}
		}
	}
	localStorage.setItem("0100000001",CryptoJS.AES.encrypt(JSON.stringify(object),k));
	displayAttempted();
}
function checkMark(){
	var mark=JSON.parse(localStorage.getItem('markArray'));
	var result=false;
	$.each(mark, function( key, value ) {
		if(value[0]==sectionNumber&&value[1]==questionNumber){
			result=true;
			return false;
		}	
	});
	return result;
}
function getQuestionMarked(){
	questionMarked=(JSON.parse(localStorage.getItem('markArray'))).length;
	$("#questionMarked").text(questionMarked);
}
function getQuestionAttempted(){
	$.each( object.sections, function( key, value ) {
		$.each( value.questions, function( k, v ) {
			if(v.userAnswers.length>0){
				questionAttempted=(questionAttempted+1);
			}
		});
	});
	displayAttempted();
}
function displayAttempted(){
	$("#questionAttempted").text(questionAttempted);
}
function getTotalQuestions(){
	totalQuestion=0;
	$.each( object.sections, function( key, value ) {
		totalQuestion=(totalQuestion+value.questions.length);
	});
	$("#totalQuestions").text(totalQuestion);
}
function calculateTime(totalSec){
	var hours = parseInt( totalSec / 3600 ) % 24;
	var minutes = parseInt( totalSec / 60 ) % 60;
	var seconds = totalSec % 60;
	var result = (hours < 10 ? "0" + hours : hours) + "-" + (minutes < 10 ? "0" + minutes : minutes) + "-" + (seconds  < 10 ? "0" + seconds : seconds);
	return result;
}
function makeResult(){
	var target=document.getElementById('all');
	var spinner=new Spinner().spin(target);
	var data=JSON.parse(CryptoJS.AES.decrypt(localStorage.getItem('0100000001'),k).toString(CryptoJS.enc.Utf8));
	$.each(data.sections, function(key,value) {
		$.each(value.questions, function(k,v) {
			if(v.userAnswers.length>0){
				v.attempted=true;
				if(checkAnswer(v.correctOptions,v.userAnswers)){
					v.marks=data.positiveMark;
				}else{
					v.marks=data.negativeMark;
				}
			}
		});
	});
	emptyStorage();
	localStorage.setItem('1100000001', CryptoJS.AES.encrypt(JSON.stringify(data),k));
	$.ajax({  
		type: "POST", //The type of HTTP method (post, get, etc)  
		url: "../SaveResult", //The URL from the form element where the AJAX request will be sent  
		data:'testid='+data.id+'&testname='+data.testName+'&mac='+cR(data),
		dataType: "json", //The type of response to expect from the server  
		success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
			if(data){
			localStorage.setItem('status',CryptoJS.AES.encrypt(JSON.stringify(true),k));
			}
			
			$(location).attr('href', 'result.html');
			spinner.stop();
		},  
		error: function ( xhr, errorType, exception ) {   
		}
		});
	
}


$(window).on('beforeunload', function(e) {
	var t=CryptoJS.AES.decrypt(localStorage.getItem('0100000007'),k).toString(CryptoJS.enc.Utf8);
    if(submit){
    	return;
    } else{
	return "Are you sure you want to leave the page? You have left with "+(t-1)+" attempts only.";
    }
    });
$(window).unload(function(){
	var p=CryptoJS.AES.decrypt(localStorage.getItem('0100000007'),k).toString(CryptoJS.enc.Utf8)-1;
	if(p=="1"){
		makeResult();
	}else{
		localStorage.setItem("0100000007",CryptoJS.AES.encrypt(JSON.stringify(p),k));
	}
});
function checkAnswer(a,b){
	a.sort();
	b.sort();
	var res=false;
	if(a.length==b.length){
		for(var i=0;i<a.length;i++){
			if((a[i]==b[i])){
				res=true;
			}else{
				res=false;
				return false;
			}
		}
	}
	return res;
}


function emptyStorage(){
	localStorage.removeItem('0100000001');
	localStorage.removeItem('0100000007');
	localStorage.removeItem('markArray');
}
function cR(resultObject){
	var tcorrect=0;
	var tincorrect=0;
	var tattempted=0;
	var tpercent=0;
	$.each( resultObject.sections, function( key, value ) {
		var marks=0;
		var correct=0;
		var incorrect=0;
		var attempted=0;
		$.each(value.questions, function(k,v) {
			marks=marks+v.marks;
			if(v.attempted){
				attempted++;
				if(v.marks>0){
					correct++;
				}else{
					incorrect++;
				}
			}
		});
		var percent=((marks*100)/(value.questions.length*resultObject.positiveMark));
		tcorrect=(tcorrect+correct);
		tattempted=(tattempted+attempted);
		tincorrect=(tincorrect+incorrect);
		tpercent=(tpercent+percent);
	});
	tpercent=(tpercent/resultObject.sections.length);
	return tpercent;
}
