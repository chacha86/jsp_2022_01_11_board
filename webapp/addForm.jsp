<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>게시물 등록</h1>
    <%@ include file="header.jspf" %>
    <form action="http://localhost:9000/article/add" method="POST">
        <div>
            제목 : <input type="text" name="title"><br />
            내용 : <input type="text" name="body"><br />
            작성자 : <input type="text" name="nickname"><br />
            <input type="submit" value="게시물 등록" />
        </div>
    </form>
</body>
</html>