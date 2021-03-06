<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Digital City Impulse | Registration</title>
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
<!-- iCheck -->
<link rel="stylesheet" href="resources/plugins/iCheck/square/blue.css">
<style>
.error-list {
	list-style-type: none;
	padding: 0px;
	margin: 0px;
}
</style>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>
<body class="hold-transition register-page container">
	<div class="register-box">
		<div class="register-logo">
			<a href="#"><b>Digital </b>City Impulse</a>
		</div>

		<div class="register-box-body">
			<p class="login-box-msg">Register a new account</p>
			<form action="/" method="POST" enctype="utf8">
				<div class="form-group has-feedback">
					<label class="control-label sr-only" for="firstName">First
						Name</label> <input type="text" class="form-control" id="firstName"
						placeholder="First name" name="firstName" value="" required
						aria-describedby="firstNameError"> <span
						class="glyphicon glyphicon-user form-control-feedback"></span> <span
						id="firstNameError" class="help-block"></span>
				</div>
				<div class="form-group has-feedback">
					<label class="control-label sr-only" for="lastName">Last
						Name</label> <input type="text" class="form-control" id="lastName"
						placeholder="Last name" name="lastName" value="" required
						aria-describedby="lastNameError"> <span
						class="glyphicon glyphicon-user form-control-feedback"></span> <span
						id="lastNameError" class="help-block"></span>
				</div>
				<div class="form-group has-feedback">
					<label class="control-label sr-only" for="email">Email
						Address</label> <input type="email" class="form-control" id="email"
						placeholder="Email" name="email" value="" required
						aria-describedby="emailError"> <span
						class="glyphicon glyphicon-envelope form-control-feedback"></span>
					<span id="emailError" class="help-block"></span>
				</div>
				<div class="form-group has-feedback">
					<label class="control-label sr-only" for="password">Password
					</label> <input type="password" class="form-control"
						placeholder="Password ( min 8 , a-z&0-9 )" name="password"
						value="" id="password" required aria-describedby="passwordError">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span><span
						id="passwordError" class="help-block"></span>
				</div>
				<div class="form-group has-feedback">
					<label class="control-label sr-only" for="matchingPassword">Retype
						Password </label> <input type="password" class="form-control"
						placeholder="Retype password" name="matchingPassword" value=""
						id="matchPassword" required aria-describedby="globalError">
					<span class="glyphicon glyphicon-log-in form-control-feedback"></span>
					<span id="globalError" class="help-block"></span>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<!-- 						<div class="checkbox icheck">
							<label> <input type="checkbox"> I agree to the <a
								href="#">terms</a>
							</label>
						</div> -->
					</div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat"
							id="submit">Register</button>
					</div>
					<!-- /.col -->
				</div>
			</form>


			<a href="login.html" class="text-center">I already have an
				account</a>
		</div>
		<!-- /.form-box -->
	</div>
	<!-- /.register-box -->

	<!-- jQuery 2.1.4 -->
	<script src="resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
	<!-- iCheck 1.0.1 -->
	<script src="resources/plugins/iCheck/icheck.min.js"></script>
	<script src="<c:url value="/resources/pwstrength.js" />"></script>
	<script>
		$(function() {
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' // optional
			});

			$('form').submit(function(event) {
				register(event);
			});

			$(":password")
					.keyup(
							function() {
								if ($("#password").val() != $("#matchPassword")
										.val()) {
									$("#globalError")
											.show()
											.html(
													'<spring:message code="PasswordMatches.user"></spring:message>');
								} else {
									$("#globalError").html("").hide();
								}
							});

			options = {
				common : {
					minChar : 8,
					usernameField : "firstName"
				},
				ui : {
					showVerdictsInsideProgressBar : true,
					showErrors : true,
					errorMessages : {
						wordLength : '<spring:message code="error.wordLength"/>',
						wordNotEmail : '<spring:message code="error.wordNotEmail"/>',
						wordLowercase : '<spring:message code="error.wordLowercase"/>',
						wordOneNumber : '<spring:message code="error.wordOneNumber"/>',
						wordLetterNumberCombo : '<spring:message code="error.wordLetterNumberCombo"/>'
					}
				},
				rules : {
					scores : {
						wordNotEmail : -100,
						wordLength : -50,
						wordSimilarToUsername : -50,
						wordSequences : 0,
						wordTwoCharacterClasses : 2,
						wordRepetitions : -6,
						wordLowercase : 5,
						wordUppercase : 3,
						wordOneNumber : 3,
						wordThreeNumbers : 5,
						wordOneSpecialChar : 3,
						wordTwoSpecialChar : 5,
						wordUpperLowerCombo : 2,
						wordLetterNumberCombo : 3,
						wordLetterNumberCharCombo : 2
					},
					activated : {
						wordNotEmail : true,
						wordLength : true,
						wordSimilarToUsername : true,
						wordSequences : false,
						wordTwoCharacterClasses : false,
						wordRepetitions : false,
						wordLowercase : true,
						wordUppercase : false,
						wordOneNumber : true,
						wordThreeNumbers : false,
						wordOneSpecialChar : false,
						wordTwoSpecialChar : false,
						wordUpperLowerCombo : false,
						wordLetterNumberCombo : true,
						wordLetterNumberCharCombo : false
					}
				}
			};
			$('#password').pwstrength(options);

		});

		function register(event) {
			event.preventDefault();
			$(".alert").html("").hide();
			$(".error-list").html("");
			if ($("#password").val() != $("#matchPassword").val()) {
				$("#globalError")
						.show()
						.html(
								'<spring:message code="PasswordMatches.user"></spring:message>');
				return;
			}
			var formData = $('form').serialize();
			/* $
					.post(
							"<c:url value="/user/registration"/>",
							formData,
							function(data) {
								if (data.message == "success") {
									window.location.href = "<c:url value="/successRegister.html"></c:url>";

								}

							})
					.fail(
							function(data) {
								//alert(data.responseJSON.error);
								if (data.responseJSON.error
										.indexOf("MailError") > -1) {
									window.location.href = "<c:url value="/emailError.html"></c:url>";
								} else if (data.responseJSON.error == "UserAlreadyExist") {
									$("#emailError").show().html(
											data.responseJSON.message);
								} else if (data.responseJSON.error
										.indexOf("InternalError") > -1) {
									window.location.href = "<c:url value="/login.html"></c:url>"
											+ "?message="
											+ data.responseJSON.message;
								} else {
									var errors = $
											.parseJSON(data.responseJSON.message);
									$.each(errors, function(index, item) {
										$("#" + item.field + "Error").show()
												.html(item.defaultMessage);
									});
									errors = $
											.parseJSON(data.responseJSON.error);
									$.each(errors, function(index, item) {
										$("#globalError").show().append(
												item.defaultMessage + "<br>");
									});
								}
							}); */

			$
					.ajax({
						type : "POST",
						url : "<c:url value='/user/registration'/>",
						data : formData,
						beforeSend : function() {
							//alert("click on submit!");
							$("title").html("Uploading Registration Info ...");
							$("#submit").attr("disabled", "true");
							$("#submit").html("");
							$("#submit")
									.append(
											"<i class='fa fa-refresh fa-spin text-muted'></i>");
						},
						success : function(data) {
							if (data.message == "success") {
								//alert("success!");
								window.location.href = "<c:url value="/successRegister.html"></c:url>";
							}
						},
						error : function(data) {
							$("#submit").html("Register");
							$("#submit").attr("disabled", "false");
							//alert(data.responseJSON.error);
							if (data.responseJSON.error.indexOf("MailError") > -1) {
								window.location.href = "<c:url value="/emailError.html"></c:url>";
							} else if (data.responseJSON.error == "UserAlreadyExist") {
								$("#emailError").show().html(
										data.responseJSON.message);
							} else if (data.responseJSON.error
									.indexOf("InternalError") > -1) {
								window.location.href = "<c:url value="/login.html"></c:url>"
										+ "?message="
										+ data.responseJSON.message;
							} else {
								var errors = $
										.parseJSON(data.responseJSON.message);
								$.each(errors, function(index, item) {
									$("#" + item.field + "Error").show().html(

									item.defaultMessage);
								});
								errors = $.parseJSON(data.responseJSON.error);
								$.each(errors, function(index, item) {
									$("#globalError").show().append(
											item.defaultMessage + "<br>");
								});
							}
						}

					});
		}
	</script>
</body>
</html>
