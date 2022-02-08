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
    <h3>댓글</h3>
    <c:forEach items="${ replies }" var="reply">
        <div>
            ${ reply.nickname } <br />
            ${ reply.body } <br />
            ${ reply.regDate } <br />
            <div>
                <form action="/article/showReplyForm">
                    <input type="hidden" name="idx" value="${ reply.idx }" />
                    <input type="submit" value="수정"/>
                </form>
                <form>
                    <input type="submit" value="삭제"/>
                </form>
            </div>
        </div>
        <hr>
    </c:forEach>
    <hr>
    <div>
        <form action="/article/addReply" method="post">
            ${ loginedUserName }<br />
            <input type="text" name="body" placeholder="댓글을 남겨보세요" />
            <input type="hidden" name="articleIdx" value="${ article.idx }" />
            <input type="hidden" name="nickname" value="${ loginedUserName }" />
            <input type="submit" value="등록" />
        </form>
    </div>
    <hr>    
</body>
</html>