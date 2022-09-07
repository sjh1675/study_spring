<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/include/header.jsp"%>

<!-- BookDetails Section Begin -->
<section class="anime-details">
	<div class="container-md spad-sm">
		<div class="anime__details__content">
			<div class="row">
				<div class="col-md-4">
					<div class="anime__details__pic set-bg"
						data-setbg="${path}/resources/img/book/origin/${book.code}.jpg">
					</div>
				</div>
				<div class="col-md-8">
					<div class="anime__details__text">
						<div class="anime__details__title">
							<h3>${book.title}</h3>
							<span>${book.auth}</span>
						</div>
						<div class="anime__details__widget">
							<div class="row">
								<div class="col-md-6 col-md-6">
									<ul>
										<li><span>장르 : </span> ${book.genre}</li>
										<li><span>작가 : </span> ${book.auth}</li>
										<li><span>출판일 : </span> <f:formatDate value="${book.pubDate}" pattern="yyyy-MM-dd"/></li>
										<li><span>출판사 : </span> ${book.pub}</li>
									</ul>
								</div>
								<div class="col-md-6 col-md-6">
									<ul>
										<li><span>쪽수 :</span> ${book.page}쪽</li>
										<li><span>ISBN :</span> ${book.code}</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="anime__details__btn float-right">
							<a href="#" class="watch-btn">대여 예약</a>
						</div>
					</div>
				</div>

				<div class="col-md anime__details__content">
					<div class="anime__details__title">
						<h3>도서 소개</h3>
					</div>
					<p>${book.intro}</p>
				</div>
			</div>

		</div>
		<%@include file="/WEB-INF/views/book/fragment/book-detail-comment.jsp"%>
	</div>
</section>

<%@include file="/WEB-INF/views/include/footer.jsp"%>