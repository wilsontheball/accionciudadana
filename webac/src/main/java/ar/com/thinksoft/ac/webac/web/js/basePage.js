$(document).ready(function() {
	
	if( navigator.userAgent.match(/Android/i) || navigator.userAgent.match(/webOS/i) ||
			 navigator.userAgent.match(/iPhone/i) ||  navigator.userAgent.match(/iPod/i))
	{
		 alert("El sistema web no es compatible con su dispositivo móvil.");
		 
	}
	
});

var numeros="0123456789";

function validarSiTieneNumeros(texto){
   for(i=0; i<texto.length; i++){
      if (numeros.indexOf(texto.charAt(i),0)!=-1){
         return false;
      }
   }
   return true;
}

function validarNumerosInput(elem){

	if (/^([0-9])*$/.test(elem.value)){
		alert("El valor " + elem.value + " es un número");
		return(true);
	}else{
		alert("esto no es un número");
		return(false);
	}
}

function validarEmail(elem) {
	if (/^w+([.-]?w+)*@w+([.-]?w+)*(.w{2,3})+$/.test(elem)){
		alert("La dirección de email " + elem + " es correcta.") 
		return (true)
	} else {
		alert("La dirección de email es incorrecta.");
		return (false);
	}
}