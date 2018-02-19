<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" charset="utf-8">
var send = function(url, data,before){
   var type= document.getElementById("type").value;
   var conType = document.getElementById("conType").value;
   var beforeSend = function(xhr){
       xhr.setRequestHeader("Accept","application/json");
       xhr.setRequestHeader("Content-Type",conType + "; charset=UTF-8");
    };
    if(type=="GET"){
       data = encodeURIComponent(data);
    }
    if(before){
       beforeSend = before;
    }
   $.ajax({ 
           type        : type
       ,   url         : url
       ,   dataType    : 'text' 
       ,   beforeSend   : beforeSend
       ,   data        : data
       ,   success    : function(res){
                   alert(res);
                   }
       ,   error       : function(xhr, status, e) {
                      dhtmlx.alert(xhr.responseText);
                  }
   });
}
</script>
<body>
   <form>
      id : <input type="text" id="id" name="id" value="test_id"><br>
      text : <input type="text" id="text" name="text" value="test_text"><br>
      type : <select id="type" name="type">
               <option value="GET">GET</option>
               <option value="POST">POST</option>
            </select> <br>
      contentType : <select id="conType" name="conType">
               <option value="multipart/form-data">multipart/form-data</option>
               <option value="text/html">text/html</option>
               <option value="application/json">application/json</option>
               <option value="application/x-www-form-urlencoded">application/x-www-form-urlencoded</option>
            </select><br>
      <input type="button" value="jsonRequestBodyToMap" onclick="go(this)"><br>
      <input type="button" value="jsonRequestBodyToObject" onclick="go(this)"><br>
      <input type="button" value="formRequestBodyToMap" onclick="go(this)"><br>
      <input type="button" value="formRequestBodyToObject" onclick="go(this)"><br>
      <input type="button" value="jsonRequestParamToMap" onclick="go(this)"><br>
      <input type="button" value="jsonRequestParamToObject" onclick="go(this)"><br>
      <input type="button" value="formRequestParamToMap" onclick="go(this)"><br>
      <input type="button" value="formRequestParamToObject" onclick="go(this)"><br>
      <input type="button" value="formToMap" onclick="go(this)"><br>
      <input type="button" value="formToObject" onclick="go(this)"><br>
   </form>
<script type="text/javascript" charset="utf-8">

   function go(btn){
      var url = "${root}/param/" + btn.value;
      var id = btn.form.id.value;
      var text = btn.form.text.value;
      var conType = document.getElementById("conType").value;
      var type = document.getElementById("type").value;
      
      btn.form.action = url;
      btn.form.enctype = conType;
      btn.form.method = type;
      
      var dataObj = {
            id       : id,
            text    : text,
            conType : conType,
            type    : type
         };
      
      var data = JSON.stringify(dataObj);
      if(btn.value=="jsonRequestBodyToMap"){
         //반드시 post + application/json + json string paramter => ok
         send(url, data);
      }else if(btn.value=="jsonRequestBodyToObject"){
         //반드시 post + application/json + json string paramter => ok
         send(url, data);
      }else if(btn.value=="formRequestBodyToMap"){
         // 모든 타입, 컨테트타입 모두다 에러
         btn.form.submit();
      }else if(btn.value=="formRequestBodyToObject"){
         // 모든 타입, 컨테트타입 모두다 에러
         btn.form.submit(); 
      }else if(btn.value=="jsonRequestParamToMap"){
         // 모든 타입, 컨텐트 타입 에러나지 않음.
         // 그러다 name과 서버가 받는 map에 제대로 key값을 받기 위해서는 
         // param key이름을 더 주어야 한다. 아래 btn.value="jsonRequestParamToObject"참고
         var param = "obj=" + data;
         send(url, param);
      }else if(btn.value=="jsonRequestParamToObject"){
         // post + application/x-www-form-urlencoded + key=value(json string) =>ok
         var param = "obj=" + data;
         send(url, param);
      }else if(btn.value=="formRequestParamToMap"){
         // get + 모든 컨텐트 타입 가능
         // post + multipart/form-data 제외한 모든 컨텐트타입 가능
         btn.form.submit(); 
      }else if(btn.value=="formRequestParamToObject"){
         // 모든 타입, 컨테트타입 모두다 에러
         btn.form.submit(); 
      }else if(btn.value=="formToMap"){
         // 모든 타입, 컨테트타입 모두다 에러나지 않으나 key : value매핑안됨
         btn.form.submit(); 
      }else if(btn.value=="formToObject"){ 
         // get + 모든 컨텐트 타입 가능
         // post + multipart/form-data 제외한 모든 컨텐트타입 가능
         btn.form.submit(); 
      }       
   }
</script>
</body>
</html>