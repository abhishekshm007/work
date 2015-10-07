

function deleteMyQuestion(qid,sid){
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../DeleteMyTestQuestion?qid="+qid+"&sid="+sid, //The URL from the form element where the AJAX request will be sent  
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				if (data) { 
					$('#myQTable').dataTable().fnReloadAjax();
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

function deletePurchasedQuestion(qid,sid){
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../DeletePurchasedTestQuestion?qid="+qid+"&sid="+sid, //The URL from the form element where the AJAX request will be sent  
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				if (data) { 
					$('#pQTable').dataTable().fnReloadAjax();
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
$('#myQTable').dataTable({
	"bAutoWidth" : false,
	"bPagination":true,
	"bProcessing": true,
	"bServerSide": true,
	"bSort":false,
	"bDestroy": true,
	"sAjaxSource": "../GetMyTestQuestion?id="+$("#tid").val(),
	"bJQueryUI" : true,
	"aoColumnDefs": [
	                 {
	                	 "aTargets": [ 2 ], // Column to target
	                	 "mRender": function ( data, type, full ) {
	                		 // 'full' is the row's data object, and 'data' is this column's data
	                		 // e.g. 'full[0]' is the comic id, and 'data' is the comic title
	                		 return '<a data-toggle="modal" data-target="#questiondetail" onclick="viewmyQ(\''+full.question.id+'\')">  View</a>';
	                	 }
	                 },
	                 {
	                	 "aTargets": [ 3 ], // Column to target
	                	 "mRender": function ( data, type, full ) {

	                		 // 'full' is the row's data object, and 'data' is this column's data
	                		 // e.g. 'full[0]' is the comic id, and 'data' is the comic title
	                		 return '<p data-placement="top" data-toggle="tooltip" title="" data-original-title="Delete"><a  onclick="deleteMyQuestion('+full.question.id+','+full.section.sectionID+')" class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete"><span class="glyphicon glyphicon-trash"></span></a></p>';
	                	 }
	                 }
	                 ],
	                 "aoColumns": [
{ "mData": "question.content" },
{ "mData":"section.sectionName"},
{ "sWidth": "5%","mData":"question.id"},
{ "sWidth": "5%", "mData":"question.id"}
],

"oLanguage": {
"sLengthMenu": "Display _MENU_ ",
"sZeroRecords": "Nothing found - sorry",
"sInfo": "Showing _START_ to _END_ of _TOTAL_ records",
"sInfoEmpty": "Showing 0 to 0 of 0 records",
"sInfoFiltered": "(filtered from _MAX_ total records)"
}

});

$('#pQTable').dataTable({
	"bAutoWidth" : false,
	"bPagination":true,
	"bProcessing": true,
	"bServerSide": true,
	"bDestroy": true,
	"sAjaxSource": "../GetPurchasedTestQuestion?id="+$("#tid").val(),
	"bJQueryUI" : true,
	"aoColumnDefs": [
	                 {
	                	 "aTargets": [ 2 ], // Column to target
	                	 "mRender": function ( data, type, full ) {
	                		 // 'full' is the row's data object, and 'data' is this column's data
	                		 // e.g. 'full[0]' is the comic id, and 'data' is the comic title
	                		 return '<a data-toggle="modal" data-target="#questiondetail" onclick="viewpQ(\''+full.question.id+'\')">  View</a>';
	                	 }
	                 },
	                 {
	                	 "aTargets": [ 3 ], // Column to target
	                	 "mRender": function ( data, type, full ) {

	                		 // 'full' is the row's data object, and 'data' is this column's data
	                		 // e.g. 'full[0]' is the comic id, and 'data' is the comic title
	                		 return '<p data-placement="top" data-toggle="tooltip" title="" data-original-title="Delete"><a  onclick="deletePurchasedQuestion('+full.question.id+','+full.section.sectionID+')" class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete"><span class="glyphicon glyphicon-trash"></span></a></p>';
	                	 }
	                 }
	                 ],
	                 "aoColumns": [
{ "mData": "question.content" },
{ "mData":"section.sectionName"},
{ "sWidth": "5%","mData":"question.id"},
{ "sWidth": "5%", "mData":"question.id"}
],

"oLanguage": {
"sLengthMenu": "Display _MENU_ ",
"sZeroRecords": "Nothing found - sorry",
"sInfo": "Showing _START_ to _END_ of _TOTAL_ records",
"sInfoEmpty": "Showing 0 to 0 of 0 records",
"sInfoFiltered": "(filtered from _MAX_ total records)"
}

});
$('div.dataTables_filter input').attr("placeholder", "by title");
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
