var tQ=0;
var testname;
var testid;
var mySelected = [];
var purchasedSelected=[];
var sectionQuestionCount=[];
$('#selectsection option').each(function() {
	sectionQuestionCount.push([$(this).val(),0]);
});
var startDate = document.getElementById("startdate");
startDate.addEventListener("input", function() {

	var value = Math.floor(new Date(startDate.value).getTime());
	var current=(Math.floor(new Date().getTime()));
	if (value < current) {
		startDate.setCustomValidity("Start date must be after now!");
	} else {
		startDate.setCustomValidity("");
	}

});
var endDate = document.getElementById("enddate");
endDate.addEventListener("input", function() {

	var value = Math.floor(new Date(endDate.value).getTime());
	var current=(Math.floor(new Date().getTime()));
	if (value < current) {
		endDate.setCustomValidity("End date must be after now!");
	} else {
		endDate.setCustomValidity("");
	}

});

function setTestId(data){
$("#tid").val(data);
	//document.cookie="testid="+data;
}

function deleteConfirm(data){
	bootbox.confirm('<span style="font-size:18px;">Are you sure you want to delete this test?</span>', function(result) {
		if(result){
			var target=document.getElementById('box');
				var spinner=new Spinner().spin(target);
				$.ajax({  
					type: "POST", //The type of HTTP method (post, get, etc)  
					url: "../DeleteTest", //The URL from the form element where the AJAX request will be sent  
					data:'testid='+data,
					dataType: "json", //The type of response to expect from the server  
					success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
						spinner.stop();
						if (data) { 
							notif({
								msg:"Deleted Successfully",
								type:'success',
								position:'center'
							});
							$('#allTestTable').dataTable().fnReloadAjax();
						}
						else{
							notif({
								msg:"Some error occur please try after some time!!!",
								type:'error',
								position:'center'
							});
						}

					},  
					error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server  
						spinner.stop();
						notif({
							msg:"Some error occur please try after some time!!!",
							type:'error',
							position:'center'
						});
					}  
				}); // Ajax close

		}
	}); 
}

function addQuestionToTest(data){
	getTestDetail(data);
	$("#heading").text('Add questions to test');


}
$("#mys").change(function(){
	//myQuestion();
	var table = $('#myQTable').dataTable();
	table.fnReloadAjax( "../GetMyQuestions?subjectid="+$("#mys").val() );
});
$("#o9s").change(function(){
	var table = $('#pQTable').dataTable();
	table.fnReloadAjax( "../GetPurchasedQuestion?subjectid="+$("#o9s").val() );

});

$("#selectsection").change(function(){
	var table = $('#myQTable').dataTable();
	table.fnReloadAjax( "../GetMyQuestions?subjectid="+$("#mys").val() );

	var table = $('#pQTable').dataTable();
	table.fnReloadAjax( "../GetPurchasedQuestion?subjectid="+$("#o9s").val() );
	displaySelected();
});

$("#addsection").click(function(event){
	$("#section").append('<input type="text" name="section"  class="form-control" placeholder="Enter Section Name">');
});

$("#createtest").submit(function(event){
	var kk=$("#createtest").serialize();
	var target=document.getElementById('box1');
	var spinner=new Spinner().spin(target);
		$.ajax({  
			type: "POST", 
			url: "../NewTest", 
			data:kk,
			dataType: "json",  
			success: function ( data, statusCode, xhr ) { 
				spinner.stop();

				if("AEN"==data){
					notif({
						msg:"This test name already exist,it must be unique!!!",
						type:'error',
						position:'center'
					});
				}else{
					if("success" == data){
						getTestDetail($("#testname").val());
						$("#box1").hide();
						$("#box2").show();
					}else{
						notif({
							msg:"Some error occur please try after some time!!!",
							type:'error',
							position:'center'
						});
					}
				}

			},
			error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server  
				spinner.stop();
				notif({
					msg:"Some error occur please try after some time!!!",
					type:'error',
					position:'center'
				});
			}  
		}); // Ajax close
	event.preventDefault();

});

function getTestDetail(data){
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../GetTestDetail", //The URL from the form element where the AJAX request will be sent  
			data:"testname="+data,
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				if (data) { 
					var d="";
					sectionQuestionCount=[];
					$.each(data.sections, function( key, value ) {
						d=d+'<option value='+value.sectionID+'>'+value.sectionName+'</option>';
						sectionQuestionCount.push([value.sectionID,0]);
					});
					$("#selectsection").append(d);
					testname=data.testName;
					testid=data.id;
					getSubjects();
					geto9ss();
				}
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
}

function getSubjects(){

		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../GetSubjects", //The URL from the form element where the AJAX request will be sent  
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				if (data) { 
					var d="";
					$.each(data, function( key, value ) {
						d=d+'<option value='+value.id+'>'+value.name+'</option>';
					});
					$("#mys").append(d);
					myQ();

				}
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
}
function geto9ss(){
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../Geto9Subjects", //The URL from the form element where the AJAX request will be sent  
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				if (data) { 
					var d="";
					$.each(data, function( key, value ) {
						d=d+'<option value='+value.id+'>'+value.name+'</option>';
					});
					$("#o9s").append(d);
					pQ();
				}
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
}
function myQ(){
	$('#myQTable').dataTable({
		"bAutoWidth" : false,
		"bPagination":true,
		"bProcessing": true,
		"bServerSide": true,
		"bDestroy": true,
		"sAjaxSource": "../GetMyQuestions?subjectid="+$("#mys").val(),
		"bJQueryUI" : true,
		"fnRowCallback": function( row, data ) {
			mySelected.filter(function(v,i) {  
				if (v[1] === data.id.toString()&&v[0]===$("#selectsection").val())
				{ 
					$(':checkbox', row).prop('checked',true);
				}
			});
		},
		"aoColumnDefs": [
		                 {
		                	 "aTargets": [ 2 ], // Column to target
		                	 "mRender": function ( data, type, full ) {
		                		 // 'full' is the row's data object, and 'data' is this column's data
		                		 // e.g. 'full[0]' is the comic id, and 'data' is the comic title
		                		 return '<a data-toggle="modal" data-target="#questiondetail" onclick="viewmyQ(\''+full.id+'\')">  View</a>';
		                	 }
		                 },
		                 {
		                	 "aTargets": [ 3 ], // Column to target
		                	 "mRender": function ( data, type, full ) {

		                		 // 'full' is the row's data object, and 'data' is this column's data
		                		 // e.g. 'full[0]' is the comic id, and 'data' is the comic title
		                		 return '<input type="checkbox" class="flat-red" id='+full.id+'>';
		                	 }
		                 }
		                 ],
		                 "aoColumns": [
{ "mData": "content" },
{ "mData":"topic"},
{ "sWidth": "5%","mData":"id"},
{ "sWidth": "5%", "mData":"id"}
],

"oLanguage": {
	"sLengthMenu": "Display _MENU_ ",
	"sZeroRecords": "Nothing found - sorry",
	"sInfo": "Showing _START_ to _END_ of _TOTAL_ records",
	"sInfoEmpty": "Showing 0 to 0 of 0 records",
	"sInfoFiltered": "(filtered from _MAX_ total records)"
}

	});
	$('#myQTable tbody').on('click', ':checkbox', function () {
		var id = this.id;
		var result = false;
		mySelected.filter(function(v,i) {  
			if (v[1] === id&&v[0]===$("#selectsection").val())
			{
				result=true;
				mySelected.splice(i,1);
				tQ--;
				sectionQuestionCount.filter(function(ve,ie) { 
					if(ve[0]==$("#selectsection").val()){
						//sectionQuestionCount.splice(ie,1);
						ve[1]=ve[1]-1;
					}
				});
				return;
			} 
		}); 
		if(!result){
			mySelected.push([$("#selectsection").val(),id]);
			tQ++;
			sectionQuestionCount.filter(function(ve,ie) { 
				if(ve[0]==$("#selectsection").val()){
					ve[1]=ve[1]+1;
				}
			});
		}
		displayTotal();
		displaySelected();
		//$(this).toggleClass('mySelected');
	} );

}

function pQ(){
	$('#pQTable').dataTable({
		"bAutoWidth" : false,
		"bPagination":true,
		"bProcessing": true,
		"bServerSide": true,
		"bDestroy": true,
		"sAjaxSource": "../GetPurchasedQuestion?subjectid="+$("#o9s").val(),
		"bJQueryUI" : true,
		"fnRowCallback": function( row, data ) {
			purchasedSelected.filter(function(v,i) {  
				if (v[1] === data.id.toString()&&v[0]===$("#selectsection").val())
				{ 
					$(':checkbox', row).prop('checked',true);
				}
			});
		},
		"aoColumnDefs": [
		                 {
		                	 "aTargets": [ 2 ], // Column to target
		                	 "mRender": function ( data, type, full ) {
		                		 // 'full' is the row's data object, and 'data' is this column's data
		                		 // e.g. 'full[0]' is the comic id, and 'data' is the comic title
		                		 return '<a data-toggle="modal" data-target="#questiondetail" onclick="viewpQ(\''+full.id+'\')">  View</a>';
		                	 }
		                 },
		                 {
		                	 "aTargets": [ 3 ], // Column to target
		                	 "mRender": function ( data, type, full ) {

		                		 // 'full' is the row's data object, and 'data' is this column's data
		                		 // e.g. 'full[0]' is the comic id, and 'data' is the comic title
		                		 return '<input type="checkbox" class="flat-red" id='+full.id+'>';
		                	 }
		                 }
		                 ],
		                 "aoColumns": [
{ "mData": "content" },
{ "mData":"topic"},
{ "sWidth": "5%","mData":"id"},
{ "sWidth": "5%", "mData":"id"}
],
"oLanguage": {
	"sLengthMenu": "Display _MENU_ ",
	"sZeroRecords": "Nothing found - sorry",
	"sInfo": "Showing _START_ to _END_ of _TOTAL_ records",
	"sInfoEmpty": "Showing 0 to 0 of 0 records",
	"sInfoFiltered": "(filtered from _MAX_ total records)"
}

	});
	$('#pQTable tbody').on('click', ':checkbox', function () {
		var id = this.id;
		var result = false;
		purchasedSelected.filter(function(v,i) {  
			if (v[1] === id&&v[0]===$("#selectsection").val())
			{ 
				result=true;
				purchasedSelected.splice(i,1);
				tQ--;
				sectionQuestionCount.filter(function(ve,ie) { 
					if(ve[0]==$("#selectsection").val()){
						ve[1]=ve[1]-1;
					}
				});
				return;
			} 
		}); 
		if(!result){
			purchasedSelected.push([$("#selectsection").val(),id]);
			tQ++;
			sectionQuestionCount.filter(function(ve,ie) { 
				if(ve[0]==$("#selectsection").val()){
					ve[1]=ve[1]+1;
				}
			});	
		}
		displayTotal();
		displaySelected();
		//$(this).toggleClass('mySelected');
	} );
}

function viewmyQ(data){
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../QuestionDetail", //The URL from the form element where the AJAX request will be sent  
			data:'questionid='+data,
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				if (data) { 
					$("#questionContent").text(data.content);
					var dataoptions="<table class=\"table\" style=\"border: none;\" ><tbody>";
					$.each(data.options, function( key, value ) {
						dataoptions=dataoptions+"<tr><td>"+(key+1)+"."+value+"</td></tr>";
					});
					dataoptions=dataoptions+"<tr><td><b>Correct Answer :</b>"+data.correctOptions+"</td></tr></tbody></table>";
					$("#options").html(dataoptions);
				}

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
}
function viewpQ(data){
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../PurchasedQuestionDetail", //The URL from the form element where the AJAX request will be sent  
			data:'questionid='+data,
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				if (data) { 
					$("#questionContent").text(data.content);
					var dataoptions="<table class=\"table\" style=\"border: none;\" ><tbody>";
					$.each(data.options, function( key, value ) {
						dataoptions=dataoptions+"<tr><td>"+(key+1)+"."+value+"</td></tr>";
					});
					dataoptions=dataoptions+"<tr><td><b>Correct Answer :</b>"+data.correctOptions+"</td></tr></tbody></table>";
					$("#options").html(dataoptions);
				}

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
}
function displayTotal(){
	$("#tq").text(tQ);
}

function displaySelected(){
	sectionQuestionCount.filter(function(v,i) { 
		if(v[0] == $("#selectsection").val()){
			$("#sq").text(v[1]);
		}
	});
}

$("#test").click(function(event){

	var target=document.getElementById('box2');
	var spinner=new Spinner().spin(target);
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../CreateTest", //The URL from the form element where the AJAX request will be sent  
			data:"myquestion="+mySelected+"&purchasedquestion="+purchasedSelected,
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				spinner.stop();
				if("NQ"==data){
					notif({
						msg:"Test cannot be empty",
						type:'warning',
						position:'center'
					});
				}else{
				notif({
					msg:"Your test is saved successfully.",
					type:'success',
					position:'center'
				});		
				location.reload();
	
				}


			},
			error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server  
				spinner.stop();
				notif({
					msg:"Some error occur please try after some time!!!",
					type:'error',
					position:'center'
				});
			}  
		}); // Ajax close
	event.preventDefault();
});

$("#addq").click(function(event){

	var target=document.getElementById('box2');
	var spinner=new Spinner().spin(target);
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../CreateTest", //The URL from the form element where the AJAX request will be sent  
			data:"myquestion="+mySelected+"&purchasedquestion="+purchasedSelected,
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				spinner.stop();
				if(data){
					notif({
					msg:'Question added successfully.',
					type:'success',
					position:'center'
				});
		window.history.back();
				}


			},
			error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server  
				spinner.stop();
				notif({
					msg:"Some error occur please try after some time!!!",
					type:'error',
					position:'center'
				});
			}  
		}); // Ajax close
	event.preventDefault();
});

$('div.dataTables_filter input').attr("placeholder", "by topic");