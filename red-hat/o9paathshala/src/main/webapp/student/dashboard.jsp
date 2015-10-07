<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.o9paathshala.dto.SessionDTO" %>
<!DOCTYPE html>
<html data-ng-app="studentRouting" data-ng-controller="MainController">
<head>
<!-- Student -->
<meta charset="UTF-8">
<title><c:out value="${applicationScope.appName }"></c:out></title>
<link rel="shortcut icon" href="../img/logo/favicon.ico" />
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<!-- bootstrap 3.0.2 -->

<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- font Awesome -->
<link href="../css/font-awesome.min.css" rel="stylesheet"
	type="text/css" />
<!-- Ionicons -->
<link href="../css/ionicons.min.css" rel="stylesheet" type="text/css" />
<link href="../css/morris/morris.css" rel="stylesheet" type="text/css" />
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<!-- Notification Style -->
<link rel="stylesheet" href="../css/notification.css" />
<link href="../css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css"
	rel="stylesheet" type="text/css" />
<link href="../css/datatables/dataTables.bootstrap.css" rel="stylesheet">
<link rel="stylesheet"
	href="../extras/TableTools/media/css/TableTools.css" />
	<link href="../css/pace.css" rel="stylesheet" />
</head>

<body class="skin-blue">
	<header class="header">
		<a  class="logo"><%=((SessionDTO)session.getAttribute("user")).getInstituteName()%></a>
		<nav class="navbar navbar-static-top" role="navigation">
			<a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas"
				role="button"> <span class="sr-only">Toggle navigation</span> <span
				class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
			</a>
			<div class="navbar-right">
				<ul class="nav navbar-nav">
					<li class="dropdown user user-menu"><a class="fakeLink"
						class="dropdown-toggle" data-toggle="dropdown"> <i
							class="glyphicon glyphicon-user"></i> <span id="topname"><c:out
									value="${user.name }"></c:out> <i class="caret"></i></span>
					</a>
						<ul class="dropdown-menu">
							<!-- User image -->
							<li class="user-header bg-light-blue" id="1img">
							<a class="fakeLink" data-ui-sref="profile"> <img style="height:90px; width:90px;" 
								src="../ProfilePicture?id=${user.id}" class="img-circle"
								alt="User Image" />
								</a>
								<p>
									<c:out value="${user.name }"></c:out>
									<small><c:out value="${user.email }"></c:out></small>
								</p></li>
								
								
								<li class="user-body">
                                    <div class="col-xs-12 text-center fakeLink">
                                        <a class="fakeLink" data-ui-sref="profile">Profile</a>
                                    </div>
                                </li>
							<!-- Menu Footer-->
							<li class="user-footer">
								<div class="pull-left">
										<a data-ui-sref="switch" class="btn btn-default btn-flat">Switch Institute</a>
								
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
						<a class="fakeLink" data-ui-sref="profile" ><img style="height:45px; width:45px;" src="../ProfilePicture?id=${user.id}" class="img-circle"
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
					<li data-ng-class="menuActive('dashboard')"><a data-ui-sref="dashboard"> <i
							class="fa fa-dashboard"></i> <span>Dashboard</span>
					</a></li>

					<li data-ng-class="menuActive('tests')"><a data-ui-sref="tests"> <i
							class="fa fa-edit"></i> <span>Test</span>
					</a></li>

					<li class="treeview">
                            <a class="fakeLink">
                                <i class="fa fa-group"></i>
                                <span>Forum</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li data-ng-class="menuActive('forum/askquestion')"><a data-ui-sref="forum/askquestion" ><i class="fa fa-angle-double-right"></i> ask question</a></li>
                                <li data-ng-class="menuActive('forum/questions')"><a data-ui-sref="forum/questions"><i class="fa fa-angle-double-right"></i> questions</a></li>
                                <li data-ng-class="menuActive('forum/tags')"><a data-ui-sref="forum/tags"><i class="fa fa-angle-double-right"></i> tags</a></li>
                            </ul>
                        </li>

					<li class="treeview"><a href="#"> <i class="fa fa-bar-chart-o"></i>
							<span>Results</span> <i class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li data-ng-class="menuActive('tresults')"><a data-ui-sref="tresults"><i
									class="fa fa-angle-double-right"></i> tabular analysis</a></li>
							<li data-ng-class="menuActive('gresults')"><a data-ui-sref="gresults"><i
									class="fa fa-angle-double-right"></i> graphical analysis</a></li>
						</ul></li>
					<li data-ng-class="menuActive('leaderboard')"><a data-ui-sref="leaderboard"> <i class="fa  fa-tasks"></i>
							<span>Leader board</span>
					</a></li>

					<li data-ng-class="menuActive('help')"><a data-ui-sref="help"> <i class="fa fa-question-circle"></i>
							<span>Help</span>
					</a></li>
				</ul>
			</section>
			<!-- /.sidebar -->
		</aside>

		<!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
                <!-- Content Header (Page header) -->
                <section  class="content-header">
                    <h1 id="breadcrumbHeading" data-ncy-breadcrumb-last="{{ncyBreadcrumbLabel}}">
                    </h1>
                     <div class="well" data-ncy-breadcrumb></div>
                </section>
             <!-- Main content -->
              <section class="content">
            <div  data-ui-view></div>
            </section><!-- /.content -->
            </aside><!-- /.right-side -->
            <!-- /.right-side -->

	</div>



	<script src="../js/jquery.js"></script>
	<script src="../js/pace.min.js" type="text/javascript"></script>
	<script src="../js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
	 <!-- Bootstrap WYSIHTML5 -->
    <script src="../js/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>
    	<script src="../js/aes.js"></script>
	<script src="../js/spin.js" type="text/javascript"></script>
	<script src="../js/notification.js"></script>
	
	<script src="../js/bootstrap.min.js" type="text/javascript"></script>
	<script src="../js/alert/bootbox.min.js" type="text/javascript"></script>
	<script src="../js/dashboard/app.js" type="text/javascript"></script>
	<script src="../js/plugins/morris/raphael-min.js"></script>
	<script src="../js/plugins/morris/morris.js"></script> 
		
	<script src="../js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
	<script src="../js/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>
	<script src="../js/plugins/datatables/jquery.dataTables.editable.js" type="text/javascript"></script>
	<script src="../js/plugins/datatables/fnReloadAjax.js" type="text/javascript"></script>
	<script src="../extras/ColReorder/media/js/ColReorder.js" type="text/javascript"></script>
	<script src="../extras/ColVis/media/js/ColVis.js" type="text/javascript"></script>
	<script src="../extras/TableTools/media/js/TableTools.min.js" type="text/javascript"></script>
	<script src="../js/dateFormat.min.js" type="text/javascript"></script>
	<script src="../js/plugins/canvas/Chart.js" type="text/javascript"></script>
	<script src="../js/group_config.js" type="text/javascript"></script>

	
	<script src="../js/angular/angular.min.js"></script>
	<script src="../js/angular/ui-bootstrap-tpls.js"></script>
	<script src="../js/angular/angular-ui-route.js"></script>
	<script src="../js/angular/angular-route.js"></script>
	<script src="../js/angular/angular-resource.js"></script>
	<script src="../js/angular/angular-breadcrumb.js"></script>
	<script src="../js/dashboard/demo.js" type="text/javascript"></script>
	<script src="../js/dashboard/dashboard.js" type="text/javascript"></script>
	<script src="../js/angular/studentRouting.js"></script>
	<script src="../js/test/checkResult.js"></script>
		
	<script type="text/javascript">
	function parseDate(data){
		var mon=(data.getMonth()+1)+"";
		if(mon.length==1)
			mon="0"+mon;
		var d=data.getDate()+"";
		if(d.length==1)
			d="0"+d;
		var c=data.getFullYear()+"-"+mon+"-"+d;
		return c;
	}
	</script>
	
</body>

</html>
