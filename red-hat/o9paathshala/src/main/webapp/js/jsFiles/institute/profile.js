function editProfile(){
	$("#e").val($("#email").text());
	$("#n").val($("#name").text());
	$("#c").val($("#contact").text());
	$("#a").val($("#address").text());
}

function editUserProfile(){
	$("#e").val($("#email").text());
	$("#n").val($("#name").text());
	$("#c").val($("#contact").text());
	$("#a").val($("#address").text());
	$("#g").val($("#gender").text());
	$("#d").val(parseDate(new Date($("#dob").text())));
}
$("#profileForm").submit(function(event){


	var kk=$("#profileForm").serialize();
	var target=document.getElementById('box');
	var spinner=new Spinner().spin(target);
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../UpdateInstituteProfile", //The URL from the form element where the AJAX request will be sent  
			data:kk,

			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				spinner.stop();
				if (data) { 
					notif({
						msg:"Saved successfully",
						type:'success',
						position:'center'
					});
					//$("#1img").html('<img src="../ProfilePicture?id=${user.id}" class="img-circle" alt="User Image" />');
					//$("#2img").html('<img src="../ProfilePicture?id=${user.id}" class="img-circle" alt="User Image" />');

					$('#editprofile').modal('toggle');
					$("#name").text($("#n").val());
					$("#email").text($("#e").val());
					$("#contact").text($("#c").val());
					$("#address").text($("#a").val());
					$("#email1").text($("#e").val());
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
	event.preventDefault();

});
$("#userProfileForm").submit(function(event){


	var kk=$("#userProfileForm").serialize();
	var target=document.getElementById('box');
	var spinner=new Spinner().spin(target);
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../UpdateProfile", //The URL from the form element where the AJAX request will be sent  
			data:kk,

			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				spinner.stop();
				if (data) { 
					notif({
						msg:"Saved successfully",
						type:'success',
						position:'center'
					});

					$('#editprofile').modal('toggle');
					$("#name").text($("#n").val());
					$("#name1").text($("#n").val());
					$("#email").text($("#e").val());
					$("#contact").text($("#c").val());
					$("#address").text($("#a").val());
					$("#gender").text($("#g").val());
					$("#dob").text($("#d").val());
					$("#1img").html('<img style="height:90px; width:90px;" src="../ProfilePicture?id=dp" class="img-circle" alt="User Image" /><p>'+$("#n").val()+'<small>'+$("#e").val()+'</small></p>');
					$("#topname").html($("#n").val()+'<i class="caret"></i>');
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
	event.preventDefault();

});

$("#passwordForm").submit(function(event){
	var kk=$("#passwordForm").serialize();
	var target=document.getElementById('box');
	var spinner=new Spinner().spin(target);
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../UpdatePassword", //The URL from the form element where the AJAX request will be sent  
			data:kk,
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				spinner.stop();
				if (data) { 
					notif({
						msg:"Saved successfully",
						type:'success',
						position:'center'
					});
					$('#editpassword').modal('toggle');
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


});




function uploadDp(form, action_url){
	var target=document.getElementById('box5');
	var spinner=new Spinner().spin(target);
	$(target).find( "*" ).attr('disabled', false);
	var iframe = document.createElement("iframe");
	iframe.setAttribute("id", "upload_iframe");
	iframe.setAttribute("name", "upload_iframe");
	iframe.setAttribute("width", "0");
	iframe.setAttribute("height", "0");
	iframe.setAttribute("border", "0");
	iframe.setAttribute("style", "width: 0; height: 0; border: none;");
	form.parentNode.appendChild(iframe);
	window.frames['upload_iframe'].name = "upload_iframe";
	iframeId = document.getElementById("upload_iframe");
	var eventHandler = function() {
		if (iframeId.detachEvent)
			iframeId.detachEvent("onload", eventHandler);
		else
			iframeId.removeEventListener("load", eventHandler, false);
		if (iframeId.contentDocument) {
			content = iframeId.contentDocument.body.innerHTML;
		} else if (iframeId.contentWindow) {
			content = iframeId.contentWindow.document.body.innerHTML;
		} else if (iframeId.document) {
			content = iframeId.document.body.innerHTML;
		}
		spinner.stop();
		content=JSON.parse(content);
		
			if(content){
				notif({
					msg:"Saved successfully.",
					type:'success',
					position:'center'
				});
				$('#editdp').modal('toggle');
				$("#dp").html('<img src="../ProfilePicture?id=dp&t='+new Date().getTime() +'" class="img-circle" alt="User Image" style="height: 180px; width: 180px;" />Edit');
				$("#1img").html('<img style="height:90px; width:90px;" src="../ProfilePicture?id=dp&t='+new Date().getTime() +'" class="img-circle" alt="User Image" /><p>'+$("#name1").text()+'<small>'+$("#email").text()+'</small></p>');
				
				$("#2img").html('<img style="height:45px; width:45px;" src="../ProfilePicture?id=dp&t='+new Date().getTime() +'" class="img-circle" alt="User Image" style="height: 180px; width: 180px;" />');
				
			}else{
				notif({
					msg:"Operation failed, please try after some time.",
					type:'error',
					position:'center'
				});
			}
		
		setTimeout('iframeId.parentNode.removeChild(iframeId)', 250);
	};
	if (iframeId.addEventListener){
		iframeId.addEventListener("load", eventHandler, true);
	}
	if (iframeId.attachEvent){
		iframeId.attachEvent("onload", eventHandler);
	}
	form.setAttribute("target", "upload_iframe");
	form.setAttribute("action", action_url);
	form.setAttribute("method", "post");
	form.setAttribute("enctype", "multipart/form-data");
	form.setAttribute("encoding", "multipart/form-data");
	form.submit();


}