var buttonDisabled = true;

$(document).ready(function() {
	
//	$('.guardarReclamo').click(function(){
//		var geocoder = new google.maps.Geocoder();
//		var address = "http://maps.google.com/maps/api/geocode/json?address=" + $('#alturaIncidente')[0].value + "+" + $('#calleIncidente')[0].value + ",Capital+federal&sensor=false";
//		//var address = $('#calleIncidente')[0].value + " " + $('#alturaIncidente')[0].value + ",Capital federal";
//		
//		geocoder.geocode( 
//				{ 'address': address }, 
//				function(results, status) 
//				{
//					if (status == google.maps.GeocoderStatus.OK) 
//					{
//						alert('ok');
//					} 
//					else 
//					{
//						alert("No fue posible hacer la conversi&oacute;n a coordenadas GPS : " + status);
//					}
//				});
//	});
	
	$('input[type=file]').change(function(){
		$('.imagen').empty();
		var ruta = $('input[type=file]').val();
				
		setTimeout(function(){
			$('.imagen').attr('src',"/tempImages/" + ruta);
			$('.imagen').css('heigth','auto');
			$('.imagen').css('width','auto');
			validarCampos();
		}, 3 * 1000);
		
	});
	
	validarCampos();
	
});

function validarCampos(){
	
	buttonDisabled =  validarSiEsNull($('#calleIncidente')[0]) || validarSiEsNull($('#alturaIncidente')[0]) || 
			validarSiEsNull($('#ciudadanoIncidente')[0]) || validarSiEsNull($('#tipoIncidente')[0]) || 
			validarSiEsNull($('#barrioIncidente')[0]);
	
	// || validarSiEsNull($('.imageUploader')[0])
	
	$('.guardarReclamo').attr('disabled',buttonDisabled);
	
	return buttonDisabled;
	
}