<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>注册用户</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="common/common.jsp"></jsp:include>
<script type="application/x-javascript">
	 addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } 
</script>
<meta name="keywords"
	content="Zhr,后台系统,管理系统,java,spring,spring-boot" />
<link href="${webroot }/static/css/style.css" rel='stylesheet' type='text/css' />
<script src="${webroot }/static/plugins/ajax/Request.js" type="text/javascript"></script>
	<!--验证插件-->
		<link rel="stylesheet" href="${webroot}/static/plugins/bootstrap-validate/bootstrapValidator.css">		
		<script src="${webroot}/static/plugins/bootstrap-validate/bootstrapValidator.js"></script>
</head>
<body>

	<h1>Zhr 基础系统</h1>
	<div class="login-form" id="defaultForm">
<%-- 		<form id="submitForm" action="${webroot }/login/exeLogin" method="post"> --%>
			<div class="head-info">
				<label class="lbl-1"></label> <label class="lbl-2"></label> <label
					class="lbl-3"></label>
			</div>
			<div class="clear"></div>
			<div class="avtar">
				<img src="${webroot }/static/images/avtar.png" />
			</div>
			<div class="form-group">
				<input type="text" class="text" name="username" placeholder="用户名"> 
				<input type="password" class="pwd" name="password" placeholder="密码" style="margin-bottom: 0em;">
				<input type="password" class="pwd" name="repassword" placeholder="确认密码" >
			</div>
			<p style="color:red">${resultMsg }</p>
			<div class="signin">
				<input class="login" type="button" value="注册用户">
			</div>
<!-- 		</form> -->
	</div>
	<div class="copy-rights"></div>
</body>
</html>
<script type="text/javascript">
	Request.BASH_PATH = "/";
	var isLogin = false;
	$(function() {
		function register() {
			var bootstrapValidator =  $('#defaultForm').data('bootstrapValidator');
				bootstrapValidator.validate();
			  if(!bootstrapValidator.isValid()){
				  return;
		       }
			if (isLogin) {
				alert("正在注册...");
				return;
			}
			var uname = $(".text").val();
			var pwd = $(".pwd").val();
			if ($.trim(uname) == "") {
				$(".text").focus();
				return;
			}
			if ($.trim(pwd) == "") {
				$(".pwd").focus();
				return;
			}
			isLogin = true;
			Request.post("pro/login/exeRegister", {
				username : uname,
				password : pwd
			}, function(e) {
				console.log(e.result);
				 if ("000"==e.result) {
					$(".login-form").fadeOut(1000, function() {
						window.location.href =webroot+"/main/bill/toWelcome";
					});
				} else {
					alert(e.resultMsg);
				}
				isLogin = false;
			} , false);
		}
		$(".login").on("click", register);
		$(".uname").keydown(function(event) {
			if (event.keyCode == 13) {
				$(".pwd").focus();
			}
		});
		$(".pwd").keydown(function(event) {
			if (event.keyCode == 13) {
				login();
			}
		});
	})
	
	 $('#defaultForm').bootstrapValidator({
		            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
		                valid: 'glyphicon glyphicon-ok',
		                invalid: 'glyphicon glyphicon-remove',
		                validating: 'glyphicon glyphicon-refresh'
		            },
		            fields: {/*验证：规则*/
		                username: {//验证input项：验证规则
		                    message: 'The username is not valid',
		                    validators: {
		                        notEmpty: {//非空验证：提示消息
		                            message: '用户名不能为空'
		                        },
		                        stringLength: {
		                            min: 6,
		                            max: 30,
		                            message: '用户名长度必须在6到30之间'
		                        },
		                        threshold :  6 , //有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
		                        remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}  
		                            url: webroot+'/login/existUser',//验证地址
		                            message: '用户已存在',//提示消息
		                            delay :  2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
		                            type: 'POST',//请求方式
		                            /**自定义提交数据，默认值提交当前input value*/
	                              data: function(validator) {
	                                  return {
	                                      username: $('input[name="username"]').val(),
	                                  };
	                               }
		                        },
		                        regexp: {
		                            regexp: /^[a-zA-Z0-9_\.]+$/,
		                            message: '用户名由数字字母下划线和.组成'
		                        }
		                    }
		                },
		                password: {
		                    message:'密码无效',
		                    validators: {
		                        notEmpty: {
		                            message: '密码不能为空'
		                        },
		                        stringLength: {
		                            min: 6,
		                            max: 30,
		                            message: '用户名长度必须在6到30之间'
		                        },
		                        identical: {//相同
		                            field: 'password', //需要进行比较的input name值
		                            message: '两次密码不一致'
		                        },
		                        different: {//不能和用户名相同
		                            field: 'username',//需要进行比较的input name值
		                            message: '不能和用户名相同'
		                        },
		                        regexp: {
		                            regexp: /^[a-zA-Z0-9_\.]+$/,
		                            message: 'The username can only consist of alphabetical, number, dot and underscore'
		                        }
		                    }
		                },
		                repassword: {
		                    message: '密码无效',
		                    validators: {
		                        notEmpty: {
		                            message: '用户名不能为空'
		                        },
		                        stringLength: {
		                            min: 6,
		                            max: 30,
		                            message: '用户名长度必须在6到30之间'
		                        },
		                        identical: {//相同
		                            field: 'password',
		                            message: '两次密码不一致'
		                        },
		                        different: {//不能和用户名相同
		                            field: 'username',
		                            message: '不能和用户名相同'
		                        },
		                        regexp: {//匹配规则
		                            regexp: /^[a-zA-Z0-9_\.]+$/,
		                            message: 'The username can only consist of alphabetical, number, dot and underscore'
		                        }
		                    }
		                }
		            }
		     });
</script>