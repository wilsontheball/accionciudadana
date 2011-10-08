$(document).ready(function() {
	var ruta = $('.rutaImagen')[0].textContent;
	if(ruta == undefined || ruta == '' || ruta == null){
		$('.contenedorImagen').css('display','none');
	}else{
		setTimeout(function(){
			$('.imagen').attr('src',"/tempImages/" + ruta);
			$('.imagen').css('heigth','auto');
			$('.imagen').css('width','auto');
		}, 3 * 1000);
	}

	if($('.numAsociados')[0].textContent == ""){
		$('.asociados').css('display','none');
	}
	
	var latitud = $('.latitudIncidente')[0].textContent;
	var longitud = $('.longitudIncidente')[0].textContent;
	$('.map').attr('src',"http://maps.google.com/maps/api/staticmap?zoom=16&size=450x450&maptype=roadmap&markers=color:blue|"+ latitud +"," + longitud + "&sensor=false");
});