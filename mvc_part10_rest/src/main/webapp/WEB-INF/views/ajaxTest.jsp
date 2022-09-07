<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<div style="background-color:gray; height:500px;">dummy</div>
	<div>
		<input type="text" id="name" /> <br>
		<input type="number" id="age" /> <br>
		<input type="button" id="get" value="GET" /> <br>
		<input type="button" id="post" value="POST" /> <br>
		<input type="button" id="PUT" value="PUT" /> <br>
	</div>
	<div id="result" style="border: 1px solid black;">
	
	</div>
	
<script>
	$(function(){
		console.log("문서 준비 완료");
		$("#get").click(function(){
			let name = $("#name").val();
			let age = $("#age").val();
			console.log('name : ' + name + ' , age : ' + age);
			
			$.ajax({
				async : false,	// false(동기화) : ajax 안에 있는 절차가 완료되기 전까지 아래 코드인 호출 완료가 뜨지 않음(default : true/비동기 실행)
				type : "GET",
				url : "getSample",
				data : {
					name : name,
					age : age
				},
				dataType : "json", // 결과 데이터(전달할 데이터가 아니라, 받은 데이터)
				success : function(data) {
					// 요청 처리 성공 시 실행
					console.log(data);
				},
				error : function(res) {
					// 실패 시 실행
					console.log(res);				
				}
			});
			console.log("ajax 호출 완료");
		});
		
		$("#post").click(function(){
			let input_name = $("#name").val();
			let input_age = $("#age").val();
			
			$.ajax({
				type : "POST", // 전송 방식
				url : "getSample", // 요청 경로
				data : {	// 전달 데이터
					name : input_name,
					age : input_age
				},
				dataType : "JSON", // JSON.parse() 시키고 전달
				success : function(dat){ // 전달 성공시 전달한 데이터가 dat 매개변수에 담김
					// data == List<SampleVO>
				console.log(dat);
				var html = "<table border=1>";
				html += "<tr>";
				html += "<th>이름</th>";
				html += "<th>나이</th>"; 								
				html += "</tr>";
				for(var i = 0; i < dat.length; i++) {
					html += "<tr>";
					html += "<td>" + dat[i].name + "</td>";
					html += "<td>" + dat[i].age + "</td>";
					html += "</tr>";
				}
				html += "</table>";
				
				// innerHTML
				$("#result").html(html);
				
				}
			});
		});
		
		$("#PUT").click(function(){
			
			let name = $("#name").val();
			let age = $("#age").val();
			console.log('name : ' + name + ' , age : ' + age);
			
			$.ajax({
				
				type : "PUT", // 전송 방식
				url : "testPUT", // 요청 경로
				headers : {
					'Content-Type' : 'application/json'					
				},
				data : JSON.stringify({	// 전달 데이터
					name : $("#name").val(),
					age : $("#age").val()
				}),

				dataType : "json",
				success : function(result) {
					console.log(result);
				}
			});
		});
	});
	

</script>

</body>
</html>