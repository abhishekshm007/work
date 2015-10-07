<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Student Registration</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Student Registration" />
<meta name="author" content="http://o9paathshala.com" />
<meta name="keyword" content="Student Registration" />
<!-- css -->
<link rel="shortcut icon" href="img/logo/favicon.ico" />
<link href="css/bootstrap.min.css" rel="stylesheet" />
	<link href="css/main.css" rel="stylesheet" />
<link href="css/pace.css" rel="stylesheet" />
<link href="css/notification.css" rel="stylesheet" />
	<script src="../js/pace.min.js" type="text/javascript"></script>
<link href="css/fancybox/jquery.fancybox.css" rel="stylesheet">
<link href="css/jcarousel.css" rel="stylesheet" />
<link href="css/flexslider.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" />

<link href="skins/default.css" rel="stylesheet" />
<link href="css/pace.css" rel="stylesheet" />
<script src='https://www.google.com/recaptcha/api.js'></script>

</head>
<body data-ng-app="o9paathshalaHome">
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&appId=1463343653886404&version=v2.0";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

<div id="wrapper">
	<!-- start header -->
	<header>
        <div class="navbar navbar-default navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.jsp"><span>o9</span> PAATHSHALA</a>
                </div>
                <div class="navbar-collapse collapse ">
                    <ul class="nav navbar-nav">
                        <li id="home" ><a href="index.jsp">HOME</a></li>
                        <li id="explore" class="dropdown">
                            <a  href="#" class="dropdown-toggle " data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false">EXPLORE <b class=" icon-angle-down"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="whatis.jsp">WHAT IS ?</a></li>
                                <li><a href="features.jsp">FEATURES</a></li>
<!-- 								<li><a href="bussiness.jsp">BUSSINESS</a></li>
 -->                            </ul>
                        </li>
                         <li id="explore" class="dropdown ">
                            <a  href="#" class="dropdown-toggle " data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false">DOWNLOAD <b class=" icon-angle-down"></b></a>
                            <ul class="dropdown-menu">
                                <li ><a target="_blank" href="http://goo.gl/D9di7s">Android App</a></li>
                        </ul>
                        </li>
                        <li id="registration" class="active dropdown">
                            <a href="#" class="dropdown-toggle " data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false">REGISTRATION <b class=" icon-angle-down"></b></a>
                            <ul class="dropdown-menu">
                                <li class="active"><a href="studentRegistration.jsp">STUDENT</a></li>
                                <li><a href="instituteRegistration.jsp">INSTITUTE</a></li>
                            </ul>
                        </li>
                        <li id="login"><a href="login.jsp">LOGIN</a></li>
                        <li id="contact" class=""><a href="contactus.jsp">CONTACT US</a></li>
                    </ul>
                </div>
            </div>
        </div>
	</header>
	</div>
	<!-- end header -->

	<section class="forms" style=" margin-bottom: 50px;">
		<div class="form-box" id="login-box" data-ng-controller="StudentRegistrationController">
			<div class="header">Student Registration 
			 </div>
			<form id="studentForm" name="studentRegistrationForm" data-ng-submit="submitForm()" novalidate>
				<div class="body bg-gray">
				<b>Note:</b> This Registration will give you access to <c:out value="${applicationScope.appName }"></c:out> contents
				<div class="form-group">
					<input type="text" name="name" required
						data-ng-model="student.name" data-ng-minlength=3
						data-ng-maxlength=100 class="form-control" placeholder="Full Name" />
				</div>
				<div class="error-container"
					data-ng-show="studentRegistrationForm.name.$dirty && studentRegistrationForm.name.$invalid">
					<small class="error" data-ng-show="studentRegistrationForm.name.$error.required">Please input your name</small>
					 <small class="error" data-ng-show="studentRegistrationForm.name.$error.minlength">Your name is required to be at least 3 characters</small>
					 <small class="error" data-ng-show="studentRegistrationForm.name.$error.maxlength">Your name cannot be longer than 100 characters</small>
				</div>
				<div class="form-group">
						<input type="email" name="email" required data-ng-model="student.email"
						data-ng-maxlength=200 class="form-control" placeholder="Email" />
					</div>
				<div class="error-container"
					data-ng-show="studentRegistrationForm.email.$dirty && studentRegistrationForm.email.$invalid">
					<small class="error" data-ng-show="studentRegistrationForm.email.$error.required">Please input your email</small>
					 <small class="error" data-ng-show="studentRegistrationForm.email.$error.maxlength">Your name cannot be longer than 200 characters</small>
					 <small class="error" data-ng-show="studentRegistrationForm.email.$error.email">That is not a valid email. Please input a valid email.</small>
				</div>
					<div class="form-group">
						<input type="password" name="password" required data-ng-model="student.password"
							data-ng-maxlength=100 data-ng-minlength=6 class="form-control" placeholder="Password" />
					</div>
					<div class="error-container"
					data-ng-show="studentRegistrationForm.password.$dirty && studentRegistrationForm.password.$invalid">
					<small class="error" data-ng-show="studentRegistrationForm.password.$error.required">Please input your password</small>
					 <small class="error" data-ng-show="studentRegistrationForm.password.$error.minlength">Your password is required to be at least 6 characters</small>
					 <small class="error" data-ng-show="studentRegistrationForm.password.$error.maxlength">Your name cannot be longer than 100 characters</small>
				</div>
					<div class="form-group">
						<input type="password" name="confirmPassword" required data-ng-model="student.confirmPassword" data-password-verify="student.password"
							class="form-control" placeholder="Confirm Password" />
					</div>
					<div class="error-container"
					data-ng-show="studentRegistrationForm.confirmPassword.$dirty && studentRegistrationForm.confirmPassword.$invalid">
					<small class="error" data-ng-show="studentRegistrationForm.confirmPassword.$error.required">Please input your password</small>
					 <small class="error" data-ng-show="studentRegistrationForm.confirmPassword.$error.minlength">Your password is required to be at least 6 characters</small>
					 <small class="error" data-ng-show="studentRegistrationForm.confirmPassword.$error.maxlength">Your name cannot be longer than 100 characters</small>
					 <small class="error" data-ng-show="studentRegistrationForm.confirmPassword.$error.passwordVerify">Your password didn't match</small>
				</div>
				
				<div class="footer">
					<button type="submit" id="submit" data-ng-disabled="studentRegistrationForm.$invalid"
						class="btn bg-default-blue btn-block">Register me</button>

					<a href="login.jsp" class="text-center">Already have an account</a>
				</div>
			</form>

		</div>
	</section>
<jsp:include page="footer.jsp"></jsp:include>