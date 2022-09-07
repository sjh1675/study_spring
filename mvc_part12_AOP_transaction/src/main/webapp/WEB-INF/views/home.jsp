<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#messageList{
		padding-left: 0;
	}
	
	#messageList li {
		list-style: none;
		border: 1px solid black;
		padding: 10px;
		height: 50px;
		margin-bottom: 3px;
	}
</style>
</head>
<body>
	<h2>MESSAGE</h2>
	<input type="text" id="targetid" placeholder="수신자 id"/>
	<br/>
	<input type="text" id="sender" placeholder="발신자 id"/>
	<br/>
	<input type="text" id="message" placeholder="MESSAGE"/>
	<br/>
	<button id="ADD">SEND MESSAGE</button>
	<ul id="messageList">
	
	</ul>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	getMessageList();

	function getMessageList(){
		$.getJSON("messages/list",function(data){
			console.log(data);
			var str = "";
			$(data).each(function() {
				var senddate = getDate(this.senddate);
				console.log(senddate);
				var opendate = "";
				console.log(this.opendate);
				if (this.opendate == null) {
					opendate = "미확인";
				} else {
					opendate = getDate(this.opendate);
				}
				str += "<li onclick='readMessage("+this.mno+",\""+this.targetid+"\",this);'>";
				str += this.mno + " | " + this.targetid + " | " + this.sender;
				str += " | " + this.message + " | " + opendate + " | " + senddate;
				str += "</li>";
			});
			$("#messageList").html(str);
		});
	}
	
	function readMessage(mno, uid, element) {
		console.log(mno);
		console.log(uid);
		console.log(element);
		$(element).html("박주신 승");
		$.ajax({
			type : "PATCH",
			url : "messages/read/" + mno + "/" + uid,
			/* headers : {
				"Content-Type" : "application/json"
			},
			data : JSON.stringify({
				uid : uid
			}), */
			dataType : "json",
			success : function(data) {
				console.log(data);
				
				var str = data.mno + " | " + data.targetid + " | " + data.sender;
				str += " | " + data.message + " | " + getDate(data.opendate) + " | " + getDate(data.senddate);
				$(element).html(str);
			},
			error : function(res, status, error){
				alert(res.responseText);
			}
		});
	}

	function getDate(date) {
		var dateTime = new Date(date);
		var dateStr = dateTime.getFullYear() + "년 ";
		dateStr += (dateTime.getMonth() + 1) + "월 ";
		dateStr += dateTime.getDate() + "일 ";
		dateStr += dateTime.getHours() + "시 ";
		dateStr += dateTime.getMinutes() + "분";
		return dateStr;
	}
	
	// 메세지 전달
	$("#ADD").click(function(){
		$.ajax({
			type : "POST",
			url : "messages/add",
			dataType : "text",
			data : {
				targetid : $("#targetid").val(),
				sender : $("#sender").val(),
				message : $("#message").val()
			},
			success : function(data){
				alert(data);
				$("#targetid").val("");
				$("#sender").val("");
				$("#message").val("");
				$("#targetid").focus();
			},
			error : function(res,status,error){
				console.log("code : "+res.status+"\n message : "+res.responseText);
				alert(res.responseText);
			}
		}); // ADD ajax end
	}); // ADD click end
</script>	

</body>
</html>














