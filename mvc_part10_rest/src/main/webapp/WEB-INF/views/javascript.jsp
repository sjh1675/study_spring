<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>javascript.jsp</title>
</head>
<body>
	<h1>자바스크립트</h1>
	<input type="button" id="btn" value="CLICK" />
	<div id="box">
	
	</div>
	<div id="box2">
	
	</div>
	<script>
		/* 
			AJAX 비동기 자바스크립트와 XML
			(Asynchronous JavaScript And XML)
			서버와 통신하기 위해 XMLHttpRequest 객체를 사용하는 것을 AJAX라 한다
			특징 : 페이지 전체를 url 요청으로 갱신하지 않고 수행되는 비동기성 화면 전환을 할 수 있다			
		*/
		var httpRequest;
			
		function makeRequest(url) {
			httpRequest = new XMLHttpRequest();
			if (!httpRequest) {
				alert("정상적으로 생성되지 않았습니다.");
				return false;
			}
			httpRequest.open('get', url + "?name=안녕&age=20");
			
			// form 타입으로 사용하겠다를 의미함(데이터 전송 용도)
			httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			httpRequest.onreadystatechange = setContents;	// 상태가 변경될 때 마다 함수 호출 설정
			httpRequest.send();
		}
				
		function setContents() {
			/* 
				0 : (uninitialized) - request가 초기화되지 않음
				1 : loading - 서버와 연결됨
				2 : loaded - 서버가 request를 받음(요청받음)
				3 : interactive - request 요청을 처리
				4 : complate - request 처리가 끝나고 응답 준비가 완료됨
			*/
			
			if (httpRequest.readyState === 4) {
				// 상태 코드 200 : 정상적으로 요청 처리 완료 
				if (httpRequest.status === 200) {
					// httpRequest.responseText : 서버의 응답을 텍스트 문자열로 반환
					// httpRequest.responseXML : 서버의 응답을 XML문서로 반환
					alert(httpRequest.responseText);
					var obj = JSON.parse(httpRequest.responseText);
					console.log(obj.name);
					console.log(obj.age);
					console.log(obj);
					document.getElementById("box").innerHTML = JSON.stringify(obj);
					var html = "<table border=1>";
					html += "<tr><th>이름</th><th>나이</th></tr>";
					html += "<tr>";
					html += "<td>";
					html += obj.name;
					html += "</td>";
					html += "<td>";
					html += obj.age;
					html += "</td>";
					html += "</tr>";
					html += "</table>";
					document.getElementById("box2").innerHTML = html;
				} else {
					// 오류 발생
					alert("요청 처리 실패");
				}
			}
			
		}
		
		document.getElementById("btn").onclick = function() {
			makeRequest('getSample');			
		};
		
	</script>
</body>
</html>