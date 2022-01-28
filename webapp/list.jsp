<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>게시물 목록</h1>
    <hr>
    <c:choose>
        <c:when test="${ loginedUserName == null }">
            <a href="/member/showLoginForm.do">로그인</a>
        </c:when>
        <c:otherwise>
            ${ loginedUserName }님 안녕하세요!
            <a href="/member/logout.do">로그아웃</a>
        </c:otherwise>
    </c:choose>
    <hr>
    <c:forEach items="${articleList}" var="article">
        <div>
            번호 : ${ article.idx } <br/>
            <a href="/article/detail?idx=${ article.idx }">제목 : ${ article.title }</a><br/> 
            작성자 : ${ article.nickname }<br/>
            작성일 : ${ article.regDate }<br/>
        </div>
        <hr>
    </c:forEach>
    <hr>
    <a href="http://localhost:9000/article/showAddForm">글쓰기</a>
    <hr>
</body>
</html>