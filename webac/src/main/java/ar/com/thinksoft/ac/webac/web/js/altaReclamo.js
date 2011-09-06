/**
 * 
 */


$(document).ready(function() {
	
	
	habilitarUbicacion($('#gpsOption').attr('checked'));
	
	$('#gpsOption').click(function(){
		habilitarUbicacion(true);
	});
	
	$('#direccionOption').click(function(){
		habilitarUbicacion(false);
	});
	
	$('input[type=file]').change(function(){
		$('.imagen').empty();
		var ruta = $('input[type=file]').val();
		$('.imagen').attr('src',"/tempImages/" + ruta);
		$('.imagen').css('display','block');
	});
	
	
	
	function habilitarUbicacion(isGps){
		$('#calleIncidente').attr('disabled', isGps);
		$('#alturaIncidente').attr('disabled', isGps);
		
		$('#latitudIncidente').attr('disabled', !isGps);
		$('#longitudIncidente').attr('disabled', !isGps);
	}
	
	
});