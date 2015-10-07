<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>
    	<c:out value="${applicationScope.tagLine }"></c:out> | 
    	<c:out value="${applicationScope.applicationName }"></c:out>
     </title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
	<link href="css/main.css" rel="stylesheet">
	<link href="css/responsive.css" rel="stylesheet">
	<link href="css/notification.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body data-ng-app="mainApp" data-ng-controller="mainController">
	<header id="header"><!--header-->
		<div class="header_top"><!--header_top-->
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<div class="contactinfo">
							<ul class="nav nav-pills">
								<li><a href="#"><i class="fa fa-phone"></i> <c:out value="${applicationScope.mobileNo }"></c:out></a></li>
								<li><a href="mailto:<c:out value='${applicationScope.supportEmailId }'></c:out>"><i class="fa fa-envelope"></i> <c:out value="${applicationScope.supportEmailId }"></c:out></a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="btn-group pull-right">
							<div class="col-sm-6 input-group-sm input-group pull-right" style="margin-top: 2px;">
								<input type="text" data-ng-model="pincode" maxlength="15" class="form-control" placeholder="Enter Pincode" />
								<span class="input-group-btn">
									<button class="btn btn-default" data-ng-click="verifyPincode()" type="button">verify</button>
								</span>
							</div>
							<div class="btn-group pull-right">
								<button type="button"
									class="btn btn-default dropdown-toggle usa"
									data-toggle="dropdown">
									Notification &nbsp; <span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li><a href="#">Track order</a></li>
									<li><a href="#">Promo codes</a></li>
								</ul>
							</div>
						</div>
						<!-- <div class="social-icons pull-right">
							<ul class="nav navbar-nav">
								<li><a href="#"><i class="fa fa-facebook"></i></a></li>
								<li><a href="#"><i class="fa fa-twitter"></i></a></li>
								<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
								<li><a href="#"><i class="fa fa-dribbble"></i></a></li>
								<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
							</ul>
						</div> -->
					</div>
				</div>
			</div>
		</div><!--/header_top-->
		
		<div class="header-middle"><!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-sm-4">
						<div class="logo pull-left">
							<a href="index.jsp"><img src="images/home/logo.png" alt="" /></a>
						</div>
						
					</div>
					<div class="col-sm-8">
						<div class="shop-menu pull-right">
							<ul class="nav navbar-nav">
								<li><a href="#"><i class="fa fa-star"></i> Wishlist</a></li>
								<li><a href="#"><i class="fa fa-crosshairs"></i> Checkout</a></li>
								<li><a href="#"><i class="fa fa-shopping-cart"></i> Cart</a></li>
								<li><a href="login.jsp"><i class="fa fa-lock"></i> Login</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-middle-->
	
		<div class="header-bottom"><!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-9">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
						</div>
						<div class="mainmenu pull-left">
							<ul class="nav navbar-nav collapse navbar-collapse">
							<c:forEach var="currentStock" items="${applicationScope.stockCotegoryList }" >
								<li class="dropdown" ><a href="#"><c:out value="${currentStock.stockCotegoryName }"></c:out><i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="stock/cotegory/<c:out value="${currentStock.stockCotegoryId }"></c:out>">See all</a></li>
										<li><a class="menuSubItem" >--------------------</a></li>
										<c:forEach var="currentProductCotegory" items="${currentStock.productCotegoryList }">
                                        <li>
                                        	<a class="menuSubItem" 
                                        		href="product/cotegory/<c:out value="${currentProductCotegory.productCotegoryId }"></c:out>"> <c:out value="${currentProductCotegory.productCotegoryName }"></c:out></a></li>
                                        		</c:forEach>
                                    </ul>
                                </li> 
                                </c:forEach>
                                <li><span class="separator"></span></li>
								<li class="dropdown"><a href="#">Sale<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a class="menuSubItem" href="blog-single.html">See all</a></li>
										<li><a class="menuSubItem" >--------------------</a></li>
                                        <li><a class="menuSubItem" href="blog.html">Fruits</a></li>
										<li><a class="menuSubItem" href="blog-single.html">Vegetables</a></li>
                                    </ul>
                                </li> 
								<li class="dropdown"><a href="#">New<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a class="menuSubItem" href="blog-single.html">See all</a></li>
										<li><a class="menuSubItem" >--------------------</a></li>
                                        <li><a class="menuSubItem" href="blog.html">Fruits</a></li>
										<li><a class="menuSubItem" href="blog-single.html">Vegetables</a></li>
                                    </ul>
                                </li> 
                                <li class="dropdown"><a href="#" style="color: #C1392B;font-weight: 500;">Premium<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a class="menuSubItem" href="blog-single.html">See all</a></li>
										<li><a class="menuSubItem" >--------------------</a></li>
                                        <li><a class="menuSubItem" href="blog.html">Fruits</a></li>
										<li><a class="menuSubItem" href="blog-single.html">Vegetables</a></li>
                                    </ul>
                                </li> 
                                 <li><span class="separator"></span></li>
                                <li class="dropdown"><a href="#">Gifting<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a class="menuSubItem" href="blog-single.html">See all</a></li>
										<li><a class="menuSubItem" >--------------------</a></li>
                                        <li><a class="menuSubItem" href="blog.html">Marriage</a></li>
										<li><a class="menuSubItem" href="blog-single.html">Birthday</a></li>
                                    </ul>
                                </li> 
                                 <li><span class="separator"></span></li>
								<li><a href="contact-us.html">Health Guidance</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="search_box pull-right">
							<input type="text" placeholder="Search"/>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-bottom-->
	</header><!--/header-->
	
<section id="slider"><!--slider-->
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div id="slider-carousel" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
							<li data-target="#slider-carousel" data-slide-to="1"></li>
							<li data-target="#slider-carousel" data-slide-to="2"></li>
						</ol>
						
						<div class="carousel-inner">
						<c:forEach items="${applicationScope.bannerList }" var="banner" varStatus="loop">
							<div class="item <c:if test="${loop.index == 0 }">active</c:if>" >
								<div class="col-sm-6">
									<h1><c:out value="${banner.productName}"></c:out></h1>
									<p><c:out value="${banner.productDesc}"></c:out></p>
									<a type="button" href="product?<c:out value="${banner.productId}"></c:out>" class="btn btn-default get">Get it now</a>
								</div>
								<div class="col-sm-6">
									<img src="images/products/<c:out value="${banner.productId}"></c:out>.jpg" class="girl img-responsive" alt="" />
									<img src="images/home/pricing.png"  class="pricing" alt="" />
								</div>
							</div>
						</c:forEach>
							</div>
							
						</div>
						
						<a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
							<i class="fa fa-angle-left"></i>
						</a>
						<a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
							<i class="fa fa-angle-right"></i>
						</a>
					</div>
					
				</div>
			</div>
	</section><!--/slider-->
	
	
	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="left-sidebar">
						<h2>Category</h2>
						<div class="panel-group category-products" id="accordian"><!--category-productsr-->
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordian" href="#fruits">
											<span class="badge pull-right"><i class="fa fa-plus"></i></span>
											Fruits
										</a>
									</h4>
								</div>
								<div id="fruits" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<c:forEach items="${applicationScope.stockCotegoryList[0].productCotegoryList}" var="current" varStatus="loop">
                                        	<li><a href="products?cotegory=<c:out value="${current.productCotegoryId}"></c:out>"> <c:out value="${current.productCotegoryName}"></c:out></a></li>
                                        	</c:forEach>
                                        	<li><a>--------------------</a></li>
											<li><a href="stock/cotegory/<c:out value="${stockCotegoryList[1].stockCotegoryId }"></c:out>">See all</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordian" href="#vegetables">
											<span class="badge pull-right"><i class="fa fa-plus"></i></span>
											Vegetables
										</a>
									</h4>
								</div>
								<div id="vegetables" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<c:forEach items="${applicationScope.stockCotegoryList[1].productCotegoryList}" var="current" varStatus="loop">
                                       	 	<li><a href="products?cotegory=<c:out value="${current.productCotegoryId}"></c:out>"><c:out value="${current.productCotegoryName}"></c:out></a></li>
                                        	</c:forEach>
                                        	<li><a>--------------------</a></li>
											<li><a href="stock/cotegory/<c:out value="${stockCotegoryList[1].stockCotegoryId }"></c:out>">See all</a></li>
										</ul>
									</div>
								</div>
							</div>
							
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordian" href="#sale">
											<span class="badge pull-right"><i class="fa fa-plus"></i></span>
											Sale
										</a>
									</h4>
								</div>
								<div id="sale" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<li><a href="blog.html">Fruits</a></li>
											<li><a href="blog-single.html">Vegetables</a></li>
											<li><a>--------------------</a></li>
											<li><a href="blog-single.html">See all</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordian" href="#new">
											<span class="badge pull-right"><i class="fa fa-plus"></i></span>
											New
										</a>
									</h4>
								</div>
								<div id="new" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<li><a href="blog.html">Fruits</a></li>
											<li><a href="blog-single.html">Vegetables</a></li>
											<li><a>--------------------</a></li>
											<li><a href="blog-single.html">See all</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordian" href="#premium">
											<span class="badge pull-right"><i class="fa fa-plus"></i></span>
											Premium
										</a>
									</h4>
								</div>
								<div id="premium" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<li><a href="blog.html">Fruits</a></li>
											<li><a href="blog-single.html">Vegetables</a></li>
											<li><a>--------------------</a></li>
											<li><a href="blog-single.html">See all</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordian" href="#gifting">
											<span class="badge pull-right"><i class="fa fa-plus"></i></span>
											Gifting
										</a>
									</h4>
								</div>
								<div id="gifting" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<li><a href="blog.html">Fruits</a></li>
											<li><a href="blog-single.html">Vegetables</a></li>
											<li><a>--------------------</a></li>
											<li><a href="blog-single.html">See all</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="#">Health Guidance</a></h4>
								</div>
							</div>
						</div><!--/category-products-->
					
					<!--brands_products-->
						<!-- <div class="brands_products">
							<h2>Brands</h2>
							<div class="brands-name">
								<ul class="nav nav-pills nav-stacked">
									<li><a href="#"> <span class="pull-right">(50)</span>Acne</a></li>
									<li><a href="#"> <span class="pull-right">(56)</span>Gr�ne Erde</a></li>
									<li><a href="#"> <span class="pull-right">(27)</span>Albiro</a></li>
									<li><a href="#"> <span class="pull-right">(32)</span>Ronhill</a></li>
									<li><a href="#"> <span class="pull-right">(5)</span>Oddmolly</a></li>
									<li><a href="#"> <span class="pull-right">(9)</span>Boudestijn</a></li>
									<li><a href="#"> <span class="pull-right">(4)</span>R�sch creative culture</a></li>
								</ul>
							</div>
						</div> -->
						<!--/brands_products-->
						
						<div class="price-range"><!--price-range-->
							<h2>Price Range</h2>
							<div class="well text-center">
								 <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600" data-slider-step="5" data-slider-value="[250,450]" id="sl2" ><br />
								 <b class="pull-left">$ 0</b> <b class="pull-right">$ 600</b>
							</div>
						</div><!--/price-range-->
						
						<!--shipping-->
						<!-- <div class="shipping text-center">
							<img src="images/home/shipping.jpg" alt="" />
						</div> -->
						<!--/shipping-->
					
					</div>
				</div>
				
				<div class="col-sm-9 padding-right">
					<div class="features_items"><!--features_items-->
						<h2 class="title text-center">Features Items</h2>
						 <div class="col-sm-4" data-ng-repeat="product in productList">
							<div class="product-image-wrapper">
								<div class="single-products">
										<div class="productinfo text-center">
											<a href="#khjskdj"><img src="images/products/{{product.productId}}.jpg" alt="" /></a>
											<h2> <i class="fa fa-inr"></i> {{product.productAmount}} </h2>
											<p> {{product.productName}} </p>
											<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
										</div>
										<img data-ng-show="product.isNew" src="images/home/new.png" class="new" alt="" />
										<img data-ng-show="product.isSale" src="images/home/sale.png" class="sale" alt="" />
								</div>
								<div class="choose">
									<ul class="nav nav-pills nav-justified">
										<li><a href="#"><i class="fa fa-plus-square"></i>Add to wishlist</a></li>
									</ul>
								</div>
							</div>
						</div> 
					</div><!--features_items-->
					<a  data-ng-click="loadProductList()"  class="btn btn-block add-to-cart"><i class="fa  fa-chevron-down"></i>Show More Products( remaining : {{moreProductCounts}} ) </a>
				</div>
			</div>
		</div>
	</section>


	
	<footer id="footer"><!--Footer-->
<!-- 		<div class="footer-top">
			<div class="container">
				<div class="row">
					<div class="col-sm-2">
						<div class="companyinfo">
							<h2><span>e</span>-shopper</h2>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,sed do eiusmod tempor</p>
						</div>
					</div>
					<div class="col-sm-7">
						<div class="col-sm-3">
							<div class="video-gallery text-center">
								<a href="#">
									<div class="iframe-img">
										<img src="images/home/iframe1.png" alt="" />
									</div>
									<div class="overlay-icon">
										<i class="fa fa-play-circle-o"></i>
									</div>
								</a>
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="video-gallery text-center">
								<a href="#">
									<div class="iframe-img">
										<img src="images/home/iframe2.png" alt="" />
									</div>
									<div class="overlay-icon">
										<i class="fa fa-play-circle-o"></i>
									</div>
								</a>
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="video-gallery text-center">
								<a href="#">
									<div class="iframe-img">
										<img src="images/home/iframe3.png" alt="" />
									</div>
									<div class="overlay-icon">
										<i class="fa fa-play-circle-o"></i>
									</div>
								</a>
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="video-gallery text-center">
								<a href="#">
									<div class="iframe-img">
										<img src="images/home/iframe4.png" alt="" />
									</div>
									<div class="overlay-icon">
										<i class="fa fa-play-circle-o"></i>
									</div>
								</a>
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="address">
							<img src="images/home/map.png" alt="" />
							<p>505 S Atlantic Ave Virginia Beach, VA(Virginia)</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		 -->
		<div class="footer-widget">
			<div class="container">
				<div class="row">
					<div class="col-sm-2">
						<div class="single-widget">
							<h2>Service</h2>
							<ul class="nav nav-pills nav-stacked">
								<li><a href="#">Online Help</a></li>
								<li><a href="#">Contact Us</a></li>
								<li><a href="#">Order Status</a></li>
								<li><a href="#">Change Location</a></li>
								<li><a href="#">FAQ’s</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="single-widget">
							<h2>Quick Shop</h2>
							<ul class="nav nav-pills nav-stacked">
								<li><a href="#">Fruits</a></li>
								<li><a href="#">Vegetables</a></li>
								<li><a href="#">Sale</a></li>
								<li><a href="#">New</a></li>
								<li><a href="#">Premium</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="single-widget">
							<h2>Policies</h2>
							<ul class="nav nav-pills nav-stacked">
								<li><a href="#">Terms of Use</a></li>
								<li><a href="#">Privacy Policy</a></li>
								<li><a href="#">Refund Policy</a></li>
								<li><a href="#">Billing System</a></li>
								<li><a href="#">Ticket System</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="single-widget">
							<h2>About Shopper</h2>
							<ul class="nav nav-pills nav-stacked">
								<li><a href="#">Company Information</a></li>
								<li><a href="#">Careers</a></li>
								<li><a href="#">Store Location</a></li>
								<li><a href="#">Affillate Program</a></li>
								<li><a href="#">Copyright</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-3 col-sm-offset-1">
						<div class="single-widget">
							<h2>About Shopper</h2>
							<form action="#" class="searchform">
								<input type="text" placeholder="Your email address" />
								<button type="submit" class="btn btn-default"><i class="fa fa-arrow-circle-o-right"></i></button>
								<p>Get the most recent updates from <br />our site and be updated your self...</p>
							</form>
						</div>
					</div>
					
				</div>
			</div>
		</div>
		
		<div class="footer-bottom">
			<div class="container">
				<div class="row">
					<p class="pull-left">Copyright © 2015 Himachal Fresh Inc. All rights reserved.</p>
					<p class="pull-right"><span><a target="_blank" href="http://www.o9paathshala.com">Developers</a></span></p>
				</div>
			</div>
		</div>
		
	</footer><!--/Footer-->
	

  
    <script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.scrollUp.min.js"></script>
	<script src="js/price-range.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
    <script src="js/notification.js"></script>
    <script src="js/pace.min.js"></script>
    
    <script src="angular/angular.min.js"></script>
	<script src="angular/ui-bootstrap-tpls.js"></script>
	<script src="angular/angular-ui-route.js"></script>
	<script src="angular/angular-route.js"></script>
	<script src="angular/mainAngular.js"></script>
</body>
</html>