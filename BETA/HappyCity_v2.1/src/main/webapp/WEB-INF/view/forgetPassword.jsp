<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Digital City Impulse | Forget password</title>
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
<style>
.lockscreen-wrapper>.overlay, .overlay-wrapper>.overlay,
	.lockscreen-wrapper>.loading-img, .overlay-wrapper>.loading-img {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}

.lockscreen-wrapper .overlay, .overlay-wrapper .overlay {
	z-index: 50;
	background: rgba(255, 255, 255, 0.7);
	border-radius: 3px;
}

.lockscreen-wrapper .overlay>.fa, .overlay-wrapper .overlay>.fa {
	position: absolute;
	top: 50%;
	left: 50%;
	margin-left: -15px;
	margin-top: -15px;
	color: #000;
	font-size: 30px;
}

.lockscreen-wrapper .overlay.dark, .overlay-wrapper .overlay.dark {
	background: rgba(0, 0, 0, 0.5);
}
</style>

</head>

<body class="hold-transition lockscreen">
	<div class="alert alert-danger alert-dismissible" id="mailerror"
		style="display: none;">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">×</button>
		<center>
			<h4>
				<i class="icon fa fa-ban"></i>
				<spring:message code="message.email.config.error"></spring:message>
			</h4>
		</center>

	</div>
	<!-- Automatic element centering -->
	<div class="lockscreen-wrapper">
		<div class="lockscreen-logo">
			<a href="#"><b>Digital </b>City Impulse</a>
		</div>
		<!-- User name -->
		<div class="lockscreen-name">Forget Password</div>

		<!-- START LOCK SCREEN ITEM -->
		<div class="lockscreen-item">
			<!-- lockscreen image -->
			<div class="lockscreen-image">
				<img src="resources/img/email_button.png" alt="User Image">
			</div>
			<!-- /.lockscreen-image -->

			<!-- lockscreen credentials (contains the form) -->
			<div class="lockscreen-credentials">
				<div class="input-group">
					<input class="form-control" id="email" name="email" type="email"
						value="" placeholder="Email">
					<div class="input-group-btn">
						<button class="btn" name="submit" type="submit" id="submit"
							onclick="resetPass()">
							<i class="fa fa-arrow-right text-muted" id="arrow"></i>
						</button>
					</div>
				</div>
			</div>
			<!-- /.lockscreen credentials -->

		</div>
		<!-- /.lockscreen-item -->
		<div class="help-block text-center">Enter the email address you
			used for registration to get an validation email.</div>
		<div class="text-center">
			<a href="<c:url value="login.html" />">Or sign in as a different
				user</a>
		</div>
	</div>


	<!-- jQuery 2.1.4 -->
	<script src="resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>

	<script type="text/javascript">
		function resetPass() {
			var email = $("#email").val();

			$
					.ajax({
						type : "POST",
						url : "<c:url value="/user/resetPassword"></c:url>",
						data : {
							email : email
						},
						beforeSend : function() {
							//alert("click on submit!");
							$("title").html("LOADING ...");
							$(".input-group-btn").addClass("overlay");
							$("#arrow").removeClass("fa-arrow-right");
							$("#arrow").addClass("fa-refresh fa-spin");
							$("#arrow").attr("disabled", "true");
						},
						success : function(data) {
							window.location.href = "<c:url value="/login.html"></c:url>"
									+ "?message=" + data.message;
						},
						error : function(data) {
							$("title").html(
									"Digital City Impulse | Forget password");
							$(".input-group-btn").removeClass("overlay");
							$("#arrow").addClass("fa-arrow-right");
							$("#arrow").removeClass("fa-refresh fa-spin");
							$("#arrow").attr("disabled", "false");
							//alert(data.responseJSON.error);
							if (data.responseJSON.error.indexOf("MailError") > -1) {
								//window.location.href = "<c:url value="/emailError.html"></c:url>";
								$("#mailerror").css("display", "block");
							} else {
								window.location.href = "<c:url value="/login.html"></c:url>"
										+ "?message="
										+ data.responseJSON.message;
							}
						}

					});

			/* 			$
			 .post(
			 "<c:url value="/user/resetPassword"></c:url>",
			 {
			 email : email
			 },
			 function(data) {
			 window.location.href = "<c:url value="/login.html"></c:url>"
			 + "?message=" + data.message;
			 })
			 .fail(
			 function(data) {
			 if (data.responseJSON.error
			 .indexOf("MailError") > -1) {
			 window.location.href = "<c:url value="/emailError.html"></c:url>";
			 } else {
			 window.location.href = "<c:url value="/login.html"></c:url>"
			 + "?message="
			 + data.responseJSON.message;
			 }
			 }); */
		}

		/* 		$(document).ajaxStart(function() {
		 $("title").html("LOADING ...");
		 $(".input-group-btn").addClass("overlay");
		 $("#arrow").removeClass("fa-arrow-right");
		 $("#arrow").addClass("fa-refresh fa-spin");
		 }); */
	</script>

</body>
</html>
