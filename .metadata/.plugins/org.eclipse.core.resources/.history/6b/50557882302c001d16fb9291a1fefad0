<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/include/header.jsp"%>


<section>
	<div class="container-md spad">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="section-title">
					<br />
					<h4>도서 정보 수정</h4>
					<br />
				</div>

				<div class="row justify-content-center">
					<div class="col-md-11 col-md-7 spad">					
						<form id="book-edit" action="${path}/books/${isbn}" method="POST"
							enctype="multipart/form-data">
							<input type="hidden" name="_method" value="PUT" />							
							<div class="product__page__title">							
								<div class="row justify-content-center">									
									<img src="${path}/resources/img/book/origin/${isbn}.jpg"/>
								</div>
								<div class="row justify-content-center">									
									<h6>기존 이미지</h6>
								</div>
							</div>
							
							<div class="input-group mb-3">
								<div class="custom-file">
									<input type="file" class="custom-file-input" name="img"
										id="img" accept="image/*"> <label
										class="custom-file-label" for="img"
										value="${path}/resources/img/book/origin/${isbn}.jpg">${isbn}.jpg</label>
								</div>
							</div>

							<div class="staffInfoList">
								<div class="input-group mb-3 flex-nowrap">
									<div class="input-group-append">
										<label class="input-group-text" for="inputGroupSelect01">장르</label>
									</div>
									<select class="custom-select" id="genre" name="genre">
										<option id="g0" value="0">장르 선택</option>
										<option id="g100" value="100">철학(100)</option>
										<option id="g200" value="200">종교(200)</option>
										<option id="g300" value="300">사회(300)</option>
										<option id="g400" value="400">과학(400)</option>
										<option id="g500" value="500">공학(500)</option>
										<option id="g600" value="600">예술(600)</option>
										<option id="g700" value="700">외국어(700)</option>
										<option id="g800" value="800">소설(800)</option>
										<option id="g900" value="900">역사(900)</option>
									</select>
								</div>

								<br />
								<!-- << input 태그 블럭 하나 . 추가시 복붙 -->
								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text" id="updateBeookTitle">도서
											명</span>
									</div>
									<input type="text" class="form-control" id="title" name="title"
										placeholder="도서명 입력" aria-label="Username"
										aria-describedby="addon-wrapping" value="${book.title}">
								</div>
								<br />
								<!-- >> input 태그 블럭 하나 -->
								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text" id="updateBookPub">ISBN</span>
									</div>
									<input type="text" class="form-control" id="code" name="code"
										placeholder="ISBN 입력" aria-label="Username"
										aria-describedby="addon-wrapping" value="${book.code}">
								</div>
								<br />

								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text" id="updateBeookAuthor">작가
										</span>
									</div>
									<input type="text" class="form-control" id="auth" name="auth"
										placeholder="작가 입력" aria-label="Username"
										aria-describedby="addon-wrapping" value="${book.auth}">
								</div>
								<br />
								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text" id="updateBookPub">출판사</span>
									</div>
									<input type="text" id="pub" class="form-control" name="pub"
										placeholder="출판사 입력" aria-label="Username"
										aria-describedby="addon-wrapping" value="${book.pub}">
								</div>
								<br />

								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text" id="updateBookPub">출판일</span>
									</div>
									<input type="date" id="pubDate" class="form-control"
										name="pubDate" placeholder="출판일 입력" aria-label="Username"
										aria-describedby="addon-wrapping" value="<f:formatDate value="${book.pubDate}" pattern="yyyy-MM-dd"/>" />
								</div>
								<br />

								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text">페이지 수</span>
									</div>
									<input type="text" id="page" class="form-control" name="page"
										placeholder="페이지 수 입력" aria-label="Username"
										aria-describedby="addon-wrapping" value="${book.page}">
								</div>
								<br />

								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text">책 소개</span>
									</div>
									<textarea rows="4" cols="50" id="intro" name="intro"
										placeholder="책 소개 입력" aria-label="Username"
										aria-describedby="addon-wrapping">${book.intro}</textarea>
								</div>
								<br />

							</div>
							
									<div class="col-md-4 anime__details__btn">
										<button type="submit" class="follow-btn float-right">수정
											완료</button>
									</div>
									
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<%@include file="/WEB-INF/views/include/footer.jsp"%>

<script>
$(function(){
	var init = "li[data-value=0]";
	var genre = "li[data-value=" + "${book.genre}" + "]";
	console.log(genre);
	$(init).attr("class", "option");
	$(genre).attr("class", "option selected");
	$(".current").text($(".option.selected").text());
});
</script>
