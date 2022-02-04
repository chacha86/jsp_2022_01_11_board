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
    <h1>게시물 상세</h1>
    <%@ include file="header.jspf" %>
    <div>
    	<div>
            번호 : ${ article.idx }
        </div>
        <div>
            제목 : ${ article.title }
        </div>        
        <div>
            내용 : ${ article.body }
        </div>
        <div>
            작성자 : ${ article.nickname }
        </div>
        <div>
            작성일 : ${ article.regDate }
        </div>
    </div>
    <hr>
    <div> 
        <form action="/article/showUpdateForm">
            <input type="hidden" name="idx" value="${ article.idx }">
            <input type="submit" value="수정" />
        </form>
        <form action="/article/delete" method="post">
            <input type="hidden" name="idx" value="${ article.idx }">
            <input type="submit" value="삭제" />
        </form> 
        
    </div>
    <hr>    
</body>
</html>