"use strict";

$(document).ready(
		function() {

			if ($("#roundNameBox").val() === "" && $('#roundId').val() !== "") {
				$('#credentials').modal('show');
			}

			$("#deleteRoundButton").click(function(e) {
				var currentUrl = window.location.href;
				var deleteUrl = currentUrl.replace("editround", "deleteround");
				window.location.href = deleteUrl;
			});

			$("#saveRoundButton").click(
					function(e) {
						if ($("#roundNameBox").val() !== ""
								&& $('#roundNameBox').val() !== "") {
							$('#credentials').modal('show');
						} else {
							document.getElementById("roundForm").submit();
						}
					});

			$("#getRoundName").click(function(e) {
				var login = {};
				login.user = $("#user").val();
				login.pass = $("#password").val();

				var currentUrl = window.location.href;
				var roundId = $('#roundId').val();
				var id = $('#roundId').val() === "" ? "" : $('#roundId').val();
				$.ajax({
					url : "/CodeComparison/getroundname/" + id,
					type : 'post',
					dataType : 'json',
					contentType : 'application/json; charset=UTF-8',
					processData : false,
					data : JSON.stringify(login),
					success : function(e) {
						window.location.href = currentUrl;
						console.log("Success");
					},
					error : function(data) {
						window.location.href = currentUrl;
						console.log("Error");
					}
				});
			});

			$("#deleteTaskButton").click(function(e) {
				var currentUrl = window.location.href;
				var deleteUrl = currentUrl.replace("edittask", "deletetask");
				window.location.href = deleteUrl;
			});

			$("#user").keyup(function(event) {
				if (event.keyCode == 13) {
					chooseRightButton();
				}
			});

			$("#password").keyup(function(event) {
				if (event.keyCode == 13) {
					chooseRightButton();
				}
			});

			function chooseRightButton(){
				var syncButton = $("#synchronize");
				var roundNameButton = $("#getRoundName");
				if (syncButton.length > 0) {
					syncButton.click();
				}
				if (roundNameButton.length > 0) {
					roundNameButton.click();
				}
			}
			
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
				var changedUrl = currentUrl.replace("task", "analyzetask");

				var noise = $("#noise").val();
				var match = $("#match").val();
				var analyzeUrl = changedUrl + "/" + noise + "/" + match;
				$("#loader").show();
				$.ajax({
					url : analyzeUrl,
					type : 'post',
					success : function(e) {
						$("#loader").hide();
						window.location.href = currentUrl;
					},
					error : function(data) {
						$("#loader").hide();
						window.location.href = currentUrl;
					}
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
