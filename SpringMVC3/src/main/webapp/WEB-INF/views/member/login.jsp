<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script type="text/javascript" src="${root }/javaScript/member/rUtil.js"></script>
</head>
<body>
	<jsp:include page="../../../index.jsp"/><br><br>

	<div align="center">
		<form action="${root }/member/loginOk.do" method="post" onsubmit="return idCheckFunc(this)">
			<table>
				<tr>
					<td>회원 ID</td>
					<td>
						<input type="text" name="id">
					</td>
				</tr>
				<tr>
					<td>회원 PW</td>
					<td>
						<input type="password" name="pw">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="확인">
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>