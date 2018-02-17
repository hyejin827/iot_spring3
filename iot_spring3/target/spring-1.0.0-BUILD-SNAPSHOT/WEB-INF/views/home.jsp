<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<script>

function callback(res){
	$("#title").html("JSON");
	$("#result_div").html(res);
}

var au = new AjaxUtil("${pPath}/json", null, "get","html");
au.send(callback);

</script>
<body>
<h1 id="title">
	Hello world!
</h1>
<div id="result_div">
</div>
</body>
</html>