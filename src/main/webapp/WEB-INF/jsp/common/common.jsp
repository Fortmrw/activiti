<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公共页面</title>
	<!--公共样式-->
    <link rel="stylesheet"  type="text/css" href="${webroot}/static/plugins/bootstrap-3.3.0/css/bootstrap.min.css">
	<link rel="stylesheet"  type="text/css" href="${webroot}/static/plugins/bootstrap-table-1.11.0/bootstrap-table.min.css">
	<link rel="stylesheet" type="text/css" href="${webroot}/static/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${webroot}/static/css/common.css"/>
	<!--模态框插件-->
	<link href="${webroot}/static/plugins/jquery-confirm/jquery-confirm.min.css" rel="stylesheet"/>
	<!--公共js-->
    <script type="text/javascript" src="${webroot}/static/js/jquery.1.12.4.min.js" ></script>
	<script type="text/javascript"  src="${webroot}/static/plugins/jquery-confirm/jquery-confirm.min.js"></script>
	<script type="text/javascript"  src="${webroot}/static/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript"  src="${webroot}/static/plugins/bootstrap-table-1.11.0/bootstrap-table.min.js"></script>
	<script type="text/javascript"  src="${webroot}/static/plugins/bootstrap-table-1.11.0/locale/bootstrap-table-zh-CN.min.js"></script>
	
	<script type="text/javascript" src="${webroot}/static/js/common.js" ></script>
</head>
<body>
<input type="hidden" id="webroot" value="${webroot}">
<script>
    var webroot=$("#webroot").val();
</script>
</body>
</html>