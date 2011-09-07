$(document).ready(function() {
	
	$('input[type=file]').change(function(){
		$('.imagen').empty();
		var ruta = $('input[type=file]').val();
				
		setTimeout(function(){
			$('.imagen').attr('src',"/tempImages/" + ruta);
		}, 5000);
		
	});
	
});