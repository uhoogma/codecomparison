"use strict";

$(document).ready(function() {
	$("#deleteRoundButton").click(function(e) {
		var num = $("#roundId").val();
		var d = window.location.href;
		var n = d.replace("editround","deleteround");
		console.log(n);
		window.location.href = n;
	});
});