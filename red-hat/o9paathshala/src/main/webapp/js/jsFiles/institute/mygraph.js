
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../MyResult", //The URL from the form element where the AJAX request will be sent  
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				if (data!= null) { 
					
					var testName=[];
					var score=[];
					$.each(data,function(k,v){
						testName.push(v.test);
						score.push(v.score);
					});
					showGraph(testName,score);
				}else{
					$('#graphAnalysis').text('No test found !!!');
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


function showGraph(test,score){

	var barChartData = {
			labels : test,
			datasets : [
			            {
			            	fillColor : "rgba(44, 118, 0, 0.5)",
			            	strokeColor : "rgba(44, 118, 0, 0.8)",
			            	highlightFill: "rgba(44, 118, 0, 0.75)",
			            	highlightStroke: "rgba(44, 118, 0, 1)",
			            	data : score
			            }
			            ]

	};

	var ctx = document.getElementById("canvas").getContext("2d");
	window.myBar = new Chart(ctx).Bar(barChartData, {
		responsive : true
	});
	update();
};
function update(){
	$.each(myBar.datasets[0].bars,function(k,v){
		if(v.value<50){
			v.fillColor = "rgba(175, 36, 7, 0.5)";
			v.strokeColor = "rgba(175, 36, 7, 0.8)";
			v.highlightFill = "rgba(175, 36, 7, 0.75)";
			v.highlightStroke = "rgba(175, 36, 7, 1)";
		}
	});


	myBar.update();
}