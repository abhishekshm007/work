var d;
        $.ajax({
            type: "POST", //The type of HTTP method (post, get, etc)
            url: "../Security", //The URL from the form element where the AJAX request will be sent
            dataType: "json", //The type of response to expect from the server
            success: function (data, statusCode, xhr ) { //Triggered after a successful response from server
            	d=data;
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
	
//    $("#startTest").submit(function(event){
//    	$('#starttest').modal('hide');
//    	try{
//            $.ajax({
//                type: "POST", //The type of HTTP method (post, get, etc)
//                url: "../StartTest", //The URL from the form element where the AJAX request will be sent
//                data: $("#startTest").serialize(), //All the data from the form serialized
//                dataType: "json", //The type of response to expect from the server
//                success: function (data, statusCode, xhr ) { //Triggered after a successful response from server
//                	if(data=="NO"){
//                		bootbox.alert('Cannot start this test.');
//                		return;
//                	}
//                	var attempts=CryptoJS.AES.encrypt("4",d);
//                	tdata = CryptoJS.AES.encrypt(JSON.stringify(data),d);
//                	if(localStorage){
//                		localStorage.setItem('0100000007',attempts);
//                		localStorage.setItem('0100000001',tdata);
//                		var markArray=[];
//                		var status=false;
//                		localStorage.setItem('status',CryptoJS.AES.encrypt(JSON.stringify(status),d));
//                		localStorage.setItem('markArray',JSON.stringify(markArray)); 
//                		window.open('../test/test.html', 'Test', 'window settings');
//                	        window.tools.close.setStyle({display:"none"});
//                	        return false;
//                	}else{
//                		alert("Your browser is outdated please update it to give test on o9paathshala");
//                	}
//                },
//                error: function ( xhr, errorType, exception ) { //Triggered if an error communicating with server
//                    var errorMessage = exception || xhr.statusText; //If exception null, then default to xhr.statusText
//
//                    alert( "There was an error creating your contact: " + errorMessage );
//                }
//            }); // Ajax close
//        }
//        catch(e){
//            alert(e);
//        }
//        event.preventDefault();  //
//    });
function te(){
      $.ajax({
      type: "POST", //The type of HTTP method (post, get, etc)
      url: "../StartTest", //The URL from the form element where the AJAX request will be sent
      data: $("#startTest").serialize(), //All the data from the form serialized
      dataType: "json", //The type of response to expect from the server
      async: false,
      success: function (data, statusCode, xhr ) { //Triggered after a successful response from server
      	if(data=="NO"){
      		var errorMessage="Can not start this test!!!"
      		notif({
      			msg:errorMessage,
      			type:'error',
      			position:'center'
      			});
      		return;
      	}
      	var attempts=CryptoJS.AES.encrypt("4",d);
      	tdata = CryptoJS.AES.encrypt(JSON.stringify(data),d);
      	if(localStorage){
      		localStorage.setItem('0100000007',attempts);
      		localStorage.setItem('0100000001',tdata);
      		var markArray=[];
      		var status=false;
      		localStorage.setItem('status',CryptoJS.AES.encrypt(JSON.stringify(status),d));
      		localStorage.setItem('markArray',JSON.stringify(markArray)); 
      		window.open('../test/test.html', '_blank');
      	       
      	        return false;
      	}else{
      		var message="Your browser is outdated please update it to give test on o9paathshala";
      		notif({
      			msg:message,
      			type:'error',
      			position:'center'
      			});
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
function testInstructions(id,duration){
	$("#duration").text(duration);
	$("#test").val(id);
}