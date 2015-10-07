
var input=document.getElementById("answer");
	var regex= /^[1-6](,[1-6])*$/;
	input.addEventListener("input", function() {
	if(!regex.test(input.value)){
		input.setCustomValidity("Answer must be in 1,2,...,6 or 1 form");
	}else {
		input.setCustomValidity("");
	}

	});
viewMyQuestion();
$("#subject").change(function(){
	viewMyQuestion();
});
$("#editQuestionForm").submit(function(){
	
	var kk=$("#editQuestionForm").serialize();
	var target=document.getElementById('box');
	var spinner=new Spinner().spin(target);
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../EditQuestion", //The URL from the form element where the AJAX request will be sent  
			data:kk,
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				spinner.stop();
				$('#editquestion').modal('toggle');
				if (data) { 
					notif({
						msg:"Saved successfully",
						type:'success',
						position:'center'
					});

					$('#myQuestionTable').dataTable().fnReloadAjax();
				}else{
					notif({
						msg:"Saved failed",
						type:'error',
						position:'center'
					});

				}

			},  
			error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server  
				spinner.stop();
				$('#editquestion').modal('toggle');
				notif({
					msg:"Some error occur, please try after some time.",
					type:'error',
					position:'center'
				});
			}  
		}); // Ajax close


});

function editQuestion(data){
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../QuestionDetail", //The URL from the form element where the AJAX request will be sent  
			data:'questionid='+data,
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				if (data) { 
					$("#questionid").val(data.id);
					$("#question").text(data.content);
					$.each(data.options, function( key, value ) {
						$('#option'+(key+1)).text(value);
					});
					$('#answer').text(data.correctOptions);

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
function deleteConfirm(data){
	bootbox.confirm('<span style="font-size:18px;">Are you sure you want to delete this Question?</span>', function(result) {
		if(result){

			var target=document.getElementById('box1');
				var spinner=new Spinner().spin(target);
				$.ajax({  
					type: "POST", //The type of HTTP method (post, get, etc)  
					url: "../DeleteQuestion", //The URL from the form element where the AJAX request will be sent  
					data:'questionid='+data,
					dataType: "json", //The type of response to expect from the server  
					success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
						spinner.stop();
						if (data) { 
							notif({
								msg:"Deleted successfully.",
								type:'success',
								position:'center'
							});

							$('#myQuestionTable').dataTable().fnReloadAjax();
						}
						else{
							notif({
								msg:"Deletion failed.",
								type:'error',
								position:'center'
							});

						}

					},  
					error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server  
						spinner.stop();
							notif({
								msg:"Some error occur please try after some time.",
								type:'error',
								position:'center'
							});
					}  
				}); // Ajax close

		}
	}); 
}
function viewQuestion(data){
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


function viewMyQuestion(){
	$('#myQuestionTable').dataTable({
		"bAutoWidth" : false,
		"bPagination":true,
		"bProcessing": true,
		"bServerSide": true,
		"bDestroy": true,
		"sAjaxSource": "../GetMyQuestions?subjectid="+$("#subject").val(),
		"bJQueryUI" : true,
		"aoColumnDefs": [

		                 {
		                	 "aTargets": [ 2 ], // Column to target
		                	 "mRender": function ( data, type, full ) {
		                		 // 'full' is the row's data object, and 'data' is this column's data
		                		 // e.g. 'full[0]' is the comic id, and 'data' is the comic title
		                		 return '<a data-toggle="modal" data-target="#myquestion" onclick="viewQuestion(\''+full.id+'\')">  View</a>';
		                	 }
		                 },
		                 {
		                	 "aTargets": [ 3 ], // Column to target
		                	 "mRender": function ( data, type, full ) {

		                		 // 'full' is the row's data object, and 'data' is this column's data
		                		 // e.g. 'full[0]' is the comic id, and 'data' is the comic title
		                		 return '<p data-placement="top" data-toggle="tooltip" title="" data-original-title="Edit"><button onclick="editQuestion(\''+full.id+'\')" class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#editquestion"><span class="glyphicon glyphicon-pencil"></span></button></p>';
		                	 }
		                 },
		                 {
		                	 "aTargets": [ 4 ], // Column to target
		                	 "mRender": function ( data, type, full ) {
		                		 // 'full' is the row's data object, and 'data' is this column's data
		                		 // e.g. 'full[0]' is the comic id, and 'data' is the comic title
		                		 return '<p data-placement="top" data-toggle="tooltip" title="" data-original-title="Delete"><button onclick="deleteConfirm('+full.id+')" class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete"><span class="glyphicon glyphicon-trash"></span></button></p>';
		                	 }
		                 }
		                 ],
		                 "aoColumns": [
{ "mData": "content" },
{ "mData":"topic"},
{ "sWidth": "5%","mData":"id"},
{ "sWidth": "5%", "mData":"id"},
{ "sWidth": "5%","mData":"id"}
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
	$('div.dataTables_filter input').attr("placeholder", "by topic");
}

