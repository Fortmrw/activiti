<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>登录系统</title>
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
</head>
<body>

	<h1>Zhr 基础系统</h1>
	<div class="login-form">
<%-- 		<form id="submitForm" action="${webroot }/login/exeLogin" method="post"> --%>
			<div class="close"><p>注册</p></div>
			<div class="head-info">
				<label class="lbl-1"></label> <label class="lbl-2"></label> <label
					class="lbl-3"></label>
			</div>
			<div class="clear"></div>
			<div class="avtar">
				<img src="${webroot }/static/images/avtar.png" />
			</div>
			<div>
				<input type="text" class="text" name="username" placeholder="用户名"> <input
					type="password" class="pwd" name="password" placeholder="密码">
			</div>
			<p id ="resultMsg" style="color:red"></p>
			<div class="signin">
				<input class="login" type="button" value="登录系统">
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
		$(".close").unbind("click");
		$(".close").on("click", function() {
			$(".login-form").fadeOut(100, function() {
				window.location.href = webroot+"/main/user/toRegister";
			});
		});
		function login() {
			if (isLogin) {
				alert("正在登录...");
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
// 			$("#submitForm").submit();
			Request.post("pro/login/exeLogin", {
				username : uname,
				password : pwd
			}, function(e) {
				 if ("000"==e.result) {
					$(".login-form").fadeOut(1000, function() {
						window.location.href =webroot+"/main/bill/toWelcome";
					});
				} else {
					$("#resultMsg").html("用户名或者名错误!");
				}
				isLogin = false;
			} , false);
		}

		$(".login").on("click", login);
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
</script>