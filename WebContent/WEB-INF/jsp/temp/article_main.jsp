<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/includes.jsp"%>

<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west',border:true,split:true," title="分类管理" style="width:150px; padding:5px;">
        <ul id="category-tree" class="easyui-tree"></ul>
    </div>
    <div id="form-article" class="easyui-panel" data-options="fit:true,region:'center',border:true">
    	<!-- Begin of toolbar -->
        <div id="wu-toolbar-article" class="wu-toolbar">
            <div class="wu-toolbar-button">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="addBtn()" plain="true">添加</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEdit()" plain="true">修改</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick="remove()" plain="true">删除</a>
            </div>
            <div class="wu-toolbar-search">
                <label>起始时间：</label><input class="easyui-datebox" style="width:100px">
                <label>结束时间：</label><input class="easyui-datebox" style="width:100px">
                <label>关键词：</label><input class="wu-text" style="width:100px">
                <a href="#" class="easyui-linkbutton" iconCls="icon-search">开始检索</a>
            </div>
        </div>
        <!-- End of toolbar -->
        <table id="wu-datagrid" class="wu-datagrid" toolbar="wu-toolbar-article"></table>
    </div>
    
</div>
<!-- Begin of easyui-dialog -->
<div id="wu-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
	<form id="wu-form" method="post">
        <table>
            <tr>
                <td width="60" align="right">姓 名:</td>
                <td><input type="text" name="name" class="wu-text" /></td>
            </tr>
            <tr>
                <td align="right">邮 箱:</td>
                <td><input type="text" name="email" class="wu-text" /></td>
            </tr>
            <tr>
                <td align="right">主 题:</td>
                <td><input type="text" name="subject" class="wu-text" /></td>
            </tr>
            <tr>
                <td valign="top" align="right">内 容:</td>
                <td><textarea name="content" rows="6" class="wu-textarea" style="width:260px"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<!-- End of easyui-dialog -->
<script type="text/javascript">
	/**
	* Name 载入菜单树
	*/
	$('#category-tree').tree({
		url:'${ctx}/column/menu',
		onClick:function(node){
			alert(node.text);
		}
	});
	
	/*
	添加文章
	*/
	function addBtn(){
		var title="添加文章";
		var url = "${ctx}/article/add";
		var iconCls = "icon-add";
		var iframe = 0;
		addTab(title,url,iconCls,iframe);
	}
	
	/**
	* Name 添加记录
	*/
	function add(){
		$('#wu-form').form('submit', {
			url:'',
			success:function(data){
				if(data){
					$.messager.alert('信息提示','提交成功！','info');
					$('#wu-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
				}
			}
		});
	}
	
	/**
	* Name 修改记录
	*/
	function edit(){
		$('#wu-form').form('submit', {
			url:'',
			success:function(data){
				if(data){
					$.messager.alert('信息提示','提交成功！','info');
					$('#wu-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
				}
			}
		});
	}
	
	/**
	* Name 删除记录
	*/
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var items = $('#wu-datagrid').datagrid('getSelections');
				var ids = [];
				$(items).each(function(){
					ids.push(this.productid);	
				});
				//alert(ids);return;
				$.ajax({
					url:'',
					data:'',
					success:function(data){
						if(data){
							$.messager.alert('信息提示','删除成功！','info');		
						}
						else
						{
							$.messager.alert('信息提示','删除失败！','info');		
						}
					}	
				});
			}	
		});
	}
	
	/**
	* Name 打开添加窗口
	*/
	function openAdd(){
		$('#wu-form').form('clear');
		$('#wu-dialog').dialog({
			closed: false,
			modal:true,
            title: "添加信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: add
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#wu-dialog').dialog('close');                    
                }
            }]
        });
	}
	
	/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		$('#wu-form').form('clear');
		var item = $('#wu-datagrid').datagrid('getSelected');
		//alert(item.productid);return;
		$.ajax({
			url:'',
			data:'',
			success:function(data){
				if(data){
					$('#wu-dialog').dialog('close');	
				}
				else{
					//绑定值
					$('#wu-form').form('load', data)
				}
			}	
		});
		$('#wu-dialog').dialog({
			closed: false,
			modal:true,
            title: "修改信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#wu-dialog').dialog('close');                    
                }
            }]
        });
	}	
	
	/**
	* Name 分页过滤器
	*/
	function pagerFilter(data){            
		if (typeof data.length == 'number' && typeof data.splice == 'function'){// is array                
			data = {                   
				total: data.length,                   
				rows: data               
			}            
		}        
		var dg = $(this);         
		var opts = dg.datagrid('options');          
		var pager = dg.datagrid('getPager');          
		pager.pagination({                
			onSelectPage:function(pageNum, pageSize){                 
				opts.pageNumber = pageNum;                   
				opts.pageSize = pageSize;                
				pager.pagination('refresh',{pageNumber:pageNum,pageSize:pageSize});                  
				dg.datagrid('loadData',data);                
			}          
		});           
		if (!data.originalRows){               
			data.originalRows = (data.rows);       
		}         
		var start = (opts.pageNumber-1)*parseInt(opts.pageSize);          
		var end = start + parseInt(opts.pageSize);        
		data.rows = (data.originalRows.slice(start, end));         
		return data;       
	}
	
	/**
	* Name 载入数据
	*/
	$('.wu-datagrid').datagrid({
		url:'${ctx}/article/info?articleCategoryId=-1',
		loadFilter:pagerFilter,		
		rownumbers:true,
		singleSelect:false,
		pageSize:20,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		fit:true,
		columns:[[
			{ checkbox:true},
			{ field:'id',title:'ID',width:100,sortable:true},
			{ field:'articleCategoryName',title:'所属栏目',width:100},
			{ field:'articletitle',title:'文章标题',width:180,sortable:true},
			{ field:'articledescription',title:'文章摘要',width:180},
			{ field:'articledatetime',title:'文章创建时间',width:100},
			{ field:'articleauthor',title:'文章作者',width:100}
		]]
	});
	
</script>