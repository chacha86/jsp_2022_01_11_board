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
    
    <c:if test="${ popupYn == null}">
        <h2>팝업창이 뜹니다.</h2>
    </c:if>    
    <%@ include file="header.jspf" %>
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
    <div>
        <c:forEach var="no" begin="${ pagination.currPageBlockStartNo }" end="${ pagination.currPageBlockEndNo }">
            <c:choose>
                <c:when test="${ no == pagination.currPageNo }">
                    <a href="/article/list?currNo=${ no }">[${ no }]</a>
                </c:when>
                <c:otherwise>
                    <a href="/article/list?currNo=${ no }">${ no }</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</body>
</html>