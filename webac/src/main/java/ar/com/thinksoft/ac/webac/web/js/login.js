$(document).ready(function() {
	$('.username').change(function(){
		$('.username')[0].value = $('.username')[0].value.toLowerCase();
		$('.username')[0].value = $('.username')[0].value.replace(" ","");
	});
	
});