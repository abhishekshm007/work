
function viewStudentReport(data,name){
	$("#sid").val(data);
	$("#sname").val(name);
//	document.cookie="sid="+data;
//	document.cookie="sname="+name;
}
$('#resultTable').dataTable({
	"bAutoWidth" : false,
	"bPagination":true,
	"bProcessing": true,
	"bServerSide": true,
	"bSort":false,
	"bDestroy": true,
	"sAjaxSource": "../GetAllResults?batchid="+$("#batch").val()+"&testid="+$("#test").val(),
	"bJQueryUI" : true,
	"aoColumnDefs": [
	                 {
	                	 "aTargets": [ 1 ], // Column to target
	                	 "mRender": function ( data, type, full ) {
	                		 // 'full' is the row's data object, and 'data' is this column's data
	                		 // e.g. 'full[0]' is the comic id, and 'data' is the comic title
	                		 return '<a data-toggle="modal" ui-sref="studentgraph" href="#/studentgraph" onclick="viewStudentReport('+full.studentid+','+'\''+full.student+'\''+')">'+full.student+'</a>';
	                	 }
	                 }
	                 ],
	"aoColumns": [
{ "mData": "batch" },
{ "mData": "student" },
{ "mData":"test"},
{ "mData": "score"},
{ "mData": "attempt"},
{ "mData": "attemptdate"}
],
"oLanguage": {
	"sLengthMenu": "Display _MENU_ records per page",
	"sZeroRecords": "Nothing found - sorry",
	"sInfo": "Showing _START_ to _END_ of _TOTAL_ records",
	"sInfoEmpty": "Showing 0 to 0 of 0 records",
	"sInfoFiltered": "(filtered from _MAX_ total records)"
},
"sDom": 'T<"clear">lfrtip' ,

"oTableTools": {
	"aButtons": [
	             {
	            	 "sExtends":    "collection",
	            	 "sButtonText": "Save",
	            	 "aButtons":    [ "csv", "xls", "pdf" ]
	             }
	             ],
	             "sSwfPath": "../extras/TableTools/media/swf/copy_csv_xls_pdf.swf"
} 

});
$('div.dataTables_filter input').attr("placeholder", "by student name");
$("#show").click(function(event){
	var table = $('#resultTable').dataTable();
	  table.fnReloadAjax( "../GetAllResults?batchid="+$("#batch").val()+"&testid="+$("#test").val());
});