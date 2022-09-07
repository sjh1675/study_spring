<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정규표현식/regex.jsp</title>
<script>
	/* 
		javascript의 정규식은 java와 문법이 조금 다를 뿐 기본 맥락과 원리는 같음
	*/
	const regex = /\d{3}-\d{4}-\d{4}/;		// /로 시작하고 /로 종료 시 이 변수를 정규식으로 인식
	
	var bool = regex.test("010-1111-1111");
	console.log(bool);
	
	var bool = regex.test("01-11-22");
	console.log(bool);
	
	const text = "안녕하세요 제 번호는 010-1111-1111입니다. 연락주세요.";
	var result = text.match(regex);	// boolean 값을 반환하지 않고 정규식과 일치하는 문자의 존재 여부를 확인하고 그 값들을 배열로 전달한다
	console.log(result);			// >> 값이 010-1111-1111 인 배열 하나가 출력됨
		
	const pattern = /\d{5}-\d{4}-\d{4}/;
	result = text.match(pattern);	
	console.log(result);
	
	var regexEmail =/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	const url = "hap0p9y@nate.com";
	console.log(url.match(regexEmail));
	console.log(regexEmail.test(url));
</script>
</head>
<body>
	<script>
		
	</script>
</body>
</html>