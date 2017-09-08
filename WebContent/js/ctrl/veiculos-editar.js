$(function() {

   var id = obterParametroDaUrlPorNome('id');
   if (id) {
       VeiculosProxy.selecionar(id)
           .done(obterOk)
           .fail(tratarErro);
   }
	
   $("#salvar").click(function(event) {
	  limparMensagensErro();
	  
      var veiculo = {
         id : $("#id").val(),
         placa : $("#placa").val(),
         proprietario : $("#proprietario").val(),
         dataEmplacamento : $("#dataEmplacamento").val(),
         valorEmplacamento : $("#valorEmplacamento").val(),
      };

      if (veiculo.id) {
          VeiculosProxy.atualizar(veiculo.id, veiculo)
             .done(atualizarOk)
             .fail(tratarErro);
       } else {
          VeiculosProxy.inserir(veiculo)
             .done(inserirOk)
             .fail(tratarErro);
       }
   });

   $("#excluir").click(function(event) {
      var id = $("#id").val();
      VeiculosProxy.excluir(id)
      	.done(excluirOk)
      	.fail(tratarErro);
   });
});

function inserirOk(data, textStatus, jqXHR) {
   $("#id").val(data);
   $("#global-message")
      .addClass("alert-success")
      .text("Veiculo com id = " + data + " criado com sucesso.")
      .show();
}

function obterParametroDaUrlPorNome(name){
   name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
   var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), 
                   results = regex.exec(location.search);
   return results === null ? "" : 
      decodeURIComponent(results[1].replace(/\+/g, " "));
}

function obterOk(data) {
   $("#id").val(data.id);
   $("#placa").val(data.placa);
   $("#proprietario").val(data.proprietario);
   $("#dataEmplacamento").val(data.dataEmplacamento);
   $("#valorEmplacamento").val(data.valorEmplacamento);
}

function atualizarOk(data, textStatus, jqXHR) {
   $("#global-message")
       .addClass("alert-success")
       .text("Veiculo atualizado com sucesso.")
       .show();
}

function excluirOk(data, textStatus, jqXHR) {
   $("#id").val(null);
   $("#placa").val(null);
   $("#proprietario").val(null);
   $("#dataEmplacamento").val(null);
   $("#valorEmplacamento").val(null);
   $("#global-message").addClass("alert-success")
      .text("Veiculo excluído com sucesso.")
      .show();
}
function limparMensagensErro() {
   /* Limpa as mensagens de erro */
   $("#global-message").removeClass("alert-danger alert-success")
      .empty().hide();
   $(".control-label").parent().removeClass("has-success");
   $(".text-danger").parent().removeClass("has-error");
   $(".text-danger").hide();
}

function tratarErro(request) {
   switch (request.status) {
   case 406:
      $("form input").each(function() {
         var id = $(this).attr("id"); var message = null;
         $.each(request.responseJSON, function(index, value) {
            if (id == value.propriedade) { message = value.mensagem; }
         });
         if (message) {
            $("#" + id).parent().addClass("has-error");
            $("#" + id + "-message").html(message).show();
            $(this).focus();
         } else {
            $("#" + id).parent().removeClass("has-error");
            $("#" + id + "-message").hide();
         }
      });
      $("#global-message")
         .addClass("alert-danger")
         .text("Verifique erros no formulário!")
         .show();
      break;
   case 404:
      $("#global-message")
          .addClass("alert-danger")
          .text("O registro solicitado não foi encontrado!")
          .show();
      break;
   case 401:
      break;
   default:
      $("#global-message")
         .addClass("alert-danger")
         .text("Erro inesperado.")
         .show();
      break;
   }
}