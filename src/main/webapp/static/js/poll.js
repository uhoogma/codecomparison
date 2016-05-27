$(document).ready(function() {
	function start() {
		$.ajax({
			url : '/CodeComparison/messages',
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