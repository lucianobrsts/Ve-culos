$(function() {
   $('#recarregarButton').click(function(event) {
	   EstadosProxy
	   .selecionarTodos()
	   .done(buscarOk);
   });
   
   $('#recarregarButton').click();
});

function buscarOk(estados) {
   var corpo = $('#estados tbody');
   corpo.empty();
   if (estados.length) {
      $.each(estados, function(i, estado) {
         corpo.append(
            $('<tr>').append(
               $('<td>').append(
                  $('<a>').attr('href',
                        'estados-editar.html?id=' + estado.id)
                     .text(estado.id)),
               $('<td>').text(estado.nome),      
               $('<td>').text(estado.sigla))
            );
      });
   } else {
	   corpo.append(
         $('<tr>').append(
            $('<td>')
               .attr('colspan', 4)
               .text('Nenhum registro encontrado!')
         )
      );
   }
}