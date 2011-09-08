$(document).ready(function() {
	
	$('input[type=file]').change(function(){
		$('.imagen').empty();
		var ruta = $('input[type=file]').val();
				
		setTimeout(function(){
			$('.imagen').attr('src',"/tempImages/" + ruta);
		}, 5000);
		
	});
	
});

function validarCamposAlta(){
	
alert(validarSiEsNull($('#calleIncidente')));
alert(validarSiEsNull($('#alturaIncidente')));
alert(validarSiEsNull($('#ciudadanoIncidente')));
alert(validarSiEsNull($('#tipoIncidente')));
alert(validarSiEsNull($('#barrioIncidente')));
alert(validarSiEsNull($('#rutaArchivo')));

return false;
	
}