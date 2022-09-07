<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="testJSON">testJSON</a>
	<h3>getSample</h3>
	<!-- 
		multipart/form-data	: 	대용량의 데이터를 전달할 때 사용하는 방식으로 있는 그대로 전송 
							 	form 요소가 파일이나 이미지를 서버에 전송할 때 사용함
		
		text/plane			: 	공백 문자만 + 기호로 변환하고 나머지는 인코딩하지 않음
		application/x-www-form-urlencoded	:	모든 값을 서버로 전달하기 전에 인코딩 됨을 명시
	 -->
	<form action="getSample" method="GET" enctype="application/x-www-form-urlencoded">
		<input type="text" name="name" /><br>
		<input type="number" name="age" /><br>
		<input type="submit" value="GET" /><br>		
	</form>
	
	
	<form action="getSample" method="POST">
		<input type="text" name="name" /><br>
		<input type="number" name="age" /><br>
		<input type="submit" value="POST" /><br>		
	</form>
	
	<hr>
	<a href="getSampleList">getSampleList</a> <br>
</body>
</html>