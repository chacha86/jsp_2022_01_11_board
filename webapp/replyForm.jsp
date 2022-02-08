<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>댓글 수정</h1>

<form action="/article/doUpdateReply" method="post">
    <input type="hidden" name="idx" value="${ reply.idx }" />
    <input type="hidden" name="parentIdx" value="${ reply.parentIdx }" />
    <input type="hidden" name="nickname" value="${ loginedUserName }" />
    ${ loginedUserName }<br />
    <input type="text" name="body" value="${ reply.body }" />
    <input type="submit" value="등록" />
</form>


</body>
</html>