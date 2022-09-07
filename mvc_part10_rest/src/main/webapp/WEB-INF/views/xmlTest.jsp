<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>xmlTest.jsp</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<input type="text" id="name" /> <br/>
	<input type="number" id="age" /> <br/>
	<button id="xmlTest">xml test</button>
	<button id="xmlJSON">xml JSON</button>
	<hr/>
	<div id="result"></div>
	<script>
		$(function(){
			$("#xmlTest").click(function(){
				var input_name = $("#name").val();
				var input_age = $("#age").val();
				
				var obj = {
					type : "POST",
					url : "xmlTest",
					data : {
						name : input_name,
						age : input_age
					},
					dataType : "xml",
					success : function(result){
						console.log(result);
						var name = $(result).find("name").text();
						var age = $(result).find("age").text();
						
						var html = "<table border=1>";
						html += "<tr>";
						html += "<td>" + name + "</td>";
						html += "<td>" + age + "</td>";
						html += "</tr>";
						html += "</table>";
						$("#result").append(html);
					}
				};
				$.ajax(obj);
			}); // clickend
			
			$("#xmlJSON").click(function(){
				var input_name = $("#name").val();
				var input_age = $("#age").val();
				
				
				$.ajax({
					type : "POST",
					url : "xmlTest",
					dataType : "xml",
					headers : {
						'Content-Type' : 'application/json'
					},
					data : JSON.stringify({
						name : input_name,
						age : input_age
					}),
					success : function(result){
						console.log(result);
					}
				});				
			});
		}); // load end
	</script>
</body>
</html>