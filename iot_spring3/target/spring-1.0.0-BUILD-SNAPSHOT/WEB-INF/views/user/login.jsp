<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>

<script>
	var winF,popW;
	$(document).ready(function(){
		winF = new dhtmlXWindows();
		winF.attachViewportTo("winVP");
		popW = winF.createWindow("win1",20,30,290,250);
		popW.button("close").hide();
		popW.button("minmax").hide();
		popW.button("park").hide();
		popW.setText("Login"); 

		winF.window("win1").centerOnScreen();
		winF.window("win1").denyMove();
		winF.window("win1").denyResize();
		var formObj = [
			        {type:"settings", offsetTop:12,name:"connectionInfo",labelAlign:"left"},
					{type:"input",name:"uiId", label:"아이디 : ",required:true},
					{type:"password",name:"uiPwd", label:"비밀번호 : ",required:true},
					{type: "block", blockOffset: 0, list: [
						{type: "button", name:"loginBtn",value: "로그인"},
						{type: "newcolumn"},
						{type: "button", name:"cancelBtn",value: "취소"},
						{type: "newcolumn"},
						{type: "button", name:"joinBtn",value: "회원가입"}
					]}
			];
		var form = popW.attachForm(formObj,true);
		
		form.attachEvent("onButtonClick",function(id){
			if(id=="loginBtn"){
				if(form.validate()){
					form.send("${root}/user/login", "GET",callback);
				}
			}else if(id=="cancelBtn"){
				form.clear();
			}else if(id=="joinBtn"){
				location.href="${pPath}/user/join";
			}
		});
	});
	
	function callback(loader, res){
		if(loader.xmlDoc.status == 200){
			var res = JSON.parse(res);
			alert(res.msg);
			if(res.loginOk){
				location.href="${root}/path/db/main";
			}
		}else{  
			console.log(res);
		}
	}
</script>
<body>
	<div id="winVP"></div>
</body>
</html>