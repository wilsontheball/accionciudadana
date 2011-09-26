var _self = this;

$(document).ready(function() {
	
	$('.auth').click(function(){
		var checked = $('.auth').attr('checked');
		$('.userCredentials').css('display',checked);
		$('.passwordCredentials').css('display',checked);
	});
	

});

