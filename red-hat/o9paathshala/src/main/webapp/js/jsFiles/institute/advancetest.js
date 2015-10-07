var startDate = document.getElementById("startdate");
		startDate.addEventListener("input", function() {

			var value = Math.floor(new Date(startDate.value).getTime());
			var current=(Math.floor(new Date().getTime()));
			if (value < current) {
				startDate.setCustomValidity("Start date must be after now!");
			} else {
				startDate.setCustomValidity("");
			}

		});
		var endDate = document.getElementById("enddate");
		endDate.addEventListener("input", function() {

			var value = Math.floor(new Date(endDate.value).getTime());
			var current=(Math.floor(new Date().getTime()));
			if (value < current) {
				endDate.setCustomValidity("End date must be after now!");
			} else {
				endDate.setCustomValidity("");
			}

		});

function setTestId(data){
	$("#tid").val(data);
	}

$("#createtest").submit(function(event){
	var kk=$("#createtest").serialize();
	var target=document.getElementById('box1');
	var spinner=new Spinner().spin(target);
		$.ajax({  
			type: "POST", 
			url: "../AdvanceTest", 
			data:kk,
			dataType: "json",  
			success: function ( data, statusCode, xhr ) { 
				spinner.stop();

				if("AEN"==data){
					notif({
						msg:"This test name already exist,it must be unique!!!",
						type:'error',
						position:'center'
					});
				}else{
					if("success" == data){
						getTestDetail($("#testname").val());
						$("#box1").hide();
						$("#box2").show();
					}else{
						notif({
							msg:"Some error occur please try after some time!!!",
							type:'error',
							position:'center'
						});
					}
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

function getTestDetail(data){
	$.ajax({  
		type: "POST", //The type of HTTP method (post, get, etc)  
		url: "../GetAdvanceTestDetail", //The URL from the form element where the AJAX request will be sent  
		data:"testname="+data,
		dataType: "json", //The type of response to expect from the server  
		success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
			if (data) { 
				$("#testid").val(data.id);
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

$("#tq").change(function(event){
	$("#omrsheet").show();
	var total=$("#tq").val();
	var data='<tr><td>#</td><td>A</td><td>B</td><td>C</td><td>D</td></tr>';
	
	for(var i=0;i<total;i++){
		data=data+'<tr><td>'+(i+1)+'</td><td><input type="checkbox" name="'+(i+1)+'" value="1" ></td><td><input type="checkbox" name="'+(i+1)+'" value="2" ></td><td><input type="checkbox" name="'+(i+1)+'" value="3" ></td><td><input type="checkbox" name="'+(i+1)+'" value="4" ></td></tr>';
	}
	
	$("#options").html(data);
	
});

function createTest(form, action_url) {
	

	var target = document.getElementById('box2');
	var spinner = new Spinner().spin(target);
	$(target).find("*").attr('disabled', false);
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
		content = JSON.parse(content);
		if ("RE" == content) {
			notif({
				msg : "You have to provide answers of all questions",
				type : 'warning',
				position : 'center'
			});

		} else {
			if (content) {
				notif({
					msg : "Saved successfully.",
					type : 'success',
					position : 'center'
				});
				$(form).trigger("reset");
				$("#createtest").trigger("reset");
				$("#box1").show();
				$("#box2").hide();
				$("#omrsheet").hide();
			} else {
				notif({
					msg : "Operation failed, please check your data.",
					type : 'error',
					position : 'center'
				});
			}
		}
		setTimeout('iframeId.parentNode.removeChild(iframeId)', 250);
	};
	if (iframeId.addEventListener) {
		iframeId.addEventListener("load", eventHandler, true);
	}
	if (iframeId.attachEvent) {
		iframeId.attachEvent("onload", eventHandler);
	}
	form.setAttribute("target", "upload_iframe");
	form.setAttribute("action", action_url);
	form.setAttribute("method", "post");
	form.setAttribute("enctype", "multipart/form-data");
	form.setAttribute("encoding", "multipart/form-data");
	form.submit();

}
