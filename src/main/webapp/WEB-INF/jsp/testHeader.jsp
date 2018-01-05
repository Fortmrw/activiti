<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>测试Header</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="common/common.jsp"></jsp:include>
<meta name="keywords"
	content="Zhr,后台系统,管理系统,java,spring,spring-boot" />
<link href="${webroot }/static/css/style.css" rel='stylesheet' type='text/css' />
<script src="${webroot }/static/plugins/ajax/Request.js" type="text/javascript"></script>
</head>
<body>
	<input type="button" name="btn" value="测试" id="btn" onclick="test()">
</body>
<script type="text/javascript">
function test(){
	$.ajax({
		type:"post",
		dataType:"json",
		url:webroot+"/test/testHeader",
		data:{
			param:"value"
		},
		beforeSend:function(xhr){
			xhr.setRequestHeader("wang","w_a_n_g");
			xhr.setRequestHeader("ya","y_a");
			xhr.setRequestHeader("ping","p_i_n_g");
		},
		success:function(data){
			console.log(data.header);
			console.log(data.body);
		}
	})
}
</script>
</html>
