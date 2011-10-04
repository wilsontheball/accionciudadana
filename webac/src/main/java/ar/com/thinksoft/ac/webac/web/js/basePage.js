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
	if (!(/^w+([.-]?w+)*@w+([.-]?w+)*(.w{2,3})+$/.test(elem))){
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


function validarCamposVacios(){
	if(validarCampos()){
		$('.error').val("Hay campos obligatorios que se encuentran vacios.");
		$('#'+elem.id).css('background-color','#FF0000');
	}
	$('.error').val("");
	$('#'+elem.id).css('background-color','');
}
