$("#switchinstitute").submit(function(event){
	var kk=$("#switchinstitute").serialize();
	var target=document.getElementById('box');
		var spinner=new Spinner().spin(target);
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../Switch", //The URL from the form element where the AJAX request will be sent  
			data:kk,
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				spinner.stop();
				if (data) { 
					window.location.href="../"+data+".jsp";
					}
					else{
						notif({
							msg:"Wrong password...",
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