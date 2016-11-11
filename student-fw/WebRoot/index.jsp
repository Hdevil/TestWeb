<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="s" uri="/struts-tags" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String reg_res = request.getParameter("reg_res");
	String log_res = request.getParameter("log_res");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>登录页面</title>

<meta name="description" content="User login page" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="/student-fw/assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="/student-fw/assets/font-awesome/4.2.0/css/font-awesome.min.css" />

<!-- text fonts -->
<link rel="stylesheet" href="/student-fw/assets/fonts/fonts.googleapis.com.css" />

<!-- ace styles -->
<link rel="stylesheet" href="/student-fw/assets/css/ace.min.css" />

<!--[if lte IE 9]>
			<link rel="stylesheet" href="/student-fw/assets/css/ace-part2.min.css" />
		<![endif]-->
<link rel="stylesheet" href="/student-fw/assets/css/ace-rtl.min.css" />
<!--
<link rel="stylesheet" type="text/css"
	href="/student-fw/assets/css/bootstrapValidator.css">
	-->
<!--[if lte IE 9]>
		  <link rel="stylesheet" href="/student-fw/assets/css/ace-ie.min.css" />
		<![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
		<script src="/student-fw/assets/js/html5shiv.min.js"></script>
		<script src="/student-fw/assets/js/respond.min.js"></script>
		
		<![endif]-->


</head>

<body class="login-layout">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>
								<i class="ace-icon fa fa-leaf green"></i> <span class="red">Ace</span>
								<span class="white" id="id-text2">应用管理</span>
							</h1>
							<h4 class="blue" id="id-company-text">&copy; 公司名称</h4>
						</div>

						<div class="space-6"></div>

						<div class="position-relative">
							<div id="login-box"
								class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<s:debug></s:debug>
										<s:if test="#parameters.reg_res!=null && #parameters.reg_res[0]==0">
										<%--  
										<%
											if (reg_res != null && reg_res.equals("0")) {
										%> --%>
										<div id="reg_error" class="alert alert-success">
											<a class="close" data-dismiss="alert">×</a> <strong>
												注册成功 </strong>注册成功请登录！
										</div>
										<%-- 
										<%
											}
										%> --%>
										</s:if>
										<s:if test="#parameters.log_res!=null && #parameters.log_res[0]==1">
										<%-- 
										<%
											if (log_res != null && log_res.equals("1")) {
										%>--%>
										<div id="reg_error" class="alert alert-danger">
											<a class="close" data-dismiss="alert">×</a> <strong>
												登录失败 </strong>该用户不存在！
										</div>
										<%-- 
										<%
											}
										%>
										--%>
										</s:if>
										<s:elseif test="#parameters.log_res!=null && #parameters.log_res[0]==2">
										<%-- 
										<%
											if (log_res != null && log_res.equals("2")) {
										%>
										--%>
										<div id="reg_error" class="alert alert-danger">
											<a class="close" data-dismiss="alert">×</a> <strong>
												登录失败 </strong>用户密码不正确！
										</div>
										<%--
										<%
											}
										%>
										--%>
										</s:elseif>
										<s:elseif test="#parameters.log_res!=null && #parameters.log_res[0]==3">
										<%--
										<%
											if (log_res != null && log_res.equals("3")) {
										%>
										--%>
										<div id="reg_error" class="alert alert-danger">
											<a class="close" data-dismiss="alert">×</a> <strong>
												登录失败 </strong>你已列入黑名单！
										</div>
										<%--
										<%
											}
										%>--%>
										</s:elseif>
										<h4 class="header blue lighter bigger">
											<i class="ace-icon fa fa-coffee green"></i> 请输入您的信息
										</h4>

										<div class="space-6"></div>

										<form id="login" method="post" action="/student-fw/login/loginAction.action">
											<fieldset>
											    <div class="form-group">
												<label class="block clearfix"> <span
													class="block input-icon input-icon-light"> <input
														name="user.name" type="text" class="form-control"
														placeholder="Username" /> 
														<s:fielderror fieldName="user.name" cssStyle="color:red" />
														<i class="ace-icon fa fa-user"></i>
												</span>
												</label> 
												</div>
												 <div class="form-group">
												<label class="block clearfix"> <span
													class="block input-icon input-icon-light"> <input
														name="user.password" type="password" class="form-control"
														placeholder="Password" /> 
														<s:fielderror fieldName="user.password" cssStyle="color:red"/>
														<i class="ace-icon fa fa-lock"></i>
												</span>
												</label>
												</div>
												<div class="space"></div>

												<div class="clearfix">
													<label class="inline"> <input type="checkbox"
														class="ace" /> <span><input class="lbl"
															type="checkbox" name="auto_load"  value="1"/>自动登录</span>
													</label>

													<button type="submit"
														class="width-35 pull-right btn btn-sm btn-primary">
														<i class="ace-icon fa fa-key"></i> <span
															class="bigger-110">登录</span>
													</button>
												</div>

												<div class="space-4"></div>
											</fieldset>
										</form>

										<div class="social-or-login center">
											<span class="bigger-110">第三方登录</span>
										</div>

										<div class="space-6"></div>

										<div class="social-login center">
											<a class="btn btn-primary"> <i
												class="ace-icon fa fa-facebook"></i>
											</a> <a class="btn btn-info"> <i
												class="ace-icon fa fa-twitter"></i>
											</a> <a class="btn btn-danger"> <i
												class="ace-icon fa fa-google-plus"></i>
											</a>
										</div>
									</div>
									<!-- /.widget-main -->

									<div class="toolbar clearfix">
										<div>
											<a href="#" data-target="#forgot-box"
												class="forgot-password-link"> <i
												class="ace-icon fa fa-arrow-left"></i> 忘记密码
											</a>
										</div>

										<div>
											<a href="#" data-target="#signup-box"
												class="user-signup-link"> 注册 <i
												class="ace-icon fa fa-arrow-right"></i>
											</a>
										</div>
									</div>
								</div>
								<!-- /.widget-body -->
							</div>
							<!-- /.login-box -->

							<div id="forgot-box" class="forgot-box widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header red lighter bigger">
											<i class="ace-icon fa fa-key"></i> 找回密码
										</h4>

										<div class="space-6"></div>
										<p>输入您的电子邮件和接收指令</p>

										<form>
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-light"> <input
														type="email" class="form-control" placeholder="Email" />
														<i class="ace-icon fa fa-envelope"></i>
												</span>
												</label>

												<div class="clearfix">
													<button type="button"
														class="width-35 pull-right btn btn-sm btn-danger">
														<i class="ace-icon fa fa-lightbulb-o"></i> <span
															class="bigger-110">发送邮件</span>
													</button>
												</div>
											</fieldset>
										</form>
									</div>
									<!-- /.widget-main -->

									<div class="toolbar center">
										<a href="#" data-target="#login-box"
											class="back-to-login-link"> 返回登录 <i
											class="ace-icon fa fa-arrow-right"></i>
										</a>
									</div>
								</div>
								<!-- /.widget-body -->
							</div>
							<!-- /.forgot-box -->

							<div id="signup-box" class="signup-box widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<s:if test="#parameters.reg_res!=null && #parameters.reg_res[0]==1">
										<%-- <%
											if (reg_res != null && reg_res.equals("1")) {
										%> --%>
										<div id="reg_error" class="alert alert-danger">
											<a class="close" data-dismiss="alert">×</a> <strong>
												注册失败 </strong>该用户已经注册！
										</div>
										<%--
										<%
											}
										%>
										--%>
										</s:if>
										<s:elseif test="#parameters.reg_res!=null && #parameters.reg_res[0]==2">
										<%--
										<%
											if (reg_res != null && reg_res.equals("2")) {
										%>
										--%>
										<div id="reg_error" class="alert alert-danger">
											<a class="close" data-dismiss="alert">×</a> <strong>
												注册失败 </strong>！
										</div>
										<%--
										<%
											}
										%>
										--%>
										</s:elseif>
										<h4 class="header green lighter bigger">
											<i class="ace-icon fa fa-users blue"></i> 用户注册
										</h4>

										<div class="space-6"></div>
										<p>填写信息:</p>

										<form id="register" method="post"
											action="/student-fw/login/registerAction.action">
											<fieldset>
												<div class="form-group">
												<label class="block clearfix"> <span
													class="block input-icon input-icon-light"> <input
														name="email" type="email" class="form-control"
														placeholder="邮箱" value="<s:property value='email'/>" />
														<s:fielderror fieldName="email"/>
														 <i class="ace-icon fa fa-envelope"></i>
												</span>
												</label> 
												</div>
												<div class="form-group">
												<label class="block clearfix"> <span
													class="block input-icon input-icon-light"> <input
														name="username" type="text" class="form-control"
														placeholder="用户名" value="<s:property value='username'/>"/>
														<s:fielderror fieldName="username"/>
														 <i class="ace-icon fa fa-user"></i>
												</span>
												</label> 
												</div>
												<div class="form-group">
												<label class="block clearfix"> <span
													class="block input-icon input-icon-light"> <input
														name="password" type="password" class="form-control"
														placeholder="密码" />
														<s:fielderror fieldName="password"/>
														 <i class="ace-icon fa fa-lock"></i>
												</span>
												</label>
												</div>
												<div class="form-group">
												 <label class="block clearfix"> <span
													class="block input-icon input-icon-light"> <input
														name="password2" type="password" class="form-control"
														placeholder="确认密码" /> 
														<s:fielderror fieldName="password2"/>
														
														<i class="ace-icon fa fa-retweet"></i>
												</span>
												</label>
												</div>
												
												 <label class="block"> <input type="checkbox"
													class="ace" /> <span class="lbl"> 接受 <a href="#">用户协议</a>
												</span>
												</label>

												<div class="space-24"></div>

												<div class="clearfix">
													<button type="reset" class="width-30 pull-left btn btn-sm">
														<i class="ace-icon fa fa-refresh"></i> <span
															class="bigger-110">重置</span>
													</button>

													<button type="submit" class="width-65 pull-right btn btn-sm btn-success">
														<span class="bigger-110">注册</span> <i
															class="ace-icon fa fa-arrow-right icon-on-right"></i>
													</button>
												</div>
											</fieldset>
										</form>
									</div>

									<div class="toolbar center">
										<a href="#" data-target="#login-box"
											class="back-to-login-link"> <i
											class="ace-icon fa fa-arrow-left"></i> 返回登录
										</a>
									</div>
								</div>
								<!-- /.widget-body -->
							</div>
							<!-- /.signup-box -->
						</div>
						<!-- /.position-relative -->

						<div class="navbar-fixed-top align-right">
							<br /> &nbsp; <a id="btn-login-dark" href="#">Dark</a> &nbsp; <span
								class="blue">/</span> &nbsp; <a id="btn-login-blur" href="#">Blur</a>
							&nbsp; <span class="blue">/</span> &nbsp; <a id="btn-login-light"
								href="#">Light</a> &nbsp; &nbsp; &nbsp;
						</div>
					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.main-content -->
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script src="/student-fw/assets/js/jquery.2.1.1.min.js"></script>

	<!-- <![endif]-->

	<!--[if IE]>
<script src="/student-fw/assets/js/jquery.1.11.1.min.js"></script>
<![endif]-->

	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery
				|| document.write("<script src='/student-fw/assets/js/jquery.min.js'>"
						+ "<"+"/script>");
	</script>

	<!-- <![endif]-->

	<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='/student-fw/assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
	<script src="/student-fw/assets/js/jquery.min.js"></script>
	<script src="/student-fw/assets/js/bootstrap.min.js"></script>
	<!-- 
	<script src="/student-fw/assets/js/bootstrapValidator.js"></script>
	-->
	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='/student-fw/assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		jQuery(function($) {
			$(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			});
		});

		var res =
	<%=reg_res%>
		if (res == 1) {
			jQuery(function($) {
				$('.user-signup-link').click();
			});
		}

  if (res == 2) {
			jQuery(function($) {
				$('.user-signup-link').click();
			});
		}
		//you don't need this, just used for changing background
		jQuery(function($) {
			$('#btn-login-dark').on('click', function(e) {
				$('body').attr('class', 'login-layout');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'blue');

				e.preventDefault();
			});
			$('#btn-login-light').on('click', function(e) {
				$('body').attr('class', 'login-layout light-login');
				$('#id-text2').attr('class', 'grey');
				$('#id-company-text').attr('class', 'blue');

				e.preventDefault();
			});
			$('#btn-login-blur').on('click', function(e) {
				$('body').attr('class', 'login-layout blur-login');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'light-blue');

				e.preventDefault();
			});

		});

		/* function reg_submit() {
			document.getElementById("register").submit();
		}
		 */
	</script>
	<%--
	<script type="text/javascript">
	$(document).ready(function() {

		$('#login').bootstrapValidator({
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				Username : {
					group : '.form-group',
					validators : {
						notEmpty : {
							message : '用户名不能为空'
						}
					}
				},
				Password : {
					group : '.form-group',
					validators : {
						notEmpty : {
							message : '用户密码不能为空'
						},

					}
				},
				
			}
		});
		
		$('#register').bootstrapValidator({
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				email : {
					group : '.form-group',
					validators : {
						notEmpty : {
							message : '地址不能为空'
						},
						emailAddress: {
	                        message: '无效地址'
	                    }
					}
				},
				username : {
					group : '.form-group',
					validators : {
						notEmpty : {
							message : '用户名不能为空'
						},
						stringLength: {
	                        min: 6,
	                        max: 30,
	                        message: '6-30字符串'
	                    },
	                    regexp: {
	                        regexp: /^[a-zA-Z0-9_\.]+$/,
	                        message: '包含字母大小写数字'
	                    },

					}
				},
				password: {
	                validators: {
	                    notEmpty: {
	                        message: '密码不能为空'
	                    },
	                    identical: {
	                        field: 'password2',
	                        message: '和password2不一致'
	                    },
	                   
	                }
	            },
	            password2: {
	                validators: {
	                    notEmpty: {
	                        message: '密码不能为空'
	                    },
	                    identical: {
	                        field: 'password',
	                        message: '和password2不一致'
	                    },
	                   
	                }
	            },
			}
		});

	});
</script>


 --%>
</body>
</html>