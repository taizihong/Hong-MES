<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="/uitest/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$(function (){
	var url="/test/userTest.json";
	var name="小姐姐";
	$.ajax({
		url : url,
		data : {//左面是数据名称-键，右面是值
			name:name
		},
		type : 'POST',
		success : function(result) {//jsondata  jsondata.getData=pageResult  pageResult.getData=list
			alert(result);
		}
	});
});
</script>
</body>
</html>