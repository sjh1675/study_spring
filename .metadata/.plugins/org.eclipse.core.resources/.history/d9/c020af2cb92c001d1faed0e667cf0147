<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/include/header.jsp"%>

<style>
section {
	font-size: 0.8rem;
	vertical-align: middle;
}

.card-body {
	height: 300px;
}

table {
	table-layout: fixed;
}

table th {
	text-align: center;
}

table td {
	overflow: hidden;
	white-space: nowrap;
	text-align: center;
	line-height: 1.8rem;
}

table td:nth-child(1) {
	text-overflow: ellipsis;
}

.seat tr td {
	background: #21223A; 
	border: 1px white solid !important;
}

.seat tr td:hover {
	cursor: pointer;
}

.seat tr td:nth-child(2), .seat tr td:nth-child(5) {
	background: none;
	border-top: 0 !important;
	border-bottom: 0 !important;
}

.seat tr td:nth-child(2):hover, .seat tr td:nth-child(5):hover{
	cursor: default;
}


.seat .able {
	background: #21223A;
}

.seat .disabled {
	background: #dc3545;
}
.seat .disabled:hover {
	cursor: default;
}

.seat .active {
	background: white;
	color: black;
}

.seat .active:hover{
 cursor: default;
}
</style>

<section>
	<div class="container-md spad">
		<div class="row justify-content-center">
			<div class="col-md-10">
				<h2 class="mb-5">
					<c:if test="${not empty user}">
						${user.name}님의 오프라인 화면
					</c:if>
				</h2>
				<div class="row row-cols-1 row-cols-md-2">
					<div class="col mb-4">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">도서 수령</h5>
								<form action="${path}/offline/${user.id}/receipt" method="post">
									<table class="table">
										<thead>
											<tr>
												<th colspan="3">도서명</th>
												<th colspan="2">예약일</th>
												<th>수령</th>
											</tr>
										</thead>
										<tbody>
											<!-- book code, book num, user id -->
											<c:choose>
												<c:when test="${!empty reserves}">
													<c:forEach var="reserve" items="${reserves}">
														<tr>
															<td colspan="3">${reserve.title}</td>
															<td colspan="2"><f:formatDate value="${reserve.date}"
																	pattern="yyyy-MM-dd" /></td>
															<td>

																<button class="btn btn-danger btn-sm" name="code"
																	value="${reserve.code}">수령</button>
															</td>
														</tr>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<tr>
														<td colspan="6">대여 예약된 책이 없습니다.</td>
													</tr>
												</c:otherwise>
											</c:choose>
										</tbody>
									</table>
								</form>
							</div>
						</div>
					</div>

					<div class="col mb-4">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">도서 반납</h5>
								<form action="${path}/offline/${user.id}/return" method="post">
									<table class="table">
										<thead>
											<tr>
												<th colspan="3">도서명</th>
												<th colspan="2">반납예정일</th>
												<th>반납</th>
											</tr>
										</thead>
										<tbody>
											<c:choose>
												<c:when test="${!empty rentals}">
													<c:forEach var="rental" items="${rentals}">
														<tr>
															<td colspan="3">${rental.title }</td>
															<td colspan="2"><f:formatDate value="${rental.date}"
																	pattern="yyyy-MM-dd" /></td>
															<td>
																<button class="btn btn-danger btn-sm" name="code"
																	value="${rental.code}">반납</button>
															</td>
														</tr>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<tr>
														<td colspan="6">반납할 책이 없습니다.</td>
													</tr>
												</c:otherwise>
											</c:choose>
										</tbody>
									</table>
								</form>
							</div>
						</div>
					</div>
					<div class="col mb-4">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">스터디룸</h5>
								<table class="table seat" id="room">
								</table>

							</div>
						</div>
					</div>
					<div class="col mb-4">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">체크인 &amp; 체크아웃</h5>
								<div class="d-flex flex-column" style="padding: 3rem">
									<button type="button" id="checkInBtn" data-seat="dataseat" class="btn btn-danger btn-lg mb-3">체크인</button>
									<button type="button" id="checkOutBtn" class="btn btn-danger btn-lg">체크아웃</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<%@include file="/WEB-INF/views/include/footer.jsp"%>

<script>

	$(function(){
		
		let checkUserCheckIn = checkReal();
		console.log("checkUserCheckIn : "+checkUserCheckIn);
		
		initRoom();
		initSeats();
		
		$('.seat .able').on('click', function(){
			if(checkUserCheckIn) return;
			console.log($(this).hasClass("active"));
			if($(this).hasClass("disabled") || $(this).hasClass("active")){
				$(this).removeClass("active");
				return;
			}
			let seat = this.innerText;
			console.log(seat);
			$(this).addClass('active');
			$('#checkInBtn').data('seat', seat);
		})
		
		$('#checkInBtn').on('click',function(){
			let seat = $(this).data('seat');
			
			$.ajax({
				type: 'post',
				url: "${path}/offline/${user.id}/checkIn",
				data: { seat : seat },
				success: function(data){
					alert("클라이언트 요청  "+ seat + " 서버 응답 "+ data);
					location.reload();
				},
				error: function(data){
					alert("요청실패");
				} 
			})
		})
		
		$('#checkOutBtn').on('click', function(){
			
			$.ajax({
				
				type: 'post',
				url: '${path}/offline/${user.id}/checkOut',
				success: function(data){
					alert('서버 응답 : '+ data);
					location.reload();
				},
				error: function(data){
					alert('요청 실패' + data);
				}
			})
		})
	})
	
	function initRoom(){
		
			let room = "";
			let row = "";
			let cell = "";
		
			for(let i = 0; i <= 3; i++){
				let k = i * 4;
				for(let j = 1+k; j <= 4+k; j++){
					if(j==(1+k) || j==(3+k)){
						cell =`<td class="able" value="\${j}">\${j}</td>`;
						cell +=`<td></td>`;
					} else{
						cell =`<td class="able" value="\${j}">\${j}</td>`;
					}
					row += cell;
				}
				
				room = `<tr>\${row}</tr>`;
				$('#room').append(room);
				row = "";
			}
		}
	
	function initSeats(){
		$.getJSON("${path}/offline/${user.id}/room",
				function(data){
			$(data).each(function(i,e){
				console.log(e);
				console.log($(`td[value='\${e}']`));
				$(`td[value='\${e}']`).switchClass('able', 'disabled');
			})
		})
	}
	
	function checkReal(){
		let isReal = "";
		$.ajax({
			async: false,
			type: "get",
			url: "${path}/offline/${user.id}/checkReal",
			dataType: 'json',
			success: function(data){
				console.log(data)
				isReal = data;
			}
		})
		console.log("isReal "+isReal);
		return isReal;
	}
	

</script>




