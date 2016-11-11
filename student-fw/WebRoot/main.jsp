<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta charset="UTF-8">
<title>学生管理系统</title>
<link rel="stylesheet" type="text/css"
	href="/student-fw/assets/css/bootstrap.min.css">
	<!-- 验证的jQuery  bootstrapValidator -->
<!--  <link rel="stylesheet" type="text/css"
	href="/student-fw/assets/css/bootstrapValidator.css">
	-->
<script src="/student-fw/assets/js/jquery.min.js"></script>
<script src="/student-fw/assets/js/bootstrap.min.js"></script>
<!--
<script src="/student-fw/assets/js/bootstrapValidator.js"></script>
-->
<script src="/student-fw/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/student-fw/sweetalert/dist/sweetalert.css">
<script type="text/javascript">
	$(document).ready(
			function() {
				$('tbody tr').click(function(e) {
					e.preventDefault();

					$('tbody tr').removeClass('success');
					$(this).addClass('success');
				});

				$('#btn_cls_del').click(
						function(e) {
							var clsname = $('a.active').text();
							swal({
								title : "删除班级",
								text : "你确定删除 " + clsname + "？",
								type : "warning",
								showCancelButton : true,
								confirmButtonColor : "#DD6B55",
								confirmButtonText : "是的, 删除!",
								cancelButtonText : "取消",
								closeOnConfirm : false
							}, function() {
								
								$('input#clsname').val("");
								$('input#clstname').val("");
								$('form#frm_cls').attr("action",
										"/student-fw/main/claszDel.action");
								$('form#frm_cls').submit();
							});
						});

				$('#btn_cls_add').click(
						function(e) {
							var studaddedit = $('h4#claszaddedit').text(
									"添加班级信息");

							$('input#clsId').val("");
							$('input#clsname').val("");
							$('input#clstname').val("");

							/* var action=$('form#frm_std').attr("action");
							 $('form#frm_std').attr("action",action+"?opt=add");*/
							$('form#frm_cls').attr("action",
									"/student-fw/main/claszAdd.action");
						});

				$('#btn_cls_edit').click(function(e) {
					var studaddedit = $('h4#claszaddedit').text("编辑班级信息");

					var clsname = $('a.active').text();
					var clstname = $('strong#clsTname').text();
					$('input#clsname').val(clsname);
					$('input#clstname').val(clstname);

					var action = $('form#frm_cls').attr("action");
					$('form#frm_cls').attr("action", "/student-fw/main/claszEdit.action");
				});

				$('#btn_std_del')
						.click(
								function(e) {
									var name = $('tr.success')
											.children("#name").text();
									var id = $('tr.success').children("#id")
											.text();
									swal({
										title : "删除学生",
										text : "你确定删除 " + name + "?",
										type : "warning",
										showCancelButton : true,
										confirmButtonColor : "#DD6B55",
										confirmButtonText : "是的, 删除它!",
										cancelButtonText : "取消",
										closeOnConfirm : false
									}, function() {
										$('input#studdId').val(id);

										$('form#frm_std_del').attr("action",
												"/student-fw/main/studDel.action");
										$('form#frm_std_del').submit();

									});
								});

				$('#btn_std_add').click(function(e) {
					var studaddedit = $('h4#studaddedit').text("添加学生信息");

					$('input#studId').val("");
					$('input#sname').val("");
					$('input#scode').val("");
					$('input#ssex_m').removeAttr("checked");
					$('input#ssex_w').removeAttr("checked");
					$('input#ssex_m').attr("checked");
					$('input#ssex_m').click();
					$('input#sbirth').val("");
					/* var action=$('form#frm_std').attr("action");
					 $('form#frm_std').attr("action",action+"?opt=add");*/
					$('form#frm_std').attr("action", "/student-fw/main/studAdd.action");
				});

				$('#btn_std_edit')
						.click(
								function(e) {
									var studaddedit = $('h4#studaddedit').text(
											"修改学生信息");

									var code = $('tr.success')
											.children("#code").text();
									var name = $('tr.success')
											.children("#name").text();
									var sex = $('tr.success').children("#sex")
											.text();
									var birth = $('tr.success').children(
											"#birth").text();
									var id = $('tr.success').children("#id")
											.text();
									$('input#sname').val(name);
									$('input#scode').val(code);
									$('input#ssex_m').removeAttr("checked");
									$('input#ssex_w').removeAttr("checked");
									if (sex == '男') {

										$('input#ssex_m').attr("checked",
												"checked");
										$('input#ssex_m').click();

									} else {
										$('input#ssex_w').attr("checked",
												"checked");
										$('input#ssex_w').click();
									}
									$('input#sbirth').val(birth);
									$('input#studId').val(id);

									$('form#frm_std').attr("action",
											"/student-fw/main/studEdit.action");
								});

			});
</script>
<%-- 
<script type="text/javascript">
	$(document).ready(function() {

		$('#frm_std').bootstrapValidator({
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				scode : {
					group : '.form-group',
					validators : {
						notEmpty : {
							message : '学生的编号不能为空'
						}
					}
				},
				sname : {
					group : '.form-group',
					validators : {
						notEmpty : {
							message : '学生的姓名不能为空'
						},

					}
				},
				sbirth : {
					group : '.form-group',
					validators : {
						notEmpty : {
							message : '学生的日期不能为空'
						},
						date : {
							format : 'YYYY-MM-DD',
							message : '日期无效（1996-05-05）'
						}
					}
				},
			}
		});

		$('#resetBtn').click(function() {
			$('#frm_std').data('bootstrapValidator').resetForm(true);
		});
		$('#frm_cls').bootstrapValidator({
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				clsname : {
					group : '.form-group',
					validators : {
						notEmpty : {
							message : '班级的名称不能为空'
						}
					}
				},
				clstname : {
					group : '.form-group',
					validators : {
						notEmpty : {
							message : '班级的老师名字不能为空'
						},

					}
				}
			}
		});

		$('#resetBtn1').click(function() {
			$('#frm_cls').data('bootstrapValidator').resetForm(true);
		});
	});
</script>

</head>
--%>
<body>
	<nav class="navbar navbar-default" style="height: 60px">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#"> <img alt="Brand"
				src="/student-fw/img/logo.png" height="35px" width="200px">
			</a>
		</div>
		<form method="post" action="<%=request.getContextPath() %>/main/exitAction.action">
		<button type="submit" class="btn btn-warning btn-xs navbar-right"
			style="margin-top: 20px; margin-right: 20px; margin-left: 20px">退出
		</button>
		</form>
		<h5 class="navbar-text navbar-right" style="margin-top: 25px">
			<!--<strong>你好，${sessionScope.user.name},&nbsp;(当前在线人数-&nbsp;${onlineNum}&nbsp;个)</strong>  -->
			<strong>你好，<s:property value="#session.user.name"/>,&nbsp;(当前在线人数-&nbsp;<s:property value="#application.onlineNum"/>&nbsp;个)</strong>
		</h5>
		<button id="btn_cls_add" type="button"
			class="btn btn-info  btn-sm navbar-right" data-toggle="modal"
			data-target="#classModal"
			style="margin-top: 18px; margin-right: 50px;">添加班级</button>
		<button id="btn_cls_edit" type="button"
			class="btn btn-info  btn-sm navbar-right" data-toggle="modal"
			data-target="#classModal"
			style="margin-top: 18px; margin-right: 20px; margin-left: 20px;">编辑班级</button>
		<button id="btn_cls_del" type="button"
			class="btn btn-info  btn-sm navbar-right" style="margin-top: 18px;">删除班级</button>
	</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="container col-md-3">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="list-group">
						<!--  	<c:forEach items="${nameMap}" var="entry">
								<a id="list-group-item-${entry.key}"
									href="/student-fw/main/mainAction.action?clsId=${entry.key}"
									class="list-group-item ${entry.key==cls.id?'active':''}">${entry.value}</a>
							</c:forEach>
						-->
						<s:iterator value="nameMap" >
						<a id="list-group-item-<s:property value='key'/> "
							href="/student-fw/main/mainAction.action?clsId=<s:property value='key'/>"
							class="list-group-item <s:property value='key==#request.cls.id?"active":""'/>" > <s:property value="value"/></a>
							  
						</s:iterator>
						
						</div>
					</div>
				</div>
			</div>
			<div class="container col-md-9">

				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						<div class="row">
							<div class="col-md-2">
								<h4>
									<strong>${cls.name}</strong>
								</h4>
							</div>
							<div class="col-md-8">
							<!-- <s:property value="count[1]+count[0]"/> -->
								<h4>班级总共：${count[1]+count[0] }人，其中男生：${count[1] }人，女生：<s:property value='count[0]'/>人</h4>
							</div>
							<div class="col-md-2">
								<h4>
									班主任：<strong id="clsTname">${cls.t_name }</strong>
								</h4>
							</div>
						</div>
					</div>
					<div class="panel-body">
						<div class="col-md-5">
							<form action="/student-fw/main/mainAction.action" class="form-inline">
								<input name="clsId" id="clsId" type="hidden" value="${cls.id }">
								<div class="form-group">
									<label for="exampleInputName2">姓名</label> <input type="text"
										name="findname" class="form-control" id="exampleInputName2"
										placeholder="输入姓名">
								</div>
								<button type="submit" class="btn btn-default">查询</button>
							</form>
						</div>
						<div class="col-md-7">
							<div class="btn-group pull-right" role="group">
								<div id="btn_std_add" class="btn-group" role="group">
									<button type="button" class="btn btn-default"
										data-toggle="modal" data-target="#studentModal">添加</button>
								</div>
								<div id="btn_std_edit" class="btn-group" role="group">
									<button type="button" class="btn btn-default"
										data-toggle="modal" data-target="#studentModal">修改</button>
								</div>
								<div id="btn_std_del" class="btn-group" role="group">
									<button type="button" class="btn btn-default">删除</button>
								</div>
							</div>
						</div>
					</div>
					<!-- Table -->
					<table class="table">
						<thead>
							<tr>
								<th>#</th>
								<th>学号</th>
								<th>姓名</th>
								<th>性别</th>
								<th>出生日期</th>
							</tr>
						</thead>
						<tbody>
							<!--<c:forEach items="${cls.students}" var="stud" varStatus="s">
								<tr class='${s.index==0 ? "success" : "" }'>
									<th id="id" hidden="hidden">${stud.id}</th>
									<th scope="row">${s.index+1}</th>
									<td id="code">${stud.code}</td>
									<td id="name">${stud.name}</td>
									<td id="sex">${stud.sex==0?"女":"男" }</td>
									<td id="birth">${stud.formatBirth}</td>
								</tr>
							</c:forEach> -->
							<s:iterator value="#request.cls.students"  var="stud" status="s">
								<tr class='#s.index==0 ? "success" : "" '>
									<th id="id" hidden="hidden"><s:property value="#stud.id"/></th>
									<th scope="row"><s:property value="#s.index+1"/></th>
									<td id="code"><s:property value="#stud.code"/></td>
									<td id="name"><s:property value="#stud.name"/></td>
									<td id="sex"><s:property value="#stud.sex==0?'女':'男' "/></td>
									<td id="birth"><s:property value="#stud.formatBirth"/></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<div style="text-align: right;display: ${pageCount==1?'none':''};">
					<nav>
					<ul class="pagination">
						<li><a href="/student-fw/main/mainAction.action?pageNum=1&clsId=${cls.id }">&laquo;</a></li>
						
						 <%-- <c:forEach begin="1" end="${pageCount}" var="num" varStatus="s">
							<li class="${pageNum==num?'active':''}"><a
								href="/student-fw/main/mainAction.action?pageNum=${num}&clsId=${cls.id }">${num}</a></li>
						</c:forEach> --%>
						
						<s:iterator begin="1" end="pageCount"  id="num" status="s">
						<li class="<s:property value='pageNum==num?"active":""'/>"><a
								href="/student-fw/main/mainAction.action?pageNum=<s:property value='#num'/>&clsId=${cls.id }">
								<s:property value='#num'/></a></li>
						</s:iterator> 
						
						<%-- <s:bean name="org.apache.struts2.util.Counter" id="num">
						    <s:param name="first" value="1" />
						    <s:param name="last" value="pageCount" />
						    <s:iterator>
						       <li class="<s:property value='pageNum==num?"active":""'/>"><a
								href="/student-fw/main/mainAction.action?pageNum=<s:property value='num[]'/>&clsId=${cls.id }"><s:property value='num[]'/></a></li>
						    </s:iterator>
						</s:bean> --%>
						
						<li><a
							href="/student-fw/main/mainAction.action?pageNum=${pageCount}&clsId=${cls.id }">&raquo;</a></li>
					</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
	</div>

	<form id="frm_cls_del" action="" method="post"
		style="display: none;">
		<input name="clasz.id" id="clsId" type="hidden" value="${cls.id }"/>
		<input type="hidden"  id="clsname" name="clasz.name"/>
		<input type="hidden"  id="clstname" name="clasz.t_name"/>
	</form>
	<!--班级添加修改弹出框-->
	<div class="modal fade" id="classModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="claszaddedit">添加班级信息</h4>
				</div>
				<form id="frm_cls" action="ClaszOptServlet" method="post">
					<div class="modal-body">
						<input name="clasz.id" id="clsId" type="hidden" value="${cls.id }">
						<div class="form-group">
							<label for="recipient-name" class="control-label">班级名称:</label> <input
								type="text" class="form-control" id="clsname" name="clasz.name">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">班主任名称:</label> <input
								type="text" class="form-control" id="clstname" name="clasz.t_name"></input>
						</div>

					</div>
					<div class="modal-footer">
						<button id="resetBtn1" type="button" class="btn btn-default"
							data-dismiss="modal">取消</button>
						<button type="submit" class="btn btn-primary">确定</button>
					</div>
				</form>
			</div>

		</div>
	</div>

	<form id="frm_std_del" action="" method="post"
		style="display: none;">
		<input name="student.id" id="studdId" type="hidden"> <input
			name="claszId" id="clsId" type="hidden" value="${cls.id }">
	</form>
	<!--学生添加修改弹出框-->
	<div class="modal fade" id="studentModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="studaddedit">添加学生信息</h4>
				</div>
				<form id="frm_std" action="" method="post">
					<div class="modal-body">
						<input name="student.id" id="studId" type="hidden">
						<div class="form-group">
							<label for="recipient-name" class="control-label">学生编号:</label> <input
								name="student.code" type="text" class="form-control" id="scode">
								<s:fielderror fieldName="student.code"/>
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">学生姓名:</label> <input
								name="student.name" type="text" class="form-control" id="sname"></input>
						<s:fielderror fieldName="student.name"/>
						</div>
						<div class="form-group">
							<label for="sex-text" class="control-label">学生性别:</label>
							<div id="sex-text">
								<label class="radio-inline"> <input name="student.sex"
									type="radio" id="ssex_m" value="1" checked="checked"> 男
								</label> <label class="radio-inline"> <input name="student.sex"
									type="radio" id="ssex_w" value="0"> 女
								</label>
							</div>
							<input name="claszId" id="clsId" type="hidden" value="${cls.id }">
						</div>
						<div class="form-group">
							<label for="birth-text" class="control-label">出生日期:</label> <input
								name="student.brith" type="text" class="form-control" id="sbirth"></input>
						<s:fielderror fieldName="student.brith"/>
						</div>

					</div>
					<div class="modal-footer">
						<button id="resetBtn" type="button" class="btn btn-default"
							data-dismiss="modal">取消</button>
						<button type="submit" class="btn btn-primary">确定</button>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>