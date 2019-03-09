<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="ys-article-form">
		<form id="ys-column-form" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td width="100px" align="right">栏目名称</td>
                <td><input type="text" name="columnName" class="wu-text" /></td>
            </tr>
            <tr>
                <td width="100px" align="right">栏目关键字</td>
                <td><input type="text" name="columnKeyword" class="wu-text" /></td>
            </tr>
            <tr>
                <td align="right">栏目等级</td>
                <td><input type="text" name="columnLevel" class="wu-text" /></td>
            </tr>
            <tr>
                <td width="100px" align="right">栏目描述</td>
                <td><textarea  rows="4" name="columnDescript" class="wu-text" /></textarea></td>
            </tr>
            <tr>
            	<td align="right">所属目录</td>
            	<td><select class="easyui-combotree" url="${ctx}/column/menu" name="columnParentId" style="width:130px" >选择目录</select></td>
            </tr>
            <tr>
            	<td align="right">创建时间</td>
            	<td><input class="easyui-datebox"  name="column-create-time" style="width:100px"></td>
            </tr>
            <tr>
            	<td align="right">属性</td>
            	<td>
	            	<span class="radioSpan">
		                <input type="radio" name="columnProperty" id="property_radio" value="0" />封面
		                <input type="radio" name="columnProperty" id="property_radio1" value="1" />列表
	            	</span>
            	</td>
            </tr>
            <tr>
            	<td align="right">封面模板</td>
            	<td><select id="column-model" name="coverTemplate"></select></td>
            </tr>
            <tr>
            	<td align="right" id="column-content">内容模板</td>
            	<td><select id="column-model-list" name="contentTemplate"></select></td>
            </tr>
        </table>
    </form>
    <div><button class="saveColumn">保存</button></div>
    </div>
    <script type="text/javascript">
   
    $(document).ready(function (){
    	$.ajax({
    		url:'${ctx}/model/info',
    		type:'get',
    		contentType : "application/json;charset=utf-8",
    		dataType : "json",
    		success:function(data){
    			  $.each(data, function(key, value) {
                      $('#column-model').append(
                              $('<option>').text(value.model_name).attr('value',
                            		  value.id));
                  });
    		}
    	});
    	
    	$.ajax({
    		url:'${ctx}/model/info',
    		type:'get',
    		contentType : "application/json;charset=utf-8",
    		dataType : "json",
    		success:function(data){
    			  $.each(data, function(key, value) {
                      $('#column-model-list').append(
                              $('<option>').text(value.model_name).attr('value',
                            		  value.id));
                  });
    		}
    	});
    	
    });
    
    $("#property_radio").attr("checked","checked");//默认第一个选中
    $("#column-model-list").css('display','none');
	$("#column-content").css('display','none');
    $('input:radio[name="column_property"]').change(function () {
        if($("#property_radio").is(":checked")){
            show();
        }
        if($("#property_radio1").is(":checked")){
        	show();
        }
    });
    function show(){
    	if($("#property_radio").is(":checked")){
    		$("#column-model-list").css('display','none');
    		$("#column-content").css('display','none');
    	}else{
    		$("#column-model-list").css('display','block');
    		$("#column-content").css('display','block');
    	}}
    
    $(".saveColumn").bind('click',function(){
    	debugger;
    	$("#ys-column-form").form('submit',{
    		url:'${ctx}/column/save',
    		onSubmit: function(param){
	        	
	        },
	        success:function(data){
	        	
	        	
	        }	
    	});
    });
    </script>
</body>
</html>