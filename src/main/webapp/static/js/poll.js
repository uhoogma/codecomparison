$(document).ready(function() {
	function start() {
		var taskId = $("#taskId").text();
		$.ajax({
			url : '/CodeComparison/messages/' + taskId,
			success : function(data) {
				$('#messages').append(data);
			}
		});
		setTimeout(start, 3000);
	}
	$("#messages").empty();
	$("#messages").text("Teated => ");
	start();
});