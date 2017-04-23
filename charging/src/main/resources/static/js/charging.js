$('#deleteConfimartionModal').on('show.bs.modal', function(event){
	var button = $(event.relatedTarget);
	var bankSlipCode = button.data('code');
	var bankSlipDescription = button.data('description');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if(!action.endsWith('/')){
		action += "/"
	}

	form.attr('action', action+bankSlipCode);
	
	modal.find('.modal-body span').html('Are you sure you wish to delete the bank slip <strong>'+bankSlipDescription+'</strong>?');
});

$(function(){
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal:',',thousands:'.',allowZero:true});
	$('.js-date').mask('00/00/0000');
	
	$('.js-update-status').on('click',function(event){
		event.preventDefault();
		
		var receiveButton = $(event.currentTarget);
		var receiveURL = receiveButton.attr('href');
		
		var response = $.ajax({
			url : receiveURL,
			type: 'PUT'
		});
		
		response.done(function(e){
			var bankslipCode = receiveButton.data('code');
			$('[data-role =' + bankslipCode+']').html('<span class="label label-success">'+ e +'</span>')
			receiveButton.hide();
		});
		
		response.failed(function(e){
			alert('Charging receive error')
		});
	});
});