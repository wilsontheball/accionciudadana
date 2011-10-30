var buttonDisabled = true;
var usuarioInvalido = true;

$(document).ready(function() {
	validarCampos();
	bloquearPantalla();
	$('.username').change(function(){
		$('.username')[0].value = $('.username')[0].value.toLowerCase();
		if($('.username')[0].value.indexOf(" ")!= -1){
			$('.error')[0].textContent = "El nombre de usuario ingresado no debe contener espacios.";
			$('.username').css('background-color','#FF0000');
			usuarioInvalido = true;
		}else{
			$('.error')[0].textContent = "";
			$('.username').css('background-color','');
			usuarioInvalido = false;
		}
		validarCampos();
	});
});

function validarCampos(){
	
	buttonDisabled =  (validarSiEsNull($('.nombre')[0]) || validarSiEsNull($('.apellido')[0]) || 
			validarSiEsNull($('.username')[0]) || validarSiEsNull($('.password')[0]) ||
			validarSiEsNull($('.re-password')[0]) || validarSiEsNull($('.mail')[0]) || 
			validarSiEsNull($('.re-mail')[0]) || usuarioInvalido); 
	
	$('.guardarRegistro').attr('disabled',buttonDisabled);
	
	return buttonDisabled;
	
}
