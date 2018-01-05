<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
</head>
<body >
<!--头部-->
<header class="clearfix">
    <a href="${webroot}/user/userList"><h1 class="fl">账单管理平台</h1></a>
    <div id="" class="fr clearfix">
        <h2 class="fl">欢迎您:<font>&nbsp;${session_user.name} 小宝宝</font></h2>
        <a href="${webroot}/store/listNotes"><i class="EM fl"></i></a>
        <a href="${webroot}/login/exeLogOut"><i class="out fl"></i></a>
    </div>
</header>
<!--头end-->
</body>
</html>


