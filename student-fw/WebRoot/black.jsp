<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
	<title>黑名单</title>
	<link rel="stylesheet" type="text/css" href="/student-fw/assets/css/bootstrap.min.css">
	<script src="/student-fw/assets/js/jquery.min.js"></script>
    <script src="/student-fw/assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" >
       $(document).ready(function () {
       	    $('tbody tr').click(function (e) {
               e.preventDefault();
              
               $('tbody tr').removeClass('active');
               $(this).addClass('active');
               var bname=$(this).children("#bname").text();
               $('input#bname').val(bname);
           });
       	 
       	 $('#black_in').click(function (e) {
       	   // var bname=$('tr.active').children("#bname").text();
       		$('form#blackform').attr("action", "/student-fw/main/bloptAction.action?opt=in");
       	   // $('input#bname').val(bname);
       	   //alert( $('input#bname').val());
         });
       	 
       	$('#black_out').click(function (e) {
       
      		$('form#blackform').attr("action", "/student-fw/main/bloptAction.action?opt=out");
        });
       	
       	$('#btn_upload').click(function (e) {
      		$('#img_file').click();
        });
       	$('#img_file').change(function (e) {
      		$('#frm_upload').submit();
        });
       	
       	
       });
       
    </script>
</head>
<body>
   <div class="row">
       <div class="col-md-7" style="text-align: right;"><h1>用户黑名单管理</h1></div>
       <div class="col-md-4" style="text-align: right;">
       <form id="frm_upload" action="/student-fw/main/uploadAction.action" method="post" enctype="multipart/form-data">
       	  <input name="img_file" id="img_file" type="file" style="display: none;"></input>
       	  <button id="btn_upload" type="button" class="btn" 
       	  style="width: 60px; height: 60px; -moz-border-radius: 50%; -webkit-border-radius: 50%; 
       	  border-radius: 50%;background-image:url(/student-fw/head_img/004.jpg);
       	  background-position:center; background-size: 60px 60px;margin-top: 20px; border-color:#000; "/>
       </form>
       </div>
    </div>
	<div class="panel panel-default" style="margin-top: 30px;margin-left: 20px;margin-right: 20px;">
      <div class="panel-heading" style="text-align: center;">
      	<form id="blackform" action="/student-fw/main/bloptAction.action" method="post" class="form-inline" role="form">
          <div class="form-group">
             <label class="sr-only" for="exampleInputEmail2">用户名称</label>
             <input id="bname" name="bname" type="text" class="form-control" value="${param.bname }" placeholder="输入黑名单的用户名称">
          </div>
        
           <button id="black_in" type="submit" class="btn btn-danger">列入黑名单</button>
           <button id="black_out" type="submit" class="btn btn-success">解除黑名单</button>
        </form>
      </div>
      <div class="panel-body">
        <table class="table table-hover">
	      <thead>
	        <tr>
	          <th>#</th>
	          <th>用户名称</th>
	          <th>列入日期</th>
	          <th>状态</th>
	        </tr>
	      </thead>
	      <tbody>
	        <c:forEach items="${black_list}" var="info" varStatus="status">
	        <tr>
	         <td>${status.index+1}</td>
  			 <td id="bname" >${info.name}</td>
  			 <!-- ${info.formatDate} -->
  			 <td id="bdate" >${info.formatDate}</td>
  			 <td> 
  			 <span class="label label-${info.removed==0?'success': 'danger'}">
  			<!--  <a href="BlackOptServlet?opt=${info.removed==0?'out':'in'}"></a> -->
  			 ${info.removed==0?'需要解除':'需要列入'} 
  			 </span> 
  			 </td>
  			 
<!-- 	          <td>1</td> -->
<!-- 	          <td id="bname">张三</td> -->
<!-- 	          <td>2011-09-08 23:45:23</td> -->
<!-- 	          <td><span class="label label-danger">列入</span></td> -->
<!-- 	        </tr> -->
<!-- 	        <tr> -->
<!-- 	          <td>2</td> -->
<!-- 	          <td  id="bname">李四</td> -->
<!-- 	          <td>2011-09-08 23:45:23</td> -->
<!-- 	          <td><span class="label label-success">解除</span></td> -->
<!-- 	        </tr> -->
<!-- 	        <tr> -->
<!-- 	          <td>3</td> -->
<!-- 	          <td  id="bname">王二</td> -->
<!-- 	          <td>2011-09-08 23:45:23</td> -->
<!-- 	          <td><span class="label label-danger">列入</span></td> -->
	        </tr>
	        </c:forEach>
	        
	      </tbody>
	     </table>
      </div>
      
    </div>

</body>
</html>