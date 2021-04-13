<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<header>
		<h1>나의 해야할 일들</h1>
	</header>
	<div onclick="location.href='./TodoFormServlet'">새로운 TODO 등록</div>
	
	<section>
		<c:forEach var="type" items="${types}">
			<section id="${type}">
				<c:forEach var="list" items="${list}">
					<c:if test="${list.type eq type}">
						<h1>${list.title}</h1>
						<div>
							등록날짜:${list.regDate}, ${list.name}, 우선순위 ${list.sequence}
						</div>
						<c:if test="${list.type ne 'DONE'}">
							<button></button>
						</c:if>
					</c:if>
				</c:forEach>
			</section>
		</c:forEach>
	</section>

</body>
</html>