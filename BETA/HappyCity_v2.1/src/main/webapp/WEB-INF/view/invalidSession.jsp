<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="messages" />
<%@ page session="true"%>
<fmt:message key="message.password" var="noPass" />
<fmt:message key="message.username" var="noUser" />
<c:if test="${param.error != null}">
	<c:choose>
		<c:when
			test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'User is disabled'}">
			<div class="alert alert-danger">
				<spring:message code="auth.message.disabled"></spring:message>
			</div>
		</c:when>
		<c:when
			test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'User account has expired'}">
			<div class="alert alert-danger">
				<spring:message code="auth.message.expired"></spring:message>
			</div>
		</c:when>
		<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'blocked'}">
			<div class="alert alert-danger">
				<spring:message code="auth.message.blocked"></spring:message>
			</div>
		</c:when>
		<c:otherwise>
			<div class="alert alert-danger">
				<!-- <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/> -->
				<spring:message code="message.badCredentials"></spring:message>
			</div>
		</c:otherwise>
	</c:choose>
</c:if>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Digital City Impulse | Session Timeout</title>
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
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script type="text/javascript">
	function validate() {
		if (document.f.j_username.value == ""
				&& document.f.j_password.value == "") {
			alert("${noUser} & ${noPass}");
			document.f.j_username.focus();
			return false;
		}
		if (document.f.j_username.value == "") {
			alert("${noUser}");
			document.f.j_username.focus();
			return false;
		}
		if (document.f.j_password.value == "") {
			alert("${noPass}");
			document.f.j_password.focus();
			return false;
		}
	}
</script>
</head>
<body class="hold-transition login-page">

	<div class="alert alert-danger alert-dismissible" >
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">×</button>
		<center><h4>
			<i class="icon fa fa-ban"></i>
			<spring:message code="message.sessionExpired"></spring:message>
		</h4></center>

	</div>

	<div class="login-box">
		<div class="login-logo">
			<a href="#"><b>Digital </b>City Impulse</a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">Sign in</p>
			<form action="j_spring_security_check" method="post"
				onsubmit="return validate();">
				<div class="form-group has-feedback">
					<input type="email" class="form-control" placeholder="Email"
						name='j_username' value=''> <span
						class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" placeholder="Password"
						name='j_password'> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-8"></div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button name="submit" type="submit"
							class="btn btn-primary btn-block btn-flat">Sign In</button>
					</div>
					<!-- /.col -->
				</div>
			</form>


			<a href="<c:url value="/forgetPassword.html" />">Forget your
				password?</a><br> <a href="<c:url value="registration.html" />"
				class="text-center">Register for a new account</a>

		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<!-- jQuery 2.1.4 -->
	<script src="resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
	<!-- iCheck 1.0.1 -->
	<script src="resources/plugins/iCheck/icheck.min.js"></script>
	<script>
		$(function() {
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' // optional
			});
		});
	</script>
</body>
</html>
