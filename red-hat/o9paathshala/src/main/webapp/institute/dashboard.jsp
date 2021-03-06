<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.o9paathshala.dto.SessionDTO" %>
<!DOCTYPE html>
<html data-ng-app="instituteRouting" data-ng-controller="MainController">
<head>
<meta charset="UTF-8">
<title> <c:out value="${applicationScope.appName }"></c:out></title>
<link rel="shortcut icon" href="../img/logo/favicon.ico" />
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>

<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="../css/font-awesome.min.css" rel="stylesheet" />
<link href="../css/ionicons.min.css" rel="stylesheet" type="text/css" />
<link href="../css/morris/morris.css" rel="stylesheet" type="text/css" />
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/datatables/TableTools.css" />
<link rel="stylesheet" href="../css/notification.css" />
<link href="../css/datatables/dataTables.bootstrap.css" rel="stylesheet" />
<link href="../css/default.css" rel="stylesheet" />
<link href="../css/style2.css" rel="stylesheet" />
<link href="../css/pace.css" rel="stylesheet" />
<style type="text/css">
.autocomplete-suggestions {
	border: 1px solid #999;
	background: #FFF;
	overflow: auto;
}

.autocomplete-suggestion {
	padding: 5px 5px;
	white-space: nowrap;
	overflow: hidden;
	font-size: 18px
}

.autocomplete-selected {
	background: #F0F0F0;
}

#editquestion .modal-content {
	height: 600px;
	overflow: auto;
}
</style>
</head>
<body class="skin-blue">
	<input type="hidden" id="tid">
	<input type="hidden" id="tname">
	<input type="hidden" id="tduration">
	<input type="hidden" id="tpmark">
	<input type="hidden" id="tnmark">
	<input type="hidden" id="tsdate">
	<input type="hidden" id="tedate">
	<input type="hidden" id="sid">
	<input type="hidden" id="sname">
	<header class="header">
		<a class="logo"><%=((SessionDTO)session.getAttribute("user")).getInstituteName()%></a>
		<nav class="navbar navbar-static-top" role="navigation">
			<a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas"
				role="button"> <span class="sr-only">Toggle navigation</span> <span
				class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
			</a>
			<div class="navbar-right">
				<ul class="nav navbar-nav">

					<!-- User Account: style can be found in dropdown.less -->
					<li class="dropdown user user-menu"><a class="fakeLink"
						class="dropdown-toggle" data-toggle="dropdown"> <i
							class="glyphicon glyphicon-user"></i> <span><c:out
									value="${user.name }"></c:out> <i class="caret"></i></span>
					</a>
						<ul class="dropdown-menu">
							<!-- User image -->
							<li class="user-header bg-light-blue" id="1img">
							<a class="fakeLink" data-ui-sref="profile"> <img
								style="height: 90px; width: 90px;"
								src="../ProfilePicture?id=${user.id}" class="img-circle"
								alt="User Image" /></a>
								<p>
									<c:out value="${user.name }"></c:out>
									<small id="email1"><c:out value="${user.email }"></c:out></small>
								</p></li>


							<li class="user-body">
								<div class="col-xs-12 text-center fakeLink">
									<a class="fakeLink" data-ui-sref="profile">Profile</a>
								</div>
							</li>
							<!-- Menu Footer-->
							<li class="user-footer">
								<div class="pull-left">
									<a data-ui-sref="switch" class="btn btn-default btn-flat">Switch
										Institute</a>
								</div>
								<div class="pull-right">
									<a href="../Logout" class="btn btn-default btn-flat">Logout</a>
								</div>
							</li>
						</ul></li>
				</ul>
			</div>
		</nav>
	</header>
	<div class="wrapper row-offcanvas row-offcanvas-left">
		<aside class="left-side sidebar-offcanvas">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- Sidebar user panel -->
				<div class="user-panel">
					<div class="pull-left image" id="2img">
						<a class="fakeLink" data-ui-sref="profile"> <img style="height: 45px; width: 45px;"
							src="../ProfilePicture?id=${user.id}" class="img-circle"
							alt="User Image" /></a>
					</div>
					<div class="pull-left info">
						<p id="name1">
							<c:out value="${user.name }"></c:out>
						</p>

					</div>
				</div>
				<!-- sidebar menu: : style can be found in sidebar.less -->
				<ul class="sidebar-menu">
					<li data-ng-class="menuActive('dashboard')"><a
						data-ui-sref="dashboard"> <i class="fa fa-dashboard"></i> <span>Dashboard</span>
					</a></li>


					<li class="treeview"><a class="fakeLink"> <i
							class="fa fa-user"></i> <span>Student</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li data-ng-class="menuActive('allstudents')"><a
								data-ui-sref="allstudents"><i
									class="fa fa-angle-double-right"></i> students</a></li>
							<li data-ng-class="menuActive('addstudents')"><a
								data-ui-sref="addstudents"><i
									class="fa fa-angle-double-right"></i> add students</a></li>
							<li data-ng-class="menuActive('changebatch')"><a
								data-ui-sref="changebatch"><i
									class="fa fa-angle-double-right"></i> change batch</a></li>
						</ul></li>

					<li class="treeview"><a class="fakeLink"> <i
							class="fa fa-user"></i> <span>Faculty</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li data-ng-class="menuActive('facultylist')"><a
								data-ui-sref="facultylist"><i
									class="fa fa-angle-double-right"></i> faculty list</a></li>
							<li data-ng-class="menuActive('addfaculty')"><a
								data-ui-sref="addfaculty"><i
									class="fa fa-angle-double-right"></i> add faculty</a></li>
							<li data-ng-class="menuActive('managebatches')"><a
								data-ui-sref="managebatches"><i
									class="fa fa-angle-double-right"></i> manage batches</a></li>

						</ul></li>
					<li class="treeview"><a class="fakeLink"> <i
							class="fa fa-qrcode"></i> <span>Batch</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li data-ng-class="menuActive('batches')"><a
								data-ui-sref="batches"><i class="fa fa-angle-double-right"></i>
									batches</a></li>
							<li data-ng-class="menuActive('addbatch')"><a
								data-ui-sref="addbatch"><i class="fa fa-angle-double-right"></i>
									add batch</a></li>
						</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-edit"></i>
							<span>Test</span> <i class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li data-ng-class="menuActive('newtest')"><a
								data-ui-sref="newtest"><i class="fa fa-angle-double-right">
								</i> new</a></li>
							<li data-ng-class="menuActive('alltest')"><a
								data-ui-sref="alltest"><i class="fa fa-angle-double-right"></i>
									all</a></li>
						</ul></li>
					<li class="treeview"><a class="fakeLink"> <i
							class="fa fa-group"></i> <span>Forum</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<!-- <li data-ng-class="menuActive('forum/askquestion')"><a
								data-ui-sref="forum/askquestion"><i
									class="fa fa-angle-double-right"></i> ask question</a></li> -->
							<li data-ng-class="menuActive('forum/questions')"><a
								data-ui-sref="forum/questions"><i
									class="fa fa-angle-double-right"></i> questions</a></li>
							<li data-ng-class="menuActive('forum/tags')"><a
								data-ui-sref="forum/tags"><i
									class="fa fa-angle-double-right"></i> tags</a></li>
						</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-table"></i>
							<span>Question Bank</span> <i class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li data-ng-class="menuActive('myquestions')"><a
								data-ui-sref="myquestions"><i
									class="fa fa-angle-double-right"></i> my questions</a></li>
							<li data-ng-class="menuActive('purchasedquestions')"><a
								data-ui-sref="purchasedquestions"><i
									class="fa fa-angle-double-right"></i> purchased questions</a></li>
							<li data-ng-class="menuActive('addquestions')"><a
								data-ui-sref="addquestions"><i
									class="fa fa-angle-double-right"></i> add questions</a></li>
							<li data-ng-class="menuActive('buyquestions')"><a
								data-ui-sref="buyquestions"><i
									class="fa fa-angle-double-right"></i> buy questions</a></li>
						</ul></li>
					<li class="treeview"><a href="#"> <i
							class="fa fa-calendar-o"></i> <span>Subjects</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li data-ng-class="menuActive('allsubjects')"><a
								data-ui-sref="allsubjects"><i
									class="fa fa-angle-double-right"></i> all subjects</a></li>
							<li data-ng-class="menuActive('addsubject')"><a
								data-ui-sref="addsubject"><i
									class="fa fa-angle-double-right"></i> add subject</a></li>
						</ul></li>

					<li data-ng-class="menuActive('results')"><a
						data-ui-sref="results"> <i class="fa fa-bar-chart-o"></i> <span>Results</span>
					</a></li>
					<li data-ng-class="menuActive('help')"><a data-ui-sref="help">
							<i class="fa  fa-question-circle"></i> <span>Help</span>
					</a></li>
				</ul>
			</section>
			<!-- /.sidebar -->
		</aside>

		<!-- Right side column. Contains the navbar and content of the page -->
		<aside class="right-side">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1 id="breadcrumbHeading"
					data-ncy-breadcrumb-last="{{ncyBreadcrumbLabel}}"></h1>
				<div class="well" data-ncy-breadcrumb></div>
			</section>
			<section class="content">
				<div data-ui-view></div>
			</section>
		</aside>

	</div>



	<script src="../js/jquery.js"></script>
	<script src="../js/pace.min.js" type="text/javascript"></script>
	<script src="../js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
	<script
		src="../js/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"
		type="text/javascript"></script>
	<script src="../js/spin.js"></script>
	<script src="../js/notification.js"></script>
	<script src="../js/bootstrap.min.js" type="text/javascript"></script>
	<script src="../js/alert/bootbox.min.js" type="text/javascript"></script>
	<script src="../js/dashboard/app.js" type="text/javascript"></script>
	<script src="../js/plugins/morris/raphael-min.js"></script>
	<script src="../js/plugins/morris/morris.js"></script>
	<script src="../js/plugins/datatables/jquery.dataTables.js"
		type="text/javascript"></script>
	<script src="../js/plugins/datatables/dataTables.bootstrap.js"
		type="text/javascript"></script>
	<script src="../js/plugins/datatables/jquery.dataTables.editable.js"
		type="text/javascript"></script>
	<script src="../js/plugins/datatables/fnReloadAjax.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="../extras/ColReorder/media/js/ColReorder.js"></script>
	<script type="text/javascript"
		src="../extras/ColVis/media/js/ColVis.js"></script>
	<script type="text/javascript"
		src="../extras/TableTools/media/js/TableTools.min.js"></script>
		<script src="../js/plugins/canvas/Chart.js"></script>
	
	<script src="../js/group_config.js" type="text/javascript"></script>
		
	<script src="../js/angular/angular.min.js"></script>
	<script src="../js/angular/ui-bootstrap-tpls.js"></script>
	<script src="../js/angular/angular-ui-route.js"></script>
	<script src="../js/angular/angular-route.js"></script>
	<script src="../js/angular/angular-resource.js"></script>
	<script src="../js/angular/angular-breadcrumb.js"></script>
	<script src="../js/angular/instituteRouting.js"></script>

	<script src="../js/dashboard/demo.js" type="text/javascript"></script>
	<script src="../js/dashboard/dashboard.js" type="text/javascript"></script>

	<script src="../js/jquery.autocomplete.js"></script>
	<script src="../js/dateFormat.min.js"></script>

	

	<script type="text/javascript">
		function parseDate(data) {

			var mon = (data.getMonth() + 1) + "";
			if (mon.length == 1)
				mon = "0" + mon;
			var d = data.getDate() + "";
			if (d.length == 1)
				d = "0" + d;
			var c = data.getFullYear() + "-" + mon + "-" + d;
			return c;
		}
		function parseTimeDate(data) {
			var hours = data.getHours() + "";
			if (hours.length == 1)
				hours = "0" + hours;
			var min = data.getMinutes() + "";
			if (min.length == 1)
				min = "0" + min;
			var mon = (data.getMonth() + 1) + "";
			if (mon.length == 1)
				mon = "0" + mon;
			var d = data.getDate() + "";
			if (d.length == 1)
				d = "0" + d;
			var c = data.getFullYear() + "-" + mon + "-" + d + "T" + hours
					+ ":" + min;
			return c;
		}
		function getCookie(cname) {
			var name = cname + "=";
			var ca = document.cookie.split(';');
			for ( var i = 0; i < ca.length; i++) {
				var c = ca[i];
				while (c.charAt(0) == ' ')
					c = c.substring(1);
				if (c.indexOf(name) == 0)
					return c.substring(name.length, c.length);
			}
			return "";
		}
		function fileUpload(form, action_url) {
			var target = document.getElementById('box2');
			var spinner = new Spinner().spin(target);
			$(target).find("*").attr('disabled', false);
			var iframe = document.createElement("iframe");
			iframe.setAttribute("id", "upload_iframe");
			iframe.setAttribute("name", "upload_iframe");
			iframe.setAttribute("width", "0");
			iframe.setAttribute("height", "0");
			iframe.setAttribute("border", "0");
			iframe.setAttribute("style", "width: 0; height: 0; border: none;");
			form.parentNode.appendChild(iframe);
			window.frames['upload_iframe'].name = "upload_iframe";
			iframeId = document.getElementById("upload_iframe");
			var eventHandler = function() {
				if (iframeId.detachEvent)
					iframeId.detachEvent("onload", eventHandler);
				else
					iframeId.removeEventListener("load", eventHandler, false);
				if (iframeId.contentDocument) {
					content = iframeId.contentDocument.body.innerHTML;
				} else if (iframeId.contentWindow) {
					content = iframeId.contentWindow.document.body.innerHTML;
				} else if (iframeId.document) {
					content = iframeId.document.body.innerHTML;
				}
				spinner.stop();
				content = JSON.parse(content);
				if ("AEN" == content) {
					notif({
						msg : "Batch with this name already exist,it must be unique",
						type : 'warning',
						position : 'center'
					});

				} else {
					if (content) {
						notif({
							msg : "Saved successfully.",
							type : 'success',
							position : 'center'
						});
						$('#addbatchform').trigger("reset");
						$(form).trigger("reset");
					} else {
						notif({
							msg : "Operation failed, please check your data.",
							type : 'error',
							position : 'center'
						});
					}
				}
				setTimeout('iframeId.parentNode.removeChild(iframeId)', 250);
			};
			if (iframeId.addEventListener) {
				iframeId.addEventListener("load", eventHandler, true);
			}
			if (iframeId.attachEvent) {
				iframeId.attachEvent("onload", eventHandler);
			}
			form.setAttribute("target", "upload_iframe");
			form.setAttribute("action", action_url);
			form.setAttribute("method", "post");
			form.setAttribute("enctype", "multipart/form-data");
			form.setAttribute("encoding", "multipart/form-data");
			form.submit();

		}
	</script>
</body>

</html>
