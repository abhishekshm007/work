var p=0;
$("#test").change(function(){
	$("#rank").text('');
	$("#v").show();
	$("#yourrank").show();
	$('#leaderBoardTable').dataTable({
		"bAutoWidth" : false,
		"bPagination":false,
		"bProcessing": true,
		"bDestroy": true,
		"bSort":false,
		"bFilter":false,
		"scrollY":"200px",
		"scrollCollapse":true,
		"paging":false,
		"bServerSide": true,
		"sAjaxSource": "../LeaderBoard?id="+$("#test").val()+"&limit="+200,
		"bJQueryUI" : true,
		"bStateSave": true,
		"aoColumnDefs": [
		                 {
		                	 "aTargets": [ 2 ], // Column to target
		                	 "mRender": function ( data, type, full ) {
                                 if(full.id==$("#sid").val()){
                                	 $("#rank").text(full.rank);
                                 }
		                		 return full.rank;
		                	 }
		                 }
		                 ],
		                 "aoColumns": [
		                               { "mData": "name" },
		                               { "mData": "score"},
		                               { "mData": "rank" },
		                               {"mData":"attempt"}
		                               ],
		                               "oLanguage": {
		                            	   "sLengthMenu": "",
		                            	   "sZeroRecords": "Nothing found - sorry",
		                            	   "sInfo": "",
		                            	   "sInfoEmpty": " ",
		                            	   "sInfoFiltered": ""
		                               }

	});	
	
});