<!DOCTYPE html>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Digital City Impulse</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="resources/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect.
    -->
<link rel="stylesheet" href="resources/dist/css/skins/skin-blue.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="resources/js/html5shiv.min.js"></script>
        <script src="resources/js/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- DataTables -->
<link rel="stylesheet"
	href="resources/plugins/datatables/dataTables.bootstrap.css">
<!-- Select2 -->
<link rel="stylesheet" href="resources/plugins/select2/select2.min.css">
<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet" href="resources/plugins/iCheck/all.css">

<!-- Owl Carousel Assets -->
<link href="resources/owl-carousel/owl.carousel.css" rel="stylesheet">
<link href="resources/owl-carousel/owl.theme.css" rel="stylesheet">
<link href="resources/owl-carousel/owl.transitions.css" rel="stylesheet">

<style>
.annotation-img {
	width: 50px;
	height: 50px;
}
</style>

</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<header class="main-header">

			<!-- Logo -->
			<a href="index2.html" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"><b>DCI</b></span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg"><b>DigitalCityImpulse</b></span>
			</a>

			<!-- Header Navbar -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"> <span class="sr-only">Toggle navigation</span>
				</a>
				<!-- Navbar Right Menu -->
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<!-- Messages: style can be found in dropdown.less-->

						<!-- User Account Menu -->
						<li class="dropdown user user-menu">
							<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <!-- The user image in the navbar-->
								<img src="resources/img/user.ico" class="user-image"
								alt="User Image"> <!-- hidden-xs hides the username on small devices so only the image appears. -->
								<span class="hidden-xs"><sec:authentication
										property="principal.firstName" /> <sec:authentication
										property="principal.lastName" /></span>
						</a>
							<ul class="dropdown-menu">
								<!-- The user image in the menu -->
								<li class="user-header"><img src="resources/img/user.ico"
									class="img-circle" alt="User Image">
									<p>
										<sec:authentication property="principal.firstName" />
										<sec:authentication property="principal.lastName" />
										<small><sec:authentication property="principal.email" /></small>
										<small>Member since <sec:authentication
												property="principal.created_on" /></small>
									</p></li>
								<!-- Menu Body -->
								<!-- Menu Footer-->
								<li class="user-footer">
									<div class="pull-left">
										<!-- 										<a href="#" class="btn btn-default btn-flat">Profile</a> -->
									</div>
									<div class="pull-right">
										<a class="btn btn-default btn-flat"
											href="<c:url value="/j_spring_security_logout" />">Sign
											out</a>
									</div>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">

			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">

				<!-- Sidebar user panel (optional) -->
				<div class="user-panel">
					<div class="pull-left image">
						<img src="resources/img/user.ico" class="img-circle"
							alt="User Image">
					</div>
					<div class="pull-left info">
						<p>
							<sec:authentication property="principal.firstName" />
							<sec:authentication property="principal.lastName" />
						</p>
						<!-- Status -->
						<!-- 						<a href="#"><i class="fa fa-circle text-success"></i> Online</a> -->
					</div>
				</div>


				<!-- Sidebar Menu -->
				<ul class="sidebar-menu">
					<li class="header"></li>
					<li><a href="comingsoon.html"><i
							class="fa fa-th-large"></i> <span>Dashboard</span></a></li>
					<li class="header">USER</li>
					<!-- Optionally, you can add icons to the links -->
					<li><a href="comingsoon.html"><i
							class="fa fa-map-pin"></i> <span>My Regions</span></a></li>
					<li><a href="comingsoon.html"><i
							class="fa fa-area-chart"></i> <span>My Analysis</span></a></li>
					<li><a href="myannotation.html"><i
							class="fa fa-files-o"></i> <span>My Annotation</span></a></li>
					<li class="header">SYSTEM</li>
					<li><a href="comingsoon.html"><i
							class="fa fa-map-marker"></i> <span>Msg on map</span></a></li>
					<li><a href="comingsoon.html"><i
							class="fa fa-bar-chart"></i> <span>Data Anlysis</span></a></li>
					<li><a href="annotation_domjs.html"><i
							class="fa fa-book"></i> <span>Corpus Annotation</span></a></li>
				</ul>
				<!-- /.sidebar-menu -->
			</section>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1></h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<img src="resources/img/coming-soon.png" class="img-responsive"
							alt="Responsive image">>
					</div>
					<div class="col-md-3"></div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<footer class="main-footer">
			<div class="pull-right hidden-xs">
				<b>Version</b> Beta 1.1.0
			</div>
			<strong>Thanks to <a href="http://almsaeedstudio.com">Almsaeed
					Studio</a>.
			</strong>
		</footer>


		<!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->

	</div>
	<!-- ./wrapper -->

	<!-- jQuery 2.1.4 -->
	<script src="resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="resources/js/less-1.7.0.min.js"></script>
	<!-- DataTables -->
	<script src="resources/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="resources/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<!-- SlimScroll -->
	<script src="resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="resources/plugins/fastclick/fastclick.min.js"></script>
	<!-- AdminLTE App -->
	<script src="resources/dist/js/app.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="resources/dist/js/demo.js"></script>
	<!-- page script -->
	<script>
		$(function() {

		});
	</script>
</body>
</html>
