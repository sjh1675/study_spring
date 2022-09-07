<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/readPage.jsp</title>
<style>
	.uploadList{
		width:100%;
	}
	
	.uploadList li{
		text-align:center;
		display:inline-block;
		padding:20px;
		list-style:none;
	}
</style>
</head>
<body>
	<h2>
		<a href="${path}/board/listReply">리스트 목록</a>
	</h2>
	<h3>READ PAGE</h3>
	<!-- 게시글 정보 name : board -->
	<table>
		<tr>
			<td>제목</td>
			<td>${board.title}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${board.writer}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				${board.content}
			</td>
		</tr>
	</table>
	<hr/>
	<!-- 첨부파일 -->
	<div>
		<ul class="uploadList">
			<c:if test="${!empty board.files}">
				<c:forEach var="file" items="${board.files}">
					<li data-src='${file}'>
						<c:choose>
							<c:when test="${fn:contains(file,'s_')}">
								<!-- 이미지 파일 -->
								<img src="${path}/displayFile?fileName=${file}"/>
								<div>
									<a href="${path}/displayFile?fileName=${fn:replace(file,'s_','')}">
									<!-- /2022/08/10/s_c4d14b86258445faaa9b471d61ae351f_java11vs17.PNG -->
									<!-- c4d14b86258445faaa9b471d61ae351f_java11vs17.PNG -->
									<!-- java11vs17.PNG -->
										${fn:substringAfter(fn:substringAfter(file,"s_"),'_')}
									</a>
								</div>
							</c:when>
							<c:otherwise>
								<!-- 일반 파일 -->
								<img src="${path}/resources/img/file.png"/>
								<div>
									<a href="${path}/displayFile?fileName=${file}">
										${fn:substringAfter(file,'_')}
									</a>
								</div>
							</c:otherwise>
						</c:choose>
					</li>
				</c:forEach>
			</c:if>
		</ul>
	</div>
	
	<!-- 수정 삭제 답글 버튼 -->
	<div>
		<c:if test="${!empty userInfo}">
			<c:if test="${userInfo.uno eq board.uno}">
				<input type="button" id="modifyBtn" value="MODIFY"/>
				<input type="button" id="removeBtn" value="REMOVE"/>
			</c:if>
			<input type="button" id="replyBtn" value="REPLY"/>
		</c:if>
	</div>
	<form id="readForm">
		<input type="hidden" name="csrf_token" value="${csrf_token}"/>
		<input type="hidden" name="bno" value="${board.bno}"/>
	</form>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	var formObj = $("#readForm");
	$("#replyBtn").click(function(){
		formObj.attr("action","replyRegister").submit();
	});
	
	$("#removeBtn").click(function(){
		
		var isDelete = confirm("게시글을 삭제하시겠습니까?");
		
		if (isDelete) {
			var arr = [];
			$(".uploadList li").each(function(){
				var fileName = $(this).attr("data-src");
				arr.push(fileName);
			});
			
			console.log(arr);
			
			if (arr.length > 0) {
				// $.post(url, parameters, callback 함수)
				$.post("${path}/deleteAllFiles", {files : arr}, function(){
					alert(result);					
				});
			}
			
			formObj.attr("action", "remove");
			formObj.attr("method", "post");
			formObj.submit();
			
		} else {
			alert('삭제 요청이 취소되었습니다.');			
		}
	});
	
	// 수정 페이지 이동
	$("#modifyBtn").click(function(){
		formObj.attr("action", "modifyPage").submit();
	});
	
</script>
</body>
</html>