var k;
var object;

$(function() {
	$("#back1").hide();
	$("#back2").hide();
        $.ajax({
            type: "POST", //The type of HTTP method (post, get, etc)
            url: "../Security", //The URL from the form element where the AJAX request will be sent
            dataType: "json", //The type of response to expect from the server
            success: function (data, statusCode, xhr ) { //Triggered after a successful response from server
            	k=data;
            	if(null!=localStorage.getItem('1100000001')){
            	object=JSON.parse(CryptoJS.AES.decrypt(localStorage.getItem('1100000001'),k).toString(CryptoJS.enc.Utf8));
            	} 	else{
            		object=JSON.parse(CryptoJS.AES.decrypt(localStorage.getItem('1100000011'),k).toString(CryptoJS.enc.Utf8));
            	}
            	displayResult(object);

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
    $('#details').click(function(){
    	$("#details").hide();
    	$("#back2").show();
    	$("#back1").show();
       var count = 1;
    	var data="";
    	$.each( object.sections, function( key, value ) {
    		data=data+"<div class=\"panel panel-default\"><div class=\"panel-heading\" style=\"background-color:#4893BE; color:white; font-weight: bold;\">"+value.sectionName +"</div><div class=\"panel-body\"><table id=\""+(count++)+"\" class=\"table table-bordered  dataTable\"><thead style=\"font-weight:bold;\"><tr><td>Question</td><td>Correct Answer</td><td>Your Answer</td><td>Marks</td></tr></thead><tbody>";

        	$.each( value.questions, function(k,v) {
        		var c="";
        		var u="";
        		$.each( v.correctOptions, function(m,n) {
            		c=c+" "+(m+1)+". "+v.options[n-1];
            	});
        		$.each( v.userAnswers, function(p,q) {
            		u=u+" "+(p+1)+". "+v.options[q-1];
            	});
        		if(v.marks>0){
        			data=data+"<tr class=\"bg-success\"><td>"+v.content+"</td><td>"+c+"</td><td>"+u+"</td><td>"+v.marks+"</td></tr>";
        		}else{
        			data=data+"<tr class=\"bg-danger\"><td>"+v.content+"</td><td>"+c+"</td><td>"+u+"</td><td>"+v.marks+"</td></tr>";
        		}
        	});
        	data=data+"</tbody></table></div></div>";
    	});
    	$("#result").html(data);
    	for(var i=1;i<=count;i++){
    	  $(function() {
              $("#"+i).dataTable({
                  "bPaginate": true,
                  "bLengthChange": true,
                  "bFilter": true,
                  "bSort": true,
                  "bInfo": true,
                  "bAutoWidth": true,
                  "sDom": 'T<"clear">lfrtip' ,
		            
		            "oTableTools": {
		            	"aButtons": [
		            					
		            					
		            					{
		            						"sExtends":    "collection",
		            						"sButtonText": "Save",
		            						"aButtons":    [ "csv", "xls", "pdf" ]
		            					}
		            				],
		                "sSwfPath": "extras/TableTools/media/swf/copy_csv_xls_pdf.swf"
		            }  
              });
              $('div.dataTables_filter input').attr("placeholder", "by question");
          });
    	}
	});
});

function displayResult(resultObject){
	var tcorrect=0;
	var tincorrect=0;
	var tattempted=0;
	var tpercent=0;
	var data="<table class=\"table table-bordered table-striped\" ><thead><tr style=\"font-weight:bold;\" ><td>Section</td><td>Attempted</td><td>Correct</td><td>Incorrect</td><td>% Marks</td></tr></thead><tbody>";
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
		data=data+"<tr><td>"+value.sectionName+"</td><td>"+attempted+"</td><td>"+correct+"</td><td>"+incorrect+"</td><td>"+percent+"</td></tr>";
	});
	tpercent=(tpercent/resultObject.sections.length);
	data=data+"<tr style=\"background-color:#4893BE; color:red; font-weight: bold;\"><td>Total</td><td>"+tattempted+"</td><td>"+tcorrect+"</td><td>"+tincorrect+"</td><td>"+tpercent+"</td></tr>";
	data=data+"<tbody></table>";
	$("#result").html(data);
	
}
$(window).bind('unload', function(){
	
	if(CryptoJS.AES.decrypt(localStorage.getItem('status'),k).toString(CryptoJS.enc.Utf8)){
		if(null!=localStorage.getItem('1100000001')){
		localStorage.setItem('1100000011',localStorage.getItem('1100000001'));
		}
		localStorage.removeItem('status');
		localStorage.removeItem('1100000001');
	}
});