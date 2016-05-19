"use strict";

$(document).ready(function() {

	$("#deleteRoundButton").click(function(e) {
		var currentUrl = window.location.href;
		var deleteUrl = currentUrl.replace("editround", "deleteround");
		window.location.href = deleteUrl;
	});

	$("#synchronize").click(function(e) {
		e.preventDefault();
		var currentUrl = window.location.href;
		var syncUrl = currentUrl.replace("task", "synchronizetask");

		var login = {};
		login.user = $("#user").val();
		login.pass = $("#password").val();

		$.ajax({
			url : syncUrl,
			type : 'post',
			dataType : 'json',
			contentType : 'application/json; charset=UTF-8',
			processData : false,
			data : JSON.stringify(login),
			success : function(e) {
				console.log("Success");
			},
			error : function(data) {
				console.log("Error");
			}

		});
	});

	$("#analyzeTask").click(function(e) {
		e.preventDefault();
		var currentUrl = window.location.href;
		var analyzeUrl = currentUrl.replace("task", "analyzetask");

		$.post(analyzeUrl, function() {
			window.location.href = currentUrl;
		});
	});
});

function addRoundToTask(id) {
	var currentUrl = window.location.href;
	var addUrl = currentUrl + "/addRound/" + id;
	$.post(addUrl, function() {
		window.location.href = currentUrl;
	});
}

function removeRoundFromTask(id) {
	var currentUrl = window.location.href;
	var removeUrl = currentUrl + "/removeRound/" + id;
	$.post(removeUrl, function() {
		window.location.href = currentUrl;
	});
}
