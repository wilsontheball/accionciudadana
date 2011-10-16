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
    	  $('#'+elem.id).css('background-color','#FF0000');
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
  	  	$('#'+elem.id).css('background-color','#FF0000');
		validarCampos();
		return false;
	}
	$('.error').val("");
	$('#'+elem.id).css('background-color','');
	validarCampos();
	return funcionOnChangeCampoIsNull(elem);
}

function validarEmail(elem) {
	var x=elem.value;
	var atpos=x.indexOf("@");
	var dotpos=x.lastIndexOf(".");
	if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length){
		$('.error').val("El email ingresado no es valido.");
		$('#'+elem.id).css('background-color','#FF0000');
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
		$('#'+elem.id).css('background-color','#FF0000');
		validarCampos();
		return false;
	}
	$('.error').val("");
	$('#'+elem.id).css('background-color','');
	validarCampos();
	return true;
	
}


/*
 * FUNCIONES MAGICAS INTERNAS
 */

function validarSiEsNull(elem){
	return (elem.value == "" || elem.value == null || elem.value == undefined);
}

function compararConfirmacionPassword(){
	if($('.re-password')[0].value != $('.password')[0].value){
		$('.error').val("Los campos no concuerdan.");
		$('.re-password').css('background-color','#FF0000');
		$('.password').css('background-color','#FF0000');
		validarCampos();
		return false;
	}
	$('.error').val("");
	$('.re-password').css('background-color','');
	$('.password').css('background-color','');
	validarCampos();
	return funcionOnChangeCampoIsNull($('.re-password')[0]) && funcionOnChangeCampoIsNull($('.password')[0]);
}

function compararConfirmacionMail(){
	if($('.re-mail')[0].value != $('.mail')[0].value){
		$('.error').val("Los campos no concuerdan.");
		$('.re-mail').css('background-color','#FF0000');
		$('.mail').css('background-color','#FF0000');
		validarCampos();
		return false;
	}
	$('.error').val("");
	$('.re-mail').css('background-color','');
	$('.mail').css('background-color','');
	validarCampos();
	return funcionOnChangeCampoIsNull($('.re-mail')[0]) && funcionOnChangeCampoIsNull($('.mail')[0]);
}

function validarCamposVacios(){
	if(validarCampos()){
		$('.error').val("Hay campos obligatorios que se encuentran vacios.");
		$('#'+elem.id).css('background-color','#FF0000');
	}
	$('.error').val("");
	$('#'+elem.id).css('background-color','');
}
