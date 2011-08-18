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
	
	$('#rutaArchivo').change(function(){
		var ruta = $('#rutaArchivo').val();
		$('#imagePreviewer').css('background','url('+ ruta +')');
	});
	
	
	
	
	function habilitarUbicacion(isGps){
		$('#calleIncidente').attr('disabled', isGps);
		$('#alturaIncidente').attr('disabled', isGps);
		
		$('#latitudIncidente').attr('disabled', !isGps);
		$('#longitudIncidente').attr('disabled', !isGps);
	}
	
	
});