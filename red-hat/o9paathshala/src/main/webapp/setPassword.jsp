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
<link href="css/pace.css" rel="stylesheet" />
<link href="css/notification.css" rel="stylesheet" />
<!-- Theme skin -->
<link href="skins/default.css" rel="stylesheet" />
<link href="css/font-awesome.css" rel="stylesheet" />


</head>
<body data-ng-app="o9paathshalaHome" onmouseup="return makeUnVisiblePassword();" >
<section class="forms">
           <div class="form-box" id="login-box" >
            <div style="background-color: #58B38A" class="header">Set up new Passowrd</div>
            <form name="setPasswordForm" action="InstituteRegistrationConfirmation" method="post" novalidate>
                <div class="body bg-gray">
                <div class="input-group form-group input-group">
                                        <input type="password" id="setPassword" name="password" data-ng-model="password.newPassword"
                                        data-ng-minlength=6 data-ng-maxlength=100  class="form-control" placeholder="New Password"/>
                                        <span class="input-group-btn">
                                            <button class="btn  btn-flat" tabindex=-1  onmousedown="return makeVisiblePassword();" type="button"><i class="fa fa-fw fa-eye"></i></button>
                                        </span>
                                    </div>
                 <div class="error-container" data-ng-show="setPasswordForm.password.$dirty && setPasswordForm.password.$invalid">
					 <small class="error" data-ng-show="setPasswordForm.password.$error.required">Please input password</small>
					 <small class="error" data-ng-show="setPasswordForm.password.$error.minlength">Your password is required to be at least 6 characters</small>
					 <small class="error" data-ng-show="setPasswordForm.password.$error.maxlength">Your password cannot be longer than 100 characters</small>
				</div>
				
				
                <div class="input-group form-group input-group">
                                        <input type="password" id="setConfirmPassword" name="confirmPassword" data-ng-model="password.confirmPassword" class="form-control" 
                                        data-setpassword-verify="password.newPassword" placeholder="Confirm New Password"/>
                                        <span class="input-group-btn">
                                            <button class="btn  btn-flat" tabindex=-1  onmousedown="return makeVisibleConfirmPassword();"  type="button"><i class="fa fa-fw fa-eye"></i></button>
                                        </span>
                                    </div>
                <div class="error-container" data-ng-show="setPasswordForm.confirmPassword.$dirty && setPasswordForm.confirmPassword.$invalid">
					 <small class="error" data-ng-show="setPasswordForm.confirmPassword.$error.required">Please input confirm password</small>
					 <small class="error" data-ng-show="setPasswordForm.confirmPassword.$error.minlength">Your name is required to be at least 6 characters</small>
					 <small class="error" data-ng-show="setPasswordForm.confirmPassword.$error.maxlength">Your name cannot be longer than 200 characters</small>
					 <small class="error" data-ng-show="setPasswordForm.confirmPassword.$error.setpasswordVerify">Your password didn't match</small>
				</div>
                </div>
                <input type="hidden" name="id" value='<%=request.getParameter("id") %>'>
                <div class="footer">                                                               
                    <button type="submit" id="submit" data-ng-disabled="setPasswordForm.$invalid" class="btn btn-success btn-block">Set</button>  
                </div>
            </form>
        </div>
	</section>

<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript">
	function makeVisiblePassword(){
		var element = document.getElementById('setPassword'); 
		  element.setAttribute("type", "text"); 
	}
	function makeUnVisiblePassword(){
		var element1 = document.getElementById('setPassword'); 
		  element1.setAttribute("type", "password"); 
		  var element2 = document.getElementById('setConfirmPassword');
		  element2.setAttribute("type", "password"); 
	}
	function makeVisibleConfirmPassword(){
		var element = document.getElementById('setConfirmPassword'); 
		  element.setAttribute("type", "text"); 
	}
	
	</script>
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard/app.js"></script>
<!-- Notification js -->
<script src="js/notification.js"></script>
<script src="js/spin.js" type="text/javascript"></script> 
<script src="js/angular/angular.js"></script>
	<script src="../js/pace.min.js" type="text/javascript"></script>
<!-- Login ajax js -->
<script src="js/angular/homeAngular.js"></script>

</body>


</html>