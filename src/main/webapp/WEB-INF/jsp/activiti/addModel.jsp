<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="zh-cn">
	<head>
		<meta charset="UTF-8">
		<title>创建模型</title>
			
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
	 		var page="activiti";
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
				<li><h3>流程管理</h3></li>
				<li><a href="${ webroot}/main/activiti/toListModel" >流程模型列表</a></li>
				<li><a href="${ webroot}/main/activiti/toListProcess" >流程部署列表</a></li>
				<li><a href="${ webroot}/main/activiti/toCreateModel" class="active" >新建流程图</a></li>
			</ul>
	    </div>
	    <!--小左侧End-->
			<!--右侧主题-->
			<div class="rightCon fr">
				<!--内容小导航-->
				<div class="subHead active">
					<a href="${ webroot}/main/activiti/toCreateModel" class="active" >新建流程图</a>   
				</div>
				<!--内容小导航End-->
				<div class="subjectNew">
					<ul class="subTit">
						<li class="active_">基本信息</li>
					</ul>
				</div>
				<div class="subjectNew">
					<h4 class="pt_50">流程图信息</h4>
					<ul class="product_info">
						<li class="form-group">
							<h6></font>名 称<font style="color: red;">*</font></h6>
							<textarea class="form-control" rows="3" cols="" id="name" name="name"></textarea>
						</li>
						<li class="form-group">
							<h6></font>K E Y<font style="color: red;">*</font></h6>
							<input class="form-control" type="text" id="key" name="key" />
						</li>
						<li class="form-group">
							<h6>描 述<font style="color: red;">*</font></h6>
							<input class="form-control" type="text" id="description" name="description" />
						</li>
					</ul>
					<div class="w-1200" style="text-align: center;">
						<a href="javascript:saveModel();" class="btn__">提交</a>
					</div>
				</div>
			</div>
		</section>
		<script type="text/javascript">
			function saveModel(){
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
	                        	name:$("#name").val();
    							key:$("#key").val();
    							description:$("#description").val();
	                        	window.location.href=webroot+'/main/activiti/create?name='+name+'&key='+key+"&description="+description;
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
		            	name: {
		                    validators: {
		                        notEmpty: {/*非空提示*/
		                            message: '不能为空'
		                        }, 
		                        stringLength: {/*长度提示*/
		                            min: 2,
		                            max: 20,
		                            message: '长度必须在2到20之间'
		                        }/*最后一个没有逗号*/
		                    }
		                },
		                key: {
		                    validators: {
		                        notEmpty: {/*非空提示*/
		                            message: '不能为空'
		                        }, 
		                        regexp: {  
		                            regexp: /^[a-zA-Z0-9._]+$/,  
		                            message: '请输入字母数字._'  
		                        },
		                        stringLength: {/*长度提示*/
		                            min: 2,
		                            max: 200,
		                            message: '长度必须在2到200之间'
		                        }/*最后一个没有逗号*/
		                    }
		                },
		                description: {
		                    validators: {
		                        notEmpty: {/*非空提示*/
		                            message: '描述不能为空'
		                        }
		                    }
		                }
		            }
		     });
		</script>
	</body>

</html>