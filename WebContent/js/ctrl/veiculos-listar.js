$(function() {
   $('#recarregarButton').click(function(event) {
	   VeiculosProxy
	   .selecionarTodos()
	   .done(buscarOk);
   });
   
   $('#recarregarButton').click();
});

function buscarOk(veiculos) {
   var corpo = $('#veiculos tbody');
   corpo.empty();
   if (veiculos.length) {
      $.each(veiculos, function(i, veiculo) {
         corpo.append(
            $('<tr>').append(
               $('<td>').append(
                  $('<a>').attr('href',
                        'veiculos-editar.html?id=' + veiculo.id)
                     .text(veiculo.placa)),
               $('<td>').text(veiculo.proprietario),
               $('<td>').text(veiculo.dataEmplacamento),
               $('<td>').text(veiculo.valorEmplacamento))
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