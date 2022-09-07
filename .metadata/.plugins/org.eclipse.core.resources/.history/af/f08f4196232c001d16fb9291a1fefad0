<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/include/header.jsp"%>

<section>
	<div class="container-md spad">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="section-title">
					<br />
					<h4><s:message code="btn.book.new"/></h4>
					<br />
				</div>

				<div class="row justify-content-center">
					<div class="col-md-11 col-sm-7 spad">
						<form id="book-insert" action="${path}/books" method="POST"
							enctype="multipart/form-data">
							<div class="product__page__title">
								<div class="row">
									
								</div>
							</div>

							<div class="input-group mb-3">
								<div class="custom-file">
									<input type="file" class="custom-file-input" name="img"
										id="img" accept="image/*"> <label
										class="custom-file-label" for="img"><s:message code="text.book.cover"/></label>
								</div>
							</div>

							<div class="staffInfoList">
								<div class="input-group mb-3 flex-nowrap">
									<div class="input-group-append">
										<label class="input-group-text" for="inputGroupSelect01"><s:message code="text.book.ㅎ둑ㄷ"/></label>
									</div>
									<select class="custom-select" id="genre" name="genre">
										<option selected value=""><s:message code="search.genre"/></option>
										<option value="100"><s:message code="genre.100"/></option>
										<option value="200"><s:message code="genre.200"/></option>
										<option value="300"><s:message code="genre.300"/></option>
										<option value="400"><s:message code="genre.400"/></option>
										<option value="500"><s:message code="genre.500"/></option>
										<option value="600"><s:message code="genre.600"/></option>
										<option value="700"><s:message code="genre.700"/></option>
										<option value="800"><s:message code="genre.800"/></option>
										<option value="900"><s:message code="genre.900"/></option>
									</select>
								</div>

								<br />
								<!-- << input 태그 블럭 하나 . 추가시 복붙 -->
								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text"><s:message code="text.book.title"/></span>
									</div>
									<input type="text" class="form-control" id="title" name="title"
										placeholder="책 제목 입력" aria-label="Username"
										aria-describedby="addon-wrapping">
								</div>
								<br />
								<!-- >> input 태그 블럭 하나 -->
								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text">ISBN</span>
									</div>
									<input type="text" class="form-control" id="code" name="code"
										placeholder="ISBN" aria-label="Username"
										aria-describedby="addon-wrapping">
								</div>
								<br />

								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text"><s:message code="text.author"/></span>
									</div>
									<input type="text" class="form-control" id="auth" name="auth"
										placeholder="작가 입력" aria-label="Username"
										aria-describedby="addon-wrapping">
								</div>
								<br />
								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text"><s:message code="text.pub"/></span>
									</div>
									<input type="text" id="pub" class="form-control" name="pub"
										placeholder="출판사 입력" aria-label="Username"
										aria-describedby="addon-wrapping">
								</div>
								<br />

								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text"><s:message code="text.pubdate"/></span>
									</div>
									<input type="date" id="pubDate" class="form-control"
										name="pubDate" placeholder="출판일 입력" aria-label="Username"
										aria-describedby="addon-wrapping">
								</div>
								<br />

								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text"><s:message code="text.book.pages"/></span>
									</div>
									<input type="text" id="page" class="form-control" name="page"
										placeholder="페이지 수 입력" aria-label="Username"
										aria-describedby="addon-wrapping">
								</div>
								<br />

								<div class="input-group flex-nowrap">
									<div class="input-group-prepend">
										<span class="input-group-text"><s:message code="text.intro"/></span>
									</div>
									<textarea rows="4" cols="50" id="intro" name="intro"
										placeholder="책 소개 입력" aria-label="Username"
										aria-describedby="addon-wrapping"></textarea>
								</div>
								<br />
							</div>
							
							<div class="col-md-4 anime__details__btn float-right">
										<button type="submit" class="follow-btn float-right"><s:message code="btn.ok"/></button>
									</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<%@include file="/WEB-INF/views/include/footer.jsp"%>