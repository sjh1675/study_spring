
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
								<h4>대여 현황</h4>
							</div>
						</div>
					</div>
				</div>


				<form id="form">
					<table class="table table-borderless table-dark mb-5">
						<tbody>
							<!-- 1행 -->
							<tr>
								<th scope="row">대여 상태</th>
								<td colspan="2">
									<div class="btn-group btn-group-toggle" data-toggle="buttons">
										<label class="btn btn-secondary active"> <input
											type="radio" name="rental" value="all" checked> 전체
										</label> <label class="btn btn-secondary"> <input type="radio"
											name="rental" value="rent"> 대여중인 도서
										</label> <label class="btn btn-secondary"> <input type="radio"
											name="rental" value="reserv"> 예약중인 도서
										</label>
									</div>
								</td>
								<td></td>
							</tr>
							<!-- 2행 -->
							<tr>
								<th scope="row">도서 발행일 별</th>
								<td colspan="3">
									<div class="input-group text-secondary">
										<input type="date" id="pubDate" name="pubDate"
											style="height: 37px" />
										<div class="input-group-append">
											<select id="pubDateOption">
												<option value="before">-이전</option>
												<option value="after">-이후</option>
											</select>
										</div>
									</div>
								</td>
							</tr>
							<!-- 3행 -->
							<tr>
								<th scope="row">검색</th>
								<td>
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<select id="searchOption">
												<option value="">키워드 선택</option>
												<option value="title">도서명</option>
												<option value="auth">작가</option>
												<option value="pub">출판사</option>
											</select>
										</div>
										<input type="text" id="searchText" class="form-control"
											style="max-height: 36.5px">
									</div>
								</td>
								<td></td>
								<td><input class="btn btn-danger" id="submit" type="submit" value="검색" /></td>
							</tr>
						</tbody>
					</table>					
				</form>

				<!-- table -->
				<h6 id="info">* 책 표지나 테이블 클릭시 해당 도서를 상세보기 할수 있습니다</h6>
				<table id="blackTable"
					class="table-data-list table-striped table-dark text-center">
					
				</table>
				<div class="form-row">
					<ul class="pagination" id="pa">

					</ul>
				</div>
			</div>
		</div>
	</div>
</section>
<%@include file="/WEB-INF/views/include/footer.jsp"%>


<script>
var initBookStr = null;
var num = 1;
$(function() {
	ajaxList(1);	
});

function initData(){
	
	initBookStr = `<tr>
		<td>번호</td>
		<td>도서명</td>
		<td>ISBN</td>
		<td>대여여부</td>
		<td>대여자</td>
		<td>도서 대여일</td>
		<td>반납 예정일</td>
	</tr>`;
}

$("#submit").on("click", function(e) {
	e.preventDefault();
	num = 1;
	ajaxList(1);
});


function ajaxList(pageNum) {	
	
	let st = $("#searchText").val();		
	let so = $("#searchOption").val();		
	let pd = $("#pubDate").val();
	let pdo = $("#pubDateOption").val();
	let ro = $("input[name=rental]:checked").val();
	console.log("searchText : " + st);
	console.log("searchOption : " + so);
	console.log("pubDate : " + pd);
	console.log("pubDateOption : " + pdo);
	console.log("rentalOption : " + ro);
	$.ajax({
		type : 'get',
		url : "${path}/staff/rentals/cond",
		data : {
			searchText : st,
			searchOption : so,
			pubDate : pd,
			pubDateOption : pdo,
			rentOption : ro,
			page : pageNum
		},
		dataType : 'json',
		success : function(result) {
			console.log(result.list);
			printList(result.list);
			printPage(result.pm);
		}
	});
}

function printList(list) {
	initData();
	$(list)
			.each(
					function() {
						
						let title = this.title;
						let code = this.code;
						let rental = this.rental;
						let rentalId = this.rentalId;
						let reserveId = this.reserveId;
						let rentalDate = new Date(this.rentalDate);
						let formatRentalDate = rentalDate.getFullYear() + "/"
								+ (rentalDate.getMonth() + 1) + "/"
								+ rentalDate.getDate();
						
						let returnDate = new Date(this.returnDate);
						let formatReturnDate = returnDate.getFullYear() + "/"
								+ (returnDate.getMonth() + 1) + "/"
								+ returnDate.getDate();
						
						let reserveDate = new Date(this.reserveDate);
						let formatReserveDate = reserveDate.getFullYear() + "/"
								+ (reserveDate.getMonth() + 1) + "/"
								+ reserveDate.getDate();
								
						initBookStr += `<tr>
							<td>\${num}</td>
							<td>\${title}</td>
							<td>\${code}</td>
							<td>\${rental} </td>`;
						if (rental == 'y') {
							initBookStr += `<td>\${rentalId}</td>
								<td>\${formatRentalDate}</td>
								<td>\${formatReturnDate}</td>`;
						} else if (rental == 'r'){
							initBookStr += `<td>\${reserveId}</td>
								<td>\${formatReserveDate}</td>
								<td> - </td>`;
						} else {
							initBookStr += `<td> - </td>
								<td> - </td>
								<td> - </td>`
						}
						initBookStr += `</tr>`;
						num = num + 1;
					});
	$("#blackTable").html(initBookStr);
}

function printPage(pm) {
	var str = "";
	if (pm.prev) {
		str += "<li><a href='" + (pm.startPage - 1) + "'> << </a></li>";
	}

	for (var i = pm.startPage; i <= pm.endPage; i++) {
		if (pm.cri.page == i) {
			str += "<li><a href='" + i + "' class='active'>" + i
					+ "</a></li>";
		} else {
			str += "<li><a href='" + i + "'>" + i + "</a></li>";
		}
	}

	if (pm.next) {
		str += "<li><a href='" + (pm.endPage + 1) + "'> >> </a></li>";
	}

	$("#pa").html(str);
}

$("#pa").on("click", "li a", function(e) {
	e.preventDefault();
	var commentPage = $(this).attr("href");
	let p = commentPage;
	num = (p - 1) * 10 + 1;
	ajaxList(p);
});

</script>

