<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Upload Result</h2>
	<c:if test="${!empty savedName}">
		<h3>savedName : ${path}/upload/${savedName}</h3>
		<a href="${path}/upload/${savedName}">${savedName}</a>
	</c:if>
	<h3>${auth}</h3>
	<h3>${content}</h3>
	<h3>saves</h3>
	<c:if test="${!empty saves}">
		<c:forEach var="f" items="${saves}">
			<h4>saves : ${f}</h4>
			<h3>saves : <a href="${path}/upload/${f}">${f}</a></h3>
		</c:forEach>
	</c:if>
</body>
</html>