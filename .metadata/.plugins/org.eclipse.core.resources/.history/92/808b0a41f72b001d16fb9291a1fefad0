
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/include/header.jsp"%>

<section>
	<div class="container-md spad">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="product__page__title">
					<div class="row">
						<div class="col-md-8 col-md-8 col-md-6 title__align__center">
							<div class="section-title">
								<h4>내 대여 현황</h4>
							</div>
						</div>
					</div>
				</div>


				<form>
					<table class="table table-borderless table-dark mb-5">
						<tbody>
							<!-- 2행 -->
							<tr>
								<th scope="row">대여 상태</th>
								<td colspan="2">
									<div class="btn-group btn-group-toggle" data-toggle="buttons">
										<label class="btn btn-secondary active"> <input
											type="radio" id="all" name="rental" value="all" checked> 전체
										</label> <label class="btn btn-secondary"> <input type="radio"
											id="rent" name="rental" value="rent"> 대여중인 도서
										</label> <label class="btn btn-secondary"> <input type="radio"
											id="reserv" name="rental" value="reserv"> 예약중인 도서
										</label>
									</div>
								</td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</form>



				<!-- table -->
				<h6 id="info">* 대여 상태에 따른 대여 이력을 확인할 수 있습니다.</h6>
				<table id="blackTable"
					class="table-data-list table-striped table-dark text-center">
					
				</table>
			</div>
		</div>
	</div>
</section>
<%@include file="/WEB-INF/views/include/footer.jsp"%>

<script>
var initBookStr = null;
var initOption = "all";
$(function() {
	ajaxList(initOption);
});

function initData(){
	initBookStr = `<tr>
		<td>번호</td>
		<td>도서명</td>
		<td>ISBN</td>
		<td>대여여부</td>
		<td>도서 대여일</td>
		<td>반납 예정일</td>
		</tr>`;
}

$("#all").on("click", function() {
	ajaxList("all");
});

$("#rent").on("click", function() {
	ajaxList("rent");
});

$("#reserv").on("click", function() {
	ajaxList("reserv");
});

function ajaxList(ro) {
	let memberId = "${user.id}";
	console.log("ro : " + ro);
	$.ajax({
		type : 'get',
		url : "${path}/members/" + memberId + "/rentals/cond",
		data : {
			id : memberId,
			rentOption : ro
		},
		dataType : 'json',
		success : function(result) {
			printList(result.rentals, result.reserves);
		}
	});
}

function printList(rentals, reserves) {
	initData();
	$(rentals)
			.each(
					function() {
						let bookNum = this.num;
						let title = this.title;
						let code = this.code;
						let rental = this.rental;
						let rentalDate = new Date(this.date);
						let formatRentalDate = rentalDate.getFullYear() + "/"
								+ (rentalDate.getMonth() + 1) + "/"
								+ rentalDate.getDate();
						
						let returnDate = new Date(this.returnDate);
						let formatReturnDate = returnDate.getFullYear() + "/"
								+ (returnDate.getMonth() + 1) + "/"
								+ returnDate.getDate();
										
						initBookStr += `<tr>
							<td>\${bookNum}</td>
							<td>\${title}</td>
							<td>\${code}</td>
							<td>대여</td>
							<td>\${formatRentalDate}</td>
							<td>\${formatReturnDate}</td>`;
						initBookStr += `</tr>`;
					});
	
	$(reserves)
	.each(
			function() {
				let bookNum = this.num;
				let title = this.title;
				let code = this.code;
				let reserveDate = new Date(this.date);
				let formatReserveDate = reserveDate.getFullYear() + "/"
						+ (reserveDate.getMonth() + 1) + "/"
						+ reserveDate.getDate();
						
				initBookStr += `<tr>
					<td>\${bookNum}</td>
					<td>\${title}</td>
					<td>\${code}</td>
					<td>예약</td>
					<td>\${formatReserveDate}</td>
					<td> - </td>`;
					
				initBookStr += `</tr>`;
			});
	$("#blackTable").html(initBookStr);
}


</script>

