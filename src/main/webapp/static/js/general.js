"use strict";

$(document).ready(function() {
	
	$("#deleteRoundButton").click(function(e) {
		var currentUrl = window.location.href;
		var deleteUrl = currentUrl.replace("editround","deleteround");
		window.location.href = deleteUrl;
	});
	
	
});

function addRoundToTask(id)
{
    var currentUrl = window.location.href;
	var addUrl = currentUrl+"/addRound/"+id;
	// window.location.href = addUrl;
	$.post( addUrl, function() {
		window.location.href = currentUrl;
		});
}

function removeRoundFromTask(id)
{
    var currentUrl = window.location.href;
	var removeUrl = currentUrl+"/removeRound/"+id;
	// window.location.href = removeUrl;
	$.post( removeUrl, function() {
		window.location.href = currentUrl;
	});
}

