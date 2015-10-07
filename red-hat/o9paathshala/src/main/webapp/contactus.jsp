<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Contact Us :: o9Paathshala</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Contact Us :: o9Paathshala" />
<meta name="author" content="http://o9paathshala.com" />
<meta name="keyword" content="Contact us" />
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
                        <li id="contact" class="active"><a href="contactus.jsp">CONTACT US</a></li>
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
		<div class="col-lg-6">
			<h4>
				<strong>Contact Us</strong>
			</h4>
			<form id="contactform" method="post" class="validateform"
				name="send-contact">
				<div class="row">
					<div class="form-group">
						<input type="text" name="name"
							placeholder="* Enter your full name" required>
					</div>
					<div class="form-group ">
						<input type="email" name="email"
							placeholder="* Enter your email address" required>
					</div>
					<div class="form-group ">
						<input type="text" name="subject" placeholder="* Enter your subject"
							required>
					</div>
					<div class="form-group margintop10 ">
						<textarea rows="12" name="message" class="input-block-level"
							placeholder="* Your message here..." required></textarea>
						<p>
							<button class="btn btn-theme margintop10 pull-left" type="submit">Submit
								message</button>
							<span class="pull-right margintop20">* Please fill all
								required form field, thanks!</span>
						</p>
					</div>
				</div>
			</form>
		</div>
		<div class="col-lg-3"></div>
		<div class="col-lg-3">
			<br> <br>
			<p>You can also reach our support team on given numbers.</p>
			<dl>
				<dt>Phone No:
				<dd>(+91)-8868979259</dd>
				<dd>(+91)-9761604698</dd>
				<dt>Email:
				<dd>
					<a href="mailto:support@o9paathshala.com">support@o9paathshala.com</a>
				</dd>
			</dl>
		</div>
	</div>
</div>
<script src="js/jquery.js"></script>
<script src="js/help.js"></script>
<jsp:include page="footer.jsp"></jsp:include>