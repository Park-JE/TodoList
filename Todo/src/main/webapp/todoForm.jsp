<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>할일 등록</title>
</head>
<body>
	<header>할일 등록</header>
	<form action="./TodoAddServlet" method="post">
		<div>
			어떤일인가요?<br>
			<input type="text" name="title" required>
		</div>
		<div>
			누가 할일인가요?<br>
			<input type="text" name="name" required>
		</div>
		<div>
			우선순위를 선택하세요<br>
			<input type="radio" name="sequence" value="1" required>1순위
			<input type="radio" name="sequence" value="2" required>2순위
			<input type="radio" name="sequence" value="3" required>3순위
		</div>
		<input type="button" value="< 이전" onclick="location.href='./MainServlet'">
		<input type="submit" value="제출">
		<input type="reset" value="내용지우기">
	</form>
</body>
</html>