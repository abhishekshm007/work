 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title><c:out value="${applicationScope.appName }"></c:out></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="http://bootstraptaste.com" />
	
<link rel="shortcut icon" href="img/logo/favicon.ico" />
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<!-- Notification css -->
<link href="css/notification.css" rel="stylesheet" />
<link href="css/pace.css" rel="stylesheet" />
<!-- Theme skin -->
<link href="skins/default.css" rel="stylesheet" />


</head>
<body id="body" data-ng-app="o9paathshalaMail">

<div class="col-lg-12">
<div class="col-lg-2"></div>
<div class="col-lg-8">
           <div >
                            <div class="box box-success" style="margin-top:10%;" data-ng-controller="InstituteResendConfirmationMail">
                                <div class="box-header">
                                    <i class="fa fa-envelope-o"></i>
                                    <h3 class="box-title">Thank you for registering at o9-Paathshala</h3>
                                </div><!-- /.box-header -->
                                <div class="box-body">
                                    <div class="callout callout-success">
                                        <h4>Hello and welcome <c:out value="${param['lk']}"></c:out></h4>
										 <span class="tab">To confirm your registration, please check the inbox of your Email.</span><br>
									    
										 <span class="tab">For any questions related to o9Pathshala services, please submit your request <a target="_blank" href="http://localhost:8080/o9pathshala">here</a>.</span><br>
										 <br>
										 <span class="tab"> To resend confirmation link, click <a class="fakeLink" data-ng-click="resend('<c:out value="${param['req']}"></c:out>','<c:out value="${param['lk']}"></c:out>')">here</a>.</span><br>
										 <br>
										Thank you...<br>
										<a href="http://localhost:8080/o9paathshala/" target="_blink">o9-Paathshala</a> Team<br>
                                    </div>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div>
</div>
<div class="col-lg-2"></div>
</div>
<!-- javascript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard/app.js"></script>
<!-- Notification js -->
<script src="js/notification.js"></script>
<script src="js/spin.js" type="text/javascript"></script> 
<script src="js/angular/angular.js"></script>
	<script src="../js/pace.min.js" type="text/javascript"></script>
<!-- Login ajax js -->
<script src="js/angular/instituteMailAngular.js"></script>
</body>
</html>
