
function deleteConfirm(data){
	bootbox.confirm('<span style="font-size:18px;">Are you sure you want to delete this Subject?', function(result) {
		if(result){
			var target=document.getElementById('box1');
			var spinner=new Spinner().spin(target);
				$.ajax({  
					type: "POST", //The type of HTTP method (post, get, etc)  
					url: "../DeleteSubject", //The URL from the form element where the AJAX request will be sent  
					data:'id='+data,
					dataType: "json", //The type of response to expect from the server  
					success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
						spinner.stop();
						if (data) { 
							notif({
								msg:"Deleted successfully",
								type:'success',
								position:'center'
							});
							$('#subjectTable').dataTable().fnReloadAjax();
						}
						else{
							notif({
								msg:"Deleted failed",
								type:'error',
								position:'center'
							});
						}

					},  
					error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server  
						spinner.stop();
						notif({
							msg:"Some error occur, please try after some time",
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
		title: "Edit Subject Title",
		value: data,
		callback: function(result) {
			if (!(result === null)) {
					var target=document.getElementById('box1');
					var spinner=new Spinner().spin(target);
					$.ajax({  
						type: "POST", //The type of HTTP method (post, get, etc)  
						url: "../EditSubject", //The URL from the form element where the AJAX request will be sent  
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
								$('#subjectTable').dataTable().fnReloadAjax();
							}
							else{
								notif({
									msg:"Operation failed",
									type:'error',
									position:'center'
								});
							}

						},  
						error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server  
							spinner.stop();
							notif({
								msg:"Some error occur, please try after some time",
								type:'error',
								position:'center'
							});
							}  
					}); // Ajax close

			} 
		}
	});
}

$("#addsubject").submit(function(event){
	var kk=$("#addsubject").serialize();
	var target=document.getElementById('box1');
	var spinner=new Spinner().spin(target);
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../AddSubject", //The URL from the form element where the AJAX request will be sent  
			data:kk,
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				spinner.stop();
				if (data) { 
					notif({
						msg:"Added successfully",
						type:'success',
						position:'center'
					});
					/*$('#subjectTable').dataTable().fnReloadAjax();*/
					$('#addsubject').trigger('reset');
				}
				else{
					notif({
						msg:"Operation failed",
						type:'error',
						position:'center'
					});
				}

			},  
			error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server  
				spinner.stop();
				notif({
					msg:"Some error occur, please try after some time",
					type:'error',
					position:'center'
				});
				}  
		}); // Ajax close

	event.preventDefault();

});