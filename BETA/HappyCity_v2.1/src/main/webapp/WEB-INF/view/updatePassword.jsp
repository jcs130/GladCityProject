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
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Digital City Impulse | Reset Password</title>
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


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
.error-list {
	list-style-type: none;
	padding: 0px;
	margin: 0px;
}
</style>
</head>
>
<body class="hold-transition register-page container">
	<div class="register-box">
		<div class="register-logo">
			<a href="#"><b>Digital </b>City Impulse</a>
		</div>

		<div class="register-box-body">
			<p class="login-box-msg">Reset Password</p>
			<div>
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
					<div class="col-xs-8"></div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button type="submit" onclick="savePass()" id="submit"
							class="btn btn-primary btn-block btn-flat">Reset</button>
					</div>
					<!-- /.col -->
				</div>
			</div>



		</div>
		<!-- /.form-box -->
	</div>
	<!-- /.register-box -->

	<!-- jQuery 2.1.4 -->
	<script src="resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="<c:url value="/resources/pwstrength.js" />"></script>
	<script>
		$(function() {

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
					},
					rules : {
						activated : {
							wordNotEmail : false,
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
				}
			};
			$('#password').pwstrength(options);

		});

		function savePass() {
			var pass = $("#password").val();
			var valid = pass == $("#matchPassword").val();
			if (!valid) {
				$("#globalError").show();
				return;
			}

			$
					.ajax({
						type : "POST",
						url : "<c:url value="/user/savePassword"></c:url>",
						data : {
							password : pass
						},
						beforeSend : function() {
							//alert("click on submit!");
							$("title").html("Resetting password ...");
							$("#submit").attr("disabled", "true");
							$("#submit").html("");
							$("#submit")
									.append(
											"<i class='fa fa-refresh fa-spin text-muted'></i>");
						},
						success : function(data) {
							window.location.href = "<c:url value="/login.html"></c:url>"
									+ "?message=" + data.message;
						},
						error : function(data) {
							window.location.href = "<c:url value="/login.html"></c:url>"
									+ "?message=" + data.responseJSON.message;
						}

					});
			/* 
			 $
			 .post(
			 "<c:url value="/user/savePassword"></c:url>",
			 {
			 password : pass
			 },
			 function(data) {
			 window.location.href = "<c:url value="/login.html"></c:url>"
			 + "?message=" + data.message;
			 })
			 .fail(
			 function(data) {
			 window.location.href = "<c:url value="/login.html"></c:url>"
			 + "?message="
			 + data.responseJSON.message;
			 }); */
		}
	</script>
</body>
</html>
