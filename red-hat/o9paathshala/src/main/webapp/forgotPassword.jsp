<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Forgot Password :: o9Paathshala</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Forgot Password :: o9Paathshala" />
<meta name="author" content="http://o9paathshala.com" />
<meta name="keyword" content="Forgot Password" />
<!-- css -->
<link rel="shortcut icon" href="img/logo/favicon.ico" />
<link href="css/bootstrap.min.css" rel="stylesheet" />
	
	<script src="../js/pace.min.js" type="text/javascript"></script>
<link href="css/fancybox/jquery.fancybox.css" rel="stylesheet">
<link href="css/jcarousel.css" rel="stylesheet" />
<link href="css/flexslider.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" />
<link href="css/main.css" rel="stylesheet" />
<link href="css/pace.css" rel="stylesheet" />
<link href="css/notification.css" rel="stylesheet" />
	<script src="../js/pace.min.js" type="text/javascript"></script>
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
                        <li id="login"><a href="login.jsp">LOGIN</a></li>
                        <li id="contact"><a href="contactus.jsp">CONTACT US</a></li>
                    </ul>
                </div>
            </div>
        </div>
	</header>
	</div>
	<!-- end header -->

			<section class="forms" style=" margin-bottom: 50px;" >
           <div class="form-box" id="login-box" data-ng-controller="ForgotPasswordController">
            <div class="header">Resetting Password</div>
            <form name="forgotPasswordForm" data-ng-submit="submitForm()" novalidate>
                <div class="body bg-gray">
                    <div class="form-group">
					<input type="email" name="email" class="form-control" required data-ng-model="email" data-ng-maxlength=200 
						placeholder="Enter Email" />
				</div>
				
				<div class="error-container"
					data-ng-show="forgotPasswordForm.email.$dirty && forgotPasswordForm.email.$invalid">
					<small class="error" data-ng-show="forgotPasswordForm.email.$error.required">Please input your email</small>
					 <small class="error" data-ng-show="forgotPasswordForm.email.$error.maxlength">Your name cannot be longer than 200 characters</small>
					 <small class="error" data-ng-show="forgotPasswordForm.email.$error.email">That is not a valid email. Please input a valid email.</small>
				</div>
                </div>
                <div class="footer">                                                               
                    <button type="submit" class="btn bg-default-blue btn-block" style="border-radius:0px !important;">Reset Password</button>  
                    
                    
                </div>
            </form>

        </div>
	</section>			
<script src="js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function() {
 
$("li").removeClass("active");
$("#login").addClass("active");
});
</script>

		<jsp:include page="footer.jsp"></jsp:include>