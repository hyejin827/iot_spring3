/**
 * 
 */

var AjaxUtilDx = function (url, dxObj, type, dataType){
	if(!url){
		alert("url정보가 없습니다.");
		return null;
	}
	this.url = url;
	var initData = {};
	
	if(dxObj && dxObj instanceof window.dhtmlXForm){
		var value = dxObj.getFormData();
		this.param = JSON.stringify(value);
	}else if(dxObj && dxObj instanceof dhtmlXGridObject ){
		var rowId = dxObj.getSelectedRowId();
		var colCnt = dxObj.getColumnCount();
		for(var i=0;i<colCnt;i++){
			var name = dxObj.getColumnId(i);
			var value = dxObj.cells(rowId, i).getValue();
			initData[name] = value;
		}
		this.param = JSON.stringify(initData);
	}
	this.type = type?type:"POST";
	this.dataType = dataType?dataType:"json";
	this.callbackSuccess = function(json){
    	var url = json.url;
    	var data = json.data;
    	var msg = json.msg;
    	if(msg){
    		alert(msg);
    	}
    	if(url){
        	pageMove(url);
    	}
	}
	
	this.send = function(callback){
		if(callback){
			this.callbackSuccess = callback;
		}
		$.ajax({ 
	        type     : this.type
	    ,   url      : this.url
	    ,   dataType : this.dataType 
	    ,header:{
	    	"Accept":"application/json",
	    	"Content-Type":"application/json; charset=UTF-8"
	    },
	    beforeSend:function(xhr){
	    	xhr.setRequestHeader("Accept","application/json");
	    	xhr.setRequestHeader("Content-Type","application/json; charset=UTF-8");
	    }
	    ,   data     : this.param
	    ,   success : this.callbackSuccess
	    ,   error : function(xhr, status, e) {
		    	alert("에러 : "+e);
		},
		done : function(e) {
		}
		});
	}
}
var AjaxUtil = function (url, params, type, dataType){
	if(!url){
		alert("url정보가 없습니다.");
		return null;
	}
	this.url = url;
	var initData = {}
	
	this.param = JSON.stringify(initData);
	if(params){
		var paramArr = params.split(",");

		var data = {};
		for(var i=0,max=paramArr.length;i<max;i++){
			var objType =  paramArr[i].split("_")[0];
			var objName = paramArr[i].split("_")[1];
			
			if(objType=="it"){
				data[objName] = $("input[name=" + objName +"]").val();
			}else if(objType=="s"){
				data[objName] = $("select[name=" + objName +"]").val();
			}
		}
		this.param = JSON.stringify(data);
		
	}
	this.type = type?type:"POST";
	this.dataType = dataType?dataType:"json";
	this.callbackSuccess = function(json){
    	var url = json.url;
    	var data = json.data;
    	var msg = json.msg;
    	if(msg){
    		alert(msg);
    	}
    	if(url){
        	pageMove(url);
    	}
	}
	
	this.setCallbackSuccess = function(callback){
		this.callbackSuccess = callback;
	}
	this.send = function(callback){
		if(callback){
			this.callbackSuccess = callback;
		}
		$.ajax({ 
	        type     : this.type
	    ,   url      : this.url
	    ,   dataType : this.dataType 
	    ,   beforeSend: function(xhr) {
	        xhr.setRequestHeader("Content-Type", "application/json");
	    }
	    ,   data     : encodeURIComponent(this.param)
	    ,   success : this.callbackSuccess
	    ,   error : function(xhr, status, e) {
		    	alert("에러 : "+xhr.responseText);
		},
		done : function(e) {
		}
		});
	}
}

/*
 * dhtmlx 커스터 마이징
 */
dhtmlXCellObject.prototype.hideAll = function(){
	this.button("close").hide();
	this.button("minmax").hide();
	this.button("park").hide();
}

dhtmlXWindows.prototype.center = function(){
	this.window("win1").centerOnScreen();
	this.window("win1").denyMove();
	this.window("win1").denyResize();
}



