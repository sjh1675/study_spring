<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<sec:authorize access="isAuthenticated()">
	<sec:authentication var="member" property="principal.member"/>
</sec:authorize>
<style>
	.chatBox{
		height: 200px;
		overflow-y: scroll;
		border: 1px solid #ccc;
		border-radius: 5px;
	}
	
	.chatWrap{
		margin-top: 3px;
		margin-bottom: 3px;		
	}
	
	.chatWrap img{
		width: 30px;
		height: 30px;
		border-radius: 15px;
		border: 1px solid black;
	}
</style>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<div class="container">
	<h1>CHAT PAGE - </h1>
	<!-- 어떤 사용자가 메세지를 전달했는지 u_profile 출력 -->
	<div class="container chatBox" id="data">
		
	</div>
	<br>
	<div class="row">
		<div class="col-md-10">
			<input type="text" class="form-control" id="message"/>
		</div>
		<div class="col-md-2">
			<input type="button" id="sendBtn" class="form-control btn btn-primary" value="SEND" />
		</div>
	</div>	
</div>
<script>
	var sock = new SockJS("chatHandler");
	
	// 소켓 연결에 성공했을 때 실행됨
	sock.onopen = function(){
		console.log("연결 완료");
		// sock.send("연결 됨");
		// var msg = "${member.u_profile},${member.u_name},님이 입장하셨습니다.";
		var msg = "${member.u_profile},${member.u_name},님이 입장하셨습니다.";
		sock.send(msg);
	}
	
	
	sock.onclose = function() {	// 새로 고침하면 연결이 끊기는 이유 -> sock이 한번 더 생성되기 때문(다시 연결됨)
		console.log("연결 끊김");
	}
	
	// 서버에 메세지가 전달되었을 때
	sock.onmessage = function(message){
		console.log(message);
		var msg = message.data;
		var datas = msg.split(",");
		// 0 : u_profile
		// 1 : u_name
		// 2 : message
		
		var userProfile = datas[0];
		var userName = datas[1];
		var userMessage = datas[2];
		
		var str = "<div class='chatWrap'>";
		if (userProfile != null && userProfile != '') {
			str += "<img src='${path}/upload" + userProfile + "'/>";
		} else {
			str += "<img src='${path}/resources/img/profile.jpg'/>";
		}
		str += "&nbsp;&nbsp;";
		str += userName + " : " + userMessage;
		str += "</div>";
		$("#data").append(str);
		$("#data").focus();
		$("#data").scrollTop($("#data").prop("scrollHeight"));	// 스크롤이 가장 아래쪽으로 이동됨
		$("#message").focus();
		
	}
	
	// send 버튼이 눌러졌을 때
	$("#sendBtn").click(function(){
		// 메세지 전달
		sendMessage();
	});
	
	// 메세지 작성 칸에서 enter가 눌러졌을 때
	$("#message").keydown(function(e){
		console.log(e.keyCode);
		if (e.keyCode == 13) {	// 13 : enter
			// 메세지 전달
			sendMessage();
		}
	});
	
	function sendMessage() {
		var message = $("#message").val();
		var userProfile = "${member.u_profile}";
		var userName = "${member.u_name}";
		sock.send(userProfile + "," + userName + "," + message);
		$("#message").val();		
	}
	
</script>
</body>
</html>