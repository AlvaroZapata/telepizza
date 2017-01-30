/**
 * 
 */
$(document).ready(function(){
	
		$('.btn-borrar').on('click', function(){
			var id = $(this).parents('tr').data('id');
			
			$.ajax({
				url : "pizzas/"+id,
				type: 'DELETE',
			    success: function(result) {
			    	$('tr[data-id="'+id+'"]').remove();
					var pizzas = parseInt( $('#cantidad-pizzas').text() );
			    	$('#cantidad-pizzas').text(ingredientes - 1);
			    }
			});
			
			
		});
		
		$('.btn-editar').on('click', function(){
			var id = $(this).parents('tr').data('id');
			var url = 'pizzas/'+id;
			
			$.get(url)
				.done(function(pizza){
					$('#id').val(pizza.id);
					$('#nombre').val(pizza.nombre);
					$('#categoria').val(pizza.categoria);
					$('#precio').val(pizza.precio);
					<!-- $('#ingrediente').val(pizza.ingrediente); -->
					pizza.ingredientes.forEach(function(ingrediente){
						var id = ingrediente.id;
						$('#ingredientes option[value='+id+']').attr('selected', true);
					});
					
					$('#modal-pizza').modal('show');
				});
		});
	});