<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div id="form-article" class="easyui-panel" data-options="fit:true,region:'center',border:true">
			<form id="model-form"  enctype="multipart/form-data" method="post">
				<input id="model-file" name="model-file" type="file" />
			</form>
			<button class="file-submit">上传</button>
		 <table id="model-table" class="model-table" ></table>
		</div>
		
		

	</div>
	
	<script type="text/javascript">
		$(".file-submit").bind('click',function(){
			$("#model-form").form('submit',{
	    		url:'${ctx}/resource/upload/fileUpload',
	    		onSubmit: function(param){
		        	
		        },
		        success:function(data){
		        	
		        	
		        }	
	    	});
		});
		
		var tab = $('#wu-tabs').tabs('getSelected');
		var index = $('#wu-tabs').tabs('getTabIndex',tab);
		
		var sel = ".model-datagrid-"+index;
		$(".model-table").addClass('model-datagrid-'+index);
		console.log(sel);
		$(sel).datagrid({
			url:'${ctx}/model/folderList?rank='+index,
			loadFilter:pagerFilter,		
			rownumbers:true,
			singleSelect:false,
			pageSize:20,        
			multiSort:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{ field:'filename',title:'文件名称',width:180,sortable:true},
				{ field:'filetype',title:'文件类型',width:100},
				{ field:'opt',title:'操作',width:100,
					formatter: function(value,row,index){
						var type = 0;
						if(row.filetype == "文件夹"){
							type = 1;
						}
						var btn = '<a class="delcls" onclick="" href="javascript:void(0)">删除</a>'+
						'<a class="morecls" onclick="nextPage(\''+ row.filename + '\',' + type + ')"  href="javascript:void(0)">打开</a>';  
		                return btn;
					}
				}
			]],
			onLoadSuccess:function(data){  
		        $('.delcls').linkbutton({text:'删除',plain:true,iconCls:'icon-delete3'});  
		        $('.morecls').linkbutton({text:'打开',plain:true,iconCls:'icon-book-next'});
		    } 
		});
		
		function nextPage(tit,path){
			var title=tit; 	
			var url = "${ctx}/model/folderList?flag=1&name="+tit;
			if(path==1){
				url = "${ctx }/model/index?name="+tit+"&rank="+index;
			}
			var iconCls = "icon-folder";
			var iframe = 0;
			addTab(title,url,iconCls,iframe);;
		} 
		
	</script>
</body>
</html>