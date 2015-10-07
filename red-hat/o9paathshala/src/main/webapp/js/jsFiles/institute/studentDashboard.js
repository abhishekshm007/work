var p=0;
$("#dtest").change(function(){
	$("#v").show();
	$('#leaderBoardTable').dataTable({
		"bAutoWidth" : false,
		"bPagination":false,
		"bProcessing": true,
		"bDestroy": true,
		"bFilter":false,
		"bSort":false,
		"scrollY":"200px",
		"scrollCollapse":true,
		"paging":false,
		"bServerSide": true,
		"sAjaxSource": "../LeaderBoard?id="+$("#dtest").val()+"&limit="+20,
		"bJQueryUI" : true,
		"bStateSave": true,
		 "aoColumns": [
		                               { "mData": "name" },
		                               { "mData": "score"},
		                               { "mData": "rank" },
		                               {"mData":"attempt"}
		                               ],
		                               "oLanguage": {
		                            	   "sLengthMenu": "",
		                            	   "sZeroRecords": "Nothing found - sorry",
		                            	   "sInfo": "Showing _START_ to _END_ of _TOTAL_ records",
		                            	   "sInfoEmpty": "",
		                            	   "sInfoFiltered": ""
		                               }

	});	
	
});