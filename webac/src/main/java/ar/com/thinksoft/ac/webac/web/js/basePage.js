$(document).ready(function() {
	
	if( navigator.userAgent.match(/Android/i) || navigator.userAgent.match(/webOS/i) ||
			 navigator.userAgent.match(/iPhone/i) ||  navigator.userAgent.match(/iPod/i))
	{
		 alert("El sistema web no es compatible con su dispositivo móvil.");
		 
	}
	
});

function validarSiEsNull(elem){
	if(elem.value == "" || elem.value == null || elem.value == undefined){
		$('.error').val("Hay campos obligatorios que se encuentran vacios.");
		return false;
	}
	return true;
	
}


var numeros="0123456789";

function validarSiTieneNumeros(texto){
   for(i=0; i<texto.length; i++){
      if (numeros.indexOf(texto.charAt(i),0)!=-1){
    	  $('.error').val("El texto ingresado no es valido.");
    	  return false;
      }
   }
   $('.error').val("");
   return true;
}

function validarNumerosInput(elem){

	if (!(/^([0-9])*$/.test(elem.value))){
		$('.error').val("El valor ingresado no es valido.");
		return false;
	}
	$('.error').val("");
	return true;
}

function validarEmail(elem) {
	if (!(/^w+([.-]?w+)*@w+([.-]?w+)*(.w{2,3})+$/.test(elem))){
		$('.error').val("El email ingresado no es valido.");
		return false;
	}
	$('.error').val("");
	return true;
}