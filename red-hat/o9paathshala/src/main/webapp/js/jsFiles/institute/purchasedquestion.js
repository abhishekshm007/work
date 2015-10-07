	purchasedQuestion();
	$("#subject").change(function(){
		purchasedQuestion();
	});
	
function viewQuestion(data){
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
function purchasedQuestion(){
	$('#purchasedQuestionTable').dataTable({
		"bAutoWidth" : false,
		"bPagination":true,
		"bProcessing": true,
		"bServerSide": true,
		"bSort":false,
		"bDestroy": true,
		"sAjaxSource": "../GetPurchasedQuestion?subjectid="+$("#subject").val(),
		"bJQueryUI" : true,
		"aoColumnDefs": [
		                 {
		                	 "aTargets": [ 2 ], // Column to target
		                	 "mRender": function ( data, type, full ) {
		                		 // 'full' is the row's data object, and 'data' is this column's data
		                		 // e.g. 'full[0]' is the comic id, and 'data' is the comic title
		                		 return '<a data-toggle="modal" data-target="#purchasedquestion" onclick="viewQuestion(\''+full.id+'\')">  View</a>';
		                	 }
		                 }
		                 ],
		                 "aoColumns": [
{ "mData": "content" },
{ "mData":"topic"},
{ "sWidth": "5%","mData":"id"}
],
"oLanguage": {
	"sLengthMenu": "Display _MENU_ ",
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
