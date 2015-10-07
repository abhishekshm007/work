$(document).ready(function(){
$("#loginForm").submit(function(event){
		try{	
		$.ajax({  
		        type: "POST", //The type of HTTP method (post, get, etc)  
		        url: "CheckLogin", //The URL from the form element where the AJAX request will be sent  
		        data: $("#loginForm").serialize(), //All the data from the form serialized  
		        dataType: "json", //The type of response to expect from the server  
		        success: function (data, statusCode, xhr ) { //Triggered after a successful response from server  
		        	window.location.href="Login?req="+data+"&remember="+$("#remember_me").val();
		        },  
		        error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server  
		        	notif({
						msg : 'Your email or password is wrong, please try again...',
						type : 'error',
						position : 'center'
					});
		        	
		        }  
		        
		    }); // Ajax close
		}
		catch(e){
		alert(e);	
		}
		event.preventDefault();  // prevent to submit the form
		});	// Click close


$("#studentRegistartionForm").submit(function(event){
	try{	
	$.ajax({  
	        type: "POST", //The type of HTTP method (post, get, etc)  
	        url: "CheckLogin", //The URL from the form element where the AJAX request will be sent  
	        data: $("#loginForm").serialize(), //All the data from the form serialized  
	        dataType: "json", //The type of response to expect from the server  
	        success: function (data, statusCode, xhr ) { //Triggered after a successful response from server  
	        	window.location.href="Login?req="+data+"&remember="+$("#remember_me").val();
	        },  
	        error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server  
	        	notif({
					msg : 'Your email or password is wrong, please try again...',
					type : 'error',
					position : 'center'
				});
	        	
	        }  
	        
	    }); // Ajax close
	}
	catch(e){
	alert(e);	
	}
	event.preventDefault();  // prevent to submit the form
	});	// Click close



$("#instituteRegistrationForm").submit(function(event){
	try{	
	$.ajax({  
	        type: "POST", //The type of HTTP method (post, get, etc)  
	        url: "CheckLogin", //The URL from the form element where the AJAX request will be sent  
	        data: $("#loginForm").serialize(), //All the data from the form serialized  
	        dataType: "json", //The type of response to expect from the server  
	        success: function (data, statusCode, xhr ) { //Triggered after a successful response from server  
	        	window.location.href="Login?req="+data+"&remember="+$("#remember_me").val();
	        },  
	        error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server  
	        	notif({
					msg : 'Your email or password is wrong, please try again...',
					type : 'error',
					position : 'center'
				});
	        	
	        }  
	        
	    }); // Ajax close
	}
	catch(e){
	alert(e);	
	}
	event.preventDefault();  // prevent to submit the form
	});	// Click close
});