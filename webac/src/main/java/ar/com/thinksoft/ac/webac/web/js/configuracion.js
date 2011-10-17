var _self = this;

$(document).ready(function() {
	
	credentialsChange();
	$('.auth').click(function(){
		_self.credentialsChange();
	
	});
	
	validarCampos();
});

function credentialsChange(){
	if(!$('.auth')[0].checked){
		$('.credenciales').css('display','none');
	}else{
		$('.credenciales').css('display','block');
	}
}

function validarCampos(){
	
	buttonDisabled =  validarSiEsNull($('.horaUnificador')[0]) || validarSiEsNull($('.minutoUnificador')[0]) || 
			validarSiEsNull($('.manianaOTardeUnificador')[0]) || validarSiEsNull($('.smtp')[0]) || 
			validarSiEsNull($('.puerto')[0]) || validarSiEsNull($('.desdeMail')[0]) || 
			validarSiEsNull($('.user')[0]) || validarSiEsNull($('.password')[0]) || 
			validarSiEsNull($('.ipBD')[0]) || validarSiEsNull($('.portBD')[0]) || 
			validarSiEsNull($('.pathTempImages')[0]) || validarSiEsNull($('.pathExportDesign')[0]) || 
			validarSiEsNull($('.pathConfig')[0]) || validarSiEsNull($('.pathDownloadApp')[0]) || 
			validarSiEsNull($('.pathDownloadApp')[0]);
	
	$('.guardarConfig').attr('disabled',buttonDisabled);
	
	return buttonDisabled;
	
}