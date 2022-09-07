<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../include/header.jsp"/>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">
						SEARCH MODIFY PAGE
					</h3>
				</div>
				<!-- sboard/register method="POST" -->
				<form action="modifyPage" method="POST">
					<div class="box-body">
						<div class="form-group">
							<label>TITLE</label>
							<input type="text" class="form-control" name="title" placeholder="ENTER TITLE" value="${board.title}"/>
						</div>
						<div class="form-group">
							<label>CONTENT</label>
							<textarea name="content" class="form-control" rows="3" placeholder="ENTER CONTENT">${board.content}</textarea>
						</div>
						<div class="form-group">
							<label>WRITER</label>
							<input type="text" class="form-control" name="writer" placeholder="ENTER WRITER" value="${board.writer}"/>
						</div>
					</div>
					
					<div class="box-footer">
						<input type="submit" value="SUBMIT" class="btn btn-warning"/>			
					</div>
					<input type="hidden" name="bno" value="${board.bno}"/>
					<input type="hidden" name="page" value="${cri.page}"/>
					<input type="hidden" name="perPageNum" value="${cri.perPageNum}"/>
					<input type="hidden" name="searchType" value="${cri.searchType}"/>
					<input type="hidden" name="keyword" value="${cri.keyword}"/>
				</form>
			</div>
		</div>
	</div>
</section>
<jsp:include page="../include/footer.jsp"/>