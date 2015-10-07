<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Home :: o9Paathshala</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Home :: o9Paathshala" />
<meta name="author" content="http://o9paathshala.com" />
<meta name="keyword" content="Home" />
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
                        <li id="home" class="active"><a href="index.jsp">HOME</a></li>
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


		<section id="featured">
			<!-- start slider -->
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<!-- Slider -->
						<div id="main-slider" class="flexslider">
					<ul class="slides">
						<li><img src="img/slides/1.jpg" alt="" />
							<div class="flex-caption">
								<h3>Test</h3>
								<p>Why give practice test using pen & paper when you can do it online?</p>
								<a href="#" class="btn btn-theme">Learn More</a>
							</div></li>
						<li><img src="img/slides/2.jpg" alt="" />
							<div class="flex-caption">
								<h3>Discussion Forum</h3>
								<p>Discuss your queries with your friends and teacher here.</p>
								<a href="#" class="btn btn-theme">Learn More</a>
							</div></li>
						<li><img src="img/slides/3.jpg" alt="" />
							<div class="flex-caption">
								<h3>Notice Board</h3>
								<p>Coming soon...</p>
								<a href="#" class="btn btn-theme">Learn More</a>
							</div></li>
						<li><img src="img/slides/4.jpg" alt="" />
							<div class="flex-caption">
								<h3>Mobile App</h3>
								<p>Access anywhere any time with o9 Paathshala mobile application.</p>
								<a href="#" class="btn btn-theme">Learn More</a>
							</div></li>
					</ul>
				</div>
						<!-- end slider -->
					</div>
				</div>
			</div>

		</section>


		<section class="callaction">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="big-cta">
							<div class="cta-text">
								<h2>
									<span>O9</span> PAATHSHALA FEATURES
								</h2>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<section id="content">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="row">
							<div class="col-lg-3">
								<div class="box">
									<div class="box-gray aligncenter">
										<h4>Tests</h4>
										<div class="icon">
											<i class="fa fa-edit fa-3x"></i>
										</div>
										<p>Practice your tests online with our web interface or mobile application.</p>
									</div>
									<div class="box-bottom">
										<a href="features.jsp#tests">Learn more</a>
									</div>
								</div>
							</div>
							<div class="col-lg-3">
								<div class="box">
									<div class="box-gray aligncenter">
										<h4>Discussion Forum</h4>
										<div class="icon">
											<i class="fa fa-group fa-3x"></i>
										</div>
										<p>Discuss and solve your queries using the discussion forum.</p>
										<br>
									</div>
									<div class="box-bottom">
										<a href="#">Learn more</a>
									</div>
								</div>
							</div>
							<div class="col-lg-3">
								<div class="box">
									<div class="box-gray aligncenter">
										<h4>Notice Board</h4>
										<div class="icon">
											<i class="fa fa-calendar-o fa-3x"></i>
										</div>
										<p>Coming soon...</p>
<br><br>
									</div>
									<div class="box-bottom">
										<a href="#">Learn more</a>
									</div>
								</div>
							</div>
							<div class="col-lg-3">
								<div class="box">
									<div class="box-gray aligncenter">
										<h4>Mobile Application</h4>
										<div class="icon">
											<i class="fa fa-mobile fa-3x"></i>
										</div>
										<p>Download o9 Paathshala mobile application & have access anywhere any time.</p>

									</div>
									<div class="box-bottom">
										<a href="#">Learn more</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- divider -->
				<div class="row">
					<div class="col-lg-12">
						<div class="solidline"></div>
					</div>
				</div>
				<!-- end divider -->

			</div>
		</section>
<script src="js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function() {
 
$("li").removeClass("active");
$("#home").addClass("active");
});
</script>
		<jsp:include page="footer.jsp"></jsp:include>