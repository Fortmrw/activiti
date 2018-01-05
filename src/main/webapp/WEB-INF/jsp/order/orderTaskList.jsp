<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>订单管理</title>
	 	<jsp:include page="../common/common.jsp"/>
	 	<link rel="stylesheet" type="text/css" href="${webroot}/static/css/listView.css"/>
	 	<script type="text/javascript"  src="${webroot}/static/plugins/bootstrap-table-1.11.0/bootstrap-table-export.js"></script>
	 	<script type="text/javascript"  src="${webroot}/static/plugins/bootstrap-table-1.11.0/tableExport.js"></script>
	 	<style type="text/css">
		 	.commodity-search{
		 		margin-left: 30px;
		 	} 
		 	.commodity-search input,.commodity-search select{
		 		height: 30px;
		 		line-height: 30px;
		 	}
		 	.search-content span{
		 		height: 30px;
		 		line-height: 30px;
		 	}
		 	.subHead a{
		 		margin-top: 12px;
		 		display: inline-block;
				width: 91px;
				height: 31px;
				line-height: 29px;
				background-color: #f7f7fa;
				border-radius: 10px;
				font-size: 14px;
				color: #6E768F;
				text-align: center;
				border: 1px solid #E3E3E3;
				margin-right: 20px;
		 	}
		 	.subHead a:nth-child(1){
		 			background-color: #FF533F;
					color: #fff;
					border: none;
		 	}
		 	select option {
		 		padding: 10px 0px 10px 5px
		 	}
	 	</style>
	 	<script type="text/javascript">
	 		var page="order";
	 	</script>
	</head>
	<body>
	<jsp:include page="../common/header.jsp"/>
		<section class="content">
		<jsp:include page="../common/menu.jsp"/>
			<!--小左侧-->
			<div class="sideSubset">
				<ul>
					<li><h3>订单管理</h3></li>
					<li><a href="${ webroot}/main/order/toListOrders" >订单列表</a></li>
					<li><a href="${ webroot}/main/order/toAddOrder" >新建订单</a></li>
					<li><a href="${ webroot}/main/order/toAddOrder"  class="active">我的订单任务</a></li>
				</ul>
			</div>
			<!--小左侧End-->
			<!--右侧主题-->
			<div class="rightCon fr">
				<!--内容小导航-->
				<div class="subHead active">
					<a href="${ webroot}/main/order/toAddOrder"  class="active">我的订单任务</a>   
				</div>
				<!--内容小导航End-->
				<div class="commodity-content">
					<div class="shop-table">
						<div class="commodity-search clearfix">
							<div class="fl search-content clearfix">
								<div class="simple-input fl clearfix">
									<span class="fl">标题</span>
									<input class="fl" type="text" name="" id="title"  />
								</div>
								<div class="simple-input fl clearfix">
									<span class="fl">订单编号</span>
									<input class="fl" type="text" name="" id="billNo"  />
								</div>
								<div class="select-input fl clearfix">
									<span class="fl">订单类型</span>
									<select class="fl" name="" id="type" >
										<option value="" >请选择</option>
										<option value="1" >收入订单</option>
										<option value="0" >支出订单</option>
									</select>
								</div>
								<div class="area-input fl clearfix">
									<span class="fl">金额</span>
									<input class="fl " type="text" id = "totalCostStart" >
									<font class="fl">—</font>
									<input class="fl " type="text" id = "totalCostEnd" >
								</div>
								<div class="area-input fl clearfix">
									<span class="fl">创建时间</span>
									<input class="fl " type="text" id = "createTimeStart" readonly>
									<font class="fl">—</font>
									<input class="fl " type="text" id = "createTimeEnd" readonly>
								</div>
							</div>
							<div class="fl search-btn">
								<button id="search">搜索</button>
								<button id="resetButton">重置</button>
							</div>
						</div>
						<table id="table" ></table>
					</div>
				</div>
			</div>
		</section>
	</body>
	<script type="text/javascript">
	var $table = $('#table');
	var totalSize;
	 $(function() {
			$table.bootstrapTable({
			url: webroot+'/main/order/toOrderTaskList',
// 			url: "../../static/data/test.json",
            striped: true,
            minimumCountColumns: 2,
            clickToSelect: true,
            responseHandler:function(res){
            	totalSize = res.total
            	return res;
            },
//	             detailFormatter: 'detailFormatter',
//	             detailView: true,
				showExport: true,  //是否显示导出按钮 
				exportDataType:'all',
	       		exportTypes:['excel'],  //导出文件类型
//	        		Icons:'glyphicon-export',  
	         	exportOptions:{
	             ignoreColumn: [0,11],  //忽略某一列的索引  
	             fileName: '订单表',  //文件名称设置  
	             worksheetName: 'sheet1',  //表格工作区名称  
	             tableName: '订单表',  
	             excelstyles: ['background-color', 'color', 'font-size', 'font-weight'],  
	         },
	            pagination: true,
	            paginationLoop: false,
	            pageList:[10,20,30,40,50],
	            classes: 'table table-hover table-no-bordered',
	            // 服务端分页
	            sidePagination: 'server',
			 queryParams:function(params) {
				var pageNum = (params.offset+1==1?1:((params.offset-(params.limit))/10+2));
				var numPerPage = params.limit;
				if(isNaN(pageNum)){pageNum="";}
				if(typeof(numPerPage)=="undefined"){numPerPage=totalSize;}
			  return {//此处添加查询的分页条件
					pageNum: pageNum,
					numPerPage: numPerPage,
		           type:$('#type').val(),
		           billNo:$('#billNo').val(),
					title:$('#title').val(),
		           totalCostStart:$('#totalCostStart').val(),
		           totalCostEnd:$('#totalCostEnd').val(),
		           createTimeStart:$('#createTimeStart').val(),
		           createTimeEnd:$('#createTimeEnd').val(),
	          };
			 },
	            smartDisplay: false,
	            idField: 'id',
	        //  sortName: 'id',
	            // 关闭排序
	            sortable: false,
	            escape: true,
	            idField: 'systemId',
	            maintainSelected: true,
	            toolbar: '#toolbar',
			columns: [
			// field： 对应数据字段 
			// title： 标题内容
			// sortable： 是否开启排序
			// halign : 内容垂直居中
			//formatter : 通过formatter可以自定义列显示的内容
			//value：当前field的值，即id
			//row：当前行的数据
				{field: 'Number', title:'序号', align: 'center',formatter:function (value, row, index) { return index+1;  }  },
				{field: 'billNo', title: '订单号', align: 'center',},
				{field: 'title', title: '标题', align: 'center',},
				{field: 'content', title: '详细内容', align: 'center'},
				{field: 'tranType', title: '订单类型', align: 'center',formatter:'typeFormatter'},
				{field: 'totalCost', title: '订单金额', align: 'center'},
				{field: 'createTime', title: '创建时间', align: 'center'},
				{field: 'action', title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false,align:"center"}
			]
		})
	 });
	 
	 function actionFormatter(value, row, index) {
	        return [
	            '<a class="like" href="${webroot}/order/orderDetail?id='+row.id+'" data-toggle="tooltip" title="详情"><i class="glyphicon glyphicon-list-alt"></i></a>　',
	        ].join('');
	    }
	 function typeFormatter(value){
		 var typeWord="-";
		 if("0"==value){
			 typeWord="支出订单";
		 }else if("1"==value){
			 typeWord="收入订单";
		 }
		 return typeWord;
	 }
		 $('#search').click(function () {
		        $table.bootstrapTable('refresh', {url:webroot+'/main/order/orderTaskList'});
			});
	 
		 $('#resetButton').click(function () {
			 	window.location.href=webroot+"/main/order/toOrderTaskList";
			});
	</script>
</html>