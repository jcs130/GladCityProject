<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--  <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>-->
<!-- jQuery -->
<script src="resources/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="resources/js/bootstrap.min.js"></script>

<script src="resources/js/wow.min.js"></script>
<script type="text/javascript" src="resources\js\home.js"></script>

<!-- Scrolling Nav JavaScript -->
<script src="resources/js/jquery.easing.min.js"></script>
<script src="resources/js/scrolling-nav.js"></script>
<link
	href="resources/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" href="resources\css\animate.min.css">
<link rel="stylesheet" href="resources\css\c.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
#map-outer {
	height: 440px;
	padding: 20px;
	border: 2px solid #CCC;
	margin-bottom: 20px;
	background-color: #FFF
}

#map-container {
	height: 400px
}

@media all and (max-width: 991px) {
	#map-outer {
		height: 650px
	}
}
</style>
</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
	<section id="home">
		<div class="navbar navbar-default navbar-static-top">
			<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
				<div class="container">
					<div class="navbar-header page-scroll">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target=".navbar-ex1-collapse">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand"><span>uOttawa</span></a>
					</div>
					<div class="collapse navbar-collapse navbar-ex1-collapse">
						<ul class="nav navbar-nav navbar-right">
							<li><a class="page-scroll" href="#home">Home</a></li>
							<li><a class="page-scroll" href="#features">Features</a></li>
							<li><a class="page-scroll" href="#download">Mobile App</a></li>
							<li><a class="page-scroll" href="#contracts">Contacts</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>

		<div class="cover">
			<div class="cover-image"
				style="background-image: url('resources/img/home_background.jpg')"></div>
			<div class="container">
				<div class="row">
					<div class="col-md-12 text-center" data-wow-delay="0.6s">
						<h1 class="text-inverse">Glad City</h1>
						<p class="text-inverse">Is your city happy today?</p>
						<div class="row">
							<div class="col-md-12" data-wow-delay="0.6s">
								<div class="section">
									<div class="container">
										<div class="row">
											<div class="col-md-12" data-wow-delay="0.6s">
												<div id="citys-example" data-interval="3000"
													class="carousel hidden-lg hidden-md slide"
													data-ride="carousel">
													<div class="carousel-inner">
														<div class="item active">
															<img
																src="resources/img/london.jpg">
															<div class="carousel-caption">
																<h2>London</h2>
																<p></p>
															</div>
														</div>
														<div class="item">
															<img
																src="resources/img/NewYork.jpg">
															<div class="carousel-caption">
																<h2>New York</h2>
																<p></p>
															</div>
														</div>
														<div class="item">
															<img
																src="resources/img/ottawa.jpg">
															<div class="carousel-caption">
																<h2>Ottawa</h2>
															</div>
														</div>
													</div>
													<a class="left carousel-control" href="#citys-example"
														data-slide="prev"><i
														class="icon-prev  fa fa-angle-left"></i></a> <a
														class="right carousel-control" href="#citys-example"
														data-slide="next"><i
														class="icon-next fa fa-angle-right"></i></a>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12" data-wow-delay="0.6s">
												<div class="hidden-sm hidden-xs section">
													<div class="container">
														<div class="row">
															<div class="col-md-4" data-wow-delay="0.6s">
																<a href="#"><img
																	src="resources/img/london.jpg"
																	class="img-responsive img-thumbnail"></a>
																<p class="text-inverse">London</p>
															</div>
															<div class="col-md-4" data-wow-delay="0.6s">
																<a href="#"><img
																	src="resources/img/NewYork.jpg"
																	class="img-responsive img-thumbnail"></a>
																<p class="text-inverse">New York</p>
															</div>
															<div class="col-md-4" data-wow-delay="0.6s">
																<a href="index.html"><img
																	src="resources/img/ottawa.jpg"
																	class="img-responsive img-thumbnail"></a>
																<p class="text-inverse">Ottawa</p>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12" data-wow-delay="0.6s">
												<div class="section">
													<div class="container">
														<div class="row">
															<div class="col-md-5" data-wow-delay="0.6s">
																<div class="container">
																	<div class="row">
																		<div class="col-md-12" data-wow-delay="0.6s">
																			<a class="btn btn-primary" href="twitteronmap.html">Twitters on the Map</a>
																		</div>
																	</div>
																</div>
															</div>
															<div class="col-md-2" data-wow-delay="0.6s">
																<br>
															</div>
															<div class="col-md-5" data-wow-delay="0.6s">
																<div class="container">
																	<div class="row">
																		<div class="col-md-12" data-wow-delay="0.6s">
																			<a class="btn btn-primary">Explore by your own</a>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section id="features">
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-12 wow fadeInLeft" data-wow-delay="0.6s">
						<div class="text-primary" contenteditable="true">
							<h1>Features</h1>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 wow fadeInLeft" data-wow-delay="0.6s">
						<div id="featurespic" data-interval="false" class="carousel slide"
							data-ride="carousel">
							<div class="carousel-inner">
								<div class="item active">
									<img
										src="resources/img/feture1.jpg">
									<div class="carousel-caption">
										<h2>Twitter on Map</h2>
										<p>Show the live data on the map</p>
									</div>
								</div>
								<div class="item">
									<img
										src="resources/img/feture2.jpg">
									<div class="carousel-caption">
										<h2>Title</h2>
										<p>Description</p>
									</div>
								</div>
							</div>
							<a class="left carousel-control" href="#featurespic"
								data-slide="prev"><i class="icon-prev fa fa-angle-left"></i></a>
							<a class="right carousel-control" href="#featurespic"
								data-slide="next"><i class="icon-next fa fa-angle-right"></i></a>
						</div>
					</div>
					<div class="col-md-6 wow fadeInRight" data-wow-delay="0.6s">
						<h1 class="text-primary">A title</h1>
						<h3>A subtitle</h3>
						<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
							Aenean commodo ligula eget dolor. Aenean massa. Cum sociis
							natoque penatibus et magnis dis parturient montes, nascetur
							ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu,
							pretium quis, sem. Nulla consequat massa quis enim. Donec pede
							justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim
							justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam
							dictum felis eu pede mollis pretium. Integer tincidunt. Cras
							dapibus. Vivamus elementum semper nisi.</p>
					</div>
				</div>
			</div>
		</div>
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-6 wow fadeInLeft" data-wow-delay="0.6s">
						<h1 class="text-primary">Video Link</h1>
						<h3>CTV&nbsp;</h3>
						<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
							Aenean commodo ligula eget dolor. Aenean massa. Cum sociis
							natoque penatibus et magnis dis parturient montes, nascetur
							ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu,
							pretium quis, sem. Nulla consequat massa quis enim. Donec pede
							justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim
							justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam
							dictum felis eu pede mollis pretium. Integer tincidunt. Cras
							dapibus. Vivamus elementum semper nisi.</p>
					</div>
					<div class="col-md-6 wow fadeInRight" data-wow-delay="0.6s">
						<div class="embed-responsive embed-responsive-16by9">
							<iframe
								src="https://bmplayer-a.akamaihd.net/shareable/embedssl.html?dc=ctvnews_web&amp;cid=527873&amp;col=379&amp;w=480&amp;h=270&amp;pl=0&amp;plh=0&amp;adSite=ctv.ctvnewsedmonton&amp;adZone=latestnews&amp;omniAcct=ctvgmnews,ctvgmglobal&amp;section=Edmonton&amp;site=edmonton&amp;shareUrl=http://edmonton.ctvnews.ca/video?clipId=527873&amp;v7=video&amp;v8=home&amp;v9=&amp;v10="
								allowfullscreen="true" width="480" height="270" scrolling="no"
								frameborder="0" webkitallowfullscreen="true"
								mozallowfullscreen="true"></iframe>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- start download -->
	<section id="download">
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-12 wow fadeInLeft" data-wow-delay="0.6s">
						<div class="text-primary"></div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 wow fadeInLeft" data-wow-delay="0.6s">
						<h2 class="text-uppercase"></h2>
						<h1 class="text-primary">Download Mobile App</h1>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
							sed do eiusmod tempor incididunt ut labore et dolore magna
							aliqua. Ut enim ad minim veniam, quis nostrud exercitation.</p>
						<button class="btn btn-primary text-uppercase">
							<i class="fa fa-download"></i>Google Play
						</button>
						<button class="btn btn-primary text-uppercase">
							<i class="fa fa-download"></i>Apple Store
						</button>
					</div>
					<div class="col-md-6 wow fadeInRight" data-wow-delay="0.6s">
						<img
							src="resources/img/appcreen.jpg"
							class="img-responsive" alt="feature img">
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- end download -->

	<!-- start contact -->
	<section id="contracts">
		<div class="section">
			<div class="container">
				<div class="row">
					<div id="map-outer" class="col-md-12 wow fadeInLeft"
						data-wow-delay="0.6s">
						<div id="address" class="col-md-4 wow fadeInRight"
							data-wow-delay="0.6s">
							<h1 class="text-primary">
								Contract us <br>
							</h1>
							<address>
								<strong>Peggy Guggenheim Collection</strong> <br>Dorsoduro,
								701-704 <br>30123 <br>Venezia <br>Italia <br>
								<abbr>P:</abbr>+39 041 240 5411
							</address>
						</div>
						<div id="map-container" class="col-md-8 wow rollIn"
							data-wow-delay="0.6s"></div>
					</div>
					<!-- /map-outer -->
				</div>
				<!-- /row -->
				<div class="row">
					<form class="form-horizontal" name="commentform">
						<div class="form-group">
							<div class="col-md-4 wow bounceInLeft" data-wow-delay="0.6s">
								<input type="text" class="form-control" id="first_name"
									name="first_name" placeholder="First Name">
							</div>
							<div class="col-md-4 wow bounceInRight" data-wow-delay="0.6s">
								<input type="text" class="form-control" id="last_name"
									name="last_name" placeholder="Last Name">
							</div>
							<div class="col-md-4 input-group wow fadeInLeft"
								data-wow-delay="0.6s">
								<span class="input-group-addon">@</span> <input type="email"
									class="form-control" id="email" name="email"
									placeholder="Email Address">
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12 wow fadeInLeft" data-wow-delay="0.6s">
								<textarea rows="6" class="form-control" id="comments"
									name="comments" placeholder="Your question or comment here"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-6 wow fadeInRight" data-wow-delay="0.6s">
								<button type="submit" value="Submit"
									class="btn btn-warning pull-right">Send</button>
							</div>
						</div>
					</form>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->

			<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
			<script>
				function init_map() {
					var myLocation = new google.maps.LatLng(45.430817,
							12.331516);

					var mapOptions = {
						center : myLocation,
						zoom : 14
					};

					var marker = new google.maps.Marker({
						position : myLocation,
						title : "Peggy Guggenheim Collection"
					});

					var map = new google.maps.Map(document
							.getElementById("map-container"), mapOptions);

					marker.setMap(map);

				}

				google.maps.event.addDomListener(window, 'load', init_map);
			</script>
		</div>
	</section>
	<footer class="section section-primary">
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<h1>Footer header</h1>
					<p>
						Lorem ipsum dolor sit amet, consectetur adipisici elit, <br>sed
						eiusmod tempor incidunt ut labore et dolore magna aliqua. <br>Ut
						enim ad minim veniam, quis nostrud
					</p>
				</div>
				<div class="col-sm-6">
					<p class="text-info text-right">
						<br> <br>
					</p>
					<div class="row">
						<div class="col-md-12 hidden-lg hidden-md hidden-sm text-left"
							data-wow-delay="0.6s">
							<a href="#"><i
								class="fa fa-3x fa-fw fa-instagram text-inverse"></i></a> <a
								href="#"><i class="fa fa-3x fa-fw fa-twitter text-inverse"></i></a>
							<a href="#"><i
								class="fa fa-3x fa-fw fa-facebook text-inverse"></i></a> <a href="#"><i
								class="fa fa-3x fa-fw fa-github text-inverse"></i></a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 hidden-xs text-right" data-wow-delay="0.6s">
							<a href="#"><i
								class="fa fa-3x fa-fw fa-instagram text-inverse"></i></a> <a
								href="#"><i class="fa fa-3x fa-fw fa-twitter text-inverse"></i></a>
							<a href="#"><i
								class="fa fa-3x fa-fw fa-facebook text-inverse"></i></a> <a href="#"><i
								class="fa fa-3x fa-fw fa-github text-inverse"></i></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- end contact -->
</body>

</html>

