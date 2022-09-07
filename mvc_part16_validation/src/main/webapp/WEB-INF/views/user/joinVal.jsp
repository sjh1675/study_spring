<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<style>

	table {
		max-width: 500px;
		margin:0 auto;
	}

	.uploadImage {
		width: 100px;
		height: 100px;
		border-radius: 50px;
		border: 1px solid #ccc;
	}
	
	#emailCodeWrap {
		display: none;
	}
	
	.text-danger {
		display: block;
		color: red;
		font-size: 14px;
	}
</style>

<form id="joinForm" action="${path}/user/joinPost" method="POST" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<th colspan="2">
				<h1>JOIN PAGE</h1>
			</th>
		</tr>
		<tr>
			<td>프로필 이미지</td>
			<td style="text-align: center">
				<img src="${path}/resources/img/profile.jpg" id="uploadImage" class="uploadImage"/>
				<br>
				<input type="file" id="profileImage" name="profileImage" accept="image/*"/>
			</td>
		</tr>
		<tr>
			<td>아이디(email)</td>
			<td>
				<input type="text" id="u_id" name="u_id" autocomplete="off"/>
				<input type="button" id="acceptEmail" value="이메일 인증">
				<div class="result"></div>
				<div id="emailCodeWrap">
					<input type="text" name="emailCode" id="emailCode"/>
					<input type="hidden" id="code"/>
				</div>
			</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>
				<input type="password" id="u_pw" name="u_pw"/>
				<div class="result"></div>
			</td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td>
				<input type="password" name="re_pw" id="re_pw"/>
				<div class="result"></div>
			</td>
		</tr>
		<tr>
			<td>이름(한글 2~6자 이내)</td>
			<td>
				<input type="text" name="u_name" id="u_name"/>
				<div class="result"></div>
			</td>
		</tr>
		<tr>
			<td>생년월일(ex>19900505)</td>
			<td>
				<input type="text" name="u_birth" id="u_birth"/>
				<div class="result"></div>
			</td>
		</tr>		
		<tr>
			<td>주소</td>
			<td>
				<div>
					<input type="text" name="u_post" id="u_post" placeholder="우편번호"/>
					<div class="result"></div>
					<input type="button" value="주소찾기" onclick="daumPostCode();"/>
				</div>
				<input type="text" name="u_addr" id="u_addr" placeholder="주소"/>
				<input type="text" name="u_addr_detail" id="u_addr_detail" placeholder="상세주소"/>
				
			</td>
		</tr>
		
		<tr>
			<td>전화번호(-제외 숫자만 입력)</td>
			<td>
				<div>
					<input type="text" name="u_phone" id="u_phone"/>
					<input type="button" value="인증코드 전송" id="sendSMS"/>
					<div class="result"></div>					
				</div>
<!-- 				<div id="codeWrap">
					<input type="text" id="code"/>
					<input type="button" id="acceptCode" value="인증"/>
				</div> -->
			</td>
		</tr>
		
		<tr>
			<!-- https://www.privacy.go.kr -->
			<td colspan="2">
				<h4>이용약관</h4>
				<textarea readonly rows="5" cols="50">당신의 개인정보는 언제든지 사에서 팔수 있으며 항상 3자에게 제공됩니다.</textarea>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<label>
					<input type="checkbox" name="u_info" id="u_info" value="y"/>
					개인정보 이용 동의
				</label>
				<div class="result"></div>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="submit" id="joinBtn" value="회원가입"/>
			</td>
		</tr>
	</table>
</form>


<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function daumPostCode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
            console.log(data);
            
            var fullAddr = "";	// 최종 주소
            var extraAddr = "";	// 조합형 주소
            var postCode = "";	// 우편번호
            
            // 사용자가 선택한 타입이 지번인지 도로명 주소인지 확인
            if (data.userSelectedType == "R") {
            	// 도로명 주소
            	fullAddr = data.roadAddress;
            	
            	if (data.bname !== '') {
            		extraAddr += data.bname;
            	}
            	
            	if (data.buildingName !== '') {
            		extraAddr += extraAddr !== '' ? ', ' + data.buildingName : data.builingName
            	}
            	
            	fullAddr += (extraAddr !== '') ? '(' + extraAddr + ')' : '';
            	
            } else {
            	fullAddr = data.jibunAddress;
            }
            
            postCode = data.zonecode;
            
            
            
            // 입력 필드에 값 넣기
            $("#u_post").val(postCode);
            $("#u_addr").val(fullAddr);
            $("#u_addr_detail").focus();
        }
    }).open();
}
</script>

<script>
	
	$(function(){
		
		$("#acceptEmail").click(function(){
			$.ajax({
				type : "GET",
				dataType : "text",
				url : "${path}/checkEmail",
				data : {
					u_id : $("#u_id").val()
				},
				success : function(code) {
					if (code) {
						alert("메일 전송 완료");
						$("#code").val(code);
						$("#emailCodeWrap").show();						
					} else {
						alert("메일 전송 실패");
					}
				}
			});
		});
		
		var regexMobile = /^[0-9]{2,3}?[0-9]{3,4}?[0-9]{4}$/;
		
		$.validator.addMethod("regex", function(value, element, regexpr){	// value는 사용자가 입력한 값 / element는 실행된 요소(u_phone) / regexpr
			return regexpr.test(value);
		});
		
		$.validator.addMethod("code", function(value, element) {
			var code = $("#code").val();
			if (code == '' || value == '') {
				return false;
			}
			return value == code;			
		});
		
		$("#joinForm").validate({
			onkeyup : function(el) {
				$(el).valid();
			},
			rules : {
				u_id : {
					required : true,
					email : true,
					remote : {
						type : "GET",
						url : "${path}/user/uidCheck"	// remote를 이용한 통신으로는 반드시 결과값이 0/1 혹은 true/false 여야 된다
														// 결과에 따라 아래의 메세지(messages) 란에 출력된다
					}
				},
				emailCode : {
					code : ""
				},
				u_pw : {
					required : true,
					minlength : 6,
					maxlength : 20
				},
				re_pw : {
					required : true,
					minlength : 6,
					maxlength : 20,
					equalTo : "#u_pw"
				},
				u_name : {
					required : true,
					rangelength : [2,6]					
				},
				u_phone : {
					required : true,
					regex : regexMobile
				},
				u_info : {
					required : true,
				}
			},
			messages : {
				u_id : {
					required : "이메일(아이디)를 작성해주세요.",
					email : "올바른 이메일 형식이 아닙니다.",
					remote : "이미 존재하는 아이디입니다."
				},
				emailCode : {
					code : "이메일 인증 코드를 확인해주세요."
				},
				u_pw : {
					required : "비밀번호를 작성해주세요.",
					minlength : "비밀번호는 최소 6자 이상입니다.",
					maxlength : "비밀번호는 최대 20자리만 할 수 있습니다."
				},
				re_pw : {
					required : "비밀번호를 작성해주세요.",
					minlength : "비밀번호는 최소 6자 이상입니다.",
					maxlength : "비밀번호는 최대 20자리만 할 수 있습니다.",
					equalTo : "비밀번호가 일치하지 않습니다."
				},
				u_name : {
					required : "이름을 입력해주세요",
					rangelength : "이름은 2~6자 이내로 작성해주세요."	
				},
				u_phone : {
					required : "전화번호를 작성해주세요.",
					regex : "올바른 번호 형식이 아닙니다."
				},
				u_info : {
					required : "개인정보 이용 동의를 확인해주세요.",
				}
			},
			errorClass : "text-danger",
			errorElement : "span",
			errorPlacement : function(error, element) {
				if (element.prop("type") === 'checkbox') {
					element.removeClass("text-danger");
					error.insertAfter(element.parent());
				} else {
					error.insertAfter(element);
				}
			},
			debug : true,	// debug 값이 true 이면 submit 되지 않고 테스트만 진행됨
			submitHandler : function(form) {	// submit이 실행 되기 직전에 처리되는 메소드
				// submit 하기 전 처리 공간
				
				
				
				
				// submit 하기 전 처리 공간 끝
				$(form).submit();
			}
		});
	});
	
</script>
</body>
</html>

