<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="title" content="<spring:message code='study.template.title' />" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<!-- 캐싱 방지  -->
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="pragma" content="no-store" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="Expires" content="-1" />

<title><spring:message code='study.template.title' /></title>

<!-- JS -->
<script src="webjars/jquery/2.1.4/dist/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="webjars/angularjs/1.7.4/angular.min.js"></script>
<script src="webjars/angularjs/1.7.4/angular-animate.min.js"></script>
<script src="webjars/angularjs/1.7.4/angular-aria.min.js"></script>
<script src="webjars/angularjs/1.7.4/angular-messages.min.js"></script>
<script src="webjars/angular-ui-bootstrap/1.3.3/ui-bootstrap-tpls.js"></script>
<script src="lib/js/page2.js"></script>

<!-- CSS -->
<link rel="stylesheet" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="shortcut icon" type="image/x-icon" href="image/favicon.ico" />

<!-- STYLE -->
<style>
</style>
</head>

<body ng-app="Page2AngularApp">
	<div class="container" ng-controller="Page2Controller">
		<!-- Nav bar -->
		<%@ include file="/WEB-INF/jspf/navigationBar.jspf"%>
		<h2>VIEW 2 [AngularJS library Used]</h2>

		<div class="row">
			<div class="col-sm-12" data-ng-init="memberDataLoading()">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>PW</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="member in memberList">
							<td>{{member.userId}}</td>
							<td>{{member.name}}</td>
							<td>{{member.pw}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>