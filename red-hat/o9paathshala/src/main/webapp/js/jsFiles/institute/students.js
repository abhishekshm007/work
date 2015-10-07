function viewProfile(data){
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../StudentProfile", //The URL from the form element where the AJAX request will be sent  
			data:'studentid='+data,
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				if (data) { 
					$("#profilepic").html('<img src="../ProfilePicture?id='+data.id +'" alt="'+data.name+'" class="img-circle"  height="215px" width="215px"> ');
					$("#stuname").text(data.name);
					$("#semail").text(data.email);
					$("#sbatch").text(data.batch);
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
	bootbox.confirm('<span style="font-size:18px;">Are you sure you want to delete this Student?</span>', function(result) {
		if(result){

			var target=document.getElementById('box1');
			var spinner=new Spinner().spin(target);
				$.ajax({  
					type: "POST", //The type of HTTP method (post, get, etc)  
					url: "../DeleteStudent", //The URL from the form element where the AJAX request will be sent  
					data:'studentid='+data,
					dataType: "json", //The type of response to expect from the server  
					success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
						spinner.stop();
						if (data) { 
							notif({
								msg:"Deleted successfully",
								type:'success',
								position:'center'
							});

							$('#studentTable').dataTable().fnReloadAjax();
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
							msg:"Some error occur, please try after some time!!!",
							type:'error',
							position:'center'
						});
					}  
				}); // Ajax close

		}
	}); 
}


$("#addsinglestudent").submit(function(event){
	var kk=$("#addsinglestudent").serialize();
	var target=document.getElementById('box1');
	var spinner=new Spinner().spin(target);
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../AddSingleStudent", //The URL from the form element where the AJAX request will be sent  
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
							msg:"Student added Successfully",
							type:'success',
							position:'center'
						});
						$('#addsinglestudent').trigger("reset");
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
