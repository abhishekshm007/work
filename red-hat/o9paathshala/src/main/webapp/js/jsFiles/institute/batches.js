
function deleteConfirm(data){
	bootbox.confirm('<span style="font-size:18px;">Are you sure you want to delete this batch?<b style="color:red"> Records of all students of this batch will also be deleted.</b></span>', function(result) {
		if(result){
			var target=document.getElementById('box1');
			var spinner=new Spinner().spin(target);
				$.ajax({  
					type: "POST", //The type of HTTP method (post, get, etc)  
					url: "../DeleteBatch", //The URL from the form element where the AJAX request will be sent  
					data:'id='+data,
					dataType: "json", //The type of response to expect from the server  
					success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
						spinner.stop();
						 
							if(data){
								notif({
									msg:"Deleted successfully",
									type:'success',
									position:'center'
								});

								$('#batchTable').dataTable().fnReloadAjax();
							}
							else{
								notif({
									msg:"Deletion failed, please try after some time!!!",
									type:'error',
									position:'center'
								});
							}
						
					},  
					error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server  
						spinner.stop();
						notif({
							msg:"Some error occured, please try after some time!!!",
							type:'error',
							position:'center'
						});
					}  
				}); // Ajax close

		}
	}); 
}

function editName(data){

	bootbox.prompt({
		title: "Edit batch name",
		value: data,
		callback: function(result) {
			if (!(result === null)) {
				var target=document.getElementById('box1');
				var spinner=new Spinner().spin(target);
					$.ajax({  
						type: "POST", //The type of HTTP method (post, get, etc)  
						url: "../EditBatch", //The URL from the form element where the AJAX request will be sent  
						data:'oldName='+data+'&newName='+result,
						dataType: "json", //The type of response to expect from the server  
						success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
							spinner.stop();
							if (data) { 
								notif({
									msg:"Saved successfully",
									type:'success',
									position:'center'
								});
									$('#batchTable').dataTable().fnReloadAjax();
								
							}else{
								notif({
									msg:"Operation failed, please try after some time!!!",
									type:'error',
									position:'center'
								});
								}
							
						},  
						error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server  
							spinner.stop();
							notif({
								msg:"Some error occured, please try after some time!!!",
								type:'error',
								position:'center'
							});
							}  
					}); // Ajax close

			} 
		}
	});
}

function studentList(data){
	$('#studentListTable').dataTable({
		"bAutoWidth" : false,
		"bPagination":true,
		"bProcessing": true,
		"bServerSide": true,
		"bSort":false,
		"bDestroy": true,
		"sAjaxSource": "../GetStudentsOnBatch?id="+data,
		"bJQueryUI" : true,
		"aoColumns": [
		              { "mData": "name" },
		              { "mData": "email" },
		             
		              ],
		              "sDom": 'T<"clear">lfrtip' ,
		              "oLanguage": {
		                  "sLengthMenu": "Display _MENU_ ",
		                  "sZeroRecords": "Nothing found - sorry",
		                  "sInfo": "Showing _START_ to _END_ of _TOTAL_ records",
		                  "sInfoEmpty": "Showing 0 to 0 of 0 records",
		                  "sInfoFiltered": "(filtered from _MAX_ total records)"
		              },
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

}

$("#changebatch").submit(function(event){
var kk=$("#changebatch").serialize();
	var target=document.getElementById('box1');
		var spinner=new Spinner().spin(target);
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../ChangeBatch", //The URL from the form element where the AJAX request will be sent  
			data:kk,
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				spinner.stop();
				if (data) { 
					notif({
						msg:"Transfer complete.",
						type:'success',
						position:'center'
					});

						$('#changebatch').trigger("reset");
					}
					else{
						notif({
							msg:"Transfer failed, please select student from hints only",
							type:'error',
							position:'center'
						});
					}
				},
			  
			error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server  
				spinner.stop();
				notif({
					msg:"Some error occur, please select student from hints only",
					type:'error',
					position:'center'
				});
			}  
		}); // Ajax close
	event.preventDefault();
});

$('#name').autocomplete({
	
	serviceUrl: '../AutoCompleteStudent',
	paramName: "tagName",
	delimiter: ",",
    transformResult: function(response) {
        return {
            suggestions: $.map($.parseJSON(response), function(item) {
                return { value:'<img src="../ProfilePicture?id='+item.id +'" alt="'+item.name+'" class="img-circle"  style="height:25px;width:25px;"> '+ item.name+'('+item.email+')', data: item.id,name:item.name };
            })
            
        };
    },
	onSelect: function(a) {
		$("#name").val(a.name);
        $("#studentid").val(a.data);
    }
    
});
