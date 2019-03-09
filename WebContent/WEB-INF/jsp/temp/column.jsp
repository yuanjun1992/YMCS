<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/includes.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div id="wu-toolbar-column" class="wu-toolbar">
            <div class="wu-toolbar-button">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="addColumn()" plain="true">添加</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEdit()" plain="true">修改</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick="remove()" plain="true">删除</a>
            </div>
	 </div>
	<table id="ys-column-treegrid" class="easyui-treegrid" toolbar="#wu-toolbar-column">
	
	</table>
</div>
<script>
$('#ys-column-treegrid').treegrid({
    url:'${ctx}/column/menu',
    idField:'id',
    treeField:'text',
    columns:[[
		{title:'栏目名称',field:'text',width:180},
		{field:'level',title:'栏目等级',width:60,align:'right'},
		{field:'id',title:'栏目ID',width:80,align:'right'},
    ]]
});
function addColumn(){
	var title="添加栏目";
	var url = "${ctx}/column/add";
	var iconCls = "icon-add";
	var iframe = 0;
	addTab(title,url,iconCls,iframe);
}
</script>