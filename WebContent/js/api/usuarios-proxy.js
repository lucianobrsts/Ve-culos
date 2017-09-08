var UsuariosProxy = {
   url : "api/usuarios",
   
   ajax : LoginProxy.ajaxJWT,
   
   inserir : function(usuario) {
      return this.ajax({
          type : "POST",
          url : this.url,
          data : JSON.stringify(usuario),
          contentType : "application/json"
      });
   },
   
   atualizar : function(id, usuario) {
      return this.ajax({
         type : "PUT",
         url : this.url + "/" + id,
         data : JSON.stringify(usuario),
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