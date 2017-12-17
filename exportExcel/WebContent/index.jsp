<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>导出台帐</title>
	<script type="text/javascript" src="com/resource/js/jquery.js" ></script>
	<script type="text/javascript" src="com/resource/js/jquery.min.js" ></script>
</head>
<body>

	<script type="text/javascript" >
		function toSubmit(){
			if(jQuery("#titleName").val()==""){
				alert("请输入保存后的文件名称");
			}else{
				jQuery("#exportTzForm").submit();
			}
		}
	</script>

	<div style="margin: 0 auto;text-align: center;margin-top: 50px;" >
		<form id="exportTzForm" action="tz.do" method="get" accept-charset="UTF-8" >
			保存名称：<input type="text" id="titleName" name="titleName" value="" />
			<br /><br />
			开始时间：<input type="text" id="beginTime" name="beginTime" value="" />
			<br />
			结束时间：<input type="text" id="endTime" name="endTime" value="" />
			<br /><br />
			<input type="button" value="导出台帐" onclick="toSubmit();" />
		</form>
	</div>
	
</body>
</html>