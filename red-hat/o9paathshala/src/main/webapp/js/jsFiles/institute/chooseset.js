var sets=[];
var amount=0;
$('#settable').dataTable({
	"bAutoWidth" : false,
	"bPagination":true,
	"bProcessing": true,
	"bServerSide": true,
	"bSort":false,
	"bDestroy": true,
	"bSort" : false,
	"sAjaxSource": "../GetAllSet",
	"bJQueryUI" : true,
	"fnRowCallback": function( row, data ) {
		sets.filter(function(v,i) {  
			if (v === data.id.toString())
			{ 
				$(':checkbox', row).prop('checked',true);
			}
		});
	},
	"aoColumnDefs": [
{
	"aTargets": [ 1 ], // Column to target
	"mRender": function ( data, type, full ) {
		// 'full' is the row's data object, and 'data' is this column's data
		// e.g. 'full[0]' is the comic id, and 'data' is the comic title
		return '100';
	}
},
{
	"aTargets": [ 3 ], // Column to target
	"mRender": function ( data, type, full ) {
		// 'full' is the row's data object, and 'data' is this column's data
		// e.g. 'full[0]' is the comic id, and 'data' is the comic title
		return '<a data-toggle="modal" class="fakeLink" data-target="#questions" onclick="setQuestions('+full.id+')">  Details</a>';
	}
},
{
	"aTargets": [ 4 ], // Column to target
	"mRender": function ( data, type, full ) {

		// 'full' is the row's data object, and 'data' is this column's data
		// e.g. 'full[0]' is the comic id, and 'data' is the comic title
		return '<input type="checkbox" class="flat-red" id='+full.id+'>';
	}
},
],
"aoColumns": [
              { "mData": "name" },
              { "mData": "id" },
              { "mData": "subject" },
              { "mData": "id" },
              { "mData":"id"},
              ],
              "oLanguage": {
            	  "sLengthMenu": "Display _MENU_ records per page",
            	  "sZeroRecords": "Nothing found - sorry",
            	  "sInfo": "Showing _START_ to _END_ of _TOTAL_ records",
            	  "sInfoEmpty": "Showing 0 to 0 of 0 records",
            	  "sInfoFiltered": "(filtered from _MAX_ total records)"
              },

});
$('div.dataTables_filter input').attr("placeholder", "by subject");
$('#settable tbody').on('click', ':checkbox', function () {
	var id = this.id;
	var result = false;
	sets.filter(function(v,i) {  
		if (v === id)
		{
			result=true;
			sets.splice(i,1);
			deduct();
			return;
		} 
	}); 
	if(!result){
		sets.push(id);
		add();
	}
	
} );
$("#buy1,#buy2").click(function(event){
		$.ajax({  
			type: "POST", //The type of HTTP method (post, get, etc)  
			url: "../CalculateAmount", //The URL from the form element where the AJAX request will be sent  
			data:'sets='+sets+'&package='+GetUrlValue("package"),
			dataType: "json", //The type of response to expect from the server  
			success: function ( data, statusCode, xhr ) { //Triggered after a successful response from server  
				if(data=="error"){
                    	 notif({
								msg:"Some error occur in processing your request!!!",
								type:'error',
								position:'center'
							});
                     }else{
                    	 if(data==0){
                    		 notif({
 								msg:"You need to select at least one set!!!",
 								type:'warning',
 								position:'center'
 							});
                    	 }else{
                    		 $("#totalamount").html("&#8377;"+data.totalAmount+"  ["+data.amount+"+"+data.tax+"<small> (service tax)</small>]");
                    		 $("#sets").text(data.sets.length);
                    		 $("#pack").text(data.pack+" months");
                    		 $("#orderdetail").modal('show');
                    	 }
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

});
function deduct(){
	$("#amount").text((+$("#amount").text())-(+GetUrlValue("price")));
}
function add(){
	
	$("#amount").text((+$("#amount").text())+(+GetUrlValue("price")));
}

function GetUrlValue(VarSearch){
    var SearchString = window.location.href;
    var VariableArray = SearchString.split('&');
    for(var i = 0; i < VariableArray.length; i++){
        var KeyValuePair = VariableArray[i].split('=');
        if(KeyValuePair[0] == VarSearch){
            return KeyValuePair[1];
        }
    }
    
    
}