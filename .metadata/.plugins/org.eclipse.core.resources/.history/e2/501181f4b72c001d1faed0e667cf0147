<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/include/header.jsp"%>

<!-- Slider Section Begin -->
<%@include file="/WEB-INF/views/include/section-slider.jsp"%>
<!-- Slider Section End -->
<style>
	.modal{
		color: graytext;
	}
</style>

<!-- 검색 -->
<div class="advance-search">
	<div class="container-md">
		<div class="row mt-5 justify-content-center">
			<form action="${path}/books">
				<div class="form-row">
					<div class="input-group justify-content-center">
						<div class="input-group-prepend">
						<input name="searchOption" value="title" hidden>
							<input type="text" name="searchText" class="form-control"
								placeholder="<s:message code='text.search.book.what'/>">
						</div>
						<button type="submit" class="btn btn-outline-danger mr-3"><s:message code="btn.search"/></button>
					</div>
				</div>
			</form>
			<button type="button" class="btn btn-outline-danger"
				data-toggle="modal" data-target="#exampleModalCenter" id="modal_btn"><s:message code="btn.search.detail"/></button>
		</div>
	</div>
</div>



<!-- Product Section Begin -->
<section class="product spad">
	<div class="container-md">
		<div class="row">
			<div class="col-md-10">
				<div class="recent__product">
					<div class="row">
						<div class="col-md-8">
							<div class="section-title">
								<h4><s:message code="btn.search"/><s:message code="text.book.new.list"/></h4>
							</div>
						</div>
					</div>
					<div class="row justify-content-around">
					<c:forEach var="recentBook" items="${recentBooks}">
						<div class="col-md-3">
							<div class="product__item">
								<div class="product__item__pic set-bg"
									data-setbg="${path}/resources/img/book/origin/${recentBook.code}.jpg">
									<div class="ep">${recentBook.pub}</div>
									<div class="comment">
										<!-- <i class="fa fa-comments"></i> 11 -->
									</div>
									<div class="view">
										<!-- <i class="fa fa-eye"></i> 9141 -->${recentBook.auth}
									</div>
								</div>
								<div class="product__item__text">
									<ul>
										<li>Active</li>
										<li>Movie</li>
									</ul>
									<h5>
										<a href="${path}/books/${recentBook.code}">${recentBook.title}</a>
									</h5>
								</div>
							</div>
						</div>
					</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Product Section End -->



<%@include file="/WEB-INF/views/include/footer.jsp"%>
<script>
	if("${message}" != ""){
		alert("${message}");
	}
</script>











<div class="modal fade" id="exampleModalCenter" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalCenterTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title text-black" id="exampleModalCenterTitle"><s:message code="btn.search.detail"/></h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="false">&times;</span>
				</button>
			</div>
			<div class="modal-body">

				<form action="${path}/books">
					<input name="searchOption" value="title" hidden>
					<div class="form-group">
						<label for="title"><s:message code="text.board.title"/></label> 
						<input type="text" class="form-control" name="searchText" id="searchText">
					</div>
					<div class="form-group">
						<label for="auth"><s:message code="text.author"/></label> 
						<input type="text" class="form-control" name="auth" id="auth">
					</div>
					<div class="form-group">
						<label for="pub"><s:message code="text.pub"/></label> 
						<input type="text" class="form-control" name="pub" id="pub">
					</div>
					<div class="form-group">
						<label for="pubDate"><s:message code="text.pubdate"/></label>
						<div class="w-100"></div>
						<input type="date" class="form control mb-2" name="pubDate" id="pubDate">
					</div>


				<button type="submit" class="btn btn-primary"><s:message code="btn.search"/></button>
				<button id="cancel-button" class="btn btn-secondary"
					data-dismiss="modal"><s:message code="btn.cancel"/></button>
				</form>
			</div>
			<div class="modal-footer">
			</div>
		</div>
	</div>
</div>












