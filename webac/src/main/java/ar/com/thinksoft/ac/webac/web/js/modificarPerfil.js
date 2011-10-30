var buttonDisabled = true;

$(document).ready(function() {
	validarCampos();
	bloquearPantalla();
});

function validarCampos(){
	
	buttonDisabled =  (validarSiEsNull($('.nombre')[0]) 
			|| validarSiEsNull($('.apellido')[0]) || 
			validarSiEsNull($('.password')[0]) || 
			validarSiEsNull($('.re-password')[0]) || 
			validarSiEsNull($('.mail')[0]) || 
			validarSiEsNull($('.re-mail')[0])); 
	
	$('.guardarRegistro').attr('disabled',buttonDisabled);
	
	return buttonDisabled;
	
}