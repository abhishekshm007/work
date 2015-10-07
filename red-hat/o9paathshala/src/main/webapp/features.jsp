<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Features :: o9Paathshala</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Features :: o9Paathshala" />
<meta name="author" content="http://o9paathshala.com" />
<meta name="keyword" content="Features" />
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
                        <li id="home"><a href="index.jsp">HOME</a></li>
                        <li id="explore" class="dropdown active">
                            <a  href="#" class="dropdown-toggle " data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false">EXPLORE <b class=" icon-angle-down"></b></a>
                            <ul class="dropdown-menu">
                                <li ><a href="whatis.jsp">WHAT IS ?</a></li>
                                <li class="active"><a href="features.jsp">FEATURES</a></li>
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

<section id="inner-headline">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<ul class="breadcrumb">
					<li><a href="index.jsp"><i class="fa fa-home"></i></a><i
						class="icon-angle-right"></i></li>
					<li><a href="#">Explore</a><i class="icon-angle-right"></i></li>
					<li class="active">Features</li>
				</ul>
			</div>
		</div>
	</div>
</section>
<section id="content">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				
				<p>
					<strong>PREPARE FOR EXAMS THE SMARTER WAY</strong>
				</p>
			<p><b>Maximize your Preparation</b></p>
			<p>o9Paathshala is an educational platform featuring the
				quickest,simplest way for organizations to train students,for FREE.
				The platform features an intuitive,easy-to-use interface to
				create,deliver and track training of students.o9Paathshala comes
				integrated in both Online testing platform and discussion forum.
				Ideal for people desiring to create and deliver tests for skills
				assessment and training,helping to accurately pinpoint the strengths
				and weaknesses of candidate,saving time and making faster
				processing.</p>
			<p>It offers a way to measure,analyze and view results of test
				takers. Its modern and clean theme is optimized for almost all
				devices including mobile and tabs. We believe preparation can be
				more social and fun and far more techno-friendly. With an
				internet-enabled device in the hands of most students,we felt a need
				for mobile test preparation platform giving students freedom to
				learn and prepare anywhere,anytime.</p></div>
		</div>
		<div class="row" id="tests">
			<div class="col-lg-4">
				<h4>Tests</h4>
				<img src="img/whatis/test.jpg" alt="" class="align-left" />
			</div>
			<div class="col-lg-8">
				<p>
					<strong>For an institute</strong>
				</p>
				<p>As an institute, you can make your own test using your own
					question bank for your students.</p>
				<p>When you will make test for students notification will be
					send to them about the test and updates on o9Paathshala mobile
					application.</p>
				<p>You can analyze the performance of students in various tests
					graphically.</p>
				<p>
					<strong>For a student</strong>
				</p>
				<p>You can access the test from anywhere any time using the web
					interface or o9Paathshala mobile application.</p>
				<p>When you will have a new test or any updates you will receive
					a notification on your mobile application.</p>
				<p>You can analyze your performance using graphical and tabular
					analysis of your results and leader board ranking will show you
					your position as compare to other students.</p>
			</div>

		</div>
		<div class="row" id="forum">
			<div class="col-lg-4">
				<h4>Discussion Forum</h4>
				<img src="img/whatis/queries.jpg" alt="" class="align-left" />
			</div>
			<div class="col-lg-8">

				<p>
					<strong>Have queries??? discuss here</strong>
				</p>
				<p>When the situation is hopeless, there's nothing to worry
					about. Come here and discuss with others.</p>
				<p>Every institute will have its own discussion forum where the
					students of that institute can discuss their queries.</p>
				<p>Here you can ask question, post queries and can give answer
					to questions.</p>
				<p>
			</div>
		</div>
		<div class="row" id="android">
			<div class="col-lg-4">
				<h4>Mobile Application</h4>
				<img src="img/whatis/android.jpg" alt="" class="align-left" />
			</div>
			<div class="col-lg-8">

				<p>
					<strong>Have accessibility any where any time...</strong>
				</p>
				<p>With the easy reach and wide use of mobile we are also
					providing you the o9Paathshala mobile application which you can
					easily download from android market place.</p>
				<p>With the mobile application you will be always updated about
					the tests.</p>
				<p>
			</div>
		</div>
	</div>
</section>
<script src="js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function() {
 
$("li").removeClass("active");
$("#explore").addClass("active");
});
</script>
<jsp:include page="footer.jsp"></jsp:include>