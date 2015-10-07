/**
 * 
 */

function tagLoad(){
	var target = document.getElementById('content');
	var spinner = new Spinner().spin(target);
	var tags=document.forms["questionForm"]["tags"].value;
	if(tags != null && tags != ""){
	angular.element(document.getElementById('tagPopover')).scope().changeInTag(tags);
	}
	else{
		$('#tagPopover').popover("hide",{"container": "body"},{ "selector": "#viewport", "padding": 0 });
	}
	spinner.stop();
}

$('body').on('click', function (e) {
    $('[data-toggle="popover"]').each(function () {
        //the 'is' for buttons that trigger popups
        //the 'has' for icons within a button that triggers a popup
        if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {
            $(this).popover('hide');
        }
    });
});


var tagCount = 0;
var tagId = "";
var separator = ",";
function attachTag(tagName,theme,id){
	if(tagCount < 5){
		if(tagId.indexOf(id) >= 0 && tagId.length > 0){
			notif({
				msg : "You can't attach same tag"
			});
			return;
		}
		tagCount++;
	$("#tagRow").append('<span style="padding: 0px 15px 0px 10px;" class="t t-dismissable callout callout-'+theme+' ">\
			<button type="button" onclick="return removeTag('+id+');" style="padding: 0;cursor: pointer;background: transparent;border: 0;-webkit-appearance: none;" \
			data-dismiss="alert" aria-hidden="true"><i class="fa fa-fw fa-times-circle"></i></button> '+ tagName +' </span>');
	$("#tagPopover").val("");
	$('#tagPopover').popover("destroy");
	tagId = tagId +separator+ id;
	angular.element(document.getElementById('tagValue')).scope().tagIds = tagId;
	document.getElementById("tagPopover").focus();
	}
	else{
		notif({
			msg : "You cannot attach more than 5 tags"
		});
	}
}


function removeTag(id){
	tagId = tagId.replace(","+id,"");
	angular.element(document.getElementById('tagValue')).scope().tagIds = tagId;
	tagCount--;
}