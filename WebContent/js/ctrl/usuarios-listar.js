$(function() {
   $('#recarregarButton').click(function(event) {
	   UsuariosProxy
	   .selecionarTodos()
	   .done(buscarOk);
   });
   
   $('#recarregarButton').click();
});

function buscarOk(usuarios) {
   var corpo = $('#usuarios tbody');
   corpo.empty();
   if (usuarios.length) {
      $.each(usuarios, function(i, usuario) {
         corpo.append(
            $('<tr>').append(
               $('<td>').append(
                  $('<a>').attr('href',
                        'usuarios-editar.html?id=' + usuario.id)
                     .text(usuario.cpf)),
               $('<td>').text(usuario.nome),
               $('<td>').text(usuario.email),
               $('<td>').text(usuario.data),
               $('<td>').text(usuario.cep))
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