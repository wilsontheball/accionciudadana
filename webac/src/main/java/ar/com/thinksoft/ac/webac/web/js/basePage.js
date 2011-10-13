$(document).ready(function() {
	
	if( navigator.userAgent.match(/Android/i) || navigator.userAgent.match(/webOS/i) ||
			 navigator.userAgent.match(/iPhone/i) ||  navigator.userAgent.match(/iPod/i))
	{
		 alert("El sistema web no es compatible con su dispositivo móvil.");
		 
	}
	
});

/*
 * FUNCIONES DE VALIDACION
 */

var numeros="0123456789";

function validarSiTieneNumeros(elem){
   for(i=0; i<elem.value.length; i++){
      if (numeros.indexOf(elem.value.charAt(i),0)!=-1){
    	  $('.error').val("El texto ingresado no es valido.");
    	  $('#'+elem.id).css('background-color','#F78181');
    	  validarCampos();
    	  return false;
      }
   }
   $('.error').val("");
   $('#'+elem.id).css('background-color','');
   validarCampos();
   return funcionOnChangeCampoIsNull(elem);
   
}

function validarNumerosInput(elem){
	if (!(/^([0-9])*$/.test(elem.value))){
		$('.error').val("El valor ingresado no es valido.");
  	  	$('#'+elem.id).css('background-color','#F78181');
		validarCampos();
		return false;
	}
	$('.error').val("");
	$('#'+elem.id).css('background-color','');
	validarCampos();
	return funcionOnChangeCampoIsNull(elem);
}

function validarEmail(elem) {
	var valor = elem.value;
	var atpos=valor.indexOf("@");
	var dotpos=valor.lastIndexOf(".");
	if (atpos<1 || dotpos<atpos+2 || dotpos+2>=valor.length){
		$('.error').val("El email ingresado no es valido.");
		$('#'+elem.id).css('background-color','#F78181');
		validarCampos();
		return false;
	}
	
	$('.error').val("");
	$('#'+elem.id).css('background-color','');
	validarCampos();
	return funcionOnChangeCampoIsNull(elem);
}

function funcionOnChangeCampoIsNull(elem){
	if(validarSiEsNull(elem)){
		$('.error').val("Hay campos obligatorios que se encuentran vacios.");
		$('#'+elem.id).css('background-color','#F78181');
		validarCampos();
		return false;
	}
	$('.error').val("");
	$('#'+elem.id).css('background-color','');
	validarCampos();
	return true;
	
}

function compararConfirmacionMail(){
	if($('.re-mail')[0].value != $('.mail')[0].value){
		$('.error').val("Los mails no son iguales.");
		$('.mail').css('background-color','#F78181');
		$('.re-mail').css('background-color','#F78181');
		validarCampos();
		return false;
	}
	$('.error').val("");
	$('.mail').css('background-color','');
	$('.re-mail').css('background-color','');
	validarCampos();
	return funcionOnChangeCampoIsNull(elem) && validarEmail(elem);
	
}

function compararConfirmacionPassword(){
	if($('.re-password')[0].value != $('.password')[0].value){
		$('.error').val("No son iguales.");
		$('.password').css('background-color','#F78181');
		$('.re-password').css('background-color','#F78181');
		validarCampos();
		return false;
	}
	$('.error').val("");
	$('.password').css('background-color','');
	$('.re-password').css('background-color','');
	validarCampos();
	return true;
	
}

/*
 * FUNCIONES MAGICAS INTERNAS
 */

function validarSiEsNull(elem){
	return (elem.value == "" || elem.value == null || elem.value == undefined);
}


function validarRepeticiones(){
	compararConfirmacionMail()
	compararConfirmacionPassword();
}

function validarCamposVacios(){
	if(validarCampos()){
		$('.error').val("Hay campos obligatorios que se encuentran vacios.");
		$('#'+elem.id).css('background-color','#F78181');
	}
	$('.error').val("");
	$('#'+elem.id).css('background-color','');
}
