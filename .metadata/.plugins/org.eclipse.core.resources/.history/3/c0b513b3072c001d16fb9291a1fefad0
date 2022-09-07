<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>

<section>
	<div class="container-md spad">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="product__page__title">
					<div class="row justify-content-between">
						<div class="col-auto">
							<div class="section-title">
								<h4>도서 현황</h4>
							</div>
						</div>
						<div class="col-auto">
							<button class="btn btn-danger"
								onclick="location.href='${path}/staff/books/new'">&#10009;
								도서 등록</button>
						</div>
					</div>
				</div>

				<form id="form">
					<table class="table table-borderless table-dark mb-5">
						<tbody>
							<!-- 1행 -->
							<tr>
								<th scope="row">장르지정</th>
								<td><select id="genre" name="genre">
										<option selected value="">장르 선택</option>
										<option value="100">철학(100)</option>
										<option value="200">종교(200)</option>
										<option value="300">사회(300)</option>
										<option value="400">과학(400)</option>
										<option value="500">공학(500)</option>
										<option value="600">예술(600)</option>
										<option value="700">외국어(700)</option>
										<option value="800">소설(800)</option>
										<option value="900">역사(900)</option>
								</select></td>
								<td></td>
							</tr>
							<!-- 2행 -->
							<tr>
								<th scope="row">도서 발행일 별</th>
								<td colspan="2">
									<div class="input-group text-secondary">
										<input type="date" id="pubDate" name="pubDate"
											style="height: 37px" />
										<div class="input-group-append">
											<select id="pubDateOption" name="pubDateOption">
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
											<select id="searchOption" name="searchOption">
												<option value="">키워드 선택</option>
												<option value="title">도서명</option>
												<option value="auth">작가</option>
												<option value="pub">출판사</option>
											</select>
										</div>
										<input type="text" id="searchText" name="searchText" class="form-control"
											style="max-height: 36.5px">
									</div>
								</td>
								<td></td>
								<td><input class="btn btn-danger" type="submit" id="submit"
									value="검색" /></td>
							</tr>
						</tbody>
					</table>
				</form>

				<!-- table -->
				<h6 id="info">* 책 표지나 테이블 클릭시 해당 도서의 수정페이지로 이동합니다</h6>
				<table id="blackTable"
					class="table-data-list table-striped table-dark text-center">
					<!-- 1블럭 -->
					<!-- 1 -->
					<thead>
						<tr onclick="location.href='${path}/staff/books/num/edit'">
							<td>번호</td>
							<td>책 표지</td>
							<td style="min-width: 130px">도서명</td>
							<td>ISBN</td>
							<td>작가</td>
							<td>출판사</td>
						</tr>
					</thead>
					<tbody id="tbody">

					</tbody>
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

<!-- 도서재고 수정 -->
<div class="modal fade" id="bookCount" data-backdrop="static"
	tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-dialog-scrollable modal-dialog-centered"
		role="document">
		<div class="modal-content">
			<form id="book-amount" method="post">
				<input type="hidden" name="id" value="${sessionScope.user.id}" />
				<div class="modal-header">
					<h5 class="modal-title">재고 추가</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="false">&times;</span>
					</button>
				</div>

				<div class="modal-body">
					<!-- 입력태그 하나 -->
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<div class="input-group-text">현재 도서 권수</div>
						</div>
						<input type="text" class="form-control" id="nowCount" name="num"
							readonly="readonly">
					</div>
					<br />
					<!-- 입력태그 하나 -->
					<!-- 입력태그 하나 -->
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<div class="input-group-text">추가할 재고 수</div>
						</div>
						<input type="number" id="" name="amount" />
					</div>
					<br />
					<!-- 입력태그 하나 -->
				</div>

				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">추가</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">취소</button>
				</div>
			</form>

		</div>
	</div>
</div>
<!-- 도서재고 수정 -->

<script>
	
	$(function() {
		ajaxList(1);
	});

	$('#submit').on("click", function(e) {
		e.preventDefault();		
		ajaxList(1);
	});

	
	function ajaxList(pageNum) {
		let lst = $("#searchText").val();		
		let lso = $("#searchOption").val();		
		let lpd = $("#pubDate").val();
		let lpdo = $("#pubDateOption").val();
		let lgr = $("#genre").val();
		
		
		$.ajax({
			type : 'get',
			url : "${path}/staff/books/cond",
			data : {
				searchText : lst,
				searchOption : lso,
				pubDate : lpd,
				pubDateOption : lpdo,
				genre : lgr,
				page : pageNum
			},
			dataType : 'json',
			success : function(result) {
				console.log(result.list);
				printList(result.list, result.nowCount, result.allCount);
				printPage(result.pm);
			}
		});
	}

	function printList(list, now, all) {
		var i = 0;
		var str = "";
		$(list)
				.each(
						function() {
							let img = this.img;
							let title = this.title;
							let code = this.code;
							let auth = this.auth;
							let pub = this.pub;
							let genre = this.genre;
							let date = new Date(this.pubDate);
							let formatDate = date.getFullYear() + "년 "
									+ (date.getMonth() + 1) + "월 "
									+ date.getDate() + "일";

							let nowCount = now[i];
							let allCount = all[i];
							let count = nowCount + " / " + allCount;

							str += "<tr onclick=\"location.href='${path}/staff/books/"+code+"/edit'\">";
							str += "<td rowspan='3'>" + (i + 1) + "</td>"
							str += "<td class='책 이미지' style='max-width: 135px' rowspan='3'><img src='${path}/resources/img/book/thumbnail/"+code+".jpg' />";
							str += "</td>";
							str += "<td class='tempTitle'>" + title + "</td>";
							str += "<td id='codetd'>" + code + "</td>";
							str += "<td>" + auth + "</td>";
							str += "<td>" + pub + "</td>";
							str += "</tr>";
							str += "<tr id='tr3'>";
							str += "<td style='min-width: 130px'>출판일</td>";
							str += "<td style='min-width: 130px'>장르</td>";
							str += "<td style='min-width: 130px'>재고 현황</td>";
							str += "<td style='min-width: 130px'>도서 폐기</td>";
							str += "</tr>";
							str += "<tr id='tr4'>";
							str += "<td>" + formatDate + "</td>";
							str += "<td>" + genre + "</td>";
							str += "<td><input type='button' class='btn btn-danger' onclick='throwData(this)' data-code='"
									+ code
									+ "' data-now='"
									+ nowCount
									+ "'data-toggle='modal' data-target='#bookCount' value='추가' id='bookAdd' name='num' /></td>";
							str += "<td><input type='button' class='btn btn-danger' data-code='"+code+"' data-toggle='modal' data-target='#exampleModalCenter' value='폐기' id='bookAdd' name='code' /></td>";
							str += "</tr>";

							i = i + 1;
						});
		$("#tbody").html(str);
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
		ajaxList(p);
	});

	function throwData(ev) {
		console.log(ev.dataset); // title, now
		var now = ev.dataset.now;
		var code = ev.dataset.code;
		$("#nowCount").val(now);
		$("#book-amount").attr("action", "${path}/books/" + code + "/single");
	}

	/* 	
	function temp (){
			$.ajax({
				url: '${path}/staff/books/plz',
				type: 'put',
				data: code,
				contentType: "application/json",
				dataType: 'json',
				success: function(result){
					$('.modal-title').text(result.title);
					$('.bookName').val(result.content);
				}
			})
		} 
	
	 */
</script>


