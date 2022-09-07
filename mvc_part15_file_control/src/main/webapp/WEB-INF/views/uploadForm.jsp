<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadForm.jsp</title>
</head>
<body>
	<h1>Upload Form</h1>
	<form action="uploadForm" method="POST" enctype="multipart/form-data">	<!-- 데이터가 문자로 인코딩 되는게 아니라 파일 자체로 전달되기 위한 인코딩 방식 -->
		<input type="file" name="file"/><br>
		<input type="submit"/>
	</form>
	<h2>UploadForm1 multiple</h2>
	<form action="uploadForm1" method="POST" enctype="multipart/form-data">
		<input type="file" name="files" multiple/> <br>
		<input type="submit"/>
	</form>
	
	<h2>UploadForm2 multi upload</h2>
	<form action="uploadForm2" method="POST" enctype="multipart/form-data">
		<input type="text" name="auth" /> <br>
		<textarea name="content" ></textarea> <br>
		<input type="file" name="file" /> <br>
		<input type="file" name="file1" /> <br>
		<input type="submit"/>
	</form>
	
	<h2>UploadForm3 multiple upload</h2>
	<form action="uploadForm3" method="POST" enctype="multipart/form-data">
		<input type="text" name="auth" /> <br>
		<textarea name="content" ></textarea> <br>
		<input type="file" name="file" multiple /> <br>
		<!-- accept 속성을 사용하여 .pdf, .xml, .jsp, text/plane, text/json 등의 파일 형식을 지정 -->
		<input type="file" name="file1" accept="image/*" /> <br>
		<input type="submit"/>
	</form>
</body>
</html>