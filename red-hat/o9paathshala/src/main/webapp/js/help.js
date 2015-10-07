
$("#contactform").submit(function(event){

	var kk=$("#contactform").serialize();
	var target=document.getElementById('box');
	var spinner=new Spinner().spin(target);
	try{	

		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "SendMessage", //The URL from the form element where the AJAX request will be sent  
			data:kk,

			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				spinner.stop();
				$("#contactform").trigger('reset');
				if (data) { 
					notif({
						msg:"Submitted successfully",
						type:'success',
						position:'center'
					});
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
					msg:"Some error occur please try after some time!!!",
					type:'error',
					position:'center'
				});
			} 
		}); // Ajax close
	}
	catch(e){
		alert(e);	
	}
	event.preventDefault();

});