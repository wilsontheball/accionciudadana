/**
 * 
 */


$(document).ready(function() {
	
	$('#coordenadasRadioButton').click(function(){
		$('#direccionRadioButton').attr('checked',false);
		
	});
	
	$('#direccionRadioButton').click(function(){
		$('#coordenadasRadioButton').attr('checked',false);
		
	});
	
});