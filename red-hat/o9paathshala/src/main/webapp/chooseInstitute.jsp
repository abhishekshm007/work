<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title><c:out value="${applicationScope.appName }"></c:out></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="https://o9paathshala.com" />
<link href="css/pace.css" rel="stylesheet" />
<link rel="shortcut icon" href="img/logo/favicon.ico" />
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/fancybox/jquery.fancybox.css" rel="stylesheet">
<link href="css/jcarousel.css" rel="stylesheet" />
<link href="css/flexslider.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" />

<!-- Notification css -->
<link href="css/notification.css" rel="stylesheet" />

<!-- Theme skin -->
<link href="skins/default.css" rel="stylesheet" />
<link href="css/main.css" rel="stylesheet" />
</head>
<body>
	<section>
		<section class="forms">
			<div class="form-box " id="login-box">
				<div class="header" style="background-color : #BCA66E !important;">Choose institute to login</div>
				<form action="SwitchInstitute" method="post">
					<div class="body bg-gray">
					
					<c:forEach items="${sessionScope.chooseInstituteData}" varStatus="loop" var="item">
						<div class="radio">
							<label> <input type="radio" name="instituteRadios"
								id="optionsRadios1" value="${loop.index}" > 
								<c:out value="${item.instituteName}"/> ( <c:out value="${item.name}"/> )
							</label>
						</div>
						 </c:forEach>
						

						<br>
						<div class="checkbox">
							<label> <input type="checkbox" name="setAsDefault" /> set as default
							</label>
						</div>
					</div>
					<div class="footer">
						<button type="submit" style="background-color : #BCA66E !important;" class="btn bg-default-blue btn-block">
							Submit
						</button>

						<p>
							<a href="Logout" style="color : #BCA66E !important;">Logout</a>
						</p>

					</div>
				</form>

			</div>
		</section>
	</section>
	<script src="js/jquery.js"></script>
	<script src="js/pace.min.js" type="text/javascript"></script>
	<script src="js/notification.js"></script>
	<script src="js/jquery.easing.1.3.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.fancybox.pack.js"></script>
	<script src="js/jquery.fancybox-media.js"></script>
	<script src="js/google-code-prettify/prettify.js"></script>
	<script src="js/portfolio/jquery.quicksand.js"></script>
	<script src="js/portfolio/setting.js"></script>
	<script src="js/jquery.flexslider.js"></script>
	<script src="js/animate.js"></script>
	<script src="js/custom.js"></script>
	<script src="js/dashboard/app.js"></script>



</body>
</html>