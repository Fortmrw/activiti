<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎页面</title>
<jsp:include page="common/common.jsp" />
<link rel="stylesheet" type="text/css"
	href="${webroot}/static/css/index.css" />
<script>
	var page = "index";
</script>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<section class="content">
	<jsp:include page="common/menu.jsp"/>
	<div class="rightCon fr">
		<div class="head clearfix">
			<div class="imgBox fl">
				<c:choose>
					<c:when test="${session_store.companyLogo !=null and !session_store.companyLogo eq ''}">
						<img src="${session_store.companyLogo}"/>
					</c:when>
					<c:otherwise>
						<img src="${webroot}/static/images/head.png"/>
					</c:otherwise>
				</c:choose>
				<p>更改头像</p>
			</div>
			<div class="fl time">
				上次登录时间：<font>
				<c:choose>
					<c:when test="${session_store.lastLoginTime !=null}"><fmt:formatDate  type="date" value="${session_store.lastLoginTime}" /></c:when>
					<c:otherwise>--/--/--</c:otherwise>
				</c:choose>
			</font>
			</div>
		</div>
		<div class="numBox">
			<ul>
				<li>
					<p>￥${empty todayIn?"0.00":todayIn }</p>
					<p>今日收入</p>
				</li>
				<li>
					<p>${empty todayGet?"0.00":todayGet }</p>
					<p>今日支出</p>
				</li>
				<li>
					<p>￥${empty yserterIn?"0.00":yserterIn }</p>
					<p>昨日收入</p>
				</li>
				<li>
					<p>${empty yserterinGet?"0.00":yserterinGet }</p>
					<p>昨日支出</p>
				</li>
			</ul>
		</div>
		<h2 class="title">代办提醒</h2>
		<div class="remind">
			<ul class="clearfix">
				<li>
					<i></i>
					<p class="col-ff533f">待完成事件（${empty waitValiedateCount?"0":waitValiedateCount }）个</p>
				</li>
			</ul>
		</div>
		<h2 class="title">常用功能</h2>
		<div class="fun">
			<ul class="clearfix">
				<li>
					<a href="${webroot }/bill/showAddBill">
					<i></i>
					<p>创建账单</p>
					</a>
				</li>
				<li>
					<a href="${webroot }/bill/showAddNote">
					<i></i>
					<p>创建提醒</p>
				</li>
				<li>
					<a href="javascript:alert('to be continue!')">
					<i></i>
					<p>帮助中心</p>
					</a>
				</li>
				<li>
					<a href="javascript:alert('to be continue!')">
					<i></i>
					<p>交易分析</p>
					</a>
				</li>
			</ul>
		</div>
	</div>
</section>
</body>
</html>