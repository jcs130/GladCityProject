<!DOCTYPE html>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
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

<!-- Select2 -->
<link rel="stylesheet" href="resources/plugins/select2/select2.min.css">
<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet" href="resources/plugins/iCheck/all.css">

<!-- Owl Carousel Assets -->
<link href="resources/owl-carousel/owl.carousel.css" rel="stylesheet">
<link href="resources/owl-carousel/owl.theme.css" rel="stylesheet">
<link href="resources/owl-carousel/owl.transitions.css" rel="stylesheet">




<style>
.example-modal .modal {
	position: relative;
	top: auto;
	bottom: auto;
	right: auto;
	left: auto;
	display: block;
	z-index: 1;
}

.example-modal .modal {
	background: transparent !important;
}
</style>

<style>
#owl-demo .owl-item div {
	padding: 5px;
}

#owl-demo .owl-item img {
	display: block;
	width: 100%;
	height: auto;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
}

.btn-positive, .btn-positive:hover {
	background-color: #9ACD32;
}

.btn-neutral, .btn-neutral:hover {
	background-color: #FFD700;
}

.btn-negative, .btn-negative:hover {
	background-color: #FF6347;
}
</style>

<style>
#msg-text {
	font-size: x-large;
}

.btn-emo>.fa, .btn-emo>.glyphicon, .btn-emo>.ion {
	font-size: large;
}

@media ( max-width : 768px) {
	#msg-text {
		font-size: large;
	}
}

@media ( max-width : 350px) {
	.btn-emo {
		min-width: 40px;
		height: 60px;
	}
	.btn-emo>.fa, .btn-emo>.glyphicon, .btn-emo>.ion {
		font-size: medium;
	}
	.pull-center {
		text-align: center;
	}
	.pull-center>.btnnn {
		float: none;
		display: inline-block;
		*display: inline;
		*zoom: 1;
	}
}
</style>
<style>

body.modal-open {
    overflow: hidden;
}
</style>

</head>
<!--
  BODY TAG OPTIONS:
  =================
  Apply one or more of the following classes to get the
  desired effect
  |---------------------------------------------------------|
  | SKINS         | skin-blue                               |
  |               | skin-black                              |
  |               | skin-purple                             |
  |               | skin-yellow                             |
  |               | skin-red                                |
  |               | skin-green                              |
  |---------------------------------------------------------|
  |LAYOUT OPTIONS | fixed                                   |
  |               | layout-boxed                            |
  |               | layout-top-nav                          |
  |               | sidebar-collapse                        |
  |               | sidebar-mini                            |
  |---------------------------------------------------------|
  -->
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
					<li><a href="comingsoon.html"><i class="fa fa-th-large"></i> <span>Dashboard</span></a></li>
					<li class="header">USER</li>
					<!-- Optionally, you can add icons to the links -->
					<li><a href="comingsoon.html"><i class="fa fa-map-pin"></i> <span>My
								Regions</span></a></li>
					<li><a href="comingsoon.html"><i class="fa fa-area-chart"></i> <span>My
								Analysis</span></a></li>
					<li><a href="myannotation.html"><i
							class="fa fa-files-o"></i> <span>My Annotation</span></a></li>
					<li class="header">SYSTEM</li>
					<li><a href="comingsoon.html"><i class="fa fa-map-marker"></i> <span>Msg
								on map</span></a></li>
					<li><a href="comingsoon.html"><i class="fa fa-bar-chart"></i> <span>Data
								Anlysis</span></a></li>
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
								<a href="/happycity/myannotation.html"
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
		<div class="modal">
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
									<%--<li><div class="row">
												<center>
													<span class="label label-success" id="this-counter">0</span>
													<span>&nbsp;annotations this time&nbsp;&nbsp;</span><br>
												</center>
											</div></li> --%>
									<!-- Image Annotation Part -->
									<li><div class="row">
											<div id="annotation" class="col-md-12">
												<div id="owl-demo" class="owl-carousel"></div>
												<!--/.owl-demo-->
											</div>
											<!-- /.col-md-12 -->
										</div></li>
									<!-- End of Image Annotation Part -->

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
		<!-- /.modal -->
	</div>
	<!-- /.example-modal -->
	<!-- REQUIRED JS SCRIPTS -->

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

	<!-- Select2 -->
	<script src="resources/plugins/select2/select2.full.min.js"></script>
	<!-- iCheck 1.0.1 -->
	<script src="resources/plugins/iCheck/icheck.min.js"></script>

	<!-- owl-carousel -->
	<script src="resources/owl-carousel/owl.carousel.js"></script>
	<script src="resources/owl-carousel/assets/js/bootstrap-collapse.js"></script>
	<script src="resources/owl-carousel/assets/js/bootstrap-transition.js"></script>
	<script src="resources/owl-carousel/assets/js/bootstrap-tab.js"></script>


	<!--<script src="resources/sweetalert/sweet-alert.js"></script>  -->


	<script>
		var msg_id;
		var counter;
		$(function() {

			//if(role=='&#91;Role&#32;&#91;id&#61;4&#44;&#32;name&#61;ROLE&#95;USER&#93;&#93')
			$("#example1").DataTable();
			$('#example2').DataTable({
				"paging" : true,
				"lengthChange" : false,
				"searching" : false,
				"ordering" : true,
				"info" : true,
				"autoWidth" : false
			});
			//Initialize Select2 Elements
			$(".select2").select2();
			//iCheck for checkbox and radio inputs
			$('input[type="checkbox"].minimal, input[type="radio"].minimal')
					.iCheck({
						checkboxClass : 'icheckbox_flat-green',
						radioClass : 'iradio_flat-green'
					});

			$.ajax({
				type : "GET",
				url : "annotation/getrecordcount",
				data : {},
				dataType : "json",
				success : function(data, textStatus) {
					$(".recent-count").html(
							"View All " + data + " Annotation History")
				}
			});

			//get recent annotations
			$
					.ajax({
						type : "GET",
						url : "annotation/getrecentannotation",
						data : {},
						dataType : "json",
						success : function(data, textStatus) {
							//var values= new Array();
							//alert(data);
							$("#recent-annotation").empty();
							$
									.each(
											data,
											function() {

												$("#recent-annotation")
														.append(
																"<li class='item'><div class='product-img'><img src='"+this.media_urls[0]+"' alt='Product Image'></div><div class='product-info'><a href='javascript::;' class='product-title'>"
																		+ this.mark_at
																		+ "<span class='label pull-right label-recent'>"
																		+ this.emotion_text
																		+ "</span></a><span class='product-description'>"
																		+ this.text
																		+ "</span></div></li>");

											});
							$.each($(".label-recent"), function() {
								switch ($(this).html()) {
								case "positive":
									$(this).addClass("label-success");
									break;
								case "neutral":
									$(this).addClass("label-warning");
									break;
								case "negative":
									$(this).addClass("label-danger");
									break;
								default:
									break;
								}
							});
						}

					});

			$("#btn-closemaodal").click(function() {
				location.reload();
			});

			$('#iannotation-modal').on('hide.bs.modal', function() {

				$("#annotation").empty();
				counter = 0;

				/* 				$("body,html").css({
				 "overflow" : "auto"
				 });
				 */
				/* 				$(".popup-overlay").css({
				 "position" : "absolute",
				 "visibility" : "hidden",
				 "opacity" : "0"
				 }); */
			})
			$('#iannotation-modal').on('show.bs.modal', function() {
				/* 				$("body").height($(window).height());
				 $("body,html").css({
				 //"height":"100%",
				 "overflow" : "hidden"
				 }); */

				/* 				$(".popup-overlay").css({
				 "position" : "fixed",
				 "visibility" : "visible",
				 "opacity" : "1"
				 }); */
				/* $('body').css('overflow', 'hidden');
				$('body').css('position', 'fixed'); */

			})

			//alert("1");

			//Operations when click on start-annotation button
			$("#start-annotation")
					.click(
							function() {
								counter = 0;
								$("#this-counter").html(counter);
								//alert("start event!");

								//get annotation language list
								var annotation_lang_array = new Array();
								$("#annotation-lang option").each(function() {
									if (this.selected) {
										annotation_lang_array.push(this.value);
									}
								});
								var annotation_lang = annotation_lang_array
										.toString();

								//get annotation part option
								annotation_part = $(
										'input[type="radio"][name="annotation-part"]:checked')
										.val();

								//get new annotated msg through ajax
								$
										.ajax({
											type : "GET",
											url : "annotation/getnewmessage",
											data : {
												user_email : "huiwen.hong@gmial.com",
												languages : annotation_lang,
												annotate_part : annotation_part
											},
											dataType : "json",
											success : function(data, textStatus) {
												msg_id = data.msg_id;
												//alert("msg_id:"+msg_id);
												//clear html element
												$("#annotation").empty();
												//append new msg to html element
												$("#annotation")
														.append(
																"<div id='owl-demo' class='owl-carousel'></div>");
												$("#owl-demo")
														.append(
																"<div><center><p id='msg-text'>"
																		+ data.text
																		+ "</p></center><center><div class='btn-group' id='text-btn-group' data-toggle='buttons'><label class='btn btn-app btn-emo btn-positive' ><input type='radio' name='options' id='positive'><i class='fa fa-smile-o'></i>Positive</label><label class='btn btn-app btn-emo btn-neutral'><input type='radio' name='options' id='neutral'><i class='fa fa-meh-o'></i>Neutral</label><label class='btn btn-app btn-emo btn-negative'><input type='radio' name='options' id='negative'><i class='fa fa-frown-o'></i>Negative</label></div></center></div>");
												$
														.each(
																data.medias,
																function() {
																	$(
																			"#owl-demo")
																			.append(
																					"<div><img class='col-md-12' src='"+this.media_url+"' /><center><div class='btn-group img-btn-group'  data-toggle='buttons'><label class='btn btn-app btn-emo btn-positive'><input type='radio' name='options' id='positive'><i class='fa fa-smile-o'></i>Positive</label><label class='btn btn-app btn-emo btn-neutral'><input type='radio' name='options' id='neutral'><i class='fa fa-meh-o'></i>Neutral</label><label class='btn btn-app btn-emo btn-negative'><input type='radio' name='options' id='negative'><i class='fa fa-frown-o'></i>Negative</label></div></center></div>");

																});

												//owl-carousel
												$("#owl-demo")
														.owlCarousel(
																{
																	navigation : true,
																	paginationSpeed : 1000,
																	goToFirstSpeed : 2000,
																	singleItem : true,
																	autoHeight : true,
																	transitionStyle : "fade",
																	navigationText : [
																			"prev item",
																			"next item" ]
																});

												//set btn-next disabled until a msg be fully annotated
												$("#btn-next").prop('disabled',
														true);

												//click event to validate if all msg item are annotated
												$("#owl-demo")
														.find("label")
														.mouseup(
																function() {
																	setTimeout(
																			function() {

																				var select_num = $(
																						"#owl-demo")
																						.find(
																								"label.active").length;
																				var all_num = $(
																						"#owl-demo")
																						.find(
																								".btn-group").length;
																				if (all_num == select_num) {
																					//alert("all selected!");
																					$(
																							"#btn-next")
																							.prop(
																									'disabled',
																									false);
																				}

																			},
																			30);

																});

											}
										});

							});

			//Operations when click on skip button
			$("#btn-skip")
					.click(
							function() {
								//alert("skip event!");

								//get annotation language list
								var annotation_lang_array = new Array();
								$("#annotation-lang option").each(function() {
									if (this.selected) {
										annotation_lang_array.push(this.value);
									}
								});
								var annotation_lang = annotation_lang_array
										.toString();

								//get annotation part option
								annotation_part = $(
										'input[type="radio"][name="annotation-part"]:checked')
										.val();

								//get new annotated msg through ajax
								$
										.ajax({
											type : "GET",
											url : "annotation/getnewmessage",
											data : {
												user_email : "huiwen.hong@gmial.com",
												languages : annotation_lang,
												annotate_part : annotation_part
											},
											dataType : "json",
											success : function(data, textStatus) {
												msg_id = data.msg_id;
												//alert("msg_id:"+msg_id);
												//clear html element
												$("#annotation").empty();
												//append new msg to html element
												$("#annotation")
														.append(
																"<div id='owl-demo' class='owl-carousel'></div>");
												$("#owl-demo")
														.append(
																"<div><center><p id='msg-text'>"
																		+ data.text
																		+ "</p></center><center><div class='btn-group' id='text-btn-group' data-toggle='buttons'><label class='btn btn-app btn-emo btn-positive'><input type='radio' name='options' id='positive'><i class='fa fa-smile-o'></i>Positive</label><label class='btn btn-app btn-emo btn-neutral'><input type='radio' name='options' id='neutral'><i class='fa fa-meh-o'></i>Neutral</label><label class='btn btn-app btn-emo btn-negative'><input type='radio' name='options' id='negative'><i class='fa fa-frown-o'></i>Negative</label></div></center></div>");
												$
														.each(
																data.medias,
																function() {
																	$(
																			"#owl-demo")
																			.append(
																					"<div><img class='col-md-12' src='"+this.media_url+"' /><center><div class='btn-group img-btn-group'  data-toggle='buttons'><label class='btn btn-app btn-emo btn-positive'><input type='radio' name='options' id='positive'><i class='fa fa-smile-o'></i>Positive</label><label class='btn btn-app btn-emo btn-neutral'><input type='radio' name='options' id='neutral'><i class='fa fa-meh-o'></i>Neutral</label><label class='btn btn-app btn-emo btn-negative'><input type='radio' name='options' id='negative'><i class='fa fa-frown-o'></i>Negative</label></div></center></div>");

																});

												//owl-carousel
												$("#owl-demo")
														.owlCarousel(
																{
																	navigation : true,
																	paginationSpeed : 1000,
																	goToFirstSpeed : 2000,
																	singleItem : true,
																	autoHeight : true,
																	transitionStyle : "fade",
																	navigationText : [
																			"prev item",
																			"next item" ]
																});

												//set btn-next disabled until a msg be fully annotated
												$("#btn-next").prop('disabled',
														true);

												//click event to validate if all msg item are annotated
												$("#owl-demo")
														.find("label")
														.mouseup(
																function() {
																	setTimeout(
																			function() {

																				var select_num = $(
																						"#owl-demo")
																						.find(
																								"label.active").length;
																				var all_num = $(
																						"#owl-demo")
																						.find(
																								".btn-group").length;
																				if (all_num == select_num) {
																					//alert("all selected!");
																					$(
																							"#btn-next")
																							.prop(
																									'disabled',
																									false);
																				}

																			},
																			30);

																});

											}
										});

							});

			//Operations when click on next button
			$("#btn-next")
					.click(
							function() {
								//alert("next event!");
								//get annotated data
								var text_emotion = $("#text-btn-group").find(
										"label.active input").attr("id");
								//alert(text_emotion);
								/* alert($(".img-btn-group").find(
										"label.active input").length); */
								var media_emotion_array = new Array();
								$.each($(".img-btn-group").find(
										"label.active input"), function() {
									media_emotion_array
											.push($(this).attr("id"));
									//alert($(this).attr("id"));
								});
								var media_emotions = media_emotion_array
										.toString();
								//alert(media_emotions);

								//send annotated data to server
								$
										.ajax({
											type : "POST",
											url : "annotation/sendannotatedmessage",
											data : {
												user_email : "temp",
												msg_id : msg_id,
												text_emotion : text_emotion,
												media_emotions : media_emotions
											},
											dataType : "json",
											success : function(data, textStatus) {
												counter = counter + 1;
												$("#this-counter")
														.html(counter);
												//alert(data.code);
												//get annotation language list
												var annotation_lang_array = new Array();
												$("#annotation-lang option")
														.each(
																function() {
																	if (this.selected) {
																		annotation_lang_array
																				.push(this.value);
																	}
																});
												var annotation_lang = annotation_lang_array
														.toString();

												//get annotation part option
												annotation_part = $(
														'input[type="radio"][name="annotation-part"]:checked')
														.val();

												//get new annotated msg through ajax
												$
														.ajax({
															type : "GET",
															url : "annotation/getnewmessage",
															data : {
																user_email : "huiwen.hong@gmial.com",
																languages : annotation_lang,
																annotate_part : annotation_part
															},
															dataType : "json",
															success : function(
																	data,
																	textStatus) {
																msg_id = data.msg_id;
																//alert("msg_id:"+msg_id);

																//clear html element
																$("#annotation")
																		.empty();
																//append new msg to html element
																$("#annotation")
																		.append(
																				"<div id='owl-demo' class='owl-carousel'></div>");
																$("#owl-demo")
																		.append(
																				"<div><center><p id='msg-text'>"
																						+ data.text
																						+ "</p></center><center><div class='btn-group' id='text-btn-group' data-toggle='buttons'><label class='btn btn-app btn-emo btn-positive'><input type='radio' name='options' id='positive'><i class='fa fa-smile-o'></i>Positive</label><label class='btn btn-app btn-emo btn-neutral'><input type='radio' name='options' id='neutral'><i class='fa fa-meh-o'></i>Neutral</label><label class='btn btn-app btn-emo btn-negative'><input type='radio' name='options' id='negative'><i class='fa fa-frown-o'></i>Negative</label></div></center></div>");
																$
																		.each(
																				data.medias,
																				function() {
																					$(
																							"#owl-demo")
																							.append(
																									"<div><img class='col-md-12' src='"+this.media_url+"' /><center><div class='btn-group img-btn-group'  data-toggle='buttons'><label class='btn btn-app btn-emo btn-positive'><input type='radio' name='options' id='positive'><i class='fa fa-smile-o'></i>Positive</label><label class='btn btn-app btn-emo btn-neutral'><input type='radio' name='options' id='neutral'><i class='fa fa-meh-o'></i>Neutral</label><label class='btn btn-app btn-emo btn-negative'><input type='radio' name='options' id='negative'><i class='fa fa-frown-o'></i>Negative</label></div></center></div>");

																				});

																//owl-carousel
																$("#owl-demo")
																		.owlCarousel(
																				{
																					navigation : true,
																					paginationSpeed : 1000,
																					goToFirstSpeed : 2000,
																					singleItem : true,
																					autoHeight : true,
																					transitionStyle : "fade",
																					navigationText : [
																							"prev item",
																							"next item" ]
																				});

																//set btn-next disabled until a msg be fully annotated
																$("#btn-next")
																		.prop(
																				'disabled',
																				true);
																//click event to validate if all msg item are annotated
																$("#owl-demo")
																		.find(
																				"label")
																		.mouseup(
																				function() {
																					setTimeout(
																							function() {

																								var select_num = $(
																										"#owl-demo")
																										.find(
																												"label.active").length;
																								var all_num = $(
																										"#owl-demo")
																										.find(
																												".btn-group").length;
																								if (all_num == select_num) {
																									//alert("all selected!");
																									$(
																											"#btn-next")
																											.prop(
																													'disabled',
																													false);
																								}

																							},
																							30);

																				});

															}
														});
											}
										});

							});

		});
	</script>


	<!-- Optionally, you can add Slimscroll and FastClick plugins.
         Both of these plugins are recommended to enhance the
         user experience. Slimscroll is required when using the
         fixed layout. -->
</body>
</html>
