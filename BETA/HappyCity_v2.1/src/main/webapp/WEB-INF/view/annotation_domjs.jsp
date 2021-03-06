﻿<!DOCTYPE html>
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
<title>Digital City Impulse | Corpus Annotation</title>
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
<!-- AdminLTE Skin-->
<link rel="stylesheet" href="resources/dist/css/skins/skin-blue.min.css">


<!-- Select2 -->
<link rel="stylesheet" href="resources/plugins/select2/select2.min.css">
<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet" href="resources/plugins/iCheck/all.css">
<!-- Owl Carousel Assets -->
<link href="resources/owl-carousel/owl.carousel.css" rel="stylesheet">
<link href="resources/owl-carousel/owl.theme.css" rel="stylesheet">
<link href="resources/owl-carousel/owl.transitions.css" rel="stylesheet">


<link rel="stylesheet" href="resources/dist/css/annotation.css">

<style>
body .modal {
	/* Workaround for https://github.com/twbs/bootstrap/issues/14839. */
	-webkit-overflow-scrolling: auto;
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
										<!-- <a href="#" class="btn btn-default btn-flat">Profile</a> -->
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
						<!-- <a href="#"><i class="fa fa-circle text-success"></i> Online</a> -->
					</div>
				</div>

				<!-- Sidebar Menu -->
				<ul class="sidebar-menu">
					<li class="header"></li>
					<li><a href="comingsoon.html"><i class="fa fa-th-large"></i>
							<span>Dashboard</span></a></li>
					<li class="header">USER</li>
					<!-- Optionally, you can add icons to the links -->
					<li><a href="comingsoon.html"><i class="fa fa-map-pin"></i>
							<span>My Regions</span></a></li>
					<li><a href="comingsoon.html"><i class="fa fa-area-chart"></i>
							<span>My Analysis</span></a></li>
					<li><a href="myannotation.html"><i class="fa fa-files-o"></i>
							<span>My Annotation</span></a></li>
					<li class="header">SYSTEM</li>
					<li><a href="comingsoon.html"><i class="fa fa-map-marker"></i>
							<span>Msg on map</span></a></li>
					<li><a href="comingsoon.html"><i class="fa fa-bar-chart"></i>
							<span>Data Analysis</span></a></li>
					<li class="active"><a href="annotation_domjs.html"><i
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
				<h1>
					Corpus Annotation <small>Please help us do the corpus
						annotation.</small>
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">

				<!-- Annotation Settings and Start Button -->
				<div class="row">
					<!-- Annotation Settings -->
					<div class="col-md-6">
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">Annotation Settings</h3>
							</div>
							<!-- /.box-header -->
							<div class='box-body'>
								<div class="col-md-12">
									<div class="form-group">
										<label>Message Language</label> <select
											class="form-control select2" id="annotation-lang"
											multiple="multiple" data-placeholder="English"
											style="width: 100%;" disabled>
											<option value="en">English</option>
											<option value="fr">French</option>
											<option value="zh">Chinese</option>
											<option value="ja">Japanese</option>
											<option value="de">German</option>
											<option value="ar">Arabic</option>
										</select>
									</div>
									<!-- /.form-group -->
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label>Message Resources</label> <select
											class="form-control select2" id="msg-resources"
											multiple="multiple" data-placeholder="Twitter"
											style="width: 100%;" disabled>
											<option value="twitter">Twitter</option>
											<option value="instagram">Instagram</option>
											<option value="sina_weibo">Sina Weibo</option>
										</select>
									</div>
									<!-- /.form-group -->
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label>Annotation Part</label> <br> <label
											style="font-weight: normal"> <input type="radio"
											name="annotation-part" value="word-and-img" class="minimal"
											checked disabled> Annotate both word and images
										</label> <br> <label style="font-weight: normal"> <input
											type="radio" name="annotation-part" value="word-only"
											class="minimal" disabled> Annotate only word
										</label> <br> <label style="font-weight: normal"> <input
											type="radio" name="annotation-part" value="img-only"
											class="minimal" disabled> Annotate only images
										</label>
									</div>
								</div>
								<div class="col-md-12">
									<!-- Start Button -->
									<!-- <button class="btn btn-app btn-flat" id="start-annotation"
										data-toggle="modal" data-target="#annotation-modal">
										<i class="fa fa-play-circle"></i> Start Annotation!
									</button> -->
									<div class="col-md-3"></div>
									<div class="col-md-6">
										<button class="btn btn-app btn-block bg-teal btn-flat"
											id="start-annotation" data-toggle="modal"
											data-target="#annotation-modal">
											<i class="fa fa-play-circle"></i> Start Annotation!
										</button>
									</div>
									<div class="col-md-3"></div>
								</div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->

					</div>
					<!-- End of Annotation Settings -->

					<!-- Recent Annotations -->
					<div class="col-md-6">
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">Recently Annotated Messages</h3>
								<div class="box-tools pull-right">
									<button class="btn btn-box-tool" data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<ul class="products-list product-list-in-box"
									id="recent-annotation">

								</ul>
							</div>
							<!-- /.box-body -->
							<div class="box-footer text-center">
								<a href="myannotation.html"
									class="uppercase recent-count">View All Annotation History</a>
							</div>
							<!-- /.box-footer -->
						</div>
						<!-- /.box -->

					</div>
					<!-- End of Recent Annotations -->

				</div>
				<!-- /.row -->


			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->


		<!-- Main Footer -->
		<footer class="main-footer">
			<div class="pull-right hidden-xs">
				<b>Version</b> Beta 1.1.0
			</div>
			<strong>Thanks to <a href="http://almsaeedstudio.com">Almsaeed
					Studio</a>.
			</strong>
		</footer>
	</div>
	<!-- ./wrapper -->


	<!--Annotation Modal-->
	<div class="example-modal modal fade" id="annotation-modal"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="btn-closemaodal">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						Annotation Module<span class="pull-right" aria-hidden="true">&nbsp;&nbsp;</span><span
							class="label label-success pull-right" id="this-counter">0</span>
					</h4>
				</div>
				<!-- /.modal-header -->

				<div class="modal-body">
					<div class="container-fluid">
						<div class="row">
							<ul class="nav">
								<li><div class="row">
										<div id="annotation" class="col-md-12">
											<div id="owl-demo" class="owl-carousel"></div>
											<!--/.owl-demo-->
										</div>
										<!-- /.col-md-12 -->
									</div></li>
							</ul>
						</div>
					</div>
					<!-- /.container-fluid -->
				</div>
				<!-- /.modal-body -->

				<div class="modal-footer">
					<button type="button" id="btn-skip"
						class="btn btn-danger btnnn pull-left">&nbsp;Skip&nbsp;</button>
					<button type="button" id="btn-next" class="btn btn-success btnnn">Submit</button>
				</div>
				<!-- /.modal-footer -->
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.example-modal -->


	<!----------------- REQUIRED JS SCRIPTS --------------------->
	<!-- jQuery 2.1.4 -->
	<script src="resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="resources/js/less-1.7.0.min.js"></script>
	<!-- SlimScroll -->
	<script src="resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="resources/plugins/fastclick/fastclick.min.js"></script>
	<!-- AdminLTE App -->
	<script src="resources/dist/js/app.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="resources/dist/js/demo.js"></script>
	<!-- page script -->

	<!-- Select2 -->
	<script src="resources/plugins/select2/select2.full.min.js"></script>
	<!-- iCheck 1.0.1 -->
	<script src="resources/plugins/iCheck/icheck.min.js"></script>

	<!-- owl-carousel -->
	<script src="resources/owl-carousel/owl.carousel.js"></script>
	<script src="resources/owl-carousel/assets/js/bootstrap-collapse.js"></script>
	<script src="resources/owl-carousel/assets/js/bootstrap-transition.js"></script>
	<script src="resources/owl-carousel/assets/js/bootstrap-tab.js"></script>

	<!--operation when this page is ready-->
	<script src="resources/dist/js/annotation.js"></script>





</body>
</html>
