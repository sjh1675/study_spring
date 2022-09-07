<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/include/header.jsp"%>

<style>
.vertical_bottom {
	position: absolute;
	bottom: 0.5rem;
}

.membership-box {
	padding-bottom: 5rem;
}
</style>

<div class="container-md spad">
	<div class="row justify-content-center">
		<div class="col-md-8">

			<div class="membership_title mb-5">
				<h2>포인트 충전</h2>
			</div>

			<div class="containder-md mt-3 row justify-content-center">
				<div class="flex-column">
					<div class="col-auto input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">현재 포인트</span>
						</div>
						<input type="text" class="form-control" id="myPoint" value="${point}"
							readonly />
					</div>

					<div class="col-auto input-group mb-3">
						<input type="text" class="form-control" id="chargeAmount"
							placeholder="충전할 금액" value="0">
						<div class="input-group-append">
							<button class="btn btn-outline-secondary" id="chargeBtn">충전</button>
						</div>
					</div>
				</div>
			</div>


			<div class="w-100 mb-5"></div>

			<div>
				<div class="membership_title">
					<h2>멤버십 가입</h2>
				</div>

				<div class="card-deck">
					<!-- 브론즈 -->
					<div class="membership-box">
						<i class="bi bi-hexagon"></i> <span class="membership-bronze">bronze</span>
						<h3 class="membership-class-title">무료</h3>

						<ul class="ul">
							<li class="mebership-li"><i class="bi bi-check-circle">
									<span>7일 동안 최대 1권 대여 가능</span>
							</i></li>
							<li class="mebership-li"><i class="bi bi-check-circle">
									<span>대여 비용 1,000원</span>
							</i></li>
						</ul>
						<button class="btn btn-danger vertical_bottom"
							onclick="onMembershipModal(this)" data-fee="0" value="Bronze">브론즈
							등급 가입</button>
					</div>

					<!-- 실버 -->
					<div class="membership-box">
						<i class="bi bi-gift-fill"></i> <span class="membership-silver">silver</span>
						<h3 class="membership-class-title">월 50,000원</h3>

						<ul class="ul">
							<li class="mebership-li"><i class="bi bi-check-circle">
									<span>스터디룸 하루에 최대 4시간 이용 가능</span>
							</i></li>
							<li class="mebership-li"><i class="bi bi-check-circle">
									<span>14일 동안 최대 2권 대여 가능</span>
							</i></li>
							<li class="mebership-li"><i class="bi bi-check-circle">
									<span>대여 비용 500원</span>
							</i></li>
							<li class="mebership-li"><i class="bi bi-check-circle">
									<span>연체시 10% 할인</span>
							</i></li>
						</ul>
						<button class="btn btn-danger vertical_bottom"
							onclick="onMembershipModal(this)" data-fee="50,000" value="Silver">실버
							등급 가입</button>
					</div>

					<!-- vip -->
					<div class="membership-box">
						<i class="bi bi-gem"></i> <span class="membership-vip">vip</span>
						<h3 class="membership-class-title">월 150,000원</h3>

						<ul class="ul">
							<li class="mebership-li"><i class="bi bi-check-circle">
									<span>스터디룸 자유롭게 이용 가능</span>
							</i></li>
							<li class="mebership-li"><i class="bi bi-check-circle">
									<span>30일 동안 최대 3권 대여 가능</span>
							</i></li>
							<li class="mebership-li"><i class="bi bi-check-circle">
									<span>대여 비용 없음</span>
							</i></li>
							<li class="mebership-li"><i class="bi bi-check-circle">
									<span>연체시 20% 할인</span>
							</i></li>
						</ul>
						<button class="btn btn-danger vertical_bottom"
							onclick="onMembershipModal(this)" data-fee="150,000" value="VIP">VIP
							등급 가입</button>
					</div>

				</div>
				<!-- .card-deck end -->
			</div>


		</div>
	</div>
</div>

</html>

<%@include file="/WEB-INF/views/include/footer.jsp"%>

<div class="modal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title"></h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p id="modal-body" style="color: black">Modal body text goes
					here.</p>
			</div>
			<input id="hiddenGrade" value="" hidden>
			<div class="modal-footer">
				<button type="button" id="updateBtn" class="btn btn-primary">확인</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>

<script>
$(function(){
	playAjax();
});
	$('#chargeBtn').click( function() {
		playAjax();		
	});
function playAjax(){
	let amount = $('#chargeAmount').val();
	let data = { point : amount };
	if(amount <= 0)return;
$.ajax({
		
		url: '${path}/members/${user.id}/charge/add',
		type: 'put',
		data:  JSON.stringify(data),
		contentType: 'application/json',
		dataType: 'json',
		success: function(result){
			alert("성공");
			$('#myPoint').val(result);
		}
		
	});
}	
	
	
	function onMembershipModal(event){
		console.log(event.value); // VIP
		console.log(event.dataset.fee);
		const grade = event.value;
		$('#hiddenGrade').val(grade);
		const title = grade + " 멤버십 가입 안내";
		const fee = event.dataset.fee;
		const content = grade + ' 등급의 월 요금제는 ' +fee+ '원 입니다. 결제하시겠습니까?'; 

		
		$('.modal-title').text(title);
		$('#modal-body').text(content);
		$('.modal').modal('show');
		
		
		
	}
	
	$('#updateBtn').on('click',function(){
		const grade = $('#hiddenGrade').val();
		$.ajax({
			type: 'post',
			url: '${path}/members/${user.id}/grade/update',
			data: { grade : grade},
			success: function(data){
				console.log(data);
				alert(grade+"등급 가입 완료");
				location.reload();
			},
			error: function(data){
				console.log(data);
			}
		})
	})
	
	
  
</script>
