<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="zh-cn">
	<head>
		<meta charset="UTF-8">
		<title>编写基本信息</title>
			
		<jsp:include page="../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="${webroot }/static/css/add.css" />
		<!--验证插件-->
		<link rel="stylesheet" href="${webroot}/static/plugins/bootstrap-validate/bootstrapValidator.css">		
		<script src="${webroot}/static/plugins/bootstrap-validate/bootstrapValidator.js"></script>
		<style>
			#ssi-clearBtn{
				display: none;
			}
			.help-block{
		       	float: left;
		       	padding-left: 210px;
		     }
	        .form-control{
	        	display:inline-block;
	        }
	        .time-check{
				display: none;
				color: red;
				font-size: 12px;
				padding-left: 210px;
				padding-bottom: 20px;
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
		</style>
		<script type="text/javascript">
	 		var page="bill";
	 	</script>
	 	<script type="text/javascript">
		 	function pagTypeShow(){
	 			var tranTypeTemp = $("#tranType").val();
	 			if("0" === tranTypeTemp ){
	 				$("#payType0").show();
	 				$("#payType1").hide();
	 			}else if("1" === tranTypeTemp){
	 				$("#payType0").hide();
	 				$("#payType1").show();
	 			}else{
	 				$("#payType0").hide();
	 				$("#payType1").hide();
	 			}
	 		}
	 	</script>
	</head>

	<body>
	<!--头-->
	<jsp:include page="../common/header.jsp"/>
	<!--头end-->
	<!--下面内容-->
	<section id="defaultForm" class="content">
	    <!--大左侧-->
	    <jsp:include page="../common/menu.jsp"/>
	    <!--大左侧End-->
	    <!--小左侧-->
	    <div class="sideSubset">
	       	<ul>
				<li><h3>账单管理</h3></li>
				<li><a href="${ webroot}/main/bill/toListBills" >账单列表</a></li>
				<li><a href="${ webroot}/main/bill/toAddBill" class="active" >新建账单</a></li>
			</ul>
	    </div>
	    <!--小左侧End-->
			<!--右侧主题-->
			<div class="rightCon fr">
				<!--内容小导航-->
				<div class="subHead active">
					<a href="${ webroot}/main/bill/toAddBill" class="active" >新建账单</a>   
				</div>
				<!--内容小导航End-->
				<div class="subjectNew">
					<ul class="subTit">
						<li class="active_">编辑基本信息</li>
					</ul>
					<h4>基本信息</h4>
					<ul class="product_info">
						<li class="form-group">
							<h6>账单类型<font style="color: red;">*</font></h6>
								<div class="clearfix" style="display: inline-block;">
									<select class="form-control" id="tranType" name="tranType" onchange="pagTypeShow()">
												<option value="">-请选择-</option>
												<option value="0">支出账单</option>
												<option value="1">收入账单</option>
									</select>
								</div>
						</li>
						<li class="form-group" hidden id="payType0">
							<h6>交易方式<font style="color: red;">*</font></h6>
								<div class="clearfix" style="display: inline-block;">
										<select class="form-control" id="payType" name="payType">
											<option value="">-请选择-</option>
											<c:forEach items="${payTypeInfo }" var="info">
												<option value="${info.index }">${info.name }</option>
											</c:forEach>
										</select>
								</div>
						</li>
						<li class="form-group" hidden id="payType1">
							<h6>交易方式<font style="color: red;">*</font></h6>
								<div class="clearfix" style="display: inline-block;">
										<select class="form-control" id="earningType" name="earningType">
											<option value="">-请选择-</option>
											<c:forEach items="${earningTypeInfo }" var="info">
												<option value="${info.index }">${info.name }</option>
											</c:forEach>
										</select>
								</div>
						</li>
					</ul>
				</div>
				<div class="subjectNew">
					<h4 class="pt_50">账单信息</h4>
					<ul class="product_info">
						<li class="form-group">
							<h6></font>标题<font style="color: red;">*</font></h6>
							<input class="form-control" type="text" id="title" name="title" />
						</li>
						<li class="form-group">
							<h6></font>详细内容<font style="color: red;">*</font></h6>
							<textarea class="form-control" rows="3" cols="" id="content" name="content"></textarea>
						</li>
						<li class="form-group">
							<h6></font>交易对象<font style="color: red;">*</font></h6>
							<input class="form-control" type="text" id="tranTarget" name="tranTarget" />
						</li>
						<li  class="form-group">
							<h6>金额<font style="color: red;">*</font></h6>
							<input class="form-control" type="text" id="totalCost" name="totalCost" />
						</li>
						<li  class="form-group">
							<h6>备注</h6>
							<input class="form-control" type="text" id="remark" name="remark" />
						</li>
					</ul>
					<div class="w-1200" style="text-align: center;">
						<a href="javascript:saveBill();" class="btn__">提交</a>
					</div>
				</div>
			</div>
		</section>
		<script type="text/javascript">
			function saveBill(){
				var bootstrapValidator =  $('#defaultForm').data('bootstrapValidator');
 				bootstrapValidator.validate();
				  if(!bootstrapValidator.isValid()){
					  return;
			       }
				$.confirm({
	                type: 'blue',
	                animationSpeed: 300,
		            title:"确定提交吗?",
	                content: "",
	                buttons: {
	                    confirm: {
	                        text: '确认',
	                        btnClass: 'waves-effect waves-button',
	                        action: function () {
	                        	$.ajax({
	        						type:"POST",
	        						data:{
	        							tranType:$("#tranType").val(),
	        							payType:$("#payType").val(),
	        							earningType:$("#earningType").val(),
	        							title:$("#title").val(),
	        							content:$("#content").val(),
	        							tranTarget:$("#tranTarget").val(),
	        							totalCost:$("#totalCost").val(),
	        							remark:$("#remark").val()
	        						},
	        						url:webroot+'/main/bill/saveBill',
	        						dataType:'json',
	        						success:function(data){
	        							if(data.result='000'){
	        								$.alert('提交成功');
	        								window.location.href=webroot+'/main/bill/toListBills';
	        							}else{
	        								alert(data.resultMsg);
	        							}
	        						}
	        					});
	                        }
	                    },
	                    cancel: {
	                        text: '取消',
	                        btnClass: 'waves-effect waves-button'
	                    }
	                }
	            });
			}
			
			 $('#defaultForm').bootstrapValidator({
		            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
		                valid: 'glyphicon glyphicon-ok',
		                invalid: 'glyphicon glyphicon-remove',
		                validating: 'glyphicon glyphicon-refresh'
		            },
		            fields: {/*验证*/
		            	title: {
		                    validators: {
		                        notEmpty: {/*非空提示*/
		                            message: '不能为空'
		                        }, 
		                        regexp: {  
		                            regexp: /^[a-zA-Z0-9\u2E80-\u9FFF._]+$/,  
		                            message: '请输入汉字字母数字._'  
		                        },
		                        stringLength: {/*长度提示*/
		                            min: 2,
		                            max: 20,
		                            message: '长度必须在2到20之间'
		                        }/*最后一个没有逗号*/
		                    }
		                },
		                content: {
		                    validators: {
		                        notEmpty: {/*非空提示*/
		                            message: '不能为空'
		                        }, 
		                        regexp: {  
		                            regexp: /^[a-zA-Z0-9\u2E80-\u9FFF._]+$/,  
		                            message: '请输入汉字字母数字._'  
		                        },
		                        stringLength: {/*长度提示*/
		                            min: 2,
		                            max: 200,
		                            message: '长度必须在2到200之间'
		                        }/*最后一个没有逗号*/
		                    }
		                },
		                totalCost: {
		                    validators: {
		                        notEmpty: {/*非空提示*/
		                            message: '金额不能为空'
		                        },
		                        numeric:{message: '只能是数字'},
		                    }
		                },
		                tranType: {
		                    validators: {
		                        notEmpty: {/*非空提示*/
		                            message: '账单类型不能为空'
		                        },
		                    }
		                },
		                tranTarget: {
		                    validators: {
		                        notEmpty: {/*非空提示*/
		                            message: '交易对象不能为空'
		                        },
		                    }
		                }
		            }
		     });
		</script>
	</body>

</html>