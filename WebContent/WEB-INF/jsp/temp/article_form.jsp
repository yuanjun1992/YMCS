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
		<form id="ys-form" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td width="100px" align="right">文章标题</td>
                <td><input type="text" name="article-title" class="wu-text" /></td>
            </tr>
            <tr>
                <td align="right">文章缩略图</td>
                <td><input id="idFile" style="width:224px" runat="server" name="pic" onchange="javascript:setImagePreview(this,localImag,preview);" type="file" /></td>
                
            </tr>
            <tr>
            	<td align="right"></td>
            	<td id="localImag">  
    					<img id="preview" alt="预览图片" onclick="over(preview,divImage,imgbig);" src="${ctx}/images/bg_header.jpg" style="width:60px; height: 60xp;"/> 
				</td>
			</tr>
            <tr>
                <td align="right">文章作者</td>
                <td><input type="text" name="article-author" class="wu-text" /></td>
            </tr>
            <tr>
            	<td align="right">所属目录</td>
            	<td><select class="easyui-combotree" url="${ctx}/column/menu" name="category-id" style="width:130px" >选择目录</select></td>
            </tr>
            <tr>
            	<td align="right">发布时间</td>
            	<td><input class="easyui-datebox"  name="article-create-time" style="width:100px"></td>
            </tr>
            <tr>
            	<td align="right">文章描述</td>
            	<td><input type="text"  name="article-description" style="width:100px" class="wu-text"></td>
            </tr>
            <tr>
            	<td align="right">关键字</td>
            	<td><input type="text"  name="keyword" style="width:100px" class="wu-text"></td>
            </tr>
            <tr>
                <td valign="top" align="right">内 容</td>
                <td><script id="myEditor" type="text/plain" style="width:75%;height:256px;">编辑文章内容</script></td>
            </tr>
        </table>
    </form>
    </div>
    <div>
    	<button id="submit_button"  class="savebut">保存</button>
    </div>	


<script type="text/javascript" src="${ctx }/editor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx }/editor/ueditor.all.js"></script>
<script type="text/javascript">
$(function(){
	var ue =  UE.getEditor('myEditor');
	UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
	UE.Editor.prototype.getActionUrl = function(action){
		if(action == '/resource/upload/images'){
			return '${ctx}/resource/upload/images';
		}else{
			return this._bkGetActionUrl.call(this, action);
		}
	};

	$('#submit_button').bind('click', function(){
	
		$.messager.progress();
	    $('#ys-form').form('submit',{
	        url: "${ctx}/article/save",
	        onSubmit: function(param){
	        	param = ue.getContent();
	            var isValid = $('#ys-form').form('validate');
	            if(!isValid){
	            	$.messager.progress('close');
	            }
	            return isValid;
	        },
	        success:function(data){
	        	var map = $.parseJSON(data);
	        	if(map.result){
	        		$.messager.progress('close');
	        	}
	        	
	        }							
	    });
	});
});
//检查图片的格式是否正确,同时实现预览
function setImagePreview(obj, localImagId, imgObjPreview) {
    var array = new Array('gif', 'jpeg', 'png', 'jpg', 'bmp'); //可以上传的文件类型
    if (obj.value == '') {
        $.messager.alert('消息','请选择要上传的图片!','info');
        return false;
    }
    else {
        var fileContentType = obj.value.match(/^(.*)(\.)(.{1,8})$/)[3]; //这个文件类型正则很有用 
        ////布尔型变量
        var isExists = false;
        //循环判断图片的格式是否正确
        for (var i in array) {
            if (fileContentType.toLowerCase() == array[i].toLowerCase()) {
                //图片格式正确之后，根据浏览器的不同设置图片的大小
                if (obj.files && obj.files[0]) {
                    //火狐下，直接设img属性 
                    imgObjPreview.style.display = 'block';
                    imgObjPreview.style.width = '64px';
                    imgObjPreview.style.height = '64px';
                    //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式 
                    imgObjPreview.src = window.URL.createObjectURL(obj.files[0]);
                }
                else {
                    //IE下，使用滤镜 
                    obj.select();
                    var imgSrc = document.selection.createRange().text;
                    //必须设置初始大小 
                    localImagId.style.width = "64px";
                    localImagId.style.height = "64px";
                    //图片异常的捕捉，防止用户修改后缀来伪造图片 
                    try {
                        localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                        localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader=").src = imgSrc;
                    }
                    catch (e) {
                        $.messager.alert("您上传的图片格式不正确，请重新选择!");
                        return false;
                    }
                    imgObjPreview.style.display = 'none';
                    document.selection.empty();
                }
                
                isExists = true;
                return true;
            }
        }
        if (isExists == false) {
            $.messager.alert('消息','上传图片类型不正确!','error');
            return false;
        }
        return false;
    }
    
}
</script>
</body>

</html>