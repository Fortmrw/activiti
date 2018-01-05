<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
</head>
<body >
<!--左侧菜单-->
<div class="sidebar">
    <ul class="sideList">
        <a href="${webroot}/main/bill/toWelcome" ><li class="index active">欢迎使用</li></a>
        <a href="${webroot}/main/bill/toListBills" ><li class="bill">账单管理</li></a>
        <a href="${webroot}/main/activiti/toListModel" ><li class="activiti">流程管理</li></a>
        <a href="${webroot}/main/order/toListOrders" ><li class="order">订单管理</li></a>
    </ul>
</div>
<script>
    var jump=page;
    switch(jump)
    {
        case 'index': jumpto('index');
            break;
        case 'bill': jumpto('bill');
            break;
        case 'activiti': jumpto('activiti');
        	break;
        case 'order': jumpto('order');
    		break;
        case '': jumpto('');
            break;
    }
    function jumpto(jump) {
        if(jump==''){
            $('.sideList li').removeClass('active')
        }else {
            $('.sideList li').removeClass('active')
            $('.sideList .'+jump).addClass('active');
        }

    }
</script>
</body>
</html>