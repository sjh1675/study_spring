<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/include/header.jsp"%>

<section>
	<div class="container-md spad">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="section-title">
					<br />
					<h4>도서 입고</h4>
					<br />
				</div>
				
				<div class="row justify-content-center">
					<div class="col-md-11 col-sm-7 spad">
						<form id="book-insert" action="${path}/books" method="POST" enctype="multipart/form-data">
							<div class="product__page__title">
								<div class="row">
									<div class="col-md-8 title__align__center">
									</div>
									<div class="col-md-4 anime__details__btn">
										<button type="submit" class="follow-btn float-right">작성 완료</button>
									</div>
								</div>
							</div>
	
					<div class="input-group mb-3">
						<div class="custom-file">
							<input type="file" class="custom-file-input" name="img"
								id="img" accept="image/*"> <label
								class="custom-file-label" for="img">책 표지 삽입</label>
						</div>
					</div>
	
							<div class="staffInfoList">
								<div class="input-group mb-3 flex-nowrap">
									<div class="input-group-append">
										<label class="input-group-text" for="inputGroupSelect01">장르</label>
									</div>
									<select class="custom-select" id="genre" name="genre">
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
									</select>
								</div>
	
								<br />
								<!-- << input 태그 블럭 하나 . 추가시 복붙 -->
								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text" >도서 명</span>
									</div>
									<input type="text" class="form-control" id="title" name="title"
										placeholder="도서명 입력" aria-label="Username"
										aria-describedby="addon-wrapping">
								</div>
								<br />
								<!-- >> input 태그 블럭 하나 -->
								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text" >ISBN</span>
									</div>
									<input type="text" class="form-control" id="code" name="code"
										placeholder="ISBN 입력" aria-label="Username"
										aria-describedby="addon-wrapping">
								</div>
								<br />
								
								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text" >작가
											</span>
									</div>
									<input type="text" class="form-control" id="auth" name="auth"
										placeholder="작가 입력" aria-label="Username"
										aria-describedby="addon-wrapping">
								</div>
								<br />
								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text">출판사</span>
									</div>
									<input type="text"  id="pub" class="form-control" name="pub"
										placeholder="출판사 입력" aria-label="Username"
										aria-describedby="addon-wrapping">
								</div>
								<br />
								
								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text">출판일</span>
									</div>
									<input type="date" id="pubDate" class="form-control" name="pubDate"
										placeholder="출판일 입력" aria-label="Username"
										aria-describedby="addon-wrapping">
								</div>
								<br />
								
								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text">페이지 수</span>
									</div>
									<input type="text"  id="page" class="form-control" name="page"
										placeholder="페이지 수 입력" aria-label="Username"
										aria-describedby="addon-wrapping">
								</div>
								<br />
								
								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text">책 소개</span>
									</div>
									<textarea rows="4" cols="50" id="intro" name="intro" placeholder="책 소개 입력" aria-label="Username"
										aria-describedby="addon-wrapping"></textarea>									
								</div>
								<br />								
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<%@include file="/WEB-INF/views/include/footer.jsp"%>