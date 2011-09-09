$(document).ready(function() {
	setTimeout(function(){
		var ruta = $('.rutaImagen')[0].textContent;
		$('.imagen').attr('src',"/tempImages/" + ruta);
		$('.imagen').css('heigth','auto');
		$('.imagen').css('width','auto');
	}, 3 * 1000);
	
});