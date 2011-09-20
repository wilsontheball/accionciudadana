var buttonDisabled = true;

$(document).ready(function() {
	
	setTimeout(function(){
		var ruta = $('.rutaImagen')[0].textContent;
		$('.imagen').attr('src',"/tempImages/" + ruta);
		$('.imagen').css('heigth','auto');
		$('.imagen').css('width','auto');
	}, 3 * 1000);
	
	
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
			validarSiEsNull($('#barrioIncidente')[0]) || validarSiEsNull($('.prioridad')[0]) || 
			validarSiEsNull($('.estadoDescripcion')[0]) ;
			//|| $('.imagen')== null || $('.imagen')== undefined;
	
	$('.guardarReclamo').attr('disabled',buttonDisabled);
	
	return buttonDisabled;
	
}