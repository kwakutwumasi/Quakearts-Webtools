var logSleep = 30000;
var limit = 1000;
var count = 0;
var server = window.location.host;
var protocol = window.location.protocol;
var logIntervalObj = -1;
var monitorSleep = 30000;
var monitorIntervalObj = -1;

var startLogger = function() {
	if (logIntervalObj == -1) {
		getLogs();
		logIntervalObj = setInterval("getLogs()", logSleep);
		$("#logoutput").append("<div id='srmsg-" + logIntervalObj+ "'>Starting system....</div>");
		$("#logoutput").find("srmsg-" + logIntervalObj).focus();
	}
}

var stopLogger = function() {
	if (logIntervalObj > -1) {
		clearInterval(logIntervalObj);
		$("#logoutput").append("<div id='stmsg-" + logIntervalObj + "'>Stopped system.</div>");
		$("#logoutput").find("stmsg-" + logIntervalObj).focus();
		logIntervalObj = -1;
	}
}

var getLogs = function() {
	$.getJSON(protocol+"//" + server + "/logger/system/events", function() {}).done(function(response) {
						if (count > limit)
							$("#logoutput").html('');

						$.each(response.data, function(i, log) {
							$("#logoutput").append("<div id='log-" + count + "' class='log "
											+ log.level.toLowerCase() + "'>"
											+ log.timeStamp + " " + log.level
											+ " [" + log.systemName + "] ("
											+ log.threadName + ") "
											+ log.message + "</div>");
							$("#logoutput").scrollTo($("#log-" + count), 800);

							count++;
						})
					}).fail(function() {
						$("#logoutput").append("<div class='log error'>FATAL Log console was unable to fetch data from server</div>");
					}).always(function() {
			});
}

var getLogFile = function() {
	window.open("serverlog?file=" + $("#logname").value, "_blank");
}

var getLogList = function() {
	$.getJSON(protocol+"//" + server + "/logger/system/loglist",function() {}).done(function(response) {
					$("#loglist").html('');
					$.each(response.files,
									function(i, file) {
										$("#loglist").append(
											"<a href='/logger/system/serverlog?file="
													+ file
													+ "' target='_blank' onclick='javascript: this.href=this.href+(?\"\":\"&filter=\")+(?\"\":\"&type=\")'>"
													+ file
													+ "</a><br />");
									})
				}).fail(function() {
					$("#loglist").html("<div class='log error'>FATAL Log console was unable to fetch data from server</div>");
					}).always(function() {
			});
}

var startMonitor = function() {
	if (monitorIntervalObj == -1) {
		getMonitorStates();
		monitorIntervalObj = setInterval("getMonitorStates()", monitorSleep);
		$("#appMonitor").append("<div id='srmsg-" + logIntervalObj
						+ "'>Starting monitor....</div>");
	}
}

var stopMonitor = function() {
	if (monitorIntervalObj > -1) {
		clearInterval(monitorIntervalObj);
		$("#appMonitor").append("<div>Stopped Monitor.</div>");
		monitorIntervalObj = -1;
	}
}

var getMonitorStates = function() {
	$.getJSON(protocol+"//" + server + "/logger/system/monitor",function() {
					}).done(function(response) {
						$("#appMonitor").html('');
						$.each(response.states, function(i, target) {
							$("#appMonitor").append(
									"<div>" + target.name
											+ ":<span class='monitor "
											+ target.state.toLowerCase() + "'>"
											+ target.state + "</span></div>");
						})
					}).fail(function() {
						$("#appMonitor").append("<div class='log error'>FATAL Log console was unable to fetch data from server</div>");
					}).always(function() {
			});
}

var appMonitorfontSize = 10;
var appMonitorfontUp = function() {
	$("#appMonitor").css("font-size", ++appMonitorfontSize + "px");
}
var appMonitorfontDown = function() {
	$("#appMonitor").css("font-size", --appMonitorfontSize + "px");
}

var logoutputfontSize = 10;
var logoutputfontUp = function() {
	$("#logoutput").css("font-size", ++logoutputfontSize + "px");
}
var logoutputfontDown = function() {
	$("#logoutput").css("font-size", --logoutputfontSize + "px");
}

var validateInterval = function(obj){
	if(!isNaN(obj.value) && obj.value.length>0){
		logSleep=obj.value; 
		$('#logoutput').append('<div>Changed interval to '+logSleep+'</div>')
	} else {
		$('#logoutput').append('<div>Illegal value for logSleep: '+obj.value+'</div>')
	}
}

var validateMInterval = function(obj){
	if(!isNaN(obj.value) && obj.value.length>0){
		monitorSleep=obj.value; 
		$('#appMonitor').append('<div>Changed monitor interval to '+logSleep+'</div>')
	} else {
		$('#appMonitor').append('<div>Illegal value for monitorSleep: '+obj.value+'</div>')
	}
}
