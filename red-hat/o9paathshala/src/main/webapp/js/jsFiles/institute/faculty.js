function viewProfile(data){
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../FacultyProfile", //The URL from the form element where the AJAX request will be sent  
			data:'facultyid='+data,
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				if (data) { 
					$("#profilepic").html('<img src="../ProfilePicture?id='+data.id +'" alt="'+data.name+'" class="img-circle"  height="215px" width="215px"> ');
					$("#fname").text(data.name);
					$("#semail").text(data.email);
					$("#scontact").text(data.contact);
					$("#sgender").text(data.gender);
					$("#sdob").text(data.dob);
					$("#saddress").text(data.address);

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
	
	bootbox.confirm('<span style="font-size:18px;">Are you sure you want to delete this Faculty?</span>', function(result) {
		if(result){
			var target=document.getElementById('box1');
			var spinner=new Spinner().spin(target);
				$.ajax({  
					type: "POST", //The type of HTTP method (post, get, etc)  
					url: "../DeleteFaculty", //The URL from the form element where the AJAX request will be sent  
					data:'facultyid='+data,
					dataType: "json", //The type of response to expect from the server  
					success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
						spinner.stop();
						if (data) { 
							notif({
								msg:"Deleted successfully ",
								type:'success',
								position:'center'
							});

							$('#facultyTable').dataTable().fnReloadAjax();
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

function batchList(data){
	$('#batchListTable').dataTable({
		"bAutoWidth" : false,
		"bPagination":true,
		"bProcessing": true,
		"bServerSide": true,
		"bSort":false,
		"bDestroy": true,
		"sAjaxSource": "../GetFacultyBatches?id="+data,
		"bJQueryUI" : true,
		"aoColumns": [
		              { "mData": "name"}
		              ],
		              "sDom": 'T<"clear">lfrtip' ,
		              "oLanguage": {
		            	  "sLengthMenu": "Display _MENU_",
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
	$('div.dataTables_filter input').attr("placeholder", "by batch");

}


$("#addfaculty").submit(function(event){
	var kk=$("#addfaculty").serialize();
	var target=document.getElementById('box1');
	var spinner=new Spinner().spin(target);
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../AddFaculty", //The URL from the form element where the AJAX request will be sent  
			data:kk,
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				spinner.stop();
				if("AEE"==data){
					notif({
						msg:"This email already exist,it must be unique",
						type:'warning',
						position:'center'
					});

				}else{
					if (data) { 
						notif({
							msg:"Faculty added Successfully",
							type:'success',
							position:'center'
						});
						$('#addfaculty').trigger("reset");
					}else{
						notif({
							msg:"Student not added, please try again",
							type:'error',
							position:'center'
						});
					}
				}
				
				
			},

			error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server  
				spinner.stop();
				notif({
					msg:"Some error occur, please try after some time.",
					type:'error',
					position:'center'
				});
			}  
		}); // Ajax close
	event.preventDefault();

});

$('#name').autocomplete({

	serviceUrl: '../AutoCompleteFaculty',
	paramName: "tagName",
	delimiter: ",",
	autoFocus: true,
	transformResult: function(response) {
		return {
			suggestions: $.map($.parseJSON(response), function(item) {
				return { value:"<img src=\"../ProfilePicture?id="+item.id +"\"  class=\"img-circle\"  style=\"height:25px;width:25px;\"> "+ item.name, data: item.id,name:item.name };
			})

		};
	},
	onSelect: function(a) {
		$("#name").val(a.name);
		$("#facultyid").val(a.data);
	}
	
	
});

$("#allot").click(function(event){
	if($("#name").val()==""||null===$("#batch").val()){
		return;
	}else{
		var kk=$("#changebatches").serialize();
		var target=document.getElementById('box1');
		var spinner=new Spinner().spin(target);
			$.ajax({  
				type: "POST", //The type of HTTP method (post, get, etc)  
				url: "../AllotBatches", //The URL from the form element where the AJAX request will be sent  
				data:kk,
				dataType: "json", //The type of response to expect from the server  
				success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
					spinner.stop();
					if (data) { 
						notif({
							msg:"Alloted successfully",
							type:'success',
							position:'center'
						});

						$('#changebatches').trigger("reset");
					}
					else{
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
						msg:"Some error occur, please try after some time!!!",
						type:'error',
						position:'center'
					});
				}  
			}); // Ajax close
		event.preventDefault();
	}


});
$("#deallot").click(function(event){
	if($("#name").val()==""||null===$("#batch").val()){
		return;
	}else{
		var kk=$("#changebatches").serialize();
		var target=document.getElementById('box1');
		var spinner=new Spinner().spin(target);
		
			$.ajax({  
				type: "POST", //The type of HTTP method (post, get, etc)  
				url: "../DeAllotBatches", //The URL from the form element where the AJAX request will be sent  
				data:kk,
				dataType: "json", //The type of response to expect from the server  
				success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
					spinner.stop();
					if (data) { 
						notif({
							msg:"Dealloted successfully",
							type:'success',
							position:'center'
						});

						$('#changebatches').trigger("reset");
					}
					else{
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
						msg:"Some error occur, please try after some time!!!",
						type:'error',
						position:'center'
					});
				}  
			}); // Ajax close
		event.preventDefault();
	}


});