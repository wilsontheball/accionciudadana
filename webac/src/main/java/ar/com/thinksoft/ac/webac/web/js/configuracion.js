var _self = this;

$(document).ready(function() {
	
	credentialsChange();
	$('.auth').click(function(){
		_self.credentialsChange();
	
	});
	

});

function credentialsChange(){
	if(!$('.auth')[0].checked){
		$('.credenciales').css('display','none');
	}else{
		$('.credenciales').css('display','block');
	}
}
