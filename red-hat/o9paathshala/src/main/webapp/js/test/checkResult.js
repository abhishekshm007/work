var k;
var data; 
$(document).ready(function(event){
if(localStorage.getItem("1100000001") != null){
$.ajax({
            type: "POST", //The type of HTTP method (post, get, etc)
            url: "../Security", //The URL from the form element where the AJAX request will be sent
            dataType: "json", //The type of response to expect from the server
            success: function (data, statusCode, xhr ) { //Triggered after a successful response from server
            	k=data;
            	data=JSON.parse(CryptoJS.AES.decrypt(localStorage.getItem('1100000001'),k).toString(CryptoJS.enc.Utf8));
            	$.ajax({  
            		type: "POST", //The type of HTTP method (post, get, etc)  
            		url: "../SaveResult", //The URL from the form element where the AJAX request will be sent  
            		data:'testid='+data.id+'&testname='+data.testName+'&mac='+cR(data),
            		dataType: "json", //The type of response to expect from the server  
            		success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
            			localStorage.removeItem('status');
            			localStorage.removeItem('1100000001');
            		},  
            		error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server  
            		}  
            	});
            },
            error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server
                var errorMessage = exception || xhr.statusText; //If exception null, then default to xhr.statusText
                notif({
                	msg:errorMessage,
                	type:'error',
                	position:'center'
                	});
            }
        });
}
});

function cR(resultObject){
	var tcorrect=0;
	var tincorrect=0;
	var tattempted=0;
	var tpercent=0;
	$.each( resultObject.sections, function( key, value ) {
		var marks=0;
		var correct=0;
		var incorrect=0;
		var attempted=0;
		$.each(value.questions, function(k,v) {
			marks=marks+v.marks;
			if(v.attempted){
				attempted++;
				if(v.marks>0){
					correct++;
				}else{
					incorrect++;
				}
			}
		});
		var percent=((marks*100)/(value.questions.length*resultObject.positiveMark));
		tcorrect=(tcorrect+correct);
		tattempted=(tattempted+attempted);
		tincorrect=(tincorrect+incorrect);
		tpercent=(tpercent+percent);
	});
	tpercent=(tpercent/resultObject.sections.length);
	return tpercent;
}
