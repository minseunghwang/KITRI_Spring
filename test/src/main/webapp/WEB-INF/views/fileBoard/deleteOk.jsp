<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<html>
<head>
<meta charset="UTF-8">
<title>파일 게시판</title>
</head>
<body>
	<c:if test="${check>0 }">
		<script type="text/javascript">
			alert("삭제되었습니다.")
			location.href="${root}/fileBoard/list.do?pageNumber=${pageNumber}";
		</script>
	</c:if>
	<c:if test="${check==0 }">
		<script type="text/javascript">
			alert("삭제되지 않았습니다.")
			location.href="${root}/fileBoard/read.do?boardNumber=${boardNumber}&pageNumber=${pageNumber}";
		</script>
	</c:if>
</body>
</html>