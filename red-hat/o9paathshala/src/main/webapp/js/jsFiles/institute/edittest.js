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
	
$("#testid").val($("#tid").val());
	$("#testname").val($("#tname").val());
	$("#duration").val($("#tduration").val());
	$("#positivemark").val($("#tpmark").val());
	$("#negativemark").val($("#tnmark").val());
	$("#startdate").val(parseTimeDate(new Date($("#tsdate").val())));
	$("#enddate").val(parseTimeDate(new Date($("#tedate").val())));
$("#edittest").submit(function(event){
	var kk=$("#edittest").serialize();
	var target=document.getElementById('box1');
	var spinner=new Spinner().spin(target);
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../EditTest", //The URL from the form element where the AJAX request will be sent  
			data:kk,
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				spinner.stop();
				if("AEN"==data){
					notif({
						msg:"This test name already exist,it must be unique.",
						type:'warning',
						position:'center'
					});

				}else{
					if(data){
						notif({
							msg:"Saved Successfully",
							type:'success',
							position:'center'
						});
						window.history.back();
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

//function getURLParameter(sParam){
//	var sPageURL=window.location.search.substring(1);
//	var sURLVariables=sPageURL.splits('&');
//	for(var i=0;i<sURLVariables.length;i++){
//		var sParameterName=sURLVariables[i].split('=');
//		if(sParameterName[0]==sParam){
//			return sParameterName[1];
//		}
//	}
//}