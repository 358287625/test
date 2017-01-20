<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	var host = window.location.host;
	var websocket;
	function init() {
		var ws = new WebSocket("ws://localhost:8080/jyd/websocket");
			 
			ws.onopen = function(){ws.send("Test!"); };
			//当有消息时，会自动调用此方法
			ws.onmessage = function(evt){console.log(evt.data);
			setTimeout(function(){ws.send("Test11!");}, 5000);
			};
			 
			ws.onclose = function(evt){console.log("WebSocketClosed!");};
			 
			ws.onerror = function(evt){console.log("WebSocketError!");};
	}
</script>
</head>
<body onload="init()">

</body>
</html>