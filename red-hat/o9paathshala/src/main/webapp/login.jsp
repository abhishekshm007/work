<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Login :: o9Paathshala</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Login :: o9Paathshala" />
<meta name="author" content="http://o9paathshala.com" />
<meta name="keyword" content="Login" />
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
        <div class="navbar navbar-default navbar-static-top" data-ng-controller="LoginController">
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
                        <li id="home"><a href="index.jsp">HOME</a></li>
                        <li id="explore" class="dropdown ">
                            <a  href="#" class="dropdown-toggle " data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false">EXPLORE <b class=" icon-angle-down"></b></a>
                            <ul class="dropdown-menu">
                                <li ><a href="whatis.jsp">WHAT IS ?</a></li>
                                <li class=""><a href="features.jsp">FEATURES</a></li>
<!-- 								<li><a href="bussiness.jsp">BUSSINESS</a></li>
 -->                            </ul>
                        </li>
                         <li id="explore" class="dropdown ">
                            <a  href="#" class="dropdown-toggle " data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false">DOWNLOAD <b class=" icon-angle-down"></b></a>
                            <ul class="dropdown-menu">
                                <li ><a target="_blank" href="http://goo.gl/D9di7s">Android App</a></li>
                        </ul>
                        </li>
                        <li id="registration" class="dropdown">
                            <a href="#" class="dropdown-toggle " data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false">REGISTRATION <b class=" icon-angle-down"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="studentRegistration.jsp">STUDENT</a></li>
                                <li><a href="instituteRegistration.jsp">INSTITUTE</a></li>
                            </ul>
                        </li>
                        <li id="login" class="active"><a href="login.jsp">LOGIN</a></li>
                        <li id="contact"><a href="contactus.jsp">CONTACT US</a></li>
                    </ul>
                </div>
            </div>
        </div>
	</header>
	</div>
	<!-- end header -->

<section id="loginForm" class="forms" style="margin-bottom: 50px;">
	<div class="form-box" id="form-box" data-ng-controller="LoginController">
		<div class="header">Log In</div>
		<form name="loginForm" data-ng-submit="submitForm()" novalidate>
			<div class="body bg-gray">
				<div class="form-group">
					<input type="email" name="email" class="form-control" required data-ng-model="user.email" data-ng-maxlength=200 
						placeholder="Enter Email" />
				</div>
				
				<div class="error-container"
					data-ng-show="loginForm.email.$dirty && loginForm.email.$invalid">
					<small class="error" data-ng-show="loginForm.email.$error.required">Please input your email</small>
					 <small class="error" data-ng-show="loginForm.email.$error.maxlength">Your name cannot be longer than 200 characters</small>
					 <small class="error" data-ng-show="loginForm.email.$error.email">That is not a valid email. Please input a valid email.</small>
				</div>
				
				
				<div class="form-group">
					<input type="password" name="password" class="form-control" required data-ng-model="user.password" data-ng-minlength=6 data-ng-maxlength=100
						placeholder="Enter Password" />
				</div>
				
				<div class="error-container"
					data-ng-show="loginForm.password.$dirty && loginForm.password.$invalid">
					<small class="error" data-ng-show="loginForm.password.$error.required">Please input your password</small>
					 <small class="error" data-ng-show="loginForm.password.$error.maxlength">Your password cannot be longer than 100 characters</small>
					  <small class="error" data-ng-show="loginForm.password.$error.minlength">Your password is required to be at least 6 characters</small>
				</div>
				
				
				<div class="form-group" style="color: #100F0F;">
					<input type="checkbox" name="remember_me" data-ng-model="user.remember" id="remember_me" />
					Remember me
				</div>
			</div>
			<div class="footer">
				<button type="submit" class="btn bg-default-blue btn-block" data-ng-disabled="loginForm.$invalid"
					style="border-radius: 0px !important;">Sign me in</button>

				<p>
					<a href="forgotPassword.jsp">I forgot my password</a>
				</p>

			</div>
		</form>
	</div>
</section>


<jsp:include page="footer.jsp"></jsp:include>