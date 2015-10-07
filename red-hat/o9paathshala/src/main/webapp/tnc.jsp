<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>T&C :: o9Paathshala</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="T&C :: o9Paathshala" />
<meta name="author" content="http://o9paathshala.com" />
<meta name="keyword" content="T&C" />
<!-- css -->
<link rel="shortcut icon" href="img/logo/favicon.ico" />
<link href="css/bootstrap.min.css" rel="stylesheet" />
	
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
                        <li id="registration" class="dropdown">
                            <a href="#" class="dropdown-toggle " data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false">REGISTRATION <b class=" icon-angle-down"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="studentRegistration.jsp">STUDENT</a></li>
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
<hr>
<div class="container">
	<div class="row" id="box">
		<div class="col-lg-12">
			<h4>TERMS & CONDITIONS</h4>
			<h5 style="color: #656565">User Agreement</h5>
			<p>Welcome to o9paathshala.com. The services of the website are
				only offered to the registered users. We strongly recommend to the
				users to read these terms and conditions carefully before using the
				website.</p>
			<p>By subscribing to or using any of our services, user agrees
				that they have read, understood and are bound by the terms,
				regardless of how they subscribe to or use the services. We reserve
				the right to make changes as and when required in these Terms and
				Conditions at any time and will make the most recent version
				available on the Site. By using this Site and continuing to use the
				Site after any modifications, user agrees to the terms and to be
				bound by any such changes. If user does not wants to be bound by the
				terms, they must not subscribe to, or use our services.</p>
			<p>The website offers variety of test preparation products where
				in the website constitutes an invitation to offer in sole context of
				displayed services and products.</p>
		</div>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>