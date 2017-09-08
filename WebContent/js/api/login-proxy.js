var LoginProxy = {
	url : "api/login",

	login : function(usuario) {
		return $.ajax({
			type : "POST",
			url : this.url,
			data : JSON.stringify(usuario),
			contentType : "application/json"
		});
	},

	adicionarControleJWT : function(settings) {
		return $.extend(
			settings,
			{
				beforeSend : function(jqXHR, settings) {
					var token = sessionStorage.CursoRWSJWSToken;
					if (token) {
						jqXHR.setRequestHeader('Authorization', 'Bearer ' + token);
					}
				},
				complete : function(jqXHR, textStatus) {
					var jwt = jqXHR.getResponseHeader('jwt');
					if (jwt) {
						sessionStorage.CursoRWSJWSToken = jwt;
					}
					
					if (jqXHR.status == 401) {
						var loginPanel = $("#loginPanel");
						if (!loginPanel.length) {
							loginPanel = $('<div id="loginPanel"></div>').appendTo('body');
						}
						loginPanel.load("/cursorws/login.html");
					}
				}
			}
		);
	},
	
	ajaxJWT : function(settings) {
		var jwtsettings = $.extend(
			settings,
			{
				beforeSend : function(jqXHR, settings) {
					var token = sessionStorage.CursoRWSJWSToken;
					if (token) {
						jqXHR.setRequestHeader('Authorization', 'Bearer ' + token);
					}
				},
				complete : function(jqXHR, textStatus) {
					var jwt = jqXHR.getResponseHeader('jwt');
					if (jwt) {
						sessionStorage.CursoRWSJWSToken = jwt;
					}
					
					if (jqXHR.status == 401) {
						var loginPanel = $("#loginPanel");
						if (!loginPanel.length) {
							loginPanel = $('<div id="loginPanel"></div>').appendTo('body');
						}
						loginPanel.load("/cursorws/login.html");
					}
				}
			}
		);
		
		return $.ajax(jwtsettings);
	}

};