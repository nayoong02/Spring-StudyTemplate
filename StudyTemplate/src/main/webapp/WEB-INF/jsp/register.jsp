<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script src="webjars/vue/2.6.12/vue.js"></script>
<script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- CSS -->
<link rel="stylesheet" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="shortcut icon" type="image/x-icon" href="image/favicon.ico" />

<!-- STYLE -->
<style>
</style>
</head>

<body>
	<div id="registerVue" class="container" style="width: 650px; height: 768px; max-width: none !important; min-height: 45%; max-height: 45%;">
		<div class="row" style="margin-top: 45px;">
			<form name="mform" method="post" action="register">
				<legend>
					<spring:message code="study.template.page.register.register" />
				</legend>
				
				<div class="col-xs-12">
					<div class="row" style="padding-left: 1px; padding-right: 1px;">
						<div style="padding-bottom: 4px; border-radius: 0px !important; margin-top: 10px;">
							
							<h5><strong>* 아이디 </strong></h5>
							<input v-model="userId" type="text" id="userId" name="userId" class="form-control" placeholder="아이디를 입력하세요." style="font-size: 15px; border-radius: 0px !important; float: left; width:85%;">
							<input type="button" id="idChk" class="btn btn-outline-primary" value="중복 확인" style="float: right;" @click = checkDuplicatedId();>
			
							<span class="error_msg" v-show="!isIDSpecialCheck" style="color: #fa6607; font-size: 13px;">아이디는 4글자 이상, 8글자 이하의 영문 소문자와 숫자만으로 입력해주세요.</span>
							
						</div>
					</div>
				</div>
				
				<div class="col-xs-12">
					<div class="row" style="padding-left: 1px; padding-right: 1px; margin-top: 30px;">
						<div style="padding-bottom: 4px; border-radius: 0px !important;">
							
							<h5><strong>* 비밀번호</strong></h5>
							<input v-model="password" type="password" id="pwd" name="password" class="form-control" placeholder="비밀번호를 입력하세요." aria-describedby="inputGroup-sizing-defaults" style="font-size: 15px; border-radius: 0px !important;">
							<span class="error_msg" v-show="!isPWSpecialCheck" style="color: #fa6607; font-size: 13px;">비밀번호는 8자 이상의 영문 소문자와 숫자만으로 입력해주세요.</span>
							
						</div>
					</div>
				</div>
				
				<div class="col-xs-12">
					<div class="row" style="padding-left: 1px; padding-right: 1px; margin-top: 30px;">
						<div style="padding-bottom: 4px; border-radius: 0px !important;">
							
							<h5><strong>* 비밀번호 확인 </strong></h5>
							<input v-model="re_password" type="password" id="re_pwd" name="re_password" class="form-control" placeholder="다시 한번 입력하세요." aria-describedby="inputGroup-sizing-defaults" style="font-size: 15px; border-radius: 0px !important;">
							<span class="error_msg" v-if="!isPWSameCheck" style="color: #fa6607; font-size: 13px;">비밀번호가 일치하지 않습니다.</span>
						
						</div>
					</div>
				</div>
				
				<div class="col-xs-12">
					<div class="row" style="padding-left: 1px; padding-right: 1px;">
						<div style="padding-bottom: 4px; border-radius: 0px !important; margin-top: 30px;">
							
							<h5><strong>* 이름 </strong></h5>
							<input v-model="userName" type="text" id="userName" name="userName" class="form-control" placeholder="이름을 입력하세요." aria-describedby="inputGroup-sizing-defaults" style="font-size: 15px; border-radius: 0px !important;">
						
						</div>
					</div>
				</div>
				
				<div class="col-xs-12">
					<div class="row" style="padding-left: 1px; padding-right: 1px;">
						<div style="padding-bottom: 4px; border-radius: 0px !important; margin-top: 30px;">
							
							<h5><strong>* 부서</strong></h5>
							<input v-model="department" type="text" id="department" name="department" class="form-control" placeholder="부서를 입력하세요." aria-describedby="inputGroup-sizing-defaults" style="font-size: 15px; border-radius: 0px !important;">
						
						</div>
					</div>
				</div>
				
				<div class="col-xs-12">
					<div class="row" style="padding-left: 1px; padding-right: 1px;">
						<div style="padding-bottom: 4px; border-radius: 0px !important; margin-top: 30px;">
							
							<h5><strong>* 직급</strong></h5>
							<input v-model="position" type="text" id="position" name="position" class="form-control" placeholder="직급을 입력하세요." aria-describedby="inputGroup-sizing-defaults" style="font-size: 15px; border-radius: 0px !important;">
						
						</div>
					</div>
				</div>
				
				<br><br>
							
				<div class="col-xs-12" style="text-align:center">
				
					<input type="button" class="btn btn-default btn-rg" value="REGISTER" style="height: 50px; width: 30%; background-color: #fa6607; color: #fff; font-size: 15px; font-weight: 600; border-radius: 0px !important; display: inline-block; margin-top: 40px;" @click = register();>
			
				</div>
						
			</form>
		</div>
	</div>
	
	<script src="lib/js/register.js"></script>
	
</body>
</html>