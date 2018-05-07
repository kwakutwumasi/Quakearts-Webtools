if(window.WebSocket){
	syshub.ws = new WebSocket(syshub.wslocation);
	syshub.ws.onmessage = function(event) {
		var message = JSON.parse(event.data);
		if(message.exception){
			var exception = message.exception;

			var agentName = '';
			if(message.agentName)
				agentName = message.agentName;				

			var moduleName = '';
			if(message.moduleName)
				moduleName = message.moduleName;				
			if(message.agentName)
			var outWindow = $('#output-window');
			outWindow.html('<div class="row"><table class="table" style="margin-left: 10px"><tbody><tr><th colspan="2">Exception:</th></tr><tr><td colspan="2">'
					+exception+'</td></tr><tr><th>Agent</th><td>'
					+agentName+'</td></tr><tr><th>Module</th><td>'
					+moduleName+'</td></tr></tbody></table></div>'
					+syshub.topten(outWindow));
		} else {
			jsf.ajax.request(syshub.agentRunnerDisplay,null,{'execute':syshub.agentRunnerDisplay,'render':syshub.agentRunnerDisplay,'delay':500});
		}
	};
	     
	syshub.ws.onerror = function(event){
		var popup = $('#'+syshub.popup);
		popup.find(".panel-body")
			.html("There was a web socket error. The error code is "+event.code);
		
		if(popup.hasClass('collapse'))
			popup.removeClass('collapse');
	};
	
	syshub.ws.onclose = function(event){
		var popup = $('#'+syshub.popup);
		popup.find(".panel-body")
			.html("The connection to the server is closed. Please refresh the page when the server restarts to view updates");
		
		if(popup.hasClass('collapse'))
			popup.removeClass('collapse');
	};
	
	syshub.topten = function(htmlObject){
		if(htmlObject.children().length > 10){
			var html = '';
			var count = 0;
			htmlObject.children().each(function(){
				if(count===10)
					return;
					
				html +=('<div class="row">'+this.innerHTML+'</div>');
				count++;
			});
			return html;
		}
		return htmlObject.html();
	}
} else {
	var popup = $('#'+syshub.popup);
	popup.find(".panel-body")
		.html("This browser does not appear to support WebSockets. Live updates will not be available");
	
	popup.removeClass('collapse');
}
