var input=document.getElementById("answer");
	var regex= /^[1-6](,[1-6])*$/;
	input.addEventListener("input", function() {
	if(!regex.test(input.value)){
		input.setCustomValidity("Answer must be in 1,2,...,6 or 1 form");
	}else {
		input.setCustomValidity("");
	}

	});
$("#addsinglequestion").submit(function(event){
	var kk=$("#addsinglequestion").serialize();
	var target=document.getElementById('box1');
	var spinner=new Spinner().spin(target);
					$.ajax({  
						type: "POST", //The type of HTTP method (post, get, etc)  
						url: "../AddSingleQuestion", //The URL from the form element where the AJAX request will be sent  
						data:kk,
						
						dataType: "json", //The type of response to expect from the server  
						success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
							spinner.stop();
							if (data) { 
								notif({
									msg:"Question added successfully",
									type:'success',
									position:'center'
								});

									$('#addsinglequestion').trigger("reset");
								}else{
									notif({
										msg:"Question not added",
										type:'error',
										position:'center'
									});

								}
								
							
						},  
						error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server  
							spinner.stop();
							notif({
								msg:"Some error occur please try after some time",
								type:'error',
								position:'center'
							});
						} 
					}); // Ajax close
		
});
