var VeiculosProxy = {
   url : "api/veiculos",
   
   ajax : LoginProxy.ajaxJWT,
   
   inserir : function(veiculo) {
      return this.ajax({
          type : "POST",
          url : this.url,
          data : JSON.stringify(veiculo),
          contentType : "application/json"
      });
   },
   
   atualizar : function(id, veiculo) {
      return this.ajax({
         type : "PUT",
         url : this.url + "/" + id,
         data : JSON.stringify(veiculo),
         contentType : "application/json"
      });
   },
   
   excluir : function(id) {
      return this.ajax({
         type : "DELETE",
         url : this.url + "/" + id
      });
   },
   
   selecionar : function(id) {
      return this.ajax({
         type : "GET",
         url : this.url + "/" + id
      });
   },

   selecionarTodos : function() {
      return this.ajax({
         type : "GET",
         url : this.url
      });
   }
   
};