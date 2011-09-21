$(document).ready(function() {
	
	if( navigator.userAgent.match(/Android/i) || navigator.userAgent.match(/webOS/i) ||
			 navigator.userAgent.match(/iPhone/i) ||  navigator.userAgent.match(/iPod/i))
	{
		 alert("El sistema web no es compatible con su dispositivo móvil.");
		 
	}
	
	$('.guardarReclamo').click(function(){
		var geocoder = new google.maps.Geocoder();
		//var address = "http://maps.google.com/maps/api/geocode/json?address=" + $('#alturaIncidente')[0].value + "+" + $('#calleIncidente')[0].value + ",Capital+federal&sensor=false";
		var address = $('#calleIncidente')[0].value + " " + $('#alturaIncidente')[0].value + ",Capital federal";
		
		geocoder.geocode( 
				{ 'address': address }, 
				function(results, status) 
				{
					if (status == google.maps.GeocoderStatus.OK) 
					{
						alert('ok');
					} 
					else 
					{
						alert("No fue posible hacer la conversi&oacute;n a coordenadas GPS : " + status);
					}
				});
	});
	
});


/*
 * FUNCIONES DE VALIDACION
 */

var numeros="0123456789";

function validarSiTieneNumeros(elem){
   for(i=0; i<elem.value.length; i++){
      if (numeros.indexOf(elem.value.charAt(i),0)!=-1){
    	  $('.error').val("El texto ingresado no es valido.");
    	  validarCampos();
    	  return false;
      }
   }
   $('.error').val("");
   validarCampos();
   return funcionOnChangeCampoIsNull(elem);
   
}

function validarNumerosInput(elem){

	if (!(/^([0-9])*$/.test(elem.value))){
		$('.error').val("El valor ingresado no es valido.");
		validarCampos();
		return false;
	}
	$('.error').val("");
	validarCampos();
	return funcionOnChangeCampoIsNull(elem);
}

function validarEmail(elem) {
	if (!(/^w+([.-]?w+)*@w+([.-]?w+)*(.w{2,3})+$/.test(elem))){
		$('.error').val("El email ingresado no es valido.");
		validarCampos();
		return false;
	}
	$('.error').val("");
	validarCampos();
	return funcionOnChangeCampoIsNull(elem);
}

function funcionOnChangeCampoIsNull(elem){
	if(validarSiEsNull(elem)){
		$('.error').val("Hay campos obligatorios que se encuentran vacios.");
		validarCampos();
		return false;
	}
	$('.error').val("");
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
	}
	$('.error').val("");
}
